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

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.PagoDiversoDAO;
import mx.qr.sace.persistencia.entidades.PagoDiverso;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Enero 31 2017
 * @copyright Q & R
 */
@JPADAO
public class PagoDiversoDAOImpl extends GenericoDAO<PagoDiverso> implements PagoDiversoDAO {

	/**
	 * @param em
	 */
	public PagoDiversoDAOImpl(EntityManager em) {
		super(em);
	}

	public List<PagoDiverso> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<PagoDiverso> criteriaQuery = criteriaBuilder.createQuery(PagoDiverso.class);
		Root<PagoDiverso> root = criteriaQuery.from(PagoDiverso.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "fichaAcademica");
				criteria.add(criteriaBuilder.equal(root.get("fichaAcademica").get("id"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
					ParameterExpression<EstatusRegistro> pER = null;
//					pER = criteriaBuilder.parameter(EstatusRegistro.class, "estatus");
//					criteria.add(criteriaBuilder.equal(root.get("estatus"), pER));
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		
		TypedQuery<PagoDiverso> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("fichaAcademica", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
//					tq.setParameter("estatus", params[1]);
				}
			}
		}
		return tq.getResultList();
	}

	@Override
	public PagoDiverso buscaPorId(Integer id) {
		return em.find(PagoDiverso.class, id);
	}

	@Override
	public void modifica(PagoDiverso entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void elimina(PagoDiverso entity) {
		// TODO Auto-generated method stub
		
	}


}
