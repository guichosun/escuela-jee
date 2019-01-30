package mx.qr.sace.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.persistencia.dao.CicloEscolarDAO;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Mayo 2016
 * @copyright Q & R
 */
@JPADAO
public class CicloEscolarDAOImpl extends GenericoDAO<CicloEscolar> implements CicloEscolarDAO {

	/**
	 * @param em
	 */
	public CicloEscolarDAOImpl(EntityManager em) {
		super(em);
	}
	
	@Override
	public void guarda(CicloEscolar entity) {
		entity.setFechaHora(new Date());
		super.guarda(entity);
	}

	public List<CicloEscolar> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<CicloEscolar> criteriaQuery = criteriaBuilder.createQuery(CicloEscolar.class);
		Root<CicloEscolar> root = criteriaQuery.from(CicloEscolar.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "id");
				criteria.add(criteriaBuilder.equal(root.get("idCicloEscolar"), pI));
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
		
		TypedQuery<CicloEscolar> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("id", params[0]);
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
	public CicloEscolar buscaPorId(Integer id) {
		// find() regresa null si no existe el registro
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CicloEscolar> query = cb.createQuery(CicloEscolar.class);
		Root<CicloEscolar> emp = query.from(CicloEscolar.class);
		query.select(emp);
		ParameterExpression<Integer> pI = cb.parameter(Integer.class, "id");
		query.where(cb.equal(emp.get("idCicloEscolar"), pI));
				// // cq = cq.where(cb.equal(car.get("id.idEscolaridad"), idEscolaridad));
		TypedQuery<CicloEscolar> typedQuery = em.createQuery(query);
		typedQuery.setParameter("id", id);
		CicloEscolar b = typedQuery.getSingleResult();
//		return em.find(CicloEscolar.class, id);
		return b;
	}

	@Override
	public void modifica(CicloEscolar entity) {
		Query q = em.createNamedQuery("CicloEscolar.modifica");
		q.setParameter("id", entity.getId());
//		q.setParameter("nombre", entity.getNombre());
//		q.setParameter("desc", entity.getDescripcion());
//		q.setParameter("estatus", entity.getEstatus());
		
		int row = q.executeUpdate();
	}

	@Override
	public void elimina(CicloEscolar entity) {
		em.remove(entity);
	}

//	@Override
//	public boolean estaCicloEscolarAsociada(CicloEscolar CicloEscolar) {
//		
//		int res = 0;
//		//DONE Terminar los dos queries
//		Query qry = em.createNativeQuery("select * from c_empresas_convenio where id_CicloEscolar = :idCicloEscolar");
//		qry.setParameter("idCicloEscolar", CicloEscolar.getIdCicloEscolar());
//		if(!qry.getResultList().isEmpty()) {
//			res = 1;
//		}
//		
//		qry = em.createNativeQuery("SELECT id_CicloEscolar FROM fichas_academicas where id_CicloEscolar = :idCicloEscolar");
//		qry.setParameter("idCicloEscolar", CicloEscolar.getIdCicloEscolar());
//		if(!qry.getResultList().isEmpty()) {
//			res = 1;
//		}
//		
////		Una CicloEscolar se considera asociada sii esta presente en cuando menos una tabla.
//		return res > 0;
//	}
}
