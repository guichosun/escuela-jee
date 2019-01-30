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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;
import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "empleados", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class Empleado extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726249423999434069L;
	private int numeroEmpleado;
	private PuestoEmpleado puestoEmpleado;
	private TipoEmpleado tipoEmpleado;
	private DatosPersona datoPersona;
	private Date fechaIngreso;
	private EstatusRegistro estatus;

	public Empleado() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "numero_empleado", unique = true, nullable = false)
	public int getNumeroEmpleado() {
		return this.numeroEmpleado;
	}

	public void setNumeroEmpleado(int numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto", nullable = false)
	public PuestoEmpleado getPuestoEmpleado() {
		return this.puestoEmpleado;
	}

	public void setPuestoEmpleado(PuestoEmpleado puestoEmpleado) {
		this.puestoEmpleado = puestoEmpleado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_empleado", nullable = false)
	public TipoEmpleado getTipoEmpleado() {
		return this.tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dato_personal", nullable = false)
	public DatosPersona getDatoPersona() {
		return this.datoPersona;
	}

	public void setDatoPersona(DatosPersona datoPersonal) {
		this.datoPersona = datoPersonal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso", nullable = false, length = 10)
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "estatus")
	public EstatusRegistro getEstatus() {
		return this.estatus;
	}

	public void setEstatus(EstatusRegistro estatus) {
		this.estatus = estatus;
	}
}
