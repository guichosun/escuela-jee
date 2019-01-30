package mx.qr.sace.persistencia.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.sace.dominio.EntidadBase;

/**
 */
@Entity
@Table(name = "becas", catalog = "SACE_DB")
@AttributeOverrides({
	@AttributeOverride(name = "usuario", 
		column = @Column(name = "usuario", nullable = false, length = 45)),
	@AttributeOverride(name = "fechaHora", 
		column = @Column(name = "fecha_hora", nullable = false))
})
@NamedQueries({
	@NamedQuery(name="Beca.modifica",
	query="UPDATE Beca b SET b.nombre = :nombre, b.descripcion= :desc, b.estatus = :estatus WHERE b.idBeca = :id "),
})
public class Beca extends EntidadBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1488254102757427551L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_beca", unique = true, nullable = false)
	private int idBeca;
	
	@Column(name = "nombre", length = 45)
	private String nombre;
	
	@Column(name = "descripcion", length = 300)
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estatus")
	private EstatusRegistro estatus;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "beca")	
	private Set<Descuento> descuentos = new HashSet<Descuento>(0);

	public Beca() {
	}

	public int getIdBeca() {
		return this.idBeca;
	}

	public void setIdBeca(int idBeca) {
		this.idBeca = idBeca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstatusRegistro getEstatus() {
		return this.estatus;
	}

	public void setEstatus(EstatusRegistro estatus) {
		this.estatus = estatus;
	}

	public Set<Descuento> getDescuentos() {
		return this.descuentos;
	}

	public void setDescuentos(Set<Descuento> descuentos) {
		this.descuentos = descuentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idBeca;
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
		Beca other = (Beca) obj;
		if (idBeca != other.idBeca)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Beca [idBeca=" + idBeca + ", nombre=" + nombre + ", estatus="
				+ estatus + "]";
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
