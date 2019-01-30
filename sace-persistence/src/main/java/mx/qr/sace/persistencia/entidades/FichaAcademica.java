package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "fichas_academicas", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class FichaAcademica extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -186548835348266754L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_ficha_academica", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_alumno", nullable = false)
	private Alumno alumno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dato_hist_academica", nullable = false)
	private DatoHistAcademico datoHistAcademico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dato_salud", nullable = false)
	private DatoSalud datoSalud;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false),
			@JoinColumn(name = "id_escolaridad", referencedColumnName = "id_escolaridad", nullable = false),
			@JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad", nullable = false) })
	private Carrera carrera;
	
	@Column(name = "id_escolaridad",insertable=false, updatable=false)
	private int idEscolaridad;
	
	@Column(name = "id_modalidad",insertable=false, updatable=false)
	private int idModalidad;
	
	@Column(name = "id_beca")
	private Integer idBeca;

	@Column(name = "esta_revalidando")
	private Boolean estaRevalidando;
	
	@Column(name = "ciclo_escolar", length = 45)
	private String cicloEscolar;
	
	@Column(name = "nivel_actual")
	private Integer nivelActual;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaRegistro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_beca", nullable = false, insertable=false, updatable=false)
	private Beca beca;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fichaAcademica")
	private Set<FichaPago> fichaPagos = new HashSet<FichaPago>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fichaAcademica")
	private Set<PagoDiverso> pagosDiversos = new HashSet<PagoDiverso>(0);

	public FichaAcademica() {
	}
	
	public FichaAcademica(String usuario) {
		this();
		this.usuario = usuario;
	}
	
	/**
	 * Para obtener el turno del alumno. El turno es computable.
	 * 
	 * @return La inicial del turno
	 */
	public String turnoFicha() {
    	/*
    	 * si modalidad == evo entonces turno es sabatino
    	 * de lo contrario
    	 * 	Si escolaridad == Bach entonce turno es matutino
    	 * de lo contrario
    	 * 	turno es vespertino
    	 */
		if(this.carrera.getModalidad().getIdModalidad() == 2) {
			return "S";
		} else {
			if(this.carrera.getEscolaridad().getIdEscolaridad() == 1) {
				return "M";
			} else {
				return "V";
			}
		}
	}
	
	public String getCicloEscolar() {
		return this.cicloEscolar;
	}

	public void setCicloEscolar(String cicloEscolar) {
		this.cicloEscolar = cicloEscolar;
	}

	public Set<FichaPago> getFichaPagos() {
		return this.fichaPagos;
	}

	public void setFichaPagos(Set<FichaPago> fichaPagos) {
		this.fichaPagos = fichaPagos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public DatoHistAcademico getDatoHistAcademico() {
		return datoHistAcademico;
	}

	public void setDatoHistAcademico(DatoHistAcademico datoHistAcademico) {
		this.datoHistAcademico = datoHistAcademico;
	}

	public DatoSalud getDatoSalud() {
		return datoSalud;
	}

	public void setDatoSalud(DatoSalud datoSalud) {
		this.datoSalud = datoSalud;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getIdEscolaridad() {
		return idEscolaridad;
	}

	public void setIdEscolaridad(int idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	public Integer getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(Integer nivelActual) {
		this.nivelActual = nivelActual;
	}

	public int getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(int idModalidad) {
		this.idModalidad = idModalidad;
	}

	public Beca getBeca() {
		return beca;
	}

	public void setBeca(Beca beca) {
		this.beca = beca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		FichaAcademica other = (FichaAcademica) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Boolean getEstaRevalidando() {
		return estaRevalidando;
	}

	public void setEstaRevalidando(Boolean estaRevalidando) {
		this.estaRevalidando = estaRevalidando;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdBeca() {
		return idBeca;
	}

	public void setIdBeca(Integer idBeca) {
		this.idBeca = idBeca;
	}

	public Set<PagoDiverso> getPagosDiversos() {
		return pagosDiversos;
	}

	public void setPagosDiversos(Set<PagoDiverso> pagosDiversos) {
		this.pagosDiversos = pagosDiversos;
	}

	@Override
	public String toString() {
		return "FichaAcademica [\nid=" + id + "\nalumno=" + alumno + "\ncarrera="
				+ carrera + "\nestaRevalidando=" + estaRevalidando
				+ "\ncicloEscolar=" + cicloEscolar + "\nnivelActual="
				+ nivelActual + "\nfechaRegistro=" + fechaRegistro + "\n]";
	}
}
