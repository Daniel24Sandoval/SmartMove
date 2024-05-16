package com.web.optiviaje.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Notificacion")
public class Notificacion {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String ubicacion;
	    private String tipoNotificacion;
	    private String mensaje;
	    private Date fechaHora;
	    private boolean leida;	    
	   @ManyToOne
	    private Usuario usuario;
	    public Notificacion() {
		}
	public Notificacion(int id, String ubicacion, String tipoNotificacion, String mensaje, Date fechaHora,
			boolean leida) {
		super();
		this.id = id;
		this.ubicacion = ubicacion;
		this.tipoNotificacion = tipoNotificacion;
		this.mensaje = mensaje;
		this.fechaHora = fechaHora;
		this.leida = leida;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public boolean isLeida() {
		return leida;
	}
	public void setLeida(boolean leida) {
		this.leida = leida;
	}
	    
}
