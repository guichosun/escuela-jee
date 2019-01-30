package mx.qr.sace.marketing.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroAsociadoException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.core.persistencia.TipoModulo;
import mx.qr.core.util.UtileriaRecursos;
import mx.qr.sace.core.ContenedorBytesPdf;
import mx.qr.sace.marketing.negocio.PreguntaEstadisticaMercadoLocal;
import mx.qr.sace.marketing.negocio.PropuestaDeMercadoLocal;
import mx.qr.sace.marketing.negocio.RegistroProspectoLocal;
import mx.qr.sace.marketing.util.UtilMarketing;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.RespuestaAPreguntaDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.RespuestaAPreguntaDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Comentario;
import mx.qr.sace.persistencia.entidades.DatoHistAcademico;
import mx.qr.sace.persistencia.entidades.DatoLaboral;
import mx.qr.sace.persistencia.entidades.DatosPersona;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.Modalidad;
import mx.qr.sace.persistencia.entidades.ResponsablePago;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;
import mx.qr.sace.persistencia.entidades.TramiteCarrera;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

@Stateless(name = "RegistroProspectoEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/RegistroProspectoEJB")
public class RegistroProspectoEJB implements RegistroProspectoLocal {

	private static final Logger log = Logger.getLogger(RegistroProspectoEJB.class);
	
	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@EJB
	private PropuestaDeMercadoLocal beanVentas;
	
	@EJB
	private PreguntaEstadisticaMercadoLocal beanPreguntaEst;
	
	@JPADAO
	public AlumnoDAO prospectoDAO;
	
	@JPADAO
	public RespuestaAPreguntaDAO rApDAO;
	
	public RegistroProspectoEJB() {
	}
	
	@PostConstruct
	public void init() {
		prospectoDAO = new AlumnoDAOImpl(em);
		rApDAO = new RespuestaAPreguntaDAOImpl(em);
	}
	
