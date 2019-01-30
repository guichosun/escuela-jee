package mx.qr.sace;

import java.io.Serializable;

import mx.qr.sace.persistencia.entidades.Alumno;

/**
 * Es la alerta visual de un alumno prospecto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
public class AlertaVisualProspecto implements Serializable {

	private Alumno prospecto;
	
	private String comentario;

	public Alumno getProspecto() {
		return prospecto;
	}

	public void setProspecto(Alumno prospecto) {
		this.prospecto = prospecto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
