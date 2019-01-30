package mx.qr.sace.ce.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.PagoDiverso;

/**
 * Define el negocio del manager que será el encargado de llevar los procesos que se
 * efectuan sobre una fichaAcademica.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
@Local
public interface ManagerDeFichaAcademicaLocal {

	/**
	 * Recupera un ficha en especifico de un alumno
	 * 
	 * @param alumno El alumno que solicita la FA
	 * @param carrera La carrera a la que pertenece la FA.
	 * 
	 * @return
	 * @throws RegistroNoEncontradoException
	 */
	public FichaAcademica recuperaFichaDelAlumno(Alumno alumno, Carrera carrera) 
			throws RegistroNoEncontradoException;
	
	/**
	 * Recupera todas la FA asociadas a un Alumno.
	 * @return
	 */
	public List<FichaAcademica> recuperaFichasAcademicas();

	/**
	 * Recupera todos los pagos asociados a una ficha academica.
	 * 
	 * @param fA La ficha Academica asociada al pago
	 * @return Todos los <code>PagoDiverso</code> que tiene la ficha academica
	 * @throws RegistroNoEncontradoException De no tener ningun pago asociado la fA
	 */
	public List<PagoDiverso> recuperaPagosDiversosDeLaFicha(FichaAcademica fA)
			throws RegistroNoEncontradoException;
}