package com.web.optiviaje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("")
	public String Menu_admin() {
		return  "admin/Menu_admin";
	}
	
	
	//CONTROLADOR SERVICIOS DE TRANSPORTE
	@GetMapping("/A_list_transport_service")
	public String A_list_transport_service() {
		return  "admin/A_list_transport_service";
	}
	@GetMapping("/B_new_transport_service")
	public String B_new_transport_service() {
		return  "admin/B_new_transport_service";
	}
	@GetMapping("/C_update_transport_service")
	public String C_update_transport_service() {
		return  "admin/C_update_transport_service";
	}
	
	//CONTROLADOR EMPRESAS DE TRANSPORTE
	@GetMapping("/D_list_transport_company")
	public String D_list_transport_company() {
		return  "admin/D_list_transport_company";
	}
	@GetMapping("/E_new_transport_company")
	public String E_new_transport_company() {
		return  "admin/E_new_transport_company";
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
