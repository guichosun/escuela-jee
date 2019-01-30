package mx.qr.sace.ce.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

/**
 * Define el comportamiento para obtener toda la informacion de los catalogos del Control Escolar.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@Local
public interface CatalogosCELocal {
	
	/**
	 * 
	 */
	public List<Escolaridad> obtenEscolaridades();
	
	/**
	 * 
	 */
	public List<Modalidad> obtenModalidadesPorEscolaridad(int idEscolaridad);

	/**
	 * 
	 * @param idCarrera
	 * @param idEscolaridad
	 * @param idModalidad
	 * @return
	 */
	public Carrera obtenCarrera(int idCarrera, int idEscolaridad, int idModalidad);
	
	/**
	 * Obtiene todas la carreras de la escolaridad y modalidad
	 *  
	 * @param idEscolaridad
	 * @param idModalidad
	 * @return
	 */
	public List<Carrera> obtenCarrerasPorEscolaridadYModalidad(Integer idEscolaridad, Integer idModalidad);
}
