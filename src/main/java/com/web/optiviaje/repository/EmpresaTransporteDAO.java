package com.web.optiviaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.optiviaje.model.EmpresaTransporte;

@Repository
public interface EmpresaTransporteDAO extends JpaRepository<EmpresaTransporte, Integer>{

}
