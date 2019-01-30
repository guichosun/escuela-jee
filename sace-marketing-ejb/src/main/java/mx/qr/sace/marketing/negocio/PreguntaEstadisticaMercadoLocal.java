package mx.qr.sace.marketing.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Pregunta;
import mx.qr.sace.persistencia.entidades.Respuesta;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;

/**
 * Define el comportamiento necesario para recuperar las Respuestas a las preguntas que sirven 
 * como estadisticas para el area de mercadotecnia.
 * 																																																		
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Diciembre 2015
 * @copyright Direccion de sistemas - IFE
 */
@Local
public interface PreguntaEstadisticaMercadoLocal {
	
	/**
	 * Recupera todas las respuestas de una pregunta de Mercado
	 * @return
	 */
	public List<Respuesta> recuperaRespuestasAPregunta(Pregunta pregunta);
	
	/**
	 * Recupera la respuesta que tiene asuciada un alumno a una pregunta que le hicieron.
	 * 
	 * @return
	 * @throws RegistroNoEncontradoException
	 */
	public Respuesta recuperaRespuestaDelAlumno(Alumno alumno, Pregunta pregunta) 
			throws RegistroNoEncontradoException;
	
	/**
	 * Recupera la RespuestaAPregunta realizada a un alumno
	 * 
	 * @param alumno
	 * @param pregunta
	 * @return
	 * @throws RegistroNoEncontradoException
	 */
	public RespuestaAPregunta recuperaRespuestaAPreguntaDelAlumno(Alumno alumno, Pregunta pregunta)
			throws RegistroNoEncontradoException;
	
	/**
	 * Modifica la respuesta al motivo de cancelacion de un alumno.
	 * 
	 * @param alumnoCancel
	 * @param respuestaNueva
	 * @throws ApplicationException
	 */
	public void modificaRespuesta(Alumno alumnoCancel, RespuestaAPregunta respuestaNueva)
			throws ApplicationException;
	
	/**
	 * Registra una respuesta de una pregunta realizada.
	 * 
	 * @param alumno
	 * @param idPregunta identificador de la pregunta realizada.
	 * @param respuesta
	 * @throws ApplicationException
	 */
	public void registraRespuestaAPregunta(Alumno alumno, 
			int idPregunta, int idRespuesta) 
					throws ApplicationException;
}
