package com.web.optiviaje.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    //@JoinColumn(name = "nlinea_id")  // Esta línea crea la columna de clave foránea en la tabla Paradero
    private NLinea nlinea;

    public Paradero() {
    }
    public String getNLineaNombre() {
        if (nlinea != null) {
            return nlinea.getCodigoLinea();
        }
        return "Servicio no disponible";
    }
    public Paradero(int id, String nombre, String urlUbicacion) {
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

    public NLinea getNlinea() {
        return nlinea;
    }

    public void setNlinea(NLinea nlinea) {
        this.nlinea = nlinea;
    }
}
