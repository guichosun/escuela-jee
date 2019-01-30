package mx.qr.sace.marketing.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.sace.marketing.negocio.PreguntaEstadisticaMercadoLocal;
import mx.qr.sace.persistencia.dao.RespuestaAPreguntaDAO;
import mx.qr.sace.persistencia.dao.RespuestaDAO;
import mx.qr.sace.persistencia.dao.impl.RespuestaAPreguntaDAOImpl;
import mx.qr.sace.persistencia.dao.impl.RespuestaDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Pregunta;
import mx.qr.sace.persistencia.entidades.Respuesta;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;
import mx.qr.sace.persistencia.entidades.RespuestaId;

/**
 * Session Bean implementation class CatalogosMarketingEJB.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@Local(PreguntaEstadisticaMercadoLocal.class)
@Stateless(name = "PreguntaEstadisticaMercadoEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/PreguntaEstadisticaMercadoEJB")
@TransactionAttribute(TransactionAttributeType.NEVER)
public class PreguntaEstadisticaMercadoEJB implements PreguntaEstadisticaMercadoLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;

	private RespuestaDAO respuestaDAO;
	
	private RespuestaAPreguntaDAO rApDAO;
	
	@PostConstruct
	public void init() {
		respuestaDAO = new RespuestaDAOImpl(em);
		rApDAO = new RespuestaAPreguntaDAOImpl(em);
	}
	
	@Override
	public List<Respuesta> recuperaRespuestasAPregunta(Pregunta pregunta) {
		// DONE Traer todas las respuestas a la pregunta 1
		return respuestaDAO.obtieneTodoPorCriteria(null, pregunta.getId());
	}

	@Override
	public Respuesta recuperaRespuestaDelAlumno(Alumno alumno, Pregunta pregunta)
			throws RegistroNoEncontradoException {
		RespuestaAPregunta rap = recuperaRespuestaAPreguntaDelAlumno(alumno, pregunta);
		return rap.getRespuesta();			
	}
	
	public RespuestaAPregunta recuperaRespuestaAPreguntaDelAlumno(Alumno alumno, Pregunta pregunta)
			throws RegistroNoEncontradoException {
		
		// TODO Podria ser traer solo las activas...
		List<RespuestaAPregunta> res = rApDAO.obtieneTodoPorCriteria(alumno.getIdAlumno(), pregunta.getId());
		if(!res.isEmpty()) {
			// Esto es un riesgo.
			return res.get(0);			
		} else {
			throw new RegistroNoEncontradoException("No hay ninguna respuesta asociada");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void modificaRespuesta(Alumno alumnoCancel, RespuestaAPregunta respuestaNueva)
			throws ApplicationException {
		
		//Obtener la RAP a modificar.
		RespuestaAPregunta rapAModificar = null;
		List<RespuestaAPregunta> raps = rApDAO.obtieneTodoPorCriteria(alumnoCancel.getIdAlumno(), 
				2);
		
		if(!raps.isEmpty()) {
			rapAModificar = raps.get(0);
		} else {
			throw new RegistroNoEncontradoException("No hay ninguna respuesta asociada");
		}

		rapAModificar.setIdRespuesta(respuestaNueva.getIdRespuesta());
		
		rApDAO.modifica(rapAModificar);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void registraRespuestaAPregunta(Alumno alumno, int idPregunta,
			int idRespuesta) throws ApplicationException {
		// TODO Auto-generated method stub
		Respuesta respPersis = respuestaDAO.buscaPorId(new RespuestaId(idRespuesta, idPregunta));
//		em.persist(arg0);
	}

}
