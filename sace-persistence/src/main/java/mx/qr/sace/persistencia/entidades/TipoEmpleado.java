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
@Table(name = "c_tipos_empleado", catalog = "SACE_DB")
public class TipoEmpleado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8182453595818712594L;
	private int idTipoEmpleado;
	private String descripcion;

	public TipoEmpleado() {
	}

	public TipoEmpleado(String descripcion) {
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_tipo_empleado", unique = true, nullable = false)
	public int getIdTipoEmpleado() {
		return this.idTipoEmpleado;
	}

	public void setIdTipoEmpleado(int idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}

	@Column(name = "descripcion", nullable = false, length = 45)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + idTipoEmpleado;
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
		TipoEmpleado other = (TipoEmpleado) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idTipoEmpleado != other.idTipoEmpleado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoEmpleado [idTipoEmpleado=" + idTipoEmpleado
				+ ", descripcion=" + descripcion + "]";
	}
}
