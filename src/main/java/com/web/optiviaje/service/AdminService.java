package com.web.optiviaje.service;

import java.util.List;
import java.util.Optional;

import com.web.optiviaje.model.EmpresaTransporte;
import com.web.optiviaje.model.ServicioTransporte;

public interface AdminService {
	//DECLARACION DE METODOS
	
	//SERVICIO DE TRANSPORTE
		public ServicioTransporte save(ServicioTransporte servicioTransporte);
		Optional<ServicioTransporte> validacion(Integer id);
		public ServicioTransporte update(ServicioTransporte servicioTransporte);
		public ServicioTransporte delete(Integer id);
		public List<ServicioTransporte> findAllServicioTransporte();
		public Optional<ServicioTransporte> get(Integer id);
	//EMPRESA DE TRANSPORTE
		public EmpresaTransporte save(Integer idst,EmpresaTransporte empresaTransporte);
		public EmpresaTransporte update(EmpresaTransporte empresaTransporte);
		public EmpresaTransporte deleteet(Integer id);
		public List<EmpresaTransporte> findAllEmpresaTransporte();
		public Optional<EmpresaTransporte> getet(Integer id);

}
