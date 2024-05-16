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
@Table(name = "NLinea")
public class NLinea {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String codigoLinea;
	    private String urlRuta;
	    private String inicio;
	    private String destino;
	    
	    
		  @OneToMany(mappedBy = "nLinea") 
		  private List<Paradero> Paradero;
		  
		  @ManyToOne 
		  private EmpresaTransporte empresaTransporte;
		  
		  @OneToMany(mappedBy = "nLinea") 
		  private List<UnidadTransporte> UnidadTransporte;
	    
		  
		  
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
	    
		   //MAPEAMOS NUESTRAS VARIABLE HACIA OTRAS CLASES QUE COMPARTE RELACION 
		   //SINTAXIS: @ONETOMANY OR MANYTOONE (MAPPENBY= OBJETO CREADO EN LA CLASE RELACIONADA (private Usuario usuario;))
		   //SINTAXIS2: PRIVATE LISTA <NOMBRE_CLASE_DERELACION> NOMBRE_TABLA_DERELACION; @Table name 
			

			 
	    
	    
}
