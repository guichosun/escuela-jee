package mx.qr.sace.marketing.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.marketing.negocio.CatalogosMarketingLocal;
import mx.qr.sace.persistencia.dao.BecaDAO;
import mx.qr.sace.persistencia.dao.DescuentoDAO;
import mx.qr.sace.persistencia.dao.EmpresaDAO;
import mx.qr.sace.persistencia.dao.impl.BecaDAOImpl;
import mx.qr.sace.persistencia.dao.impl.DescuentoDAOImpl;
import mx.qr.sace.persistencia.dao.impl.EmpresaDAOImpl;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.EmpresaConvenio;

/**
 * Session Bean implementation class CatalogosMarketingEJB.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@Local(CatalogosMarketingLocal.class)
@Stateless(name = "CatalogosMarketingEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/CatalogosMarketingEJB")
public class CatalogosMarketingEJB implements CatalogosMarketingLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;

	@JPADAO
	private EmpresaDAO empresaDAO;
	
	@JPADAO
	private BecaDAO becaDAO;
	
	private DescuentoDAO descuentoDAO;
	
	@PostConstruct
	public void init() {
		empresaDAO = new EmpresaDAOImpl(em);
		becaDAO = new BecaDAOImpl(em);
		descuentoDAO = new DescuentoDAOImpl(em);
	}
	
	/**
     * @see CatalogosMarketingLocal#recuperaEmpresas()
     */
    public List<Empresa> recuperaEmpresas() {
		return empresaDAO.obtieneTodoPorCriteria();
    }

	public Empresa recuperaEmpresa(int id) throws ApplicationException {
		return empresaDAO.buscaPorId(new Object[]{id}); 
	}
	
	public EmpresaConvenio recuperaConvenioDeEmpresa(int idEmpresa) {
		/*
		 * - Obtener el registro de EmpresaConvenio mediante el idEmpresa.
		 * - Si existe, traer la Beca asociada
		 * - de lo contrario null
		 */
		EmpresaConvenio ec = empresaDAO.obtenConvenio(idEmpresa);
		if(ec != null) {
			// Traer la beca asociada
			ec.getBeca().getIdBeca();
		} 
		return ec;
	}

	public Beca recuperaBeca(int idBeca) throws RegistroNoEncontradoException {
		Beca b = becaDAO.buscaPorId(idBeca);
		return b;
	}

	@Override
	public List<Beca> recuperaBecas() throws ApplicationException {
		return recuperaBecas(EstatusRegistro.ACTIVO); 
	}
	
	@Override
	public List<Beca> recuperaBecas(EstatusRegistro estatusBeca)
			throws ApplicationException {
		return becaDAO.obtieneTodoPorCriteria(null, estatusBeca);
	}

	@Override
	public Descuento[] recuperaDescuentosDeUnaBeca(int idBeca)
			throws ApplicationException {
		
		Beca beca = becaDAO.buscaPorId(idBeca);
		
		// Sacar los descuentos asociados a una beca que estén como activos.
		List<Descuento> des = descuentoDAO.obtieneTodoPorCriteria(null, beca);
		
		Descuento[] desc = new Descuento[2];
		int x = 0;
		for(Descuento d : des) {
			desc[x] = d;
			x++;
		}
		return desc;
	}



}
