package mx.qr.sace.core.negocio;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Alumno;

/**
 * La interface de negocio (business interface) para recuperar a un alumno en sus diferentes estados
 * PROSPECTO, CANDIDATO, INSCRITO, ALUMNO
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@Local
public interface BuscarAlumnoLocal {

	
	/**
	 * Recupera a un Alumno prospecto mediante el sistema de estudios y su matricula.
	 * 
	 * @param sisEstudio
	 * @param matricula
	 * @return
	 * @throws ApplicationException
	 */
	public Alumno recuperaPorMatricula(SistemaEstudio sisEstudio,
			EstatusAlumno estatus, String matricula) throws ApplicationException;
}
