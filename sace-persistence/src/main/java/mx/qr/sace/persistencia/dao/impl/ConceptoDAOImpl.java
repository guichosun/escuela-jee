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

import mx.qr.core.persistencia.EntidadDAO;
import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.entidades.ConceptoFichaPago;
import mx.qr.sace.persistencia.entidades.ConceptoId;
import mx.qr.sace.persistencia.entidades.FichaPago;
import mx.qr.sace.persistencia.entidades.TipoFichaPago;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 6 2016
 * @copyright Q & R
 */
@JPADAO
public class ConceptoDAOImpl extends GenericoDAO<ConceptoFichaPago> implements EntidadDAO<ConceptoId, ConceptoFichaPago> {

	
	public ConceptoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void guarda(ConceptoFichaPago entity) {
		
	}

	@Override
	public ConceptoFichaPago buscaPorId(ConceptoId id) {
		return null;
	}

	@Override
	public void modifica(ConceptoFichaPago entity) {
		
	}

	@Override
	public void elimina(ConceptoFichaPago entity) {
		
	}

	@Override
	public List<ConceptoFichaPago> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ConceptoFichaPago> criteriaQuery = criteriaBuilder.createQuery(ConceptoFichaPago.class);
		Root<ConceptoFichaPago> root = criteriaQuery.from(ConceptoFichaPago.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "idFichaPago");
				criteria.add(criteriaBuilder.equal(root.get("id").get("idFichaPago"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		 
		TypedQuery<ConceptoFichaPago> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("idFichaPago", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
				}
			}
		}
		return tq.getResultList();
	}


}
