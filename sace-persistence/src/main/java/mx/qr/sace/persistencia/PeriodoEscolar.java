package mx.qr.sace.persistencia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Mayo 2016
 * @copyright Q & R
 */
@Entity
@Table(name = "periodos_escolares", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class PeriodoEscolar extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396250511520464541L;
	private int idPeriodo;
	private CicloEscolar cicloEscolar;
	private String nombre;
	private String estatus;
	private Date fechaInicio;
	private Date fechaTermino;

	public PeriodoEscolar() {
	}
	
	/**
	 * @param idPeriodo
	 */
	public PeriodoEscolar(int idPeriodo) {
		super();
		this.idPeriodo = idPeriodo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_periodo", unique = true, nullable = false)
	public int getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ciclo", nullable = false)
	public CicloEscolar getCicloEscolar() {
		return this.cicloEscolar;
	}

	public void setCicloEscolar(CicloEscolar ciclosEscolares) {
		this.cicloEscolar = ciclosEscolares;
	}

	@Column(name = "nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", length = 9)
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio", nullable = false, length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_termino", nullable = false, length = 10)
	public Date getFechaTermino() {
		return this.fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
}
