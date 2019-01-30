package mx.qr.sace.ca.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.ca.negocio.RegistroCicloEscolarLocal;
import mx.qr.sace.persistencia.PeriodoEscolar;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.CicloEscolarDAO;
import mx.qr.sace.persistencia.dao.PeriodoEscolarDAO;
import mx.qr.sace.persistencia.dao.impl.CicloEscolarDAOImpl;
import mx.qr.sace.persistencia.dao.impl.PeriodoEscolarDAOImpl;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

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
@Local(RegistroCicloEscolarLocal.class)
@Stateless(name = "RegistroCicloEscolarEJB", 
	mappedName="java:global/sace-ear/sace-controlacademico-ejb/RegistroCicloEscolarEJB")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RegistroCicloEscolarEJB implements RegistroCicloEscolarLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@JPADAO
	private AlumnoDAO alumnoDAO;
	
	@JPADAO
	private CicloEscolarDAO cicloDAO;
	
	@JPADAO
	private PeriodoEscolarDAO peDAO;
	
	@PostConstruct
	public void init() {
		cicloDAO = new CicloEscolarDAOImpl(em);
		peDAO = new PeriodoEscolarDAOImpl(em);
	}

	@Override
	public void registra(CicloEscolar ciclo, List<PeriodoEscolar> periodos) 
			throws ApplicationException {
		
		// DONE Guardar el ciclo
		cicloDAO.guarda(ciclo);
		em.refresh(ciclo);
		
		peDAO.guarda(ciclo, periodos);
		
	}

	@Override
	public CicloEscolar consulta() throws ApplicationException {
		return null;
	}

	@Override
	public void modifica() throws ApplicationException {
	}

	@Override
	public void elimina() throws ApplicationException {
	}
}
