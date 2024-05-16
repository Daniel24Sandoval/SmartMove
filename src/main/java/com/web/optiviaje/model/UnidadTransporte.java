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
@Table(name = "UnidadTransporte")
public class UnidadTransporte {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String nombreConductor;
	    private String placa;
	    private Boolean estado;
	    private double distanciaKilometros;
	    private int capacidad;
	    
		
		  @ManyToOne 
		  private NLinea nLinea;
		  
		  @OneToMany(mappedBy = "unidadTransporte") 
		  private List<Ruta> Ruta;
		 
	    public UnidadTransporte() {
			// TODO Auto-generated constructor stub
		}

		public UnidadTransporte(int id, String nombreConductor, String placa, Boolean estado,
				double distanciaKilometros, int capacidad) {
			super();
			this.id = id;
			this.nombreConductor = nombreConductor;
			this.placa = placa;
			this.estado = estado;
			this.distanciaKilometros = distanciaKilometros;
			this.capacidad = capacidad;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombreConductor() {
			return nombreConductor;
		}

		public void setNombreConductor(String nombreConductor) {
			this.nombreConductor = nombreConductor;
		}

		public String getPlaca() {
			return placa;
		}

		public void setPlaca(String placa) {
			this.placa = placa;
		}

		public Boolean getEstado() {
			return estado;
		}

		public void setEstado(Boolean estado) {
			this.estado = estado;
		}

		public double getDistanciaKilometros() {
			return distanciaKilometros;
		}

		public void setDistanciaKilometros(double distanciaKilometros) {
			this.distanciaKilometros = distanciaKilometros;
		}

		public int getCapacidad() {
			return capacidad;
		}

		public void setCapacidad(int capacidad) {
			this.capacidad = capacidad;
		}
	    
		
}
