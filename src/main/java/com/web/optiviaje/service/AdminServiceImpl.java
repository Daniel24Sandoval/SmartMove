package com.web.optiviaje.service;

 
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.optiviaje.model.EmpresaTransporte;
import com.web.optiviaje.model.NLinea;
import com.web.optiviaje.model.Paradero;
import com.web.optiviaje.model.ServicioTransporte;
import com.web.optiviaje.model.UnidadTransporte;
import com.web.optiviaje.repository.ServicioTransporteDAO;
import com.web.optiviaje.repository.UnidadTransporteDAO;
import com.web.optiviaje.repository.EmpresaTransporteDAO;
import com.web.optiviaje.repository.NLineaDAO;
import com.web.optiviaje.repository.ParaderoDAO;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private ServicioTransporteDAO servicioTransporteDAO;
    @Autowired
    private EmpresaTransporteDAO empresaTransporteDAO;
    @Autowired
    private NLineaDAO nLineaDAO;
    @Autowired
    private ParaderoDAO paraderoDAO;
    @Autowired
    private UnidadTransporteDAO transporteDAO;

    @Override
    public ServicioTransporte save(ServicioTransporte servicioTransporte) {
        return servicioTransporteDAO.save(servicioTransporte);
    }

    @Override
    public ServicioTransporte update(ServicioTransporte servicioTransporte) {
        return servicioTransporteDAO.save(servicioTransporte);
    }

    @Override
    public void delete(Integer id) {
        servicioTransporteDAO.deleteById(id);
    }

    @Override
    public List<ServicioTransporte> findAllServicioTransporte() {
        return servicioTransporteDAO.findAll();
    }

    @Override
    public Optional<ServicioTransporte> get(Integer id) {
        return servicioTransporteDAO.findById(id);
    }

    @Override
    public EmpresaTransporte save(EmpresaTransporte empresaTransporte) {
        return empresaTransporteDAO.save(empresaTransporte);
    }

    @Override
    public EmpresaTransporte update(EmpresaTransporte empresaTransporte) {
        return empresaTransporteDAO.save(empresaTransporte);
    }

    @Override
    public void deleteet(Integer id) {
        empresaTransporteDAO.deleteById(id);
    }

    @Override
    public List<EmpresaTransporte> findAllEmpresaTransporte() {
        return empresaTransporteDAO.findAll();
    }

    @Override
    public Optional<EmpresaTransporte> getet(Integer id) {
        return empresaTransporteDAO.findById(id);
    }

    @Override
    public NLinea save(NLinea linea) {
        return nLineaDAO.save(linea);
    }

    @Override
    public NLinea update(NLinea linea) {
        return nLineaDAO.save(linea);
    }

    @Override
    public void deletetl(Integer id) {
        nLineaDAO.deleteById(id);
    }

    @Override
    public List<NLinea> findAllNLinea() {
        return nLineaDAO.findAll();
    }
    @Override
    public List<NLinea> getnl(Integer empresaTransporteId) {
        return nLineaDAO.findByEmpresaTransporteId(empresaTransporteId);
    }



    @Override
    public Paradero save(Paradero paradero) {
        return paraderoDAO.save(paradero);
    }

    @Override
    public Paradero update(Paradero paradero) {
        return paraderoDAO.save(paradero);
    }

    @Override
    public void deletep(Integer id) {
        paraderoDAO.deleteById(id);
    }

    @Override
    public List<Paradero> findAllNParadero() {
        return paraderoDAO.findAll();
    }

    @Override
    public Optional<Paradero> getp(Integer id) {
        return paraderoDAO.findById(id);
    }

    @Override
    public UnidadTransporte save(UnidadTransporte unidadTransporte) {
        return transporteDAO.save(unidadTransporte);
    }

    @Override
    public UnidadTransporte update(UnidadTransporte unidadTransporte) {
        return transporteDAO.save(unidadTransporte);
    }

    @Override
    public void deleteut(Integer id) {
        transporteDAO.deleteById(id);
    }

    @Override
    public List<UnidadTransporte> findAllNUnidadTransporte() {
        return transporteDAO.findAll();
    }

    @Override
    public Optional<UnidadTransporte> getut(Integer id) {
        return transporteDAO.findById(id);
    }

	@Override
	public Optional<NLinea> getnln(Integer id) {
		// TODO Auto-generated method stub
		return nLineaDAO.findById(id);
	}

	@Override
	public List<Paradero> getpp(Integer id) {
		// TODO Auto-generated method stubparaderoDAO.findByNLineaId(id)
		return paraderoDAO.findAllByNlinea_Id(id);
	}

	@Override
	public List<UnidadTransporte> getutt(Integer id) {
		// TODO Auto-generated method stub
		return transporteDAO.findAllByNlinea_Id(id);
	}
	
	
	//EXPERIMENTO
	 

    
	@Override
	public Optional<NLinea> findByCodigoLinea(String codigoLinea) {
		// TODO Auto-generated method stub
		return nLineaDAO.findFirstByCodigoLinea(codigoLinea);
	}
	
	@Override
	public UnidadTransporte seleccionarUnidadTransporte(String idRuta) {
	    NLinea linea = nLineaDAO.findByCodigoLinea(idRuta);
	    if (linea != null) {
	        List<UnidadTransporte> unidades = transporteDAO.findAllByNlinea_Id(linea.getId());
	        if (!unidades.isEmpty()) {
	            UnidadTransporte unidadSeleccionada = unidades.get(new Random().nextInt(unidades.size()));
	            unidadSeleccionada.setEstado(false); // Actualizar el estado a "no lleno"
	            transporteDAO.save(unidadSeleccionada);
	            return unidadSeleccionada;
	        }
	    }
	    return null; // Manejar el caso en que no se encuentren unidades de transporte
	}
}