package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 */
@Entity
@Table(name = "c_puestos_empleado", catalog = "SACE_DB")
public class PuestoEmpleado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1957919667286142653L;
	private int idPuesto;
	private String descripcion;
	private String tipoPago;
	private String sueldoPorHora;

	public PuestoEmpleado() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_puesto", unique = true, nullable = false)
	public int getIdPuesto() {
		return this.idPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}

	@Column(name = "descripcion", nullable = false, length = 45)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "tipo_pago", nullable = false, length = 9)
	public String getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	@Column(name = "sueldo_por_hora", length = 45)
	public String getSueldoPorHora() {
		return this.sueldoPorHora;
	}

	public void setSueldoPorHora(String sueldoPorHora) {
		this.sueldoPorHora = sueldoPorHora;
	}
}
