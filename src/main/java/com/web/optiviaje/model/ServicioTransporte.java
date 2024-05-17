package com.web.optiviaje.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServicioTransporte")
public class ServicioTransporte {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String tipoServicio;
    private String descripcion;
    
  //MAPEAMOS NUESTRAS VARIABLE HACIA OTRAS CLASES QUE COMPARTE RELACION 
	   //SINTAXIS: @ONETOMANY OR MANYTOONE (MAPPENBY= OBJETO CREADO EN LA CLASE RELACIONADA (private Usuario usuario;))
	   //SINTAXIS2: PRIVATE LISTA <NOMBRE_CLASE_DERELACION> NOMBRE_TABLA_DERELACION; @Table name 
	     @OneToMany(mappedBy = "servicioTransporte")
	     private List<EmpresaTransporte> EmpresaTransporte;
    public ServicioTransporte() {

	}
	
 
	
	public ServicioTransporte(int id, String nombre, String tipoServicio, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoServicio = tipoServicio;
		this.descripcion = descripcion;
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



	public String getTipoServicio() {
		return tipoServicio;
	}



	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public List<EmpresaTransporte> getEmpresaTransporte() {
		return EmpresaTransporte;
	}



	public void setEmpresaTransporte(List<EmpresaTransporte> empresaTransporte) {
		EmpresaTransporte = empresaTransporte;
	}



	 
 
	}
