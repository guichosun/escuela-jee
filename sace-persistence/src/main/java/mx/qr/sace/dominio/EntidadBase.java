package mx.qr.sace.dominio;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.qr.core.persistencia.Entidad;

/**
 */
@MappedSuperclass
public class EntidadBase implements java.io.Serializable, Entidad {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7384774119680720552L;
	
	protected String usuario;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaHora;
	
	/**
	 * 
	 */
	public EntidadBase() {
		super();
	}

	/**
	 * Construye a la entidad con el usuario que lo esta manipulando
	 * @param usuario
	 */
	public EntidadBase(String usuario) {
		this();
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		EntidadBase other = (EntidadBase) obj;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
}
