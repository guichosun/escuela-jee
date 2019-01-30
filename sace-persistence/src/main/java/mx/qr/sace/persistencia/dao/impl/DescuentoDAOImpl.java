package mx.qr.sace.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
import mx.qr.sace.persistencia.dao.DescuentoDAO;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Diciembre 2015
 * @copyright Q & R
 */
@JPADAO
public class DescuentoDAOImpl extends GenericoDAO<Descuento> implements DescuentoDAO {

	public DescuentoDAOImpl(EntityManager em) {
		super(em);
	}

	public void guarda(Descuento entity) {
		entity.setFechaHora(new Date());
		super.guarda(entity);
//		em.persist(entity);

	}

	@Override
	public Descuento buscaPorId(Integer id) {
		// find() regresa null si no existe el registro
		return em.find(Descuento.class, id);
	}

	@Override
	public void modifica(Descuento entity) {
		em.merge(entity);
	}

	@Override
	public void elimina(Descuento entity) {

	}

	@Override
	public List<Descuento> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Descuento> criteriaQuery = criteriaBuilder.createQuery(Descuento.class);
		Root<Descuento> root = criteriaQuery.from(Descuento.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "id");
				criteria.add(criteriaBuilder.equal(root.get("idDescuento"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
					ParameterExpression<Beca> pB = null;
					pB = criteriaBuilder.parameter(Beca.class, "beca");
					criteria.add(criteriaBuilder.equal(root.get("beca"), pB));
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		
		TypedQuery<Descuento> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("id", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
					tq.setParameter("beca", params[1]);
				}
			}
		}
		return tq.getResultList();
	}

}
