package com.web.optiviaje.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ruta")
public class Ruta {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String tramo;
		
		  @ManyToOne 
		  private UnidadTransporte unidadTransporte;
		  
		  @OneToMany(mappedBy = "ruta") 
		  private List<Viaje> Viaje;
		 
	    public Ruta() {
			// TODO Auto-generated constructor stub
		}
		public Ruta(int id, String tramo) {
			super();
			this.id = id;
			this.tramo = tramo;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTramo() {
			return tramo;
		}
		public void setTramo(String tramo) {
			this.tramo = tramo;
		}
		public UnidadTransporte getUnidadTransporte() {
			return unidadTransporte;
		}
		public void setUnidadTransporte(UnidadTransporte unidadTransporte) {
			this.unidadTransporte = unidadTransporte;
		}
		public List<Viaje> getViaje() {
			return Viaje;
		}
		public void setViaje(List<Viaje> viaje) {
			Viaje = viaje;
		}
 
	    
}
