package mx.qr.sace.finanzas.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.core.persistencia.TipoTramite;
import mx.qr.sace.core.ConstantesSACE;
import mx.qr.sace.finanzas.negocio.CatalogosFinanzasLocal;
import mx.qr.sace.persistencia.dao.PrecioConceptoDAO;
import mx.qr.sace.persistencia.dao.TramiteDAO;
import mx.qr.sace.persistencia.dao.impl.PrecioConceptoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.TramiteDAOImpl;
import mx.qr.sace.persistencia.entidades.PrecioConcepto;
import mx.qr.sace.persistencia.entidades.Tramite;

/**
 * Inplementacion del negocio para los catalogos para el 
 * area de finanzas.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Enero 2017
 * @copyright Q & R
 */
@Local(CatalogosFinanzasLocal.class)
@Stateless(name = "CatalogosFinanzasEJB", 
	mappedName="java:global/sace-ear/sace-finanzas-ejb/CatalogosFinanzasEJB")
public class CatalogosFinanzasEJB implements CatalogosFinanzasLocal {

	@PersistenceContext(unitName = "sacePU")
	private EntityManager em;
	
	@JPADAO
	private TramiteDAO tramiteDAO;
	
	@JPADAO
	private PrecioConceptoDAO precioConceptoDAO;

	@PostConstruct
	public void init() {
		tramiteDAO = new TramiteDAOImpl(em);
		precioConceptoDAO = new PrecioConceptoDAOImpl(em);
	}
	
	@Override
	public List<Tramite> obtenTramitesPorTipo(TipoTramite tipoTramite) {
		return tramiteDAO.obtieneTodoPorCriteria(tipoTramite);
	}

	@Override
	public List<Tramite> obtenConceptos() {
		// Se obtienen todos los tramites (tipo_tramite=CONCEPTO) que seran tratados como unos conceptos
		List<Tramite> conceptos = obtenTramitesPorTipo(TipoTramite.CONCEPTO);
		
		// Se agrega el tramite (tipo_tramite=CUOTA) Uniforme
		conceptos.add(
				tramiteDAO.buscaPorId(ConstantesSACE.IDENTIFICADOR_UNIFORME));
		
		return conceptos;
	}

	@Override
	public PrecioConcepto obtenPrecioConcepto(Tramite concepto) {
		List<PrecioConcepto> precios = precioConceptoDAO.obtieneTodoPorCriteria(
				concepto.getIdTramite(), EstatusRegistro.ACTIVO);
		PrecioConcepto pc = null;
		if(!precios.isEmpty()) {
			pc = precios.get(0);
		}
		return pc;
	}

}
