package mx.qr.sace.core.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * Define el comportamiento necesario para obtener Alumnos por paginado
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Octubre 2015
 * @copyright Direccion de sistemas - IFE
 */
@Local
public interface AlumnoPaginadoLocal {

	/**
	 * Recupera el conjunto de alumnos
	 * 
	 * @param indicePrimerElemento
	 * @param tamanioPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosColumna
	 * @param escolaridad
	 * @param modalidad
	 * @param estatus El estatus del alumno
	 * @return
	 */
	public List<Alumno> recuperaPorPaginado(
			int indicePrimerElemento, int tamanioPagina, 
			String campoOrden, int tipoOrdenamiento, Map<String, Object> filtrosColumna,
			Escolaridad escolaridad, Modalidad modalidad, EstatusAlumno estatus);

	/**
	 * Para recuperar la cantidad de alumnos del estatus, registrados por
	 * Escolaridad y Modalidad.
	 * 
	 * @param escolaridad
	 * @param modalidad
	 * @param estatus El estatus del alumno.
	 * @return
	 */
	public Integer recuperaTotalDeRegistros(Escolaridad escolaridad, Modalidad modalidad,
			EstatusAlumno estatus);
}
