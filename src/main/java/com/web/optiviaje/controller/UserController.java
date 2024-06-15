package com.web.optiviaje.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.web.optiviaje.model.NLinea;
import com.web.optiviaje.model.Ruta;
import com.web.optiviaje.model.UnidadTransporte;
import com.web.optiviaje.model.Viaje;
import com.web.optiviaje.service.AdminService;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdminService adminService;
    
	@GetMapping("")
	public String Landig() {
		return  "user/Landig";
	}
	
	 @GetMapping("/login")
 public String login() {
		return  "user/login";
	 }
	
	 @GetMapping("/Registro")
	 public String Registro() {
	 return  "user/Registro";
	 }
	 
	 
	@GetMapping("Menu")
	 public String Menu() {
			 return "user/Menu";
		 }
	@GetMapping("Menu2")
	 public String Menu2() {
			 return "user/Menu2";
		 }
	// Método para generar una placa aleatoria
	private String generarPlacaAleatoria() {
	    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String numeros = "0123456789";
	    Random random = new Random();
	    StringBuilder placa = new StringBuilder();

	    for (int i = 0; i < 3; i++) {
	        placa.append(caracteres.charAt(random.nextInt(caracteres.length())));
	    }
	    for (int i = 0; i < 4; i++) {
	        placa.append(numeros.charAt(random.nextInt(numeros.length())));
	    }
	    return placa.toString();
	}
	
	private final Object lock = new Object();

	@PostMapping("recibirIdRuta")
	@ResponseBody
	public String recibirIdRuta(@RequestBody String json) {
	    System.out.println("JSON recibido: " + json);
	    String idRuta;
	    try {
	        JSONObject jsonObject = new JSONObject(json);
	        if (jsonObject.has("idRuta")) {
	            idRuta = jsonObject.getString("idRuta");
	        } else {
	            return "El JSON no contiene la clave 'idRuta'";
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return "Error al analizar el JSON: " + e.getMessage();
	    }

	    System.out.println("ID de ruta recibida: " + idRuta);

	    synchronized (lock) {
	        Optional<NLinea> lineaExistenteOpt = adminService.findByCodigoLinea(idRuta);
	        if (lineaExistenteOpt.isPresent()) {
	            System.out.println("La línea con el código " + idRuta + " ya existe.");
	            return "La línea con el código " + idRuta + " ya existe.";
	        }
	        NLinea nuevaLinea = new NLinea();
	        nuevaLinea.setCodigoLinea(idRuta);
	        try {
	            adminService.save(nuevaLinea);
	            for (int i = 0; i < 2; i++) {
	                UnidadTransporte unidadTransporte = new UnidadTransporte();
	                unidadTransporte.setNlinea(nuevaLinea);
	                unidadTransporte.setNombreConductor("Default");
	                unidadTransporte.setCapacidad(50);
	                unidadTransporte.setPlaca(generarPlacaAleatoria());
	                adminService.save(unidadTransporte);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error al guardar la ruta: " + e.getMessage();
	        }
	        return "ID de ruta recibida con éxito: " + idRuta;
	    }
	}
///ESTE ES PARA OBTENER UNIDAD DE TRANSPOTE
	@PostMapping("/seleccionarUnidadTransporte")
	public ResponseEntity<UnidadTransporte> seleccionarUnidadTransporte(@RequestBody String json) {
	    // Analizar el JSON para obtener el id de la ruta
	    String idRuta;
	    try {
	        JSONObject jsonObject = new JSONObject(json);
	        idRuta = jsonObject.getString("idRuta");
	    } catch (JSONException e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body(null);
	    }

	    // Obtener la unidad de transporte
	    UnidadTransporte unidad = adminService.seleccionarUnidadTransporte(idRuta);

	    // Comprobar si se obtuvo una unidad de transporte válida
	    if (unidad != null) {
	        // Devolver la unidad de transporte
	        return ResponseEntity.ok().body(unidad);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@PostMapping("/saveRV")
	public ResponseEntity<Object> saveRV(@ModelAttribute Ruta ruta, @ModelAttribute Viaje viaje, @RequestParam("idUnidadTransporte") Integer idUnidadTransporte) {
	    // Buscar la unidad de transporte por su ID
	    UnidadTransporte unidadTransporte = adminService.findById(idUnidadTransporte);

	    // Asignar la unidad de transporte a la ruta
	    ruta.setUnidadTransporte(unidadTransporte);

	    // Guardar la ruta para obtener su ID
	    Ruta rutaGuardada = adminService.save(ruta);

	    // Asignar la ruta al viaje
	    viaje.setRuta(rutaGuardada);

	    // Guardar el viaje
	    adminService.save(viaje);

	    // Retornar un estado HTTP 200 OK sin contenido adicional
	    return ResponseEntity.ok().build();
	}


	@GetMapping("histo")
	 public String histo() {
			 return "user/histo";
		 }
}
