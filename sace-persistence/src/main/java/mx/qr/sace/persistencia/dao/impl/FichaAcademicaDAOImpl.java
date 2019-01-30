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
import mx.qr.sace.persistencia.dao.FichaAcademicaDAO;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.FichaAcademica;

/**
 * La implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * 				Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
@JPADAO
public class FichaAcademicaDAOImpl extends GenericoDAO<FichaAcademica>
		implements FichaAcademicaDAO {

	/**
	 * @param em
	 */
	public FichaAcademicaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public FichaAcademica buscaPorId(Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FichaAcademica> query = cb.createQuery(FichaAcademica.class);
		Root<FichaAcademica> emp = query.from(FichaAcademica.class);
		query.select(emp);
		ParameterExpression<Integer> pI = cb.parameter(Integer.class, "id");
		query.where(cb.equal(emp.get("id"), pI));
				// // cq = cq.where(cb.equal(car.get("id.idEscolaridad"), idEscolaridad));
		TypedQuery<FichaAcademica> typedQuery = em.createQuery(query);
		typedQuery.setParameter("id", id);
		FichaAcademica b = typedQuery.getSingleResult();
		return b;
//		return em.find(FichaAcademica.class, id);
	}

	@Override
	public void modifica(FichaAcademica entity) {

	}

	@Override
	public void elimina(FichaAcademica entity) {

	}

	@Override
	public List<FichaAcademica> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<FichaAcademica> criteriaQuery = criteriaBuilder.createQuery(FichaAcademica.class);
		Root<FichaAcademica> root = criteriaQuery.from(FichaAcademica.class);
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
					ParameterExpression<Alumno> p2 =
							criteriaBuilder.parameter(Alumno.class, "alumno");
					criteria.add(criteriaBuilder.equal(root.get("alumno"), p2));
				}
				if(params.length > 2) {
					if(params[2] != null) {
						ParameterExpression<Carrera> p3 =
								criteriaBuilder.parameter(Carrera.class, "carrera");
						criteria.add(criteriaBuilder.equal(root.get("carrera"), p3));
					}
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		
		TypedQuery<FichaAcademica> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("id", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
					tq.setParameter("alumno", params[1]);
				}
				if(params.length > 2) {
					if(params[2] != null) {
						tq.setParameter("carrera", params[2]);
					}
				}
			}
		}
		return tq.getResultList();
	}

}
