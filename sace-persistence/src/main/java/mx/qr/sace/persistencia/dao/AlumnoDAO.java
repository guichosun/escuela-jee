package mx.qr.sace.persistencia.dao;

import java.util.List;
import java.util.Map;

import mx.qr.core.persistencia.EntidadDAO;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 2015
 * @copyright Q & R
 */
public interface AlumnoDAO extends EntidadDAO<Integer, Alumno>{

	public List<Alumno> obtenPorPaginado(int indicePrimerElemento,
			int tamanioPagina, String campoOrden, int tipoOrdenamiento,
			Map<String, Object> filtrosColumna, Escolaridad escolaridad,
			Modalidad modalidad, EstatusAlumno estatus);
	
	public int obtenTotalDeRegistros(Escolaridad escolaridad,
			Modalidad modalidad, EstatusAlumno estatus);
	
	/** 
	 * 
	 * @param sisEstudios
	 * @param parametros Parametros para filtrar a los alumnos
	 * @return
	 */
	public List<Alumno> obtenPorCadena(SistemaEstudio sisEstudios, Object...parametros);
}
