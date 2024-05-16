package com.web.optiviaje.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Contacto")
public class Contacto {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String nombre;
	    private String correo;
	    private String numeros;
	    private String mensaje;
	    public Contacto() {
			// TODO Auto-generated constructor stub
		}
		public Contacto(int id, String nombre, String correo, String numeros, String mensaje) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.correo = correo;
			this.numeros = numeros;
			this.mensaje = mensaje;
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
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		public String getNumeros() {
			return numeros;
		}
		public void setNumeros(String numeros) {
			this.numeros = numeros;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
	    
}
