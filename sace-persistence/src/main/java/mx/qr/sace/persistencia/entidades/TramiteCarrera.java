package mx.qr.sace.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.qr.core.persistencia.EstatusRegistro;

/**
 */
@Entity
@Table(name = "carreras_tramites", catalog = "SACE_DB")
public class TramiteCarrera implements java.io.Serializable, Comparable<TramiteCarrera> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434137856304412095L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_carrera_tramite", unique = true, nullable = false)
	private int idCarreraTramite;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false),
			@JoinColumn(name = "id_escolaridad", referencedColumnName = "id_escolaridad", nullable = false),
			@JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad", nullable = false) })	
	private Carrera carrera;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tramite", nullable = false)	
	private Tramite tramite;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estatus")
	private EstatusRegistro estatus;
	
	@Column(name = "cuota", nullable = false, precision = 4)
	private float cuota;
	
	@Column(name = "observaciones", length = 45)
	private String observaciones;

	public TramiteCarrera() {
	}

	public int getIdCarreraTramite() {
		return this.idCarreraTramite;
	}

	public void setIdCarreraTramite(int idCarreraTramite) {
		this.idCarreraTramite = idCarreraTramite;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera auxCarrera) {
		this.carrera = auxCarrera;
	}

	public Tramite getTramite() {
		return this.tramite;
	}

	public void setTramite(Tramite auxTramite) {
		this.tramite = auxTramite;
	}

	public EstatusRegistro getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusRegistro estatus) {
		this.estatus = estatus;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(float cuota) {
		this.cuota = cuota;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCarreraTramite;
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
		TramiteCarrera other = (TramiteCarrera) obj;
		if (idCarreraTramite != other.idCarreraTramite)
			return false;
		return true;
	}

	@Override
	public int compareTo(TramiteCarrera o) {
		int ret = 1;
		if(this.getIdCarreraTramite() < o.idCarreraTramite) {
			ret = 0;
		}
		return ret;
	}

}
