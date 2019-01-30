package mx.qr.sace.persistencia.entidades;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mx.qr.core.persistencia.Entidad;

/**
 * La respuesta a la pregunta estadistica
 */
@Entity
@Table(name = "c_respuestas", catalog = "SACE_DB")
public class Respuesta implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846860987501422666L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idRespuesta", column = @Column(name = "id_respuesta", nullable = false)),
			@AttributeOverride(name = "idPregunta", column = @Column(name = "id_pregunta", nullable = false)) })
	private RespuestaId id;

	@Column(name = "descripcion", length = 100)
	private String descripcion;
	
	public Respuesta() {
	}


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
		Respuesta other = (Respuesta) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public void setUsuario(String usuario) {
	}


	@Override
	public void setFechaHora(Date fecha) {
	}


	public RespuestaId getId() {
		return id;
	}


	public void setId(RespuestaId id) {
		this.id = id;
	}

}
