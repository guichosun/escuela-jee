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
import mx.qr.sace.persistencia.dao.RespuestaAPreguntaDAO;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;

/**
 * Implementacion de acceso a 
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Diciembre 2015
 * @copyright Q & R
 */
@JPADAO
public class RespuestaAPreguntaDAOImpl extends GenericoDAO<RespuestaAPregunta> implements RespuestaAPreguntaDAO {

	/**
	 * @param em
	 */
	public RespuestaAPreguntaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void guarda(RespuestaAPregunta entity) {
		entity.setFechaHora(new Date());
		super.guarda(entity);
//		em.persist(entity);
	}

	@Override
	public RespuestaAPregunta buscaPorId(Integer id) {
		return null;
	}

	@Override
	public void modifica(RespuestaAPregunta entity) {
		entity.setFechaHora(new Date());
		em.merge(entity);
	}

	@Override
	public void elimina(RespuestaAPregunta entity) {

	}

	@Override
	public List<RespuestaAPregunta> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<RespuestaAPregunta> criteriaQuery = criteriaBuilder.createQuery(RespuestaAPregunta.class);
		Root<RespuestaAPregunta> rootFrom = criteriaQuery.from(RespuestaAPregunta.class);
		criteriaQuery.select(rootFrom);
		
		List<Predicate> predicados = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "idAlumno");
				predicados.add(criteriaBuilder.equal(rootFrom.get("idAlumno"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
					pI = criteriaBuilder.parameter(Integer.class, "idPregunta");
					predicados.add(criteriaBuilder.equal(rootFrom.get("idPregunta"), pI));
				}
				if(params.length > 2) {
					if(params[2] != null) {
					}
				}
			}
			
			if (predicados.size() == 1) {
				criteriaQuery.where(predicados.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(predicados.toArray(new Predicate[0])));
			}
		}
		
		TypedQuery<RespuestaAPregunta> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("idAlumno", params[0]);
			}
			if(params.length > 1) {
				if(params[1] != null) {
					tq.setParameter("idPregunta", params[1]);
				}
			}
		}
		return tq.getResultList();
	}

}
