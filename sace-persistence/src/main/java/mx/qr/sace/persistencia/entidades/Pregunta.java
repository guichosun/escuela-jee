package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Una pregunta estadistica
 */
@Entity
@Table(name = "c_preguntas", catalog = "SACE_DB")
public class Pregunta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846860987501422666L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_pregunta", unique = true, nullable = false)
	private int id;

	@Column(name = "descripcion", length = 100)
	private String descripcion;
	
	public Pregunta() {
	}
	
	/**
	 * @param id
	 */
	public Pregunta(int id) {
		this();
		this.id = id;
	}



	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
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
		Pregunta other = (Pregunta) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
