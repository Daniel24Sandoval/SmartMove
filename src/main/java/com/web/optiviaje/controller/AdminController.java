package com.web.optiviaje.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.web.optiviaje.model.EmpresaTransporte;
import com.web.optiviaje.model.NLinea;
import com.web.optiviaje.model.Paradero;
import com.web.optiviaje.model.ServicioTransporte;
import com.web.optiviaje.model.UnidadTransporte;
import com.web.optiviaje.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    // IMPRESIÓN DE PRUEBAS POR CONSOLA
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    // INYECCIÓN DE DEPENDENCIAS
    @Autowired
    private AdminService adminService;
    
    // MENÚ PRINCIPAL DEL ADMINISTRADOR
    @GetMapping("")
    public String Menu_admin() {
        return "admin/Menu_admin";
    }

    // CONTROLADOR SERVICIOS DE TRANSPORTE

    // LISTA DE SERVICIOS DE TRANSPORTE
    @GetMapping("/A_list_transport_service")
    public String A_list_transport_service(Model model) {
        model.addAttribute("listats", adminService.findAllServicioTransporte());
        return "admin/A_list_transport_service";
    }

    // FORMULARIO DE CREAR SERVICIOS DE TRANSPORTE
    @GetMapping("/B_new_transport_service")
    public String B_new_transport_service() {
        return "admin/B_new_transport_service";
    }

    // ACCIÓN DE CREAR SERVICIOS DE TRANSPORTE
    @PostMapping("/save")
    public String save(ServicioTransporte servicioTransporte) {
        LOGGER.info("Este es el Objeto: {}", servicioTransporte.getDescripcion());
        adminService.save(servicioTransporte);
        return "redirect:/admin/A_list_transport_service";
    }

    // ACCIÓN DE ELIMINAR SERVICIOS DE TRANSPORTE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        adminService.delete(id);
        return "redirect:/admin/A_list_transport_service";
    }

    // FORMULARIO DE ACTUALIZAR SERVICIOS DE TRANSPORTE
    @GetMapping("/C_update_transport_service/{id}")
    public String C_update_transport_service(@PathVariable Integer id, Model model) {
        Optional<ServicioTransporte> optionalst = adminService.get(id);
        if (optionalst.isPresent()) {
            model.addAttribute("st", optionalst.get());
        }
        return "admin/C_update_transport_service";
    }

    // ACCIÓN DE ACTUALIZAR SERVICIOS DE TRANSPORTE
    @PostMapping("/update")
    public String update(ServicioTransporte servicioTransporte) {
        adminService.save(servicioTransporte);
        return "redirect:/admin/A_list_transport_service";
    }

    
    
    
    // CONTROLADOR EMPRESAS DE TRANSPORTE

    // LISTA DE EMPRESAS DE TRANSPORTE
    @GetMapping("/D_list_transport_company")
    public String D_list_transport_company(Model model) {
        model.addAttribute("listatc", adminService.findAllEmpresaTransporte());
        return "admin/D_list_transport_company";
    }

    // FORMULARIO DE CREAR EMPRESAS DE TRANSPORTE
    @GetMapping("/E_new_transport_company")
    public String E_new_transport_company(Model model) {
        Iterable<ServicioTransporte> tiposTransporte = adminService.findAllServicioTransporte();
        model.addAttribute("tiposTransporte", tiposTransporte);
        return "admin/E_new_transport_company";
    }

    // ACCIÓN DE CREAR EMPRESAS DE TRANSPORTE
    @PostMapping("/saveet")
    public String saveet(EmpresaTransporte empresaTransporte) {
        adminService.save(empresaTransporte);
        return "redirect:/admin/D_list_transport_company";
    }

    // FORMULARIO DE ACTUALIZAR EMPRESAS DE TRANSPORTE
    @GetMapping("/F_update_transport_company/{id}")
    public String F_update_transport_company(@PathVariable Integer id, Model model) {
        Iterable<ServicioTransporte> tiposTransporte = adminService.findAllServicioTransporte();
        model.addAttribute("tiposTransporte", tiposTransporte);
        Optional<EmpresaTransporte> optionalst = adminService.getet(id);
        if (optionalst.isPresent()) {
            model.addAttribute("et", optionalst.get());
        }
        return "admin/F_update_transport_company";
    }

    // ACCIÓN DE ACTUALIZAR EMPRESAS DE TRANSPORTE
    @PostMapping("/updateet")
    public String updateet(EmpresaTransporte empresaTransporte) {
        adminService.save(empresaTransporte);
        return "redirect:/admin/D_list_transport_company";
    }
    // ACCION DE ELIMINAR EMPRESAS DE TRANSPORTE
    @PostMapping("/deleteet/{id}")
    public String deleteet(@PathVariable Integer id) {
        adminService.deleteet(id);
        return "redirect:/admin/D_list_transport_company";  
    }
    
    
    
    
    
    // CONTROLADOR RUTAS DE EMPRESAS DE TRANSPORTE

    // LISTA DE RUTAS DE EMPRESAS DE TRANSPORTE
    @GetMapping("/G_list_routes_transport/{id}")
    public String G_list_routes_transport(@PathVariable Integer id, Model model) {
        List<NLinea> lineas = adminService.getnl(id);
        model.addAttribute("lineas", lineas);
        return "admin/G_list_routes_transport";
    }

    // FORMULARIO DE CREAR RUTAS DE EMPRESAS DE TRANSPORTE
    @GetMapping("/H_new_routes_transport/{id}")
    public String H_new_routes_transport(@PathVariable Integer id,Model model) {
        model.addAttribute("empresaTransporteId", id);
        
        return "admin/H_new_routes_transport";
    }
    // ACCION DE CREAR RUTAS DE EMPRESAS DE TRANSPORTE
    @PostMapping("/savenlr") 
    public String savenlr(NLinea linea) {
        adminService.save(linea);
        return "redirect:/admin/G_list_routes_transport/"+linea.getEmpresaTransporte().getId();
    }
    // FORMULARIO DE ACTUALIZAR RUTAS DE EMPRESAS DE TRANSPORTE
    @GetMapping("/I_update_routes_transport/{id}")
    public String I_update_routes_transport(@PathVariable Integer id, Model model) {
    	model.addAttribute("NlineaId", id);
         Optional<NLinea> optionalst = adminService.getnln(id);
        if (optionalst.isPresent()) {
            model.addAttribute("nl", optionalst.get());
        }
        return "admin/I_update_routes_transport";
    }
    // ACCION DE ACTUALIZAR RUTAS DE EMPRESAS DE TRANSPORTE
    @PostMapping("/updatenlr")
    public String updatenlr(NLinea linea) {
        adminService.update(linea);
        return "redirect:/admin/G_list_routes_transport/" + linea.getEmpresaTransporte().getId();
    }
    // ACCION DE ELIMINAR RUTAS DE EMPRESAS DE TRANSPORTE
    @PostMapping("/deletenlr/{id}")
    public String deletenlr(@PathVariable Integer id, Model model) {
    	Optional<NLinea> optionalst = adminService.getnln(id);
    	int var = 0;
        if (optionalst.isPresent()) {
          var=optionalst.get().getEmpresaTransporteid();
			 
        }
        adminService.deletetl(id);
        
        return "redirect:/admin/G_list_routes_transport/"+var;// 
    }

    
    
    
    // CONTROLADOR PARADERO DE RUTAS

    // LISTA DE PARADEROS DE RUTAS
    @GetMapping("/J_list_whereabouts_route/{id}")
    public String J_list_whereabouts_route(@PathVariable Integer id,Model model) {
    	List<Paradero> paraderos = adminService.getpp(id);
        model.addAttribute("paradero", paraderos);
        return "admin/J_list_whereabouts_route";
    }

    // FORMULARIO DE CREAR PARADEROS DE RUTAS
    @GetMapping("/K_new_whereabouts_route/{id}")
    public String K_new_whereabouts_route(@PathVariable Integer id, Model model) {
        model.addAttribute("rutaId", id);

        return "admin/K_new_whereabouts_route";
    }
    // ACCION DE CREAR PARADEROS DE RUTAS

    @PostMapping("/savep")
    public String savep(Paradero paradero) {
        adminService.save(paradero);
        return "redirect:/admin/J_list_whereabouts_route/" + paradero.getNlinea().getId();
    }

    // FORMULARIO DE ACTUALIZAR PARADEROS DE RUTAS
    @GetMapping("/L_update_whereabouts_route/{id}")
    public String L_update_whereabouts_route(@PathVariable Integer id, Model model) {
        Optional<Paradero> optionalParadero = adminService.getp(id);
        if (optionalParadero.isPresent()) {
            model.addAttribute("paradero", optionalParadero.get());
            model.addAttribute("lineas", adminService.findAllNLinea());
        }
        return "admin/L_update_whereabouts_route";
    }
    // ACCION DE ACTUALIZAR PARADEROS DE RUTAS
    @PostMapping("/updatep")
    public String updatep(Paradero paradero) {
        adminService.update(paradero);
        return "redirect:/admin/J_list_whereabouts_route"; // Aquí debes pasar los IDs necesarios
    }
    // ACCION DE ELIMINAR PARADEROS DE RUTAS
    @PostMapping("/deletep/{id}")
    public String deletep(@PathVariable Integer id) {
        adminService.deletep(id);
        return "redirect:/admin/J_list_whereabouts_route"; // Aquí debes pasar los IDs necesarios
    }

    
    
    
    
    // CONTROLADOR UNIDAD DE TRANSPORTE

    // LISTA DE UNIDADES DE TRANSPORTE
    @GetMapping("/N_list_transport_unit/{id}")
    public String N_list_transport_unit(@PathVariable Integer id,Model model) {
        model.addAttribute("unidades", adminService.findAllNUnidadTransporte());
        List<UnidadTransporte> unidadTransportes = adminService.getutt(id);
        model.addAttribute("unidad", unidadTransportes);
        return "admin/N_list_transport_unit";
    }

    // FORMULARIO DE CREAR UNIDADES DE TRANSPORTE
    @GetMapping("/O_new_transport_unit/{id}")
    public String O_new_transport_unit(@PathVariable Integer id,Model model) {
        model.addAttribute("empresas", adminService.findAllEmpresaTransporte());
        return "admin/O_new_transport_unit";
    }
    // ACCION DE CREAR UNIDADES DE TRANSPORTE
    @PostMapping("/saveut")
    public String saveut(UnidadTransporte unidadTransporte) {
        adminService.save(unidadTransporte);
        return "redirect:/admin/N_list_transport_unit/"+ unidadTransporte.getNlinea().getId();
    }
    // FORMULARIO DE ACTUALIZAR UNIDADES DE TRANSPORTE
    @GetMapping("/P_update_transport_unit/{id}")
    public String P_update_transport_unit(@PathVariable Integer id, Model model) {
        Optional<UnidadTransporte> optionalUnidad = adminService.getut(id);
        if (optionalUnidad.isPresent()) {
            model.addAttribute("unidad", optionalUnidad.get());
            model.addAttribute("empresas", adminService.findAllEmpresaTransporte());
        }
        return "admin/P_update_transport_unit";
    }
    // ACCION DE ACTUALIZAR UNIDADES DE TRANSPORTE
    @PostMapping("/updateut")
    public String updateut(UnidadTransporte unidadTransporte) {
        adminService.update(unidadTransporte);
        return "redirect:/admin/N_list_transport_unit";
    }
    // ACCION DE ELIMINAR UNIDADES DE TRANSPORTE
    @PostMapping("/deleteut/{id}")
    public String deleteut(@PathVariable Integer id) {
        adminService.deleteut(id);
        return "redirect:/admin/N_list_transport_unit";
    }


}
