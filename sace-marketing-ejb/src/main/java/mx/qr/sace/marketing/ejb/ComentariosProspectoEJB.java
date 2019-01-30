package mx.qr.sace.marketing.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.core.persistencia.TipoModulo;
import mx.qr.sace.marketing.negocio.ComentariosProspectoLocal;
import mx.qr.sace.persistencia.dao.ComentarioDAO;
import mx.qr.sace.persistencia.dao.impl.ComentarioDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Comentario;

/**
 * Session Bean de impplementacion para la funcionalidad para obtener 
 * los comentarios (observaciones) efectuadas a un prospecto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
//@Stateless(name="ComentariosProspectoEJB", mappedName = "ejb/marketing/ComentariosProspectoEJB")
@Stateful(name="ComentariosProspectoEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/ComentariosProspectoEJB")
@Local(ComentariosProspectoLocal.class)
public class ComentariosProspectoEJB implements ComentariosProspectoLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
	@JPADAO
	private ComentarioDAO comentarioDAO;

	@PostConstruct
	private void init() {
		comentarioDAO = new ComentarioDAOImpl(em);
	}
	
	/**
     * @see ComentariosProspectoLocal#recuperaTodosDeUnProspecto(Alumno)
     */
    public List<Comentario> recuperaTodosDeUnProspecto(Alumno prospecto) {
        
			return null;
    }

	/**
     * @see ComentariosProspectoLocal#recuperaDeUnProspecto(Alumno)
     */
    public Comentario recuperaDeUnProspecto(Alumno prospecto) {
//    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//		CriteriaQuery<Comentario> criteriaQuery = criteriaBuilder.createQuery(Comentario.class);
//		Root<Comentario> root = criteriaQuery.from(Comentario.class);
//		criteriaQuery.select(root);
//		
//		List<Predicate> criteria = new ArrayList<Predicate>();
//
//			ParameterExpression<Alumno> p = criteriaBuilder.parameter(Alumno.class, "alumno");
//			criteria.add(criteriaBuilder.equal(root.get("alumno"), p));
//		
//		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
//		
////		criteriaQuery.where(criteriaBuilder.equal(root.get("datoPersona").get("edad"), "32"));
//		TypedQuery<Comentario> tq = em.createQuery(criteriaQuery);
//		tq.setParameter("alumno", prospecto);
//		
//		List<Comentario> alumnos = tq.getResultList();
    	
    	// Traer todos los comentarios del prospecto echos en marketing.
		List<Comentario> alumnos = comentarioDAO.obtieneTodoPorCriteria(null, TipoModulo.MKT, prospecto);
		Comentario c = null;
		if(alumnos != null && !alumnos.isEmpty()) {
			c = alumnos.get(0);
		}
		return c;
    }

}
