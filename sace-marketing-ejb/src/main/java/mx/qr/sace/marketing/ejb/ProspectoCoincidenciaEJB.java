package mx.qr.sace.marketing.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.marketing.negocio.BuscadorCoincidenciasLocal;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.FichaPagoDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.FichaPagoDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Carrera;

/**
 * Session Bean implementation class BuascadorCoincidenciaEJB
 */
@Local(BuscadorCoincidenciasLocal.class)
@Stateless(name = "ProspectoCoincidenciaEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/ProspectoCoincidenciaEJB")
@TransactionAttribute(TransactionAttributeType.NEVER)
public class ProspectoCoincidenciaEJB implements BuscadorCoincidenciasLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;

	// @Inject
	@JPADAO
	private FichaPagoDAO fichaPagoDAO;
	
	@JPADAO
	private AlumnoDAO alumnoDAO;
	
	@PostConstruct
	public void initDependencias() {
		alumnoDAO = new AlumnoDAOImpl(em);
		fichaPagoDAO = new FichaPagoDAOImpl(em);
	}
	
	/**
     * @see BuscadorCoincidenciasLocal#recuperaCoincidencias(SistemaEstudio, String)
     */
    public List<Alumno> recuperaCoincidencias(SistemaEstudio sisEstudios,
    		Carrera carrera, EstatusAlumno estatus, String cadena) {

    	List<Alumno> list = alumnoDAO.obtenPorCadena(sisEstudios, cadena, estatus, carrera);
//		
//		Collections.sort(list, new AspiranteComparator());
		
		return list;
    }

}
