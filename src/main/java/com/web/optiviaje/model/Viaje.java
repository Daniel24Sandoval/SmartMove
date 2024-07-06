package com.web.optiviaje.model;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Viaje")
public class Viaje {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String distancia;
    private String duracion;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHoraSalida;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaHoraLlegada;
    private String origen;
    private String destino;
    private String trafico;
    
    
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Ruta ruta;

    public String getNLineaNombre() {
        if (ruta != null) {
        	
        	NLinea nlinea = ruta.getUnidadTransporte().getNlinea();
        	           
        	return nlinea.getCodigoLinea();
             
            
        }
        return "Servicio no disponible";
    }
    
    public Viaje() {
 	}

	public Viaje(int id, String distancia, String duracion, LocalDateTime fechaHoraSalida,
			LocalDateTime fechaHoraLlegada, String origen, String destino, String trafico) {
		super();
		this.id = id;
		this.distancia = distancia;
		this.duracion = duracion;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.origen = origen;
		this.destino = destino;
		this.trafico = trafico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public LocalDateTime getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
		this.fechaHoraLlegada = fechaHoraLlegada;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTrafico() {
		return trafico;
	}

	public void setTrafico(String trafico) {
		this.trafico = trafico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}





 


 
    
}