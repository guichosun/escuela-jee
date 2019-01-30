package mx.qr.sace.marketing.negocio;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroAsociadoException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.sace.core.ContenedorBytesPdf;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Comentario;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.Modalidad;
import mx.qr.sace.persistencia.entidades.ResponsablePago;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;
import mx.qr.sace.persistencia.entidades.TramiteCarrera;

/**
 * La interface de negocio (business interface) para el registro de prospectos.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Septiembre 2015
 * @copyright Q & R
 */
@Local
public interface RegistroProspectoLocal {

	/**
	 * Registra al alumno, se crea la ficha academica, se registra al responsable del pago asi
	 * como los datos laborales y los comentarios.
	 * 
	 * Aclarando que este alumno se registra como un alimno de tipo prospecto.
	 * 
	 * @param preospecto
	 * @param fichaAcademica
	 * @param responsablePago
	 * @param comentario
	 * @throws ApplicationException
	 */
	public void registro(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago, Comentario comentario, 
			RespuestaAPregunta respuestaAPregMKT) 
					throws ApplicationException;
	
	/**
	 * Registra al alumno con una beca seleccionada, el monto de la mensualidad y de la inscripción.
	 * 
	 * Asi como tambien los comentarios hechos por las vendedoras t los tramites que
	 * llevara el presupuesto.
	 * 
	 * @param preospecto
	 * @param fichaAcademica
	 * @param becaSeleccionada
	 * @param montoTotalIns
	 * @param montoTotalMens
	 * @param comentario
	 * @param tramitesAPagar
	 * @throws ApplicationException
	 */
	public void registro(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago,
			Beca becaSeleccionada, Float montoTotalIns, Float montoTotalMens,
			Comentario comentario, Set<TramiteCarrera> tramitesAPagar,
			RespuestaAPregunta respuestaAPregMKT) throws ApplicationException;

	/**
	 * Obtiene a toos los alumnos prospectos de todas las carreras
	 * de la <code>escolaridad</code> y la <code>modalidad</code>
	 * 
	 * @param escolaridad
	 * @param modalidad
	 * @return Todos los alumnos prospectos, de lo contrario vacia.
	 */
	public List<Alumno> obtenPor(Escolaridad escolaridad, Modalidad modalidad);
	
	/**
	 * Modifica el registro de un alumno PROSPECTO o CANDIDATO
	 * 
	 * Se podran modificar los datos personales
	 * Se podra pasar del estatus CANDIDATO a PROSPECTO 
	 * (cuando pase este caso la informacion del presupuesto no se vera afectada, 
	 * se veria como que un PROSPECTO tiene presupuesto) y viceversa
	 * 
	 */
	public void modifica(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago, Beca becaSeleccionada,
			Float montoTotalIns, Float montoTotalMens,
			Comentario comentario, Set<TramiteCarrera> tramitesAPagar, RespuestaAPregunta respuestaAPregMKT) 
					throws RegistroAsociadoException, RegistroNoEncontradoException, ApplicationException;
	
	public void modifica(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago, Comentario comentario, 
			RespuestaAPregunta respuestaAPregMKT) 
					throws RegistroAsociadoException, RegistroNoEncontradoException, ApplicationException;
	
	/**
	 * Cencela a un prospecto guardando el motivo por el cual se cancela
	 * 
	 * @param alumno
	 * @param rAp
	 * @throws ApplicationException
	 */
	public void cancela(Alumno alumno, RespuestaAPregunta rAp) throws ApplicationException;
	
	/**
	 * Elabora la ficha de pago con el presupuesto del prospecto.
	 * 
	 * @param prospecto El Prospecto al que se hara la ficha del presupuesto.
	 * @param fichaAcademica La ficha academica con la que se efectuara la ficha del presupuesto.
	 * @return
	 * @throws ApplicationException
	 */
	public ContenedorBytesPdf elaboraFichaPagoPresupuesto(
			Alumno prospecto, FichaAcademica fichaAcademica) throws ApplicationException;
}
