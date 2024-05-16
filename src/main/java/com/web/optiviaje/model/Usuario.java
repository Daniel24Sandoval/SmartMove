package com.web.optiviaje.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String nombres;
	    private String correoElectronico;
	    private String contrasena;
	    private int numeroTelefono;
	    private boolean estado;

	   //MAPEAMOS NUESTRAS VARIABLE HACIA OTRAS CLASES QUE COMPARTE RELACION 
	   //SINTAXIS: @ONETOMANY OR MANYTOONE (MAPPENBY= OBJETO CREADO EN LA CLASE RELACIONADA (private Usuario usuario;))
	   //SINTAXIS2: PRIVATE LISTA <NOMBRE_CLASE_DERELACION> NOMBRE_TABLA_DERELACION; @Table name 
	    @OneToMany(mappedBy = "usuario")
	    private List<PreguntaFrecuente> PreguntaFrecuente;

	    @OneToMany(mappedBy  = "usuario")
	    private List<Notificacion> notificacion;

	    @OneToMany(mappedBy = "usuario")
	    private List<Preferencia> preferencia;

	    @OneToMany(mappedBy = "usuario")
	    private List<PreguntaFrecuente> preguntaFrecuentes;

	    @OneToMany(mappedBy = "usuario")
	    private List<Viaje> viaje;
	    
	    
	    
	    //CONSTRUCTOR VACIO:
	    public Usuario() {
	    	
		}
	    //CONSTRUCTOR LLENO:
		public Usuario(int id, String nombres, String correoElectronico, String contrasena, int numeroTelefono,
				boolean estado) {
			super();
			this.id = id;
			this.nombres = nombres;
			this.correoElectronico = correoElectronico;
			this.contrasena = contrasena;
			this.numeroTelefono = numeroTelefono;
			this.estado = estado;
		}
		
	    //GEET AND SEETER:

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombres() {
			return nombres;
		}
		public void setNombres(String nombres) {
			this.nombres = nombres;
		}
		public String getCorreoElectronico() {
			return correoElectronico;
		}
		public void setCorreoElectronico(String correoElectronico) {
			this.correoElectronico = correoElectronico;
		}
		public String getContrasena() {
			return contrasena;
		}
		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}
		public int getNumeroTelefono() {
			return numeroTelefono;
		}
		public void setNumeroTelefono(int numeroTelefono) {
			this.numeroTelefono = numeroTelefono;
		}
		public boolean isEstado() {
			return estado;
		}
		public void setEstado(boolean estado) {
			this.estado = estado;
		}
	    
	    

	    
	    
	    
	    
}
