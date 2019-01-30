package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.TipoTramite;

/**
 * El Tramite de la escuela
 */
@Entity
@Table(name = "c_tramite", catalog = "SACE_DB")
public class Tramite implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846860987501422608L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_tramite", unique = true, nullable = false)
	private int idTramite;

	@Column(name = "descripcion", length = 45)
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_tramite")
	private TipoTramite tipoTramite;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tramite")
	private Set<TramiteCarrera> tramiteCarreras = new HashSet<TramiteCarrera>(0);

	public Tramite() {
	}
	
	public Tramite(int idTramite) {
		this();
		this.idTramite = idTramite;
	}

	public int getIdTramite() {
		return this.idTramite;
	}

	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<TramiteCarrera> getTramiteCarreras() {
		return tramiteCarreras;
	}

	public void setTramiteCarreras(Set<TramiteCarrera> tramiteCarreras) {
		this.tramiteCarreras = tramiteCarreras;
	}

	public TipoTramite getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(TipoTramite comodinIdentificador) {
		this.tipoTramite = comodinIdentificador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTramite;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tramite other = (Tramite) obj;
		if (idTramite != other.idTramite)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tramite [idTramite=" + idTramite 
				+ ", descripcion=" + descripcion + "]";
	}

	@Override
	public void setUsuario(String usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFechaHora(Date fecha) {
		// TODO Auto-generated method stub
		
	}

}
