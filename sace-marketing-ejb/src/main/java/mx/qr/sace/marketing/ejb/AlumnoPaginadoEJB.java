package mx.qr.sace.marketing.ejb;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.persistencia.JPADAO;
import mx.qr.sace.core.negocio.AlumnoPaginadoLocal;
import mx.qr.sace.persistencia.dao.AlumnoDAO;
import mx.qr.sace.persistencia.dao.impl.AlumnoDAOImpl;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * Session Bean de impplementacion para la funcionalidad del paginado de prospectos
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
@Stateless(name="AlumnoPaginadoEJB", 
	mappedName="java:global/sace-ear/sace-marketing-ejb/AlumnoPaginadoEJB")
public class AlumnoPaginadoEJB implements AlumnoPaginadoLocal {

	@PersistenceContext(unitName = "sacePU")
	protected EntityManager em;
	
	@Resource
	private EJBContext context;
	
//	@JPADAO
	private AlumnoDAO alumnoDAO;
	
	@PostConstruct
	private void init() {
		alumnoDAO = new AlumnoDAOImpl(em);
	}
	
	@Override
	public List<Alumno> recuperaPorPaginado(int indicePrimerElemento,
			int tamanioPagina, String campoOrden, int tipoOrdenamiento,
			Map<String, Object> filtrosColumna, Escolaridad escolaridad,
			Modalidad modalidad, EstatusAlumno estatus) {
		
		return alumnoDAO.obtenPorPaginado(indicePrimerElemento, tamanioPagina,
				campoOrden, tipoOrdenamiento, filtrosColumna, 
				escolaridad, modalidad, estatus);
	}

	@Override
	public Integer recuperaTotalDeRegistros(Escolaridad escolaridad,
			Modalidad modalidad, EstatusAlumno estatus) {
		
		return alumnoDAO.obtenTotalDeRegistros(escolaridad, modalidad, estatus);
	}
	
}
