package mx.qr.sace.ce.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.ce.negocio.ManagerDeFichaAcademicaLocal;
import mx.qr.sace.core.ConstantesSACE;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.DatoGeneralDAO;
import mx.qr.sace.persistencia.dao.FichaAcademicaDAO;
import mx.qr.sace.persistencia.dao.PagoDiversoDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.FichaAcademicaDAOImpl;
import mx.qr.sace.persistencia.dao.impl.PagoDiversoDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.PagoDiverso;

import org.apache.log4j.Logger;

/**
 * Session Bean implementation class ManagerDeFichaAcademicaEJB
 */
@Local(ManagerDeFichaAcademicaLocal.class)
@Stateless(name="ManagerDeFichaAcademicaEJB",
	mappedName = "java:global/sace-ear/sace-controlescolar-ejb/ManagerDeFichaAcademicaEJB")
@TransactionAttribute(TransactionAttributeType.NEVER)
public class ManagerDeFichaAcademicaEJB implements ManagerDeFichaAcademicaLocal {

	private static final Logger log = Logger.getLogger(ManagerDeFichaAcademicaEJB.class);
	
	@PersistenceContext(unitName = "sacePU")
	private EntityManager em;
	
	@JPADAO
	private AlumnoDAO alumnoDAO;
	
	@JPADAO
	private FichaAcademicaDAO faDAO;
	
	@JPADAO
	private DatoGeneralDAO datoGralDAO;
	
	@JPADAO
	private PagoDiversoDAO pagoDAO;

	@PostConstruct
	public void init() {
		alumnoDAO = new AlumnoDAOImpl(em);
		datoGralDAO = new DatoGeneralDAO(em);
		faDAO = new FichaAcademicaDAOImpl(em);
		pagoDAO = new PagoDiversoDAOImpl(em);
	}
	
	/**
     * @see ManagerDeFichaAcademicaLocal#recuperaFichaDelAlumno(Alumno, Carrera)
     */
    public FichaAcademica recuperaFichaDelAlumno(Alumno alumno, Carrera carrera) 
    		throws RegistroNoEncontradoException {
    	
        // DONE Obtener la ficha academica del DAO 
    	List<FichaAcademica> list = faDAO.obtieneTodoPorCriteria(null, alumno, carrera);
    	if(list.isEmpty()) {
    		log.warn("No hay fichas registradas");
    		throw new RegistroNoEncontradoException("No hay fichas registradas"); 
    	}
    	// OJO! ESTOY SUPONIENDO QUE SOLO HABRA UNA FA
		return list.get(0);
    }

	/**
     * @see ManagerDeFichaAcademicaLocal#recuperaFichasAcademicas()
     */
    public List<FichaAcademica> recuperaFichasAcademicas() {
        // TODO OBTENER TODAS LAS FA QUE TIENE UN ALUMNO
		return null;
    }

	@Override
	public List<PagoDiverso> recuperaPagosDiversosDeLaFicha(FichaAcademica fA)
			throws RegistroNoEncontradoException {
		// TODO Recuperar todos los PagoDiverso asociados a una FA.
		List<PagoDiverso> lista = pagoDAO.obtieneTodoPorCriteria(fA.getId());
		if(lista.isEmpty()) {
    		log.warn("No tiene pagos asociados la ficha: "+fA.getId());
    		throw new RegistroNoEncontradoException("No tiene pagos la ficha academica", 
    				ConstantesSACE.ERROR_RECUPERABLE); 
    	}
		return lista;
	}
}