	@Override
	public void registro(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago,
			Comentario comentario, RespuestaAPregunta respuestaAPregMKT) throws ApplicationException {
		
		registro(prospecto, fichaAcademica, responsablePago,
				null, null, null, comentario, null,
				respuestaAPregMKT);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registro(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago, Beca becaSeleccionada,
			Float montoTotalIns, Float montoTotalMens,
			Comentario comentario, Set<TramiteCarrera> tramitesAPagar, RespuestaAPregunta respuestaAPregMKT)
					throws ApplicationException {
		
		try {
			DatosPersona dp = prospecto.getDatoPersona();
	    	dp.setFechaHora(new Date());
	    	em.persist(dp);

	    	DatoHistAcademico dha = fichaAcademica.getDatoHistAcademico();
	    	dha.setFechaHora(new Date());
	    	em.persist(dha);
	    	
	    	// Agregar el Dato laboral del responsable
	    	DatoLaboral dl = responsablePago.getDatoLaboral();
	    	if(dl.getEmpresa()!=null) {
	    		Empresa emp = em.find(Empresa.class, dl.getEmpresa().getId());
	    		dl.setEmpresa(emp);
	    	}
	    	dl.setFechaHora(new Date());
	    	em.persist(dl);
	    	
	    	// Agregar al responsable de pago
	    	responsablePago.setFechaHora(new Date());
	    	em.persist(responsablePago);

	    	// Registra al alumno
	    	prospecto.setResponsable(responsablePago);
	    	prospecto.setFechaRegistro(new Date());
	    	prospecto.setFechaHora(new Date());
	    	em.persist(prospecto);
//	    	
	    	// DONE Generar la matricula del prospecto
	    	prospecto.setMatricula(UtilMarketing.obtenMatricula(prospecto, fichaAcademica));

	    	// Registrar la Ficha Academica
	    	// TODO Analizar lo de la revalidación
	    	fichaAcademica.setEstaRevalidando(false);
	    	fichaAcademica.setIdEscolaridad(fichaAcademica.getCarrera().getId().getIdEscolaridad());
	    	fichaAcademica.setIdModalidad(fichaAcademica.getCarrera().getId().getIdModalidad());
	    	fichaAcademica.setFechaRegistro(new Date());
	    	fichaAcademica.setCicloEscolar("2016-2016");
	    	fichaAcademica.setNivelActual(1);
	    	fichaAcademica.setAlumno(prospecto);
	    	fichaAcademica.setFechaHora(new Date());
	    	fichaAcademica.setIdBeca(
	    			becaSeleccionada != null ? becaSeleccionada.getIdBeca() : null);
	    	
	    	em.persist(fichaAcademica);
	    	
	    	// Agregar el Comentario relacionado al alumno
	    	comentario.setAlumno(prospecto);
	    	comentario.setModulo(TipoModulo.MKT);
	    	em.persist(comentario);
	    	
	    	// DONE Registrar la respuesta a la pregunta realizada.
	    	respuestaAPregMKT.setFechaHora(new Date());
	    	respuestaAPregMKT.setIdAlumno(prospecto.getIdAlumno());
	    	em.persist(respuestaAPregMKT);
//	    	beanPreguntaEst.registraRespuestaAPregunta(prospecto,
//	    			respuestaAPregMKT.getIdPregunta(), respuestaAPregMKT.getIdRespuesta());
	    	
	    	// Realizar el prsupuesto al CANDIDATO
	    	if(prospecto.getEstatus() == EstatusAlumno.CANDIDATO) {
	    		beanVentas.hacerPresupuesto(prospecto.getMatricula(), fichaAcademica, 
	    				becaSeleccionada,
	    				montoTotalIns, montoTotalMens, tramitesAPagar);
			} 
    	
		} catch(PersistenceException pe) {
			context.setRollbackOnly();
			log.error(pe);
			throw new ApplicationException("No se pudo realizar la operación. Codigo de error 1405");
		}
	}
	
	public void modifica(Alumno prospecto, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago,
			Comentario comentario, RespuestaAPregunta respuestaAPregMKT)
					throws RegistroAsociadoException, RegistroNoEncontradoException, ApplicationException {
		
		// Traer el registro previo guardado.
		// si en BD tiene CANDIDATO y se va a cambiar a PROSPECTO entonces
		//	- eliminar el presupuesto.
		// si en BD tiene PROSPECTO y se va a cambiar a CANDIDATO entonces
		//	- hacer el presupuesto
		// si en BD tiene CANDIDATO y trae CANDIDATO entonces
		//	- modificar todo (presupuesto)
		// si en BD tiene PROSPECTO y trae PROSPECTO entonce
		//	- modificar sencillo
		modifica(prospecto, fichaAcademica, responsablePago,
				null, null, null, comentario, null,
				respuestaAPregMKT);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void modifica(Alumno prospectoDespues, FichaAcademica fichaAcademica,
			ResponsablePago responsablePago, Beca becaSeleccionada,
			Float montoTotalIns, Float montoTotalMens,
			Comentario comentario, Set<TramiteCarrera> tramitesAPagar, RespuestaAPregunta respuestaAPregMKT) 
					throws RegistroAsociadoException, RegistroNoEncontradoException, ApplicationException {

		fichaAcademica.setBeca(becaSeleccionada);
		fichaAcademica.setIdBeca(
    			becaSeleccionada != null ? becaSeleccionada.getIdBeca() : null);
		em.merge(prospectoDespues.getDatoPersona());
		em.merge(fichaAcademica.getDatoHistAcademico());
		em.merge(responsablePago.getDatoLaboral());
		em.merge(fichaAcademica);
		em.merge(responsablePago);
		em.merge(comentario);
		em.flush();
		/*
		 * Traer la respuesta previa.
		 * - si la respuesta son iguales (la que esta en db y la que trae de la vista), 
		 * significa que no esta modificada la respuesta seleccionada entonces no hacer nada.
		 * 
		 * - Traer la RespuestAPregunta por medio de el idAlumno y el idRespuesta
		 * - Si existe no hacer nada.
		 * -Si no existe, hacer
		 * 	- Eliminar todos los registros mediante idAlumno, idPregunta=1 y el modulo MKT
		 * 	- Insertar la nueva RespuestAPregunta
		 */
		
		List<RespuestaAPregunta> raps = rApDAO.obtieneTodoPorCriteria(
				prospectoDespues.getIdAlumno(), respuestaAPregMKT.getIdPregunta());
		
		if(!raps.isEmpty()) {
			boolean guardar = false;
			for(RespuestaAPregunta rap : raps) {
				if(respuestaAPregMKT.getIdRespuesta() != rap.getIdRespuesta()) {
					em.remove(rap);
					guardar = true;
				}
			}
			if(guardar) {
				RespuestaAPregunta rap = new RespuestaAPregunta(1);
				rap.setIdRespuesta(respuestaAPregMKT.getIdRespuesta());
				rap.setUsuario(prospectoDespues.getUsuario());
				rap.setFechaHora(new Date());
		    	rap.setIdAlumno(prospectoDespues.getIdAlumno());
		    	em.persist(rap);
			}
		}
		
		// Para saber el estatus que trae antes de modificar
		Alumno prospectoAntes = em.find(Alumno.class, prospectoDespues.getIdAlumno());
		EstatusAlumno estatusViejo = prospectoAntes.getEstatus();
		
		em.merge(prospectoDespues);
		
		// Un PROSPECTO pasa a ser un PROSPECTO 
		// - No hubo cambio en el estado mas si en los datos. NO HAY NADA POR HACER EN EL PRESUPUESTO.
		
    	// Realizar el prsupuesto al CANDIDATO.
//    	if(prospectoDespues.getEstatus() == EstatusAlumno.CANDIDATO) {
//    		
//    		beanVentas.modificarPresupuesto(prospectoDespues.getMatricula(), fichaAcademica, 
//    				becaSeleccionada,
//    				montoTotalIns, montoTotalMens, tramitesAPagar);
//		}
		// Un CANDIDATO pasa a ser un PROSPECTO (o sea por contactar)
		//	- Se tiene que descartar el presupuesto que anteriormente ya hecho

		// Un CANDIDATO pasa a ser un CANDIDATO
		// - No hubo cambio en el estado mas si en los datos
    	if(estatusViejo == EstatusAlumno.CANDIDATO && prospectoDespues.getEstatus() == EstatusAlumno.CANDIDATO) {
    		
    		beanVentas.modificarPresupuesto(prospectoDespues.getMatricula(), fichaAcademica, 
				becaSeleccionada,
				montoTotalIns, montoTotalMens, tramitesAPagar);
    	} 

		// Un PROSPECTO pasa a ser un CANDIDATO
		//	- Se tiene que hacer el presupuesto
    	if(estatusViejo == EstatusAlumno.PROSPECTO && prospectoDespues.getEstatus() == EstatusAlumno.CANDIDATO) {
    		beanVentas.hacerPresupuesto(prospectoDespues.getMatricula(), fichaAcademica, 
				becaSeleccionada,
				montoTotalIns, montoTotalMens, tramitesAPagar);
    	}
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Alumno> obtenPor(Escolaridad escolaridad, Modalidad modalidad) {
		
		Query q = em.createQuery(AlumnoDAOImpl.qryTodosLosProspectos());
		
		q.setParameter("estatus", EstatusAlumno.PROSPECTO);
		if(escolaridad != null) {
			q.setParameter("idEscolaridad", escolaridad.getIdEscolaridad());
		}
		if(modalidad != null) {
			q.setParameter("idModalidad", modalidad.getIdModalidad());
		}
		
		return (List<Alumno>) q.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cancela(Alumno alumno, RespuestaAPregunta rAp)
			throws ApplicationException {
		/*
		 * - Traer al alumno persistente
		 * - cambiar el estatus a CANCELADO
		 * TODO Registrar el movimiento  en la tabla de movimientos.
		 * - registrar el motivo de la cancelacion.
		 */
		
		Alumno alPersistente = prospectoDAO.buscaPorId(alumno.getIdAlumno());
		alPersistente.setEstatus(EstatusAlumno.CANCELADO);
		
		rAp.setFechaHora(new Date());
		rApDAO.guarda(rAp);
		
	}

	@Override
	public ContenedorBytesPdf elaboraFichaPagoPresupuesto(Alumno prospecto,
			FichaAcademica fichaAcademica) throws ApplicationException {
		
		/* TODO Algoritmo para poder elaborar la ficha de pago 
		 * - Traer al Alumno de la matricula
		 * - Recuperar la ficha de pago de la ficha academica, ¿pero cual?
		 * - Saber si la ficha academica tiene beca 
		 */
		ContenedorBytesPdf cbp = null;
		
		try {
//		    JasperReport reporte = JasperCompileManager.compileReport(reportPath+File.separator+"hello.jrxml");
		    JasperReport reporte = (JasperReport) JRLoader.loadObject(
		    		UtileriaRecursos.obtieneRecursoComoStream("Formatos_compilados/fichasPresupuesto/Mktcomp.jasper"));
		    
		    HashMap<String, Object> params = new HashMap<String, Object>();
		    params.put("datoPersonal", prospecto.getDatoPersona());
		    params.put("carrera", fichaAcademica.getCarrera());
		    params.put("alumno", prospecto);

//		    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOStream);
//		    JasperRunManager.runReportToPdfStream(reporte, servletOStream, params, new JREmptyDataSource());
		    
		    return new ContenedorBytesPdf(reporte.getName(), 
		    		JasperRunManager.runReportToPdf(reporte, params, new JREmptyDataSource()));
		} catch(JRException e ) {
			e.printStackTrace();
		}
		return cbp;
	}
}
