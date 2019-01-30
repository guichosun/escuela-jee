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
import mx.qr.sace.persistencia.dao.RespuestaDAO;
import mx.qr.sace.persistencia.entidades.Respuesta;
import mx.qr.sace.persistencia.entidades.RespuestaId;

/**
 * Implementacion del acceso a datos para el catalogo c_respuestas.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@JPADAO
public class RespuestaDAOImpl extends GenericoDAO<Respuesta> implements RespuestaDAO {

	/**
	 * @param em
	 */
	public RespuestaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Respuesta buscaPorId(RespuestaId id) {
		// TODO Agregar comportamiento para buscar una respuesta
		return null;
	}

	@Override
	public void modifica(Respuesta entity) {

	}

	@Override
	public void elimina(Respuesta entity) {
	}

	@Override
	public List<Respuesta> obtieneTodoPorCriteria(Object... params) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Respuesta> criteriaQuery = criteriaBuilder.createQuery(Respuesta.class);
		Root<Respuesta> root = criteriaQuery.from(Respuesta.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if(params.length > 0) {
			ParameterExpression<Integer> pI = null;
			if (params[0] != null) {
				pI = criteriaBuilder.parameter(Integer.class, "idRespuesta");
				criteria.add(criteriaBuilder.equal(root.get("id").get("idRespuesta"), pI));
			}
			if(params.length > 1) {
				if(params[1] != null) {
					pI = criteriaBuilder.parameter(Integer.class, "idPregunta");
					criteria.add(criteriaBuilder.equal(root.get("id").get("idPregunta"), pI));
				}
			}
			
			if (criteria.size() == 1) {
				criteriaQuery.where(criteria.get(0));
			} else {
				criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
			}
		}
		 
		TypedQuery<Respuesta> tq = em.createQuery(criteriaQuery);
		
		if(params.length > 0) {
			if (params[0] != null) {
				tq.setParameter("idRespuesta", params[0]);
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
