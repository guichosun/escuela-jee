package mx.qr.sace.ce.negocio;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.FichaAcademica;

/**
 * Define el negocio en el proceso de inscripion de un alumno.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
@Local
public interface ProcesoInscripcionLocal {

	/**
	 * Para realizar el proceso de inscripcion, que basicamente se resume en: <br/>
	 * + Registrar los datos de salud.<br/>
	 * + Registrar documentos entregados.<br/>
	 * + Cambiar el estatus del Alumno a estudiante. 
	 */
	public void hacerInscripcion(Alumno alumnoPorInscribir, FichaAcademica fAAlumno,
			boolean[] docsEntregados) throws ApplicationException;

	public void actualizarDatosInscripcion();
	
	public void calcelaIscripcion();
	
	public void verInscripcion();
}