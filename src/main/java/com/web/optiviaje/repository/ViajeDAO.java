package com.web.optiviaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.optiviaje.model.Viaje;

@Repository
public interface ViajeDAO extends JpaRepository<Viaje, Integer> {

	List<Viaje> findByusuarioId(Integer usuarioId);
	

}
