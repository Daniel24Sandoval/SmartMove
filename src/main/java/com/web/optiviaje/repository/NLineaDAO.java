package com.web.optiviaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.optiviaje.model.NLinea;
 
@Repository
public interface NLineaDAO extends JpaRepository<NLinea, Integer> {
    List<NLinea> findByEmpresaTransporteId(Integer empresaTransporteId);
    Optional<NLinea> findFirstByCodigoLinea(String codigoLinea);

    NLinea findByCodigoLinea(String codigoLinea);

}
