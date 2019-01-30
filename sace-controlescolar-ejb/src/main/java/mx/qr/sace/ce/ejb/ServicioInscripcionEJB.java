package mx.qr.sace.ce.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.ce.bo.ManagerInscripcion;
import mx.qr.sace.ce.negocio.ProcesoInscripcionLocal;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.DatoGeneralDAO;
import mx.qr.sace.persistencia.dao.FichaAcademicaDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.FichaAcademicaDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.FichaAcademica;

/**
 * Implementacio del bean de negoco para el proceso de inscripcion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
@Local(ProcesoInscripcionLocal.class)
@Stateless(name = "ServicioInscripcionEJB", 
	mappedName="java:global/sace-ear/sace-controlescolar-ejb/ServicioInscripcionEJB")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ServicioInscripcionEJB implements ProcesoInscripcionLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@JPADAO
	private AlumnoDAO alumnoDAO;
	
	private DatoGeneralDAO datoGralDAO;
	
	@JPADAO
	private FichaAcademicaDAO fADAO;
	
	private ManagerInscripcion managerInscripcion;
	
	@PostConstruct
	public void init() {
		alumnoDAO = new AlumnoDAOImpl(em);
		managerInscripcion = new ManagerInscripcion(em);
		datoGralDAO = new DatoGeneralDAO(em);
		fADAO = new FichaAcademicaDAOImpl(em);
	}

	/**
     * @see ProcesoInscripcionLocal#hacerInscripcion()
     */
    public void hacerInscripcion(Alumno alumnoPorInscribir, FichaAcademica fAAlumno,
    		boolean[] docsEntregados) throws ApplicationException {
    	/*
		 * Proceso de inscripción base en nuevo ingreso. Una sola transaccion
		 * Esto es:
		 * + Registrar los datos de salud.
		 * + Registrar documentos entregados.
		 * + Cambiar el estatus del Alumno a estudiante 
		 */
    	
    	FichaAcademica fAManaged = fADAO.buscaPorId(fAAlumno.getId());
    	// + Registrar los datos de salud.
    	datoGralDAO.guardaDatoSalud(fAAlumno.getDatoSalud());
    	fAManaged.setDatoSalud(fAAlumno.getDatoSalud());
    	
    	// + Registrar documentos entregados.
    	managerInscripcion.guardaDocumentosEntregan(
    			alumnoPorInscribir.getDatoPersona(), docsEntregados);
    	
    	// + Cambiar el estatus del Alumno a estudiante
    	Alumno alumnoManaged = alumnoDAO.buscaPorId(alumnoPorInscribir.getIdAlumno());
    	alumnoManaged.setEstatus(ManagerInscripcion.ALUMNO_ESTUDIANTE);
    }
    
	/**
     * @see ProcesoInscripcionLocal#verInscripcion()
     */
    public void verInscripcion() {
        // TODO Auto-generated method stub
    }

	/**
     * @see ProcesoInscripcionLocal#calcelaIscripcion()
     */
    public void calcelaIscripcion() {
        // TODO Auto-generated method stub
    }

	@Override
	public void actualizarDatosInscripcion() {
		// TODO Auto-generated method stub
		
	}

}
