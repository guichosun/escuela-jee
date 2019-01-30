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
import mx.qr.core.persistencia.TipoModulo;
import mx.qr.sace.persistencia.dao.ComentarioDAO;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Comentario;

/**
 * Implementacion
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Junio 2016
 * @copyright Q & R
 */
@JPADAO
public class ComentarioDAOImpl extends GenericoDAO<Comentario> 
		implements ComentarioDAO {

	public ComentarioDAOImpl() {
		
	}
	
	public ComentarioDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void guarda(Comentario entity) {
		entity.setFechaHora(new Date());
		super.guarda(entity);
	}
	
	@Override
	public void elimina(Comentario entity) {
		// TODO Eliminar el prospecto.
	}

	@Override
	public List<Comentario> obtieneTodoPorCriteria(Object... parametros) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Comentario> criteriaQuery = criteriaBuilder.createQuery(Comentario.class);
		Root<Comentario> root = criteriaQuery.from(Comentario.class);
		criteriaQuery.select(root);
		
		List<Predicate> criteria = new ArrayList<Predicate>();

		ParameterExpression<Alumno> p = criteriaBuilder.parameter(Alumno.class, "alumno");
		criteria.add(criteriaBuilder.equal(root.get("alumno"), p));
		
		ParameterExpression<TipoModulo> p2 = criteriaBuilder.parameter(TipoModulo.class, "modulo");
		criteria.add(criteriaBuilder.equal(root.get("modulo"), p2));
		
		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
		
		TypedQuery<Comentario> tq = em.createQuery(criteriaQuery);
		tq.setParameter("modulo", parametros[1]);
		tq.setParameter("alumno", parametros[2]);
		
//		List<Comentario> alumnos = tq.getResultList();
		return tq.getResultList();
	}

	@Override
	public Comentario buscaPorId(Integer id) {
		return null;
	}

	@Override
	public void modifica(Comentario entity) {
		// TODO Modificar el registro
	}
}
