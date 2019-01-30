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
import mx.qr.core.persistencia.TipoTramite;
import mx.qr.sace.persistencia.dao.PrecioConceptoDAO;
import mx.qr.sace.persistencia.entidades.PrecioConcepto;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Enero 31 2017
 * @copyright Q & R
 */
@JPADAO
public class PrecioConceptoDAOImpl extends GenericoDAO<PrecioConcepto> implements PrecioConceptoDAO {

	/**
	 * @param em
	 */
	public PrecioConceptoDAOImpl(EntityManager em) {
		super(em);
	}

	public List<PrecioConcepto> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<PrecioConcepto> criteriaQuery = criteriaBuilder.createQuery(PrecioConcepto.class);
		Root<PrecioConcepto> root = criteriaQuery.from(PrecioConcepto.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "idTramite");
				criteria.add(criteriaBuilder.equal(root.get("tramite").get("idTramite"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
					ParameterExpression<EstatusRegistro> pER = null;
					pER = criteriaBuilder.parameter(EstatusRegistro.class, "estatus");
					criteria.add(criteriaBuilder.equal(root.get("estatus"), pER));
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		
		TypedQuery<PrecioConcepto> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("idTramite", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
					tq.setParameter("estatus", params[1]);
				}
			}
		}
		return tq.getResultList();
	}

	@Override
	public PrecioConcepto buscaPorId(Integer id) {
		return em.find(PrecioConcepto.class, id);
	}

	@Override
	public void modifica(PrecioConcepto entity) {
		
	}

	@Override
	public void elimina(PrecioConcepto entity) {
		
	}

}
