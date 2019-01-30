package mx.qr.sace.marketing.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Comentario;

/**
 * Define el comportamiento necesario para recuperar los comentarios o el comentario realizado
 * a un alumno prospecto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Octubre 2015
 * @copyright Direccion de sistemas - IFE
 */
@Local
public interface ComentariosProspectoLocal {

	/**
	 * Recupera el comentario hecho a un alumno prospecto
	 * @param prospecto
	 * @return De no tener un comentario asociado el prospecto regresará null.
	 */
	public Comentario recuperaDeUnProspecto(Alumno prospecto);
	
	/**
	 * Recupera la pila de comentarios realizados al prospescto
	 * 
	 * @param prospecto
	 * @return
	 */
	public List<Comentario> recuperaTodosDeUnProspecto(Alumno prospecto);
}
