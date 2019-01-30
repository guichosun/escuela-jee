package mx.qr.sace.ca.ejb;

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
import mx.qr.sace.ca.negocio.InformanteCicloEscolarLocal;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.DatoGeneralDAO;
import mx.qr.sace.persistencia.dao.FichaAcademicaDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.FichaAcademicaDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.CarreraId;
import mx.qr.sace.persistencia.entidades.CicloEscolar;
import mx.qr.sace.persistencia.entidades.FichaAcademica;

/**
 * Implementacio del negoco para las operaciones:
 * + visualizar
 * + informar
 * acciones sobre un ciclo escolar.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Mayo 2016
 * @copyright Q & R
 */
@Local(InformanteCicloEscolarLocal.class)
@Stateless(name = "ManagerCicloEscolarEJB", 
	mappedName="java:global/sace-ear/sace-controlescolar-ejb/ManagerCicloEscolarEJB")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class InformanteCicloEscolarEJB implements InformanteCicloEscolarLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@JPADAO
	private AlumnoDAO alumnoDAO;
	
	private DatoGeneralDAO datoGralDAO;
	
	@JPADAO
	private FichaAcademicaDAO fADAO;
	
	@PostConstruct
	public void init() {
		alumnoDAO = new AlumnoDAOImpl(em);
		datoGralDAO = new DatoGeneralDAO(em);
		fADAO = new FichaAcademicaDAOImpl(em);
	}

	@Override
	public CicloEscolar verCicloDeCarrera(CarreraId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CicloEscolar> verCiclosDeCarrera(CarreraId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pedirPeriodosdeCiclo() {
		// TODO Auto-generated method stub
		
	}

}
