package mx.qr.sace.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.persistencia.GenericoDAO;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 4 2015
 * @copyright Q & R
 */
@JPADAO
public class AlumnoDAOImpl extends GenericoDAO<Alumno> implements AlumnoDAO {

	/**
	 * @param em
	 */
	public AlumnoDAOImpl(EntityManager em) {
		super(em);
	}

	public static String qryTodosLosProspectos() {
		StringBuffer query = new StringBuffer();
		query.append("SELECT c ");
		query.append("FROM Alumno c ");
		query.append("WHERE ");
		List<String> criteria = new ArrayList<String>();
		criteria.add("c.idEscolaridad = :idEscolaridad");
		criteria.add("c.idModalidad = :idModalidad");
		criteria.add("c.estatus = :estatus");

		for (int i = 0; i < criteria.size(); i++) {
			if (i > 0) {
				query.append(" AND ");
			}
			query.append(criteria.get(i));
		}

		return query.toString();
	}


	@Override
	public Alumno buscaPorId(Integer id) {
		return em.find(Alumno.class, id);
	}

	@Override
	public void modifica(Alumno entity) {
		em.refresh(entity);

	}

	@Override
	public void elimina(Alumno entity) {
		

	}

	@Override
	public List<Alumno> obtenPorPaginado(int indicePrimerElemento,
			int tamanioPagina, String campoOrden, int tipoOrdenamiento,
			Map<String, Object> filtrosColumna, Escolaridad escolaridad,
			Modalidad modalidad, EstatusAlumno estatus) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Alumno> criteriaQuery = criteriaBuilder.createQuery(Alumno.class);
		Root<Alumno> alumno = criteriaQuery.from(Alumno.class);
		criteriaQuery.select(alumno);
		criteriaQuery.distinct(true);
		Join<Alumno, FichaAcademica> fichaA = alumno.join("fichas", JoinType.LEFT);
		
		List<Predicate> criteria = new ArrayList<Predicate>();

		// Solo los prospectos
		ParameterExpression<EstatusAlumno> pEstatus = criteriaBuilder.parameter(EstatusAlumno.class, "estatus");
		criteria.add(criteriaBuilder.equal(alumno.get("estatus"), pEstatus));
		
