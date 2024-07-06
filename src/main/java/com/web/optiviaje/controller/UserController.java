package com.web.optiviaje.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.web.optiviaje.model.NLinea;
import com.web.optiviaje.model.Ruta;
import com.web.optiviaje.model.UnidadTransporte;
import com.web.optiviaje.model.Usuario;
import com.web.optiviaje.model.Viaje;
import com.web.optiviaje.service.AdminService;

import jakarta.servlet.http.HttpSession;

 


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
		return "user/login";
	}
	
	
	 @PostMapping("/loginUser")
 public String login( @RequestParam String correoElectronico, @RequestParam String contrasena, 	Model model, HttpSession session ) {
		 Usuario usuario = adminService.find(correoElectronico);
		 if (usuario != null && usuario.getContrasena().equals(contrasena)) {
		        session.setAttribute("usuario", usuario); // Almacenar el usuario en la sesión
		        model.addAttribute("usuario", usuario);
		        return "user/Menu2";
		    } else {
		        model.addAttribute("loginError", "Invalid username or password.");
		        return "user/login";
		    }
	 
	 }
	
	 @GetMapping("/Registro")
	 public String Registro() {
	 return  "user/Registro";
	 }
	 
	 @PostMapping("/CRegistro")
	 public String CRegistro(Usuario usuario) {
		 	
		 adminService.save(usuario);
		 
			return "user/login";
		}
	 
	@GetMapping("Menu")
	 public String Menu() {
			 return "user/Menu";
		 }
	@GetMapping("Menu2")
	public String Menu2(HttpSession session, Model model) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario"); // Obtener el usuario de la sesión
	    if (usuario == null) {
	        // Si no hay un usuario en la sesión, redirigir al usuario a la página de inicio de sesión
	        return "redirect:/user/login";
	    } else {
	        // Si hay un usuario en la sesión, agregar el usuario al modelo y mostrar la página Menu2
	        model.addAttribute("usuario", usuario);
	        
	        return "user/Menu2";
	    }
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
///OBTENER ID DE RUTA
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

	            NLinea lineaExistente = lineaExistenteOpt.get();
	            List<UnidadTransporte> unidades = adminService.findUnidadesByLinea(lineaExistente);

	            if (unidades.isEmpty()) {
	                // Si la línea de ruta existente no tiene unidades de transporte, crear y guardar nuevas unidades
	                for (int i = 0; i < 2; i++) {
	                    UnidadTransporte unidadTransporte = new UnidadTransporte();
	                    unidadTransporte.setNlinea(lineaExistente);
	                    unidadTransporte.setNombreConductor("Default");
	                    unidadTransporte.setCapacidad(50);
	                    unidadTransporte.setPlaca(generarPlacaAleatoria());
	                    adminService.save(unidadTransporte);
	                }
	                return "Se han creado nuevas unidades de transporte para la línea " + idRuta;
	            }

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
	// Guardar el viaje
	@PostMapping("/saveRV")
	public String saveRV(@ModelAttribute Usuario usuario, @ModelAttribute Ruta ruta, @ModelAttribute Viaje viaje,
	        @RequestParam("idUnidadTransporte") Integer idUnidadTransporte, @RequestParam("idUsuario") Integer idUsuario) {
	    // Buscar la unidad de transporte por su ID
	    UnidadTransporte unidadTransporte = adminService.findById(idUnidadTransporte);
	    Usuario usuarioGuardado = adminService.getu(idUsuario).get();

	    // Asignar la unidad de transporte a la ruta
	    ruta.setUnidadTransporte(unidadTransporte);

	    // Guardar la ruta para obtener su ID
	    Ruta rutaGuardada = adminService.save(ruta);
	    // Asignar el usuario al viaje
	    viaje.setUsuario(usuarioGuardado);
	    // Asignar la ruta al viaje
	    viaje.setRuta(rutaGuardada);

	    // Guardar el viaje
	    adminService.save(viaje);

	    // Redirigir al usuario a la página Menu2
	    return "redirect:/user/Menu2";
	}


	@GetMapping("histo")
	public String histo(HttpSession session, Model model) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario"); // Obtener el usuario de la sesión

	    if (usuario == null) {
	        // Si no hay un usuario en la sesión, redirigir al usuario a la página de inicio de sesión
	        return "redirect:/user/login";
	    } else {
	        // Si hay un usuario en la sesión, agregar el usuario al modelo
	        model.addAttribute("usuario", usuario);

	        // Recuperar los viajes del usuario y agregarlos al modelo
	        List<Viaje> viajes = adminService.getU(usuario.getId());
	        model.addAttribute("viajes", viajes);

	        return "user/histo";
	    }
	}
	
	// Perfil del usuario
	@GetMapping("/Perfil")
	public String Perfil(HttpSession session, Model model) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario");

	    if (usuario == null) {
	        return "redirect:/user/login";
	    } else {
	    
	            model.addAttribute("usuario", usuario);
	       
	        return "user/Perfil";
	    }
	}
 	 
	  // actualizar usuario
        @PostMapping("/update_user")
		public String update_user(HttpSession session, Model model) {
        	 Usuario usuario = (Usuario) session.getAttribute("usuario");

     	    if (usuario == null) {
     	        return "redirect:/user/login";
     	    } else {
     	    
     	            model.addAttribute("usuario", usuario);
     	       
     	        return "user/update_user";
     	    }
     	}
        
        ///ACCION DE UPDATE DE USER:
        @PostMapping("/updateu")
		public String updateu(Usuario usuario, HttpSession session) {
			adminService.update(usuario);
			session.setAttribute("usuario", usuario);
			return "user/Perfil";
		}
        
        
        ///CERRAR SESION
        @GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate(); // Invalidar la sesión
			return "redirect:/user/login"; // Redirigir al usuario a la página de inicio de sesión
		}
	
}
