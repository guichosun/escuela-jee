package mx.qr.sace.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.EmpresaDAO;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.EmpresaConvenio;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Noviembre 4 2015
 * @copyright Q & R
 */
@JPADAO
public class EmpresaDAOImpl extends GenericoDAO<Empresa> implements EmpresaDAO {

	/**
	 * @param em
	 */
	public EmpresaDAOImpl(EntityManager em) {
		super(em);
	}

	public List<Empresa> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> root = criteriaQuery.from(Empresa.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "id");
				criteria.add(criteriaBuilder.equal(root.get("id"), pI));
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
		
		TypedQuery<Empresa> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("id", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
				}
			}
		}
		return tq.getResultList();
	}

	@Override
	public Empresa buscaPorId(Object[] id) {
		return em.find(Empresa.class, id[0]);
	}

	@Override
	public void modifica(Empresa entity) {
		
	}

	@Override
	public void elimina(Empresa entity) {
		
	}

	@Override
	public EmpresaConvenio obtenConvenio(int idEmpresa) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<EmpresaConvenio> criteriaQuery = criteriaBuilder.createQuery(EmpresaConvenio.class);
		Root<EmpresaConvenio> root = criteriaQuery.from(EmpresaConvenio.class);
		criteriaQuery.select(root);
		
		Predicate criteria = null;
		
		ParameterExpression<Integer> pI = null;
		pI = criteriaBuilder.parameter(Integer.class, "id");
		criteria = criteriaBuilder.equal(root.get("id").get("idEmpresa"), pI);
			
		criteriaQuery.where(criteria);
		
		TypedQuery<EmpresaConvenio> tq = em.createQuery(criteriaQuery);
		
		tq.setParameter("id", idEmpresa);
		
		try {
			return tq.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
	}
}
