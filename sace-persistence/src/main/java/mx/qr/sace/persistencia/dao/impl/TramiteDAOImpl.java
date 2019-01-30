package mx.qr.sace.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.core.persistencia.TipoTramite;
import mx.qr.sace.persistencia.dao.TramiteDAO;
import mx.qr.sace.persistencia.entidades.Tramite;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Enero 31 2017
 * @copyright Q & R
 */
@JPADAO
public class TramiteDAOImpl extends GenericoDAO<Tramite> implements TramiteDAO {

	public TramiteDAOImpl() {
	}
	/**
	 * @param em
	 */
	public TramiteDAOImpl(EntityManager em) {
		super(em);
	}

	public List<Tramite> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Tramite> criteriaQuery = criteriaBuilder.createQuery(Tramite.class);
		Root<Tramite> root = criteriaQuery.from(Tramite.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<TipoTramite> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(TipoTramite.class, "tipoTramite");
				criteria.add(criteriaBuilder.equal(root.get("tipoTramite"), pI));
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		
		TypedQuery<Tramite> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("tipoTramite", params[0]);
			}
		}
		return tq.getResultList();
	}

	@Override
	public Tramite buscaPorId(Integer id) {
		return em.find(Tramite.class, id);
	}

	@Override
	public void modifica(Tramite entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elimina(Tramite entity) {
		// TODO Auto-generated method stub
		
	}

}
