package com.web.optiviaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.optiviaje.model.NLinea;
import com.web.optiviaje.model.UnidadTransporte;

@Repository
public interface UnidadTransporteDAO extends JpaRepository<UnidadTransporte, Integer>{
     List<UnidadTransporte> findAllByNlinea_Id(Integer nlineaId);
     //    List<Paradero> findAllByNlinea_Id(Integer nlineaId);
     Optional<UnidadTransporte>findById(Integer id);
	//List<UnidadTransporte> findAllByNlinea_Id(NLinea linea);
   // List<UnidadTransporte> findAllByNlinea_Id(Integer nlineaId);

}
