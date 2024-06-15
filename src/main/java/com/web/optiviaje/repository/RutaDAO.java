package com.web.optiviaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.optiviaje.model.Ruta;

@Repository
public interface RutaDAO extends JpaRepository<Ruta, Integer> {

}
