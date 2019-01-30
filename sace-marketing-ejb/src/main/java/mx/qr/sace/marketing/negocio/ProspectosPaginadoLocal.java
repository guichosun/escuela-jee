package mx.qr.sace.marketing.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * Define el comportamiento necesario para obtener a Alumno prospecto por paginado
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Octubre 2015
 * @copyright Direccion de sistemas - IFE
 */
@Local
public interface ProspectosPaginadoLocal {

	/**
	 * Recupera el conjunto de prospectos
	 * 
	 * @param indicePrimerElemento
	 * @param tamanioPagina
	 * @param campoOrden
	 * @param tipoOrdenamiento
	 * @param filtrosColumna
	 * @param escolaridad
	 * @param modalidad
	 * @return
	 */
	public List<Alumno> recuperaProspectosPorPaginado(
			int indicePrimerElemento, int tamanioPagina, 
			String campoOrden, int tipoOrdenamiento, Map<String, Object> filtrosColumna,
			Escolaridad escolaridad, Modalidad modalidad);

	/**
	 * Para recuperar la cantidad de registros segun la escolaridad y la modalidad
	 * 
	 * @param escolaridad
	 * @param modalidad
	 * @return
	 */
	public Integer recuperaTotalDeRegistros(Escolaridad escolaridad, Modalidad modalidad);
}
