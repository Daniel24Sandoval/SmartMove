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
@Table(name = "N_Linea")
public class NLinea {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String codigoLinea;
	    private String urlRuta;
	    private String inicio;
	    private String destino;
	    
	    
		  @OneToMany(mappedBy = "nlinea") 
		  private List<Paradero> Paradero;
		  
		  @ManyToOne 
		 @JoinColumn(name = "empresaTransporte_id ", referencedColumnName = "id")
		  private EmpresaTransporte empresaTransporte;
		  
		  @OneToMany(mappedBy = "nlinea") 
 		  private List<UnidadTransporte> UnidadTransporte;
		  
		    public String getEmpresaTransporteNombre() {
		        if (empresaTransporte != null) {
		            return empresaTransporte.getNombre();
		        }
		        return "Servicio no disponible";
		    }
		    public int getEmpresaTransporteid() {
		        if (empresaTransporte != null) {
		            return empresaTransporte.getId();
		        }
		        return (Integer) null;
		    }
		  public NLinea() {
			// TODO Auto-generated constructor stub
		}
		  
		public NLinea(int id, String codigoLinea, String urlRuta, String inicio, String destino) {
			super();
			this.id = id;
			this.codigoLinea = codigoLinea;
			this.urlRuta = urlRuta;
			this.inicio = inicio;
			this.destino = destino;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCodigoLinea() {
			return codigoLinea;
		}
		public void setCodigoLinea(String codigoLinea) {
			this.codigoLinea = codigoLinea;
		}
		public String getUrlRuta() {
			return urlRuta;
		}
		public void setUrlRuta(String urlRuta) {
			this.urlRuta = urlRuta;
		}
		public String getInicio() {
			return inicio;
		}
		public void setInicio(String inicio) {
			this.inicio = inicio;
		}
		public String getDestino() {
			return destino;
		}
		public void setDestino(String destino) {
			this.destino = destino;
		}
		public List<Paradero> getParadero() {
			return Paradero;
		}
		public void setParadero(List<Paradero> paradero) {
			Paradero = paradero;
		}
		public EmpresaTransporte getEmpresaTransporte() {
			return empresaTransporte;
		}
		public void setEmpresaTransporte(EmpresaTransporte empresaTransporte) {
			this.empresaTransporte = empresaTransporte;
		}
		public List<UnidadTransporte> getUnidadTransporte() {
			return UnidadTransporte;
		}
		public void setUnidadTransporte(List<UnidadTransporte> unidadTransporte) {
			UnidadTransporte = unidadTransporte;
		}


 
}
