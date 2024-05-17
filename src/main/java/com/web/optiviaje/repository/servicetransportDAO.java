package com.web.optiviaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.optiviaje.model.ServicioTransporte;
@Repository
public interface servicetransportDAO extends JpaRepository<ServicioTransporte, Integer>{

}
