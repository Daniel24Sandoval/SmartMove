package com.web.optiviaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.optiviaje.model.EmpresaTransporte;
import com.web.optiviaje.model.ServicioTransporte;
import com.web.optiviaje.repository.servicetransportDAO;
import com.web.optiviaje.repository.transportcompanyDAO;
@Service
public class AdminServiceImpl implements AdminService{
@Autowired
private servicetransportDAO servicetransportDAO;
@Autowired
private transportcompanyDAO transportcompanyDAO;

	@Override
	public ServicioTransporte save(ServicioTransporte servicioTransporte) {
		 
		return servicetransportDAO.save(servicioTransporte);
	}

	@Override
	public ServicioTransporte update( ServicioTransporte servicioTransporte) {
 
		return servicetransportDAO.save(servicioTransporte);
	}
	@Override
	public ServicioTransporte delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ServicioTransporte> findAllServicioTransporte() {
		// TODO Auto-generated method stub
		return servicetransportDAO.findAll();
	}

	@Override
	public Optional<ServicioTransporte> get(Integer id) {
		// TODO Auto-generated method stub
		return servicetransportDAO.findById(id);
	}

	 	@Override
	    public EmpresaTransporte save(Integer idst, EmpresaTransporte empresaTransporte) {
	        ServicioTransporte servicioTransporte = servicetransportDAO.findById(idst).orElse(null);
	        if (servicioTransporte != null) {
	            empresaTransporte.setServicioTransporte(servicioTransporte);
	        }
	        return transportcompanyDAO.save(empresaTransporte);
	    }

	@Override
	public EmpresaTransporte update(EmpresaTransporte empresaTransporte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaTransporte deleteet(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpresaTransporte> findAllEmpresaTransporte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EmpresaTransporte> getet(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<ServicioTransporte> validacion(Integer id) {
		// TODO Auto-generated method stub
		return servicetransportDAO.findById(id);
	}


}
