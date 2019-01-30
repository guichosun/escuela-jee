package mx.qr.sace.marketing.ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.core.ConstantException;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.persistencia.EntidadDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.core.ConstantesSACE;
import mx.qr.sace.marketing.negocio.PropuestaDeMercadoLocal;
import mx.qr.sace.persistencia.dao.BecaDAO;
import mx.qr.sace.persistencia.dao.FichaPagoDAO;
import mx.qr.sace.persistencia.dao.impl.BecaDAOImpl;
import mx.qr.sace.persistencia.dao.impl.ConceptoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.FichaPagoDAOImpl;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.ConceptoFichaPago;
import mx.qr.sace.persistencia.entidades.ConceptoId;
import mx.qr.sace.persistencia.entidades.Descuento;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.FichaPago;
import mx.qr.sace.persistencia.entidades.TipoFichaPago;
import mx.qr.sace.persistencia.entidades.TramiteCarrera;

/**
 * Bean de impplementacion para la funcionalidad de propuesta de mercado.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@Stateless(name = "PropuestaDeMercadoEJB")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PropuestaDeMercadoEJB implements PropuestaDeMercadoLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;

	// @Inject
	@JPADAO
	private FichaPagoDAO fichaPagoDAO;
	
	@JPADAO
	private EntidadDAO conceptoDAO;
	
	private BecaDAO becaDAO;

	@PostConstruct
	public void init() {
		fichaPagoDAO = new FichaPagoDAOImpl(em);
		becaDAO = new BecaDAOImpl(em);
		conceptoDAO = new ConceptoDAOImpl(em);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void hacerPresupuesto(String matricula, 
			FichaAcademica fA, Beca becaSeleccionada,
			Float montoTotalIns, Float montoTotalMens,
			Set<TramiteCarrera> tramitesAPagar) 
					throws ApplicationException {
		
		ConceptoFichaPago concept = null;
		FichaPago fichaPago = new FichaPago();
		fichaPago.setFichaAcademica(fA);
		fichaPago.setFechaElaboracion(new Date());
		fichaPago.setNombre("FP de la persona: "+matricula);
		fichaPago.setUsuario(fA.getUsuario());
		fichaPago.setFechaHora(new Date());
		fichaPago.setTipoFichaPago(ConstantesSACE.FICHA_INSCRIPCION);
		// Guardar el registro padre del presupuesto
		em.persist(fichaPago);
		em.refresh(fichaPago);
		
		// Armar los conceptos del presupuesto.
		/*
		 * 1- Recorrer todos los tramites (cuotas de la carrera, para formar cada concepto.
		 */
		float tot = 0;
		for(TramiteCarrera tc : tramitesAPagar) {// prospecto.getCarrera().getTramites()) {
			ConceptoId id = new ConceptoId(fichaPago.getIdFichaPago(), tc.getTramite().getIdTramite());
			concept = new ConceptoFichaPago(id);
			// 2- Para cada tramite hacer un conceptro
			concept.setTramite(tc.getTramite());
			if(tc.getTramite().getIdTramite() == 1) { // Inscripcion
				concept.setMonto(montoTotalIns);
			} else if(tc.getTramite().getIdTramite() == 4) { // Mensualidad
				concept.setMonto(montoTotalMens);
			} else {
				concept.setMonto(tc.getCuota());
			}
			concept.setFichaPago(fichaPago);
			
			// Guardar el concepto
			em.persist(concept);
			tot += concept.getMonto();  
		}
		
		// Se actualiza el total de la ficha de pago
		fichaPago.setTotal(tot);
	}

	@Override
	public void modificarPresupuesto(String matricula, FichaAcademica fA,
			Beca becaSeleccionada, Float montoTotalInsNuevo, Float montoTotalMensNuevo,
			Set<TramiteCarrera> tramitesAPagar) throws ApplicationException {
		
		// em.find(FichaPago.class, arg1);
		FichaPago fichaInscripcion = null;
		
		// Obtener la ficha de pago de la inscripcion para poder modificar el total
		List<FichaPago> fichas = fichaPagoDAO.obtieneTodoPorCriteria(fA.getId(), ConstantesSACE.FICHA_INSCRIPCION);
		if(fichas.isEmpty()) {
			throw new ApplicationException("Registro no encontrado");
		} 
		
		// Sólo hay una sola ficha de inscripcion
		fichaInscripcion = fichas.get(0);
		
		// Esto es:
		// Sacar todos los conceptos relacionados a la ficha de pago, iterar:
		List<ConceptoFichaPago> conceptoFichaPagos = conceptoDAO.obtieneTodoPorCriteria(fichaInscripcion.getIdFichaPago());
		// iterar para sacar la sumatoria de tods los conceptos
		float tot = 0;
		for(ConceptoFichaPago c : conceptoFichaPagos) {
			// para los conceptos 1 y 2 actualizarr con los valores: montoTotalInsNuevo, montoTotalMensNuevo.
			if(c.getTramite().getIdTramite() == ConstantesSACE.IDENTIFICADOR_INSCRIPCION) { 
				c.setMonto(montoTotalInsNuevo);
				tot = tot + montoTotalInsNuevo;
			} else if(c.getTramite().getIdTramite() == ConstantesSACE.IDENTIFICADOR_MENSUALIDAD) {
				c.setMonto(montoTotalMensNuevo);
				tot = tot + montoTotalMensNuevo;
			} else {
				tot = tot + c.getMonto();
			}
		}
		
		// por ultimo actualizar el total en la ficha de pago de inscripcion
		fichaInscripcion.setTotal(tot);
		
	}
	
	public void registroBeca(Beca beca, List<Descuento> descuentosPorBeca)
			throws ApplicationException {
		

	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Beca> recuperaBecas() throws ApplicationException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Beca> query = cb.createQuery(Beca.class);
		Root<Beca> rootBeca = query.from(Beca.class);
		TypedQuery<Beca> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

//	@Override
//	public Beca recuperaBeca(int idBeca) throws ApplicationException {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Beca> query = cb.createQuery(Beca.class);
//		Root<Beca> emp = query.from(Beca.class);
//		query.select(emp)
//		.where(cb.equal(emp.get("idBeca"), idBeca));
//		TypedQuery<Beca> typedQuery = em.createQuery(query);
//		Beca b = typedQuery.getSingleResult();
//		
//		// Con todo y los descuentos de la beca
//		for(Descuento d : b.getDescuentos()) {
//			d.getTramite().getDescripcion();
//		}
//		return b;
//	}

}
