package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;
import mx.qr.sace.dominio.EntidadBase;

/**
 * Una empresa
 */
@Entity
@Table(name = "c_empresas", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
public class Empresa extends EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846860987501422666L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_empresa", unique = true, nullable = false)
	private int id;

	@Column(name = "nombre", length = 100)
	private String nombre;
	
	public Empresa() {
	}
	
	/**
	 * @param id
	 */
	public Empresa(int id) {
		this();
		this.id = id;
	}

	/**
	 * @param id
	 * @param nombre
	 */
	public Empresa(int id, String nombre) {
		this(id);
		this.nombre = nombre;
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
		Empresa other = (Empresa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nombre=" + nombre + "]";
	}
}
