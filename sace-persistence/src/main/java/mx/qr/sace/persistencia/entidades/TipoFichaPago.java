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
@Table(name = "c_tipos_ficha_pago", catalog = "SACE_DB")
public class TipoFichaPago implements java.io.Serializable {

	private static final long serialVersionUID = 8182453596668712594L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_tipo_ficha", unique = true, nullable = false)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;

	public TipoFichaPago() {
	}

	public TipoFichaPago(int id) {
		this();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		TipoFichaPago other = (TipoFichaPago) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoFichaPago [id=" + id + ", nombre=" + nombre + "]";
	}
}
