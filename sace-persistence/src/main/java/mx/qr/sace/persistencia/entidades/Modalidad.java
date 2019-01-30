package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 */
@Entity
@Table(name = "c_modalidades", catalog = "SACE_DB")
public class Modalidad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5030128418188590514L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_modalidad", unique = true, nullable = false)
	private int idModalidad;
	
	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modalidad")
	private Set<Carrera> carreras = new HashSet<Carrera>(0);
	
	public Modalidad() {

	}
	public Modalidad(int id) {
		this.idModalidad = id;
	}
	public Modalidad(int id, String descripcion) {
		this(id);
		this.descripcion = descripcion;
	}

	public int getIdModalidad() {
		return this.idModalidad;
	}

	public void setIdModalidad(int idModalidad) {
		this.idModalidad = idModalidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Carrera> getCarreras() {
		return carreras;
	}
	public void setCarreras(Set<Carrera> carreras) {
		this.carreras = carreras;
	}
}
