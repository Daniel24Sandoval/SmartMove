package com.web.optiviaje.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PreguntaFrecuente")
public class PreguntaFrecuente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pregunta;
    private String respuesta;
    private Date fechaHora;
    private String estado;
    
    
  //ESTA TABLA ESTA RELACIONADA CON LA TABLA USUARIO
 
	  @ManyToOne 
	  private Usuario usuario;
	 
    public PreguntaFrecuente() {
	}


public PreguntaFrecuente(int id, String pregunta, String respuesta, Date fechaHora, String estado) {
	super();
	this.id = id;
	this.pregunta = pregunta;
	this.respuesta = respuesta;
	this.fechaHora = fechaHora;
	this.estado = estado;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getPregunta() {
	return pregunta;
}


public void setPregunta(String pregunta) {
	this.pregunta = pregunta;
}


public String getRespuesta() {
	return respuesta;
}


public void setRespuesta(String respuesta) {
	this.respuesta = respuesta;
}


public Date getFechaHora() {
	return fechaHora;
}


public void setFechaHora(Date fechaHora) {
	this.fechaHora = fechaHora;
}


public String getEstado() {
	return estado;
}


public void setEstado(String estado) {
	this.estado = estado;
}
    
}
