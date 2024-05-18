package com.web.optiviaje.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmpresaTransporte")
public class EmpresaTransporte {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String ruc;
    private String descripcion;
    
	   @ManyToOne 
	    @JoinColumn(name = "servicioTransporte_id", referencedColumnName = "id")
	   private ServicioTransporte servicioTransporte;
	   //MAPEAMOS NUESTRAS VARIABLE HACIA OTRAS CLASES QUE COMPARTE RELACION 
	   //SINTAXIS: @ONETOMANY OR MANYTOONE (MAPPENBY= OBJETO CREADO EN LA CLASE RELACIONADA (private Usuario usuario;))
	   //SINTAXIS2: PRIVATE LISTA <NOMBRE_CLASE_DERELACION> NOMBRE_TABLA_DERELACION; @Table name 
	    @OneToMany(mappedBy = "empresaTransporte")
	    private List<NLinea> nlinea;
	   
	    public String getServicioTransporteNombre() {
	        if (servicioTransporte != null) {
	            return servicioTransporte.getNombre();
	        }
	        return "Servicio no disponible";
	    }
    public EmpresaTransporte() {
	}

	public EmpresaTransporte(int id, String nombre, String ruc, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ruc = ruc;
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
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ServicioTransporte getServicioTransporte() {
		return servicioTransporte;
	}
	public void setServicioTransporte(ServicioTransporte servicioTransporte) {
		this.servicioTransporte = servicioTransporte;
	}
	public List<NLinea> getNlinea() {
		return nlinea;
	}
	public void setNlinea(List<NLinea> nlinea) {
		this.nlinea = nlinea;
	}
 

	 
}