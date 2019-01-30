/**
 * 
 */
package mx.qr.sace.persistencia.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.CatalogosDAO;
import mx.qr.sace.persistencia.entidades.Tramite;

/**
 * Implementacion para el acceso a todo los catalogos.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Diciembre 2015
 * @copyright Q & R
 */
@JPADAO
public class CatalogosDAOImpl extends GenericoDAO implements CatalogosDAO {

	/**
	 * @param em
	 */
	public CatalogosDAOImpl(EntityManager em) {
		super(em);
	}

	/* (non-Javadoc)
	 * @see mx.qr.sace.persistencia.dao.CatalogosDAO#obtenTramitePorId(int)
	 */
	@Override
	public Tramite obtenTramitePorId(int pk) {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT c ");
		query.append("FROM Tramite c ");
		query.append("WHERE c.idTramite = :id");

		Query q = em.createQuery(query.toString());
		q.setParameter("id", pk);
		return (Tramite)q.getSingleResult();
	}

}
