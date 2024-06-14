package com.web.optiviaje.service;

import java.util.List;
import java.util.Optional;

import com.web.optiviaje.model.EmpresaTransporte;
import com.web.optiviaje.model.NLinea;
import com.web.optiviaje.model.Paradero;
import com.web.optiviaje.model.ServicioTransporte;
import com.web.optiviaje.model.UnidadTransporte;

public interface AdminService {
	//DECLARACION DE METODOS
	
	//SERVICIO DE TRANSPORTE
		public ServicioTransporte save(ServicioTransporte servicioTransporte);
		public ServicioTransporte update(ServicioTransporte servicioTransporte);
		public void delete(Integer id);
		public List<ServicioTransporte> findAllServicioTransporte();
		public Optional<ServicioTransporte> get(Integer id);
	//EMPRESA DE TRANSPORTE
		public EmpresaTransporte save(EmpresaTransporte empresaTransporte);
		public EmpresaTransporte update(EmpresaTransporte empresaTransporte);
		public void deleteet(Integer id);
		public List<EmpresaTransporte> findAllEmpresaTransporte();
		public Optional<EmpresaTransporte> getet(Integer id);
	 //RUTAS DE TRANSPORTE (LINEAS DE TRANSPORTE) NLINE
		public NLinea save(NLinea linea);
		public NLinea update(NLinea linea);
		public void deletetl(Integer id);
		public List<NLinea> findAllNLinea();
		public List<NLinea> getnl(Integer empresaTransporteId);	 
		public Optional<NLinea> getnln(Integer id);
    //PARADEROS DE TRANSPORTE SEGUN NLINE(RUTAS DE TRANSPORTE)
		public Paradero save(Paradero paradero);
		public Paradero update(Paradero paradero);
		public void deletep(Integer id);
		public List<Paradero> findAllNParadero();
		public List<Paradero> getpp(Integer id);
		public Optional<Paradero> getp(Integer id);
		public  Optional<NLinea> findByCodigoLinea(String codigoLinea);
		///public List<Paradero> obtenerOActualizarRuta(String codigoLinea);
		//private List<String> obtenerParaderosDesdeWeb(String url);
	 //UNIDAD TRANSPORTE
		public UnidadTransporte save(UnidadTransporte unidadTransporte);
		public UnidadTransporte update(UnidadTransporte unidadTransporte);
		public void deleteut(Integer id);
		public List<UnidadTransporte> findAllNUnidadTransporte();
		public List<UnidadTransporte> getutt(Integer id);
		public Optional<UnidadTransporte> getut(Integer id);
		public UnidadTransporte seleccionarUnidadTransporte(String idRuta);

}
