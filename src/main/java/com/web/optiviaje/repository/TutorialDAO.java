package com.web.optiviaje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.optiviaje.model.Tutorial;

public interface TutorialDAO extends JpaRepository<Tutorial, Integer>{

	List<Tutorial> findAllByTitulo(String idus);

}