		if(escolaridad != null) {
			ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class, "idEscolaridad");
			criteria.add(criteriaBuilder.equal(fichaA.get("idEscolaridad"), p));
		}
		
		if(modalidad != null) {
			ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class, "idModalidad");
			criteria.add(criteriaBuilder.equal(fichaA.get("idModalidad"), p));
		}
		
		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
		
		TypedQuery<Alumno> tq = em.createQuery(criteriaQuery);
		tq.setParameter("estatus", estatus);
		tq.setParameter("idEscolaridad", escolaridad.getIdEscolaridad());
		tq.setParameter("idModalidad", modalidad.getIdModalidad());
		
		tq.setFirstResult(indicePrimerElemento);
		tq.setMaxResults(tamanioPagina);
		List<Alumno> alumnos = tq.getResultList();
		
		return alumnos;
	}

	@Override
	public int obtenTotalDeRegistros(Escolaridad escolaridad,
			Modalidad modalidad, EstatusAlumno estatus) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
		Root<Alumno> alumno = criteriaQuery.from(Alumno.class);
		criteriaQuery.select(criteriaBuilder.count(alumno));
		criteriaQuery.distinct(true);
		Join<Alumno, FichaAcademica> fichaA = alumno.join("fichas", JoinType.LEFT);
		
		List<Predicate> criteria = new ArrayList<Predicate>();

		// Solo los prospectos
		ParameterExpression<EstatusAlumno> pEstatus = criteriaBuilder.parameter(EstatusAlumno.class, "estatus");
		criteria.add(criteriaBuilder.equal(alumno.get("estatus"), pEstatus));
		
		if(escolaridad != null) {
			ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class, "idEscolaridad");
			criteria.add(criteriaBuilder.equal(fichaA.get("idEscolaridad"), p));
		}
		
		if(modalidad != null) {
			ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class, "idModalidad");
			criteria.add(criteriaBuilder.equal(fichaA.get("idModalidad"), p));
		}
		
		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
		
		TypedQuery<Long> tq = em.createQuery(criteriaQuery);
		
		tq.setParameter("estatus", estatus);
		tq.setParameter("idEscolaridad", escolaridad.getIdEscolaridad());
		tq.setParameter("idModalidad", modalidad.getIdModalidad());
		
		return tq.getSingleResult().intValue();
	}

	@Override
	public List<Alumno> obtenPorCadena(SistemaEstudio sisEstudios,
			Object...parametros) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Alumno> criteriaQuery = criteriaBuilder.createQuery(Alumno.class);
		Root<Alumno> alumno = criteriaQuery.from(Alumno.class);
		criteriaQuery.select(alumno);
		criteriaQuery.distinct(true);
		Join<Alumno, FichaAcademica> fichaA = alumno.join("fichas", JoinType.LEFT);
		
		List<Predicate> criteria = new ArrayList<Predicate>();

		Predicate predicadoLike = null;
		ParameterExpression<Integer> peI = null;
		if(parametros.length > 0) {
			ParameterExpression<String> pCadena = criteriaBuilder.parameter(String.class, "matricula");
			predicadoLike = criteriaBuilder.like(alumno.<String>get("matricula"), pCadena);
			criteria.add(predicadoLike);
			ParameterExpression<EstatusAlumno> pEstatus = criteriaBuilder.parameter(EstatusAlumno.class, "estatus");
			criteria.add(criteriaBuilder.equal(alumno.get("estatus"), pEstatus));
			
			if(parametros[2] != null) {
				peI = criteriaBuilder.parameter(Integer.class, "idCarrera");
				criteria.add(criteriaBuilder.equal(fichaA.get("carrera").get("id").get("idCarrera"), peI));
			}
		}
		
		peI = criteriaBuilder.parameter(Integer.class, "idEscolaridad");
		criteria.add(criteriaBuilder.equal(fichaA.get("idEscolaridad"), peI));
		
		peI = criteriaBuilder.parameter(Integer.class, "idModalidad");
		criteria.add(criteriaBuilder.equal(fichaA.get("idModalidad"), peI));
		
		criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
		
		TypedQuery<Alumno> tq = em.createQuery(criteriaQuery);
		if(parametros.length > 0) {
			tq.setParameter("matricula", parametros[0].toString()+"%");
			tq.setParameter("estatus", parametros[1]);
			
			if(parametros[2] != null) {
				tq.setParameter("idCarrera", ((Carrera)parametros[2]).getId().getIdCarrera());
			}
		}
		tq.setParameter("idEscolaridad", sisEstudios.getEscolaridad().getIdEscolaridad());
		tq.setParameter("idModalidad", sisEstudios.getModalidad().getIdModalidad());

		criteriaQuery.orderBy(criteriaBuilder.asc(alumno.get("datoPersona").get("apePaterno")));
		
		List<Alumno> alumnos = tq.getResultList();
		
		return alumnos;
	}
	
	@Override
	public List<Alumno> obtieneTodoPorCriteria(Object... parametros) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<Alumno> criteriaQuery = criteriaBuilder.createQuery(Alumno.class);
		Root<Alumno> alumno = criteriaQuery.from(Alumno.class);
		criteriaQuery.select(alumno);
		criteriaQuery.distinct(true);
		// Hace join con la collection fichas que esta en Alumno
		Join<Alumno, FichaAcademica> fichaA = alumno.join("fichas", JoinType.LEFT);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		if(parametros.length > 0 ) {
			ParameterExpression<Integer> pI = null;
			ParameterExpression<String> pCadena = null;

			if(parametros[0] instanceof Integer) {
				pI = criteriaBuilder.parameter(Integer.class, "idEsc");
				criteria.add(criteriaBuilder.equal(fichaA.get("idEscolaridad"), pI));
			} else {
				throw new IllegalArgumentException("El parametro idEscolaridad tiene que ser entero");
			} 
			if(parametros.length > 1) {
				if(parametros[1] != null) {
					if(parametros[1] instanceof Integer) {
						pI = criteriaBuilder.parameter(Integer.class, "idMod");
						criteria.add(criteriaBuilder.equal(fichaA.get("idModalidad"), pI));
					} else {
						throw new IllegalArgumentException("El parametro idModalidad tiene que ser entero");
					}
				}
				if(parametros.length > 2) {
					if(parametros[2] != null) {
						if(parametros[2] instanceof EstatusAlumno) {
							ParameterExpression<EstatusAlumno> pEstatus = 
									criteriaBuilder.parameter(EstatusAlumno.class, "estatus");
							criteria.add(criteriaBuilder.equal(alumno.get("estatus"), pEstatus));
						} else {
							throw new IllegalArgumentException("El parametro estatus tiene que ser entero");
						}
					}
					if(parametros.length > 3) {
						if(parametros[3] != null) {
							if(parametros[3] instanceof String) {
								pCadena = criteriaBuilder.parameter(String.class, "matricula");
								criteria.add(criteriaBuilder.equal(alumno.get("matricula"), pCadena));
							} else {
								throw new IllegalArgumentException("El parametro matricula tiene que ser una cadena");
							}
						}
					}
				}
									
				if (criteria.size() == 1) {
					criteriaQuery.where(criteria.get(0));
				} else {
					criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[0])));
				}
			}
		}
		
		TypedQuery<Alumno> tq = em.createQuery(criteriaQuery);
		
		if(parametros.length > 0 ) {
			tq.setParameter("idEsc", parametros[0]);
			if(parametros.length > 1) {
				if(parametros[1] != null) {
					tq.setParameter("idMod", parametros[1]);
				}
				if(parametros.length > 2) {
					if(parametros[2] != null) {
						tq.setParameter("estatus", parametros[2]);
					}
					if(parametros.length > 3) {
						if(parametros[3] != null) {
							tq.setParameter("matricula", parametros[3]);
						}
					}
				}
			}
		}
		criteriaQuery.orderBy(criteriaBuilder.asc(alumno.get("datoPersona").get("apePaterno")));
		
		List<Alumno> alumnos = tq.getResultList();
		
		return alumnos;
	}
}
