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
import mx.qr.sace.persistencia.dao.FichaPagoDAO;
import mx.qr.sace.persistencia.entidades.FichaPago;
import mx.qr.sace.persistencia.entidades.TipoFichaPago;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Agosto 4 2015
 * @copyright Q & R
 */
@JPADAO
public class FichaPagoDAOImpl extends GenericoDAO<FichaPago> implements FichaPagoDAO {

	/**
	 * @param em
	 */
	public FichaPagoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public FichaPago buscaPorId(Integer id) {
		return null;
	}

	@Override
	public void modifica(FichaPago entity) {
		
	}

	@Override
	public void elimina(FichaPago entity) {
	}

	@Override
	public List<FichaPago> obtieneTodoPorCriteria(Object... params) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<FichaPago> criteriaQuery = criteriaBuilder.createQuery(FichaPago.class);
		Root<FichaPago> root = criteriaQuery.from(FichaPago.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "id");
				criteria.add(criteriaBuilder.equal(root.get("fichaAcademica").get("id"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
					ParameterExpression<TipoFichaPago> ptfp = criteriaBuilder.parameter(TipoFichaPago.class, "tipoFichaPago");
					criteria.add(criteriaBuilder.equal(root.get("tipoFichaPago"), ptfp));
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		 
		TypedQuery<FichaPago> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("id", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
					tq.setParameter("tipoFichaPago", params[1]);
				}
			}
		}
		return tq.getResultList();
	}

}
