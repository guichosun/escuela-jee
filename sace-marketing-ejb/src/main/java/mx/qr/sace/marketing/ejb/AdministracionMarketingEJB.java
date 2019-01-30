package mx.qr.sace.marketing.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.BasicoEJB;
import mx.qr.core.exception.RegistroAsociadoException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.marketing.negocio.AdministracionMarketingLocal;
import mx.qr.sace.persistencia.dao.BecaDAO;
import mx.qr.sace.persistencia.dao.CatalogosDAO;
import mx.qr.sace.persistencia.dao.DescuentoDAO;
import mx.qr.sace.persistencia.dao.impl.BecaDAOImpl;
import mx.qr.sace.persistencia.dao.impl.CatalogosDAOImpl;
import mx.qr.sace.persistencia.dao.impl.DescuentoDAOImpl;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;
import mx.qr.sace.persistencia.entidades.Tramite;

/**
 * Session Bean implementation class AdministracionMarketingEJB
 */
@Local(AdministracionMarketingLocal.class)
@Stateless(name = "AdministracionMarketingEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/AdministracionMarketingEJB")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AdministracionMarketingEJB extends BasicoEJB implements AdministracionMarketingLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;

	@JPADAO
	private BecaDAO becaDAO;
	
	@JPADAO
	private CatalogosDAO catalogosDAO;

	@JPADAO
	private DescuentoDAO descuentoDAO;
	
	@PostConstruct
	public void init() {
		becaDAO = new BecaDAOImpl(em);
		catalogosDAO = new CatalogosDAOImpl(em);
		descuentoDAO = new DescuentoDAOImpl(em);
	}
	
	/**
     * @see AdministracionMarketingLocal#eliminaBeca(Beca)
     */
    public void eliminaBeca(Beca beca) throws RegistroAsociadoException, RegistroNoEncontradoException {
    	alteraBeca(2, beca, null);
    }

	/**
     * @see AdministracionMarketingLocal#registraBeca(Beca, Descuento[])
     */
    public void registraBeca(Beca beca, Descuento[] descuentos) {
    	// DONE Definir el negocio para el registro de una beca.
    	
    	// Primero guardar la beca.
    	becaDAO.guarda(beca);

    	// Registrar los descuentos con la beca asociada.
    	// Traer el Tramite de Inscripcion y Mensualidad
    	Tramite t = null;

    	// Traer la Inscripcion
    	t = catalogosDAO.obtenTramitePorId(1);
		descuentos[0].setTramite(t);
    	descuentos[0].setBeca(beca);
    	descuentoDAO.guarda(descuentos[0]);
    	
    	// Traer la Mensualidad
    	t = catalogosDAO.obtenTramitePorId(4);
    	descuentos[1].setTramite(t);
    	descuentos[1].setBeca(beca);
    	descuentoDAO.guarda(descuentos[1]);

    }

	/**
     * @see AdministracionMarketingLocal#alteraBeca(int, Beca, Descuento[])
     */
    public void alteraBeca(int operacion, Beca beca, Descuento[] descuentos)
    		throws RegistroAsociadoException, RegistroNoEncontradoException {
    	
    	/*
    	 * Como regla general:
    	 * No poder alterar una beca asociada a una empresa o a un alumno.
    	 */

    	// DONE Revisar si en registro a alterar existe aun.
    	Beca becaPersistente = becaDAO.buscaPorId(beca.getIdBeca());
    	if(becaPersistente == null) {
    		throw new RegistroNoEncontradoException("Registro no encontrado");
    	}
    	
    	// DONE Revisar si una beca tiene asociacion con Empresas o con Alumnos
    	boolean ret = becaDAO.estaBecaAsociada(beca);
    	if(ret) {
    		throw new RegistroAsociadoException("No puede eliminar esta Beca,"
    				+ " ya ha sido asociada con un Alumno o a una Empresa");
    	}
    	
        switch (operacion) {
			case 2:
				becaDAO.elimina(becaPersistente);
				break;
			default:
				
				becaDAO.modifica(beca);
				
				Descuento desPersistente = descuentoDAO.buscaPorId(descuentos[0].getIdDescuento());
				desPersistente.setTieneDescuentoElTramite(descuentos[0].isTieneDescuentoElTramite());
				desPersistente.setValor(descuentos[0].getValor());
				descuentoDAO.modifica(desPersistente);
				
				desPersistente = descuentoDAO.buscaPorId(descuentos[1].getIdDescuento());
				desPersistente.setTieneDescuentoElTramite(descuentos[1].isTieneDescuentoElTramite());
				desPersistente.setValor(descuentos[1].getValor());
				descuentoDAO.modifica(desPersistente);
				
				break;
			}
    }

	/**
     * @see AdministracionMarketingLocal#registraConvenio()
     */
    public void registraConvenio() {
    }

}
