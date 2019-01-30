package mx.qr.sace.ca.negocio;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Alumno;

/**
 * La interface de negocio (business interface) para el registro de de los alumnos inscritos en el instituto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@Local
public interface RegistroAlumnoLocal {

	/**
	 * 
	 */
	public void modifica();
	
	/**
	 * 
	 */
	public void elimina();
	
	/**
	 * Recupera a un Alumno prospecto mediante el sistema de estudios y su matricula.
	 * 
	 * @param sisEstudio
	 * @param matricula
	 * @return
	 * @throws ApplicationException
	 */
	public Alumno recuperaPorMatricula(SistemaEstudio sisEstudio, String matricula) throws ApplicationException;
}
