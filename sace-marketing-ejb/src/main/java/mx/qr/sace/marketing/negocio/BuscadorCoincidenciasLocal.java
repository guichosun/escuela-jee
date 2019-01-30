package mx.qr.sace.marketing.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Carrera;

/**
 * Define el negocio para realizar una buscada por coincidencias.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Noviembre 2015
 * @copyright Direccion de sistemas - IFE
 */
@Local
public interface BuscadorCoincidenciasLocal {
	
	/**
	 * Recupera todas las coincidencias sobre alumnos con el estatus indicado
	 * @param sisEstudios
	 * @param carrera La carrera en la que se hara la busqueda.
	 * @param estatus
	 * @param cadena
	 * @return
	 */
	public List<Alumno> recuperaCoincidencias(SistemaEstudio sisEstudios,
			Carrera carrera, EstatusAlumno estatus, String cadena);
}
