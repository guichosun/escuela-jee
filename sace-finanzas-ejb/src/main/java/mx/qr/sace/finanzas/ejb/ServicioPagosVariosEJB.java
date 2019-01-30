package mx.qr.sace.finanzas.ejb;

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
import mx.qr.sace.finanzas.negocio.RegistroPagosVariosLocal;
import mx.qr.sace.persistencia.dao.FichaAcademicaDAO;
import mx.qr.sace.persistencia.dao.PagoDiversoDAO;
import mx.qr.sace.persistencia.dao.PrecioConceptoDAO;
import mx.qr.sace.persistencia.dao.TramiteDAO;
import mx.qr.sace.persistencia.dao.impl.PagoDiversoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.PrecioConceptoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.TramiteDAOImpl;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.PagoDiverso;

/**
 * Implementacio del bean de negoco para el proceso de inscripcion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
@Local(RegistroPagosVariosLocal.class)
@Stateless(name = "ServicioPagosVariosEJB", 
	mappedName="java:global/sace-ear/sace-finanzas-ejb/ServicioPagosVariosEJB")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ServicioPagosVariosEJB implements RegistroPagosVariosLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@JPADAO
	private TramiteDAO tramiteDAO;
	
	@JPADAO
	private PrecioConceptoDAO precioConceptoDAO;
	
	@JPADAO
	private PagoDiversoDAO pagoDAO;
	
	@PostConstruct
	public void init() {
		tramiteDAO = new TramiteDAOImpl(em);
		precioConceptoDAO = new PrecioConceptoDAOImpl(em);
		pagoDAO = new PagoDiversoDAOImpl(em);
	}

	@Override
	public void realizaPago(PagoDiverso pago, FichaAcademica fichaAcademica)
			throws ApplicationException {
		// TODO Registrar el pago
		pago.setFichaAcademica(fichaAcademica);
		pagoDAO.guarda(pago);
		
		// OJO! Hay que evaluar si no es necesario notificar o impactar otra cosa al momento de registrar el pago
	}

	@Override
	public void actualizaPago() {
		
	}

	@Override
	public void calcelaPago() {
		
	}


}
