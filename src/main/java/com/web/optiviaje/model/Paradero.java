package com.web.optiviaje.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Paradero")
public class Paradero {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String nombre;
	    private String urlUbicacion;
	     @ManyToOne
	     private NLinea nLinea;
	    public Paradero() {
			// TODO Auto-generated constructor stub
		}
		public Paradero(int id, String nombre, String urlUbicacion) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.urlUbicacion = urlUbicacion;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getUrlUbicacion() {
			return urlUbicacion;
		}
		public void setUrlUbicacion(String urlUbicacion) {
			this.urlUbicacion = urlUbicacion;
		}
		public NLinea getnLinea() {
			return nLinea;
		}
		public void setnLinea(NLinea nLinea) {
			this.nLinea = nLinea;
		}
		 
		
}
