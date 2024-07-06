package com.web.optiviaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.optiviaje.model.Usuario;

public interface UsuarioDAO extends JpaRepository <Usuario, Integer> {

	Usuario findByCorreoElectronico(String correoElectronico);

}
