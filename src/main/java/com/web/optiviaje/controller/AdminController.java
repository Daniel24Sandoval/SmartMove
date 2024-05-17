package com.web.optiviaje.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import org.slf4j.Logger;

import com.web.optiviaje.model.EmpresaTransporte;
import com.web.optiviaje.model.ServicioTransporte;
import com.web.optiviaje.service.AdminService;

 

@Controller
@RequestMapping("/admin")
public class AdminController {
	//IMPRECION DE PRUEBAS POR CONSOLA:
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
 //VARIABLE
    @Autowired
    private AdminService adminService;
    
	@GetMapping("")
	public String Menu_admin() {
		return  "admin/Menu_admin";
	}
	
	
	//CONTROLADOR SERVICIOS DE TRANSPORTE
	@GetMapping("/A_list_transport_service")
	public String A_list_transport_service(Model model) {
		model.addAttribute("listats", adminService.findAllServicioTransporte());
		
		return  "admin/A_list_transport_service";
	}
	@GetMapping("/B_new_transport_service")
	public String B_new_transport_service() {
		return  "admin/B_new_transport_service";
	}
	//METODO DE GUARDADO DE SERVICION DE TRANSPORTE
	@PostMapping("/save")
	public String save(ServicioTransporte servicioTransporte) {
        LOGGER.info("Este es el Objeto: {}", servicioTransporte.getDescripcion());
        adminService.save(servicioTransporte);
		return "redirect:/user";
	}
	 
	@GetMapping("/C_update_transport_service/{id}")
	public String C_update_transport_service(@PathVariable Integer id, Model model) {
		ServicioTransporte servicioTransporte=new ServicioTransporte();
		Optional<ServicioTransporte> optionalst=adminService.get(id);
		servicioTransporte=optionalst.get();
		model.addAttribute("st",servicioTransporte);
	    return "admin/C_update_transport_service"; //  
	}
	@PostMapping("/update")
	public String update(ServicioTransporte servicioTransporte) {
        adminService.save(servicioTransporte);
		return "redirect:/A_list_transport_service";
	}
	 

	//CONTROLADOR EMPRESAS DE TRANSPORTE
	@GetMapping("/D_list_transport_company")
	public String D_list_transport_company() {
		return  "admin/D_list_transport_company";
	}
	//
	@GetMapping("/E_new_transport_company")
	public String E_new_transport_company(Model model) {
        Iterable<ServicioTransporte> tiposTransporte = adminService.findAllServicioTransporte();
        model.addAttribute("tiposTransporte", tiposTransporte);
 
		return  "admin/E_new_transport_company";
	}
	@PostMapping("/saveet")
	public String saveet(@RequestParam("servicioTransporte_id") Integer servicioTransporte_id, EmpresaTransporte empresaTransporte) {
	    // REGISTRA INFORMACIÓN SOBRE LOS DATOS RECIBIDOS
	    LOGGER.info("ID DE SERVICIO DE TRANSPORTE RECIBIDO: {}", servicioTransporte_id);
	    LOGGER.info("NOMBRE DE EMPRESA RECIBIDO: {}", empresaTransporte.getNombre());
	    LOGGER.info("DESCRIPCIÓN RECIBIDA: {}", empresaTransporte.getDescripcion());
	    LOGGER.info("RUC: {}", empresaTransporte.getRuc());
	    // GUARDA LOS DATOS EN EL SERVICIO
	    adminService.save(servicioTransporte_id, empresaTransporte);

	    // REDIRIGE A LA PÁGINA DE LISTA DE EMPRESAS DE TRANSPORTE
	    return "redirect:/admin/D_list_transport_company";
	}


	@GetMapping("/F_update_transport_company")
	public String F_update_transport_company() {
		return  "admin/F_update_transport_company";
	}	
		
	//CONTROLADOR RUTAS (IDRUTAS) DE EMPRESAS DE TRANSPORTE
	@GetMapping("/G_list_routes_transport")
	public String G_list_routes_transport() {
		return  "admin/G_list_routes_transport";
	}
	@GetMapping("/H_new_routes_transport")
	public String H_new_routes_transport() {
		return  "admin/H_new_routes_transport";
	}
	@GetMapping("/I_update_routes_transport")
	public String I_update_routes_transport() {
		return  "admin/I_update_routes_transport";
	}
	
	//CONTROLADOR PARADERO DE RUTAS
	@GetMapping("/J_list_whereabouts_route")
	public String J_list_whereabouts_route() {
		return  "admin/J_list_whereabouts_route";
	}
	@GetMapping("/K_new_whereabouts_route")
	public String K_new_whereabouts_route() {
		return  "admin/K_new_whereabouts_route";
	}
	@GetMapping("/L_update_whereabouts_route")
	public String L_update_whereabouts_route() {
		return  "admin/L_update_whereabouts_route";
	}
	
	//CONTROLADOR UNIDAD DE TRANSPORTE
	    @GetMapping("/M_list_routes_transport")
	    public String M_list_routes_transport() {
	 	    return  "admin/M_list_routes_transport";
	    }
		@GetMapping("/N_list_transport_unit")
		public String N_list_transport_unit() {
			return  "admin/N_list_transport_unit";
		}
		@GetMapping("/O_new_transport_unit")
		public String O_new_transport_unit() {
			return  "admin/O_new_transport_unit";
		}
		@GetMapping("/P_update_transport_unit")
		public String P_update_transport_unit() {
			return  "admin/P_update_transport_unit";
		}

}
