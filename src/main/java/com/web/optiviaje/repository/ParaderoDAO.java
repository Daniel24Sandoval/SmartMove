package com.web.optiviaje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
import com.web.optiviaje.model.Paradero;

@Repository
public interface ParaderoDAO extends JpaRepository<Paradero, Integer>{
	//List<Paradero> findByNLineaId(Integer nLineaId);
    List<Paradero> findAllByNlinea_Id(Integer nlineaId);

}    

