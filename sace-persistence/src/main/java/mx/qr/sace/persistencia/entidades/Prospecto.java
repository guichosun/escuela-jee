package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
//@Entity
//@Table(name = "prospectos", catalog = "SACE_DB")
//@AttributeOverrides({
//	@AttributeOverride(name = "usuario", 
//		column = @Column(name = "usuario", nullable = false, length = 45)),
//	@AttributeOverride(name = "fechaHora", 
//		column = @Column(name = "fecha_hora", nullable = false))
//})
public class Prospecto extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398538125081650245L;
	
//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name = "id_prospecto", unique = true, nullable = false)
//	private int idProspecto;
//	
//	@Column(name = "matricula", length = 45)
//	private String matricula;
//	
//	@Column(name = "esta_revalidando")
//	private Boolean estaRevalidando;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "fecha_registro", nullable = false)
//	private Date fechaRegistro;
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "estatus")
//	private EstatusAlumno estatus;
//
//	@Column(name = "observaciones", length = 300)
//	private String observaciones;
//
//	public Prospecto() {
//	}
//
//	public int getIdProspecto() {
//		return this.idProspecto;
//	}
//
//	public void setIdProspecto(int idProspecto) {
//		this.idProspecto = idProspecto;
//	}
//
//	public String getMatricula() {
//		return this.matricula;
//	}
//
//	public void setMatricula(String matricula) {
//		this.matricula = matricula;
//	}
//	
//	public Boolean getEstaRevalidando() {
//		return this.estaRevalidando;
//	}
//
//	public void setEstaRevalidando(Boolean estaRevalidando) {
//		this.estaRevalidando = estaRevalidando;
//	}
//
//	public EstatusAlumno getEstatus() {
//		return this.estatus;
//	}
//
//	public void setEstatus(EstatusAlumno estatus) {
//		this.estatus = estatus;
//	}
//
//	public String getObservaciones() {
//		return observaciones;
//	}
//
//	public void setObservaciones(String observaciones) {
//		this.observaciones = observaciones;
//	}
//
//	public Date getFechaRegistro() {
//		return fechaRegistro;
//	}
//
//	public void setFechaRegistro(Date fechaRegistro) {
//		this.fechaRegistro = fechaRegistro;
//	}
//	
//	
}
