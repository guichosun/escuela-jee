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
import mx.qr.sace.persistencia.dao.BecaDAO;
import mx.qr.sace.persistencia.entidades.Beca;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Diciembre 2015
 * @copyright Q & R
 */
@JPADAO
public class BecaDAOImpl extends GenericoDAO<Beca> implements BecaDAO {

	/**
	 * @param em
	 */
	public BecaDAOImpl(EntityManager em) {
		super(em);
	}
	
	@Override
	public void guarda(Beca entity) {
		entity.setFechaHora(new Date());
		super.guarda(entity);
//		em.persist(entity);
	}

	public List<Beca> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Beca> criteriaQuery = criteriaBuilder.createQuery(Beca.class);
		Root<Beca> root = criteriaQuery.from(Beca.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "id");
				criteria.add(criteriaBuilder.equal(root.get("idBeca"), pI));
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
		
		TypedQuery<Beca> tq = em.createQuery(criteriaQuery);
		
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
	public Beca buscaPorId(Integer id) {
		// find() regresa null si no existe el registro
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Beca> query = cb.createQuery(Beca.class);
		Root<Beca> emp = query.from(Beca.class);
		query.select(emp);
		ParameterExpression<Integer> pI = cb.parameter(Integer.class, "id");
		query.where(cb.equal(emp.get("idBeca"), pI));
				// // cq = cq.where(cb.equal(car.get("id.idEscolaridad"), idEscolaridad));
		TypedQuery<Beca> typedQuery = em.createQuery(query);
		typedQuery.setParameter("id", id);
		Beca b = typedQuery.getSingleResult();
//		return em.find(Beca.class, id);
		return b;
	}

	@Override
	public void modifica(Beca entity) {
		Query q = em.createNamedQuery("Beca.modifica");
		q.setParameter("id", entity.getIdBeca());
		q.setParameter("nombre", entity.getNombre());
		q.setParameter("desc", entity.getDescripcion());
		q.setParameter("estatus", entity.getEstatus());
		
		int row = q.executeUpdate();
	}

	@Override
	public void elimina(Beca entity) {
		em.remove(entity);
	}

	@Override
	public boolean estaBecaAsociada(Beca beca) {
		
		int res = 0;
		//DONE Terminar los dos queries
		Query qry = em.createNativeQuery("select * from c_empresas_convenio where id_beca = :idBeca");
		qry.setParameter("idBeca", beca.getIdBeca());
		if(!qry.getResultList().isEmpty()) {
			res = 1;
		}
		
		qry = em.createNativeQuery("SELECT id_beca FROM fichas_academicas where id_beca = :idBeca");
		qry.setParameter("idBeca", beca.getIdBeca());
		if(!qry.getResultList().isEmpty()) {
			res = 1;
		}
		
//		Una beca se considera asociada sii esta presente en cuando menos una tabla.
		return res > 0;
	}
}
