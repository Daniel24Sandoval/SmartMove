package com.web.optiviaje.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Preferencia")
public class Preferencia {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String servicioTransportePreferible;
	    private boolean priorizarTiempo;
	    @ManyToOne
	    private Usuario usuario; 
	    public Preferencia() {
		}
		public Preferencia(int id, String servicioTransportePreferible, boolean priorizarTiempo) {
			super();
			this.id = id;
			this.servicioTransportePreferible = servicioTransportePreferible;
			this.priorizarTiempo = priorizarTiempo;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getServicioTransportePreferible() {
			return servicioTransportePreferible;
		}
		public void setServicioTransportePreferible(String servicioTransportePreferible) {
			this.servicioTransportePreferible = servicioTransportePreferible;
		}
		public boolean isPriorizarTiempo() {
			return priorizarTiempo;
		}
		public void setPriorizarTiempo(boolean priorizarTiempo) {
			this.priorizarTiempo = priorizarTiempo;
		}
		public Usuario getUsuario() {
			return usuario;
		}
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		 
	    
}
