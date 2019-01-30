package mx.qr.sace.persistencia.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;

/**
 */
@Entity
@Table(name = "carreras", catalog = "SACE_DB")
public class Carrera implements java.io.Serializable, Entidad {

	private static final long serialVersionUID = -4007058309605324064L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idCarrera", column = @Column(name = "id_carrera", nullable = false)),
			@AttributeOverride(name = "idEscolaridad", column = @Column(name = "id_escolaridad", nullable = false)),
			@AttributeOverride(name = "idModalidad", column = @Column(name = "id_modalidad", nullable = false)) })
	private CarreraId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modalidad", nullable = false, insertable = false, updatable = false)
	private Modalidad modalidad;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_escolaridad", nullable = false, insertable = false, updatable = false)	
	private Escolaridad escolaridad;
	
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	@Column(name = "duracion", nullable = false)
	private int duracion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_nivel", nullable = false, insertable = false, updatable = false)	
	private TipoNivel tipoNivel;
	
	@Column(name = "niveles", nullable = false)
	private int niveles;
	
	@Column(name = "codigo_identificador", nullable = false)
	private String codigoIdentificador;
	
	@Column(name = "cantidad_ciclos", nullable = false)
	private int cantidadCiclos;
	
	@Column(name = "cantidad_periodos", nullable = false)
	private int cantidadPeriodosPorCiclo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carrera")
	private Set<TramiteCarrera> tramites = new HashSet<TramiteCarrera>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carrera")
	private Set<RequisitoCarrera> requisitos = new HashSet<RequisitoCarrera>(0);
	
	public Carrera() {
	}

	public CarreraId getId() {
		return this.id;
	}

	public void setId(CarreraId id) {
		this.id = id;
	}

	public Modalidad getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(Modalidad auxModalidad) {
		this.modalidad = auxModalidad;
	}

	public Escolaridad getEscolaridad() {
		return this.escolaridad;
	}

	public void setEscolaridad(Escolaridad auxEscolaridad) {
		this.escolaridad = auxEscolaridad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public void setUsuario(String usuario) {
		
		
	}

	@Override
	public void setFechaHora(Date fecha) {
		
		
	}

	public Set<TramiteCarrera> getTramites() {
		return tramites;
	}

	public void setTramites(Set<TramiteCarrera> tramites) {
		this.tramites = tramites;
	}

	public Set<RequisitoCarrera> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(Set<RequisitoCarrera> requisitos) {
		this.requisitos = requisitos;
	}

	public int getNiveles() {
		return niveles;
	}

	public void setNiveles(int niveles) {
		this.niveles = niveles;
	}

	public TipoNivel getTipoNivel() {
		return tipoNivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Carrera other = (Carrera) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public String getCodigoIdentificador() {
		return codigoIdentificador;
	}

	public void setCodigoIdentificador(String codigoIdentificador) {
		this.codigoIdentificador = codigoIdentificador;
	}

	public int getCantidadCiclos() {
		return cantidadCiclos;
	}

	public void setCantidadCiclos(int cantidadCiclos) {
		this.cantidadCiclos = cantidadCiclos;
	}

	public int getCantidadPeriodosPorCiclo() {
		return cantidadPeriodosPorCiclo;
	}

	public void setCantidadPeriodosPorCiclo(int cantidadPeriodos) {
		this.cantidadPeriodosPorCiclo = cantidadPeriodos;
	}

	public void setTipoNivel(TipoNivel tipoNivel) {
		this.tipoNivel = tipoNivel;
	}

	@Override
	public String toString() {
		return "Carrera [\nid=" + id + ", modalidad=" + modalidad
				+ ", escolaridad=" + escolaridad + ", nombre=" + nombre
				+ ", duracion=" + duracion + ", tipoNivel=" + tipoNivel
				+ ", codigoIdentificador=" + codigoIdentificador
				+ ", cantidadCiclos=" + cantidadCiclos
				+ ", cantidadPeriodosPorCiclo=" + cantidadPeriodosPorCiclo
				+ "\n]";
	}
}
