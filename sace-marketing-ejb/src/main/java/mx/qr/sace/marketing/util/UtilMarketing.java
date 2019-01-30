package mx.qr.sace.marketing.util;

import java.util.Calendar;

import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.FichaAcademica;


/**
 * Utility class con metodos auxiliares para el modulo de Marketing
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Octubre 2015
 * @copyright Q & R
 */
public final class UtilMarketing {
	
	/**
	 * Para generar la matricula de un alumno
	 * 
	 * @param fichaAcademica
	 * @return
	 */
	public static String obtenMatricula(Alumno alumno, FichaAcademica fichaAcademica) {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(fichaAcademica.getCarrera().getCodigoIdentificador());
    	sb.append(fichaAcademica.turnoFicha());
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(alumno.getFechaRegistro());
    	sb.append(UtilMarketing.reduceDigitos(cal.get(Calendar.YEAR)));
    	sb.append(UtilMarketing.normalizaDigito(cal.get(Calendar.MONTH) + 1, 10));
    	
    	// TODO Revisar reiniciar secuencia
    	sb.append(alumno.getIdAlumno());
    	return sb.toString();
    }
	
	/**
	 * Normaliza un digito agregando ceros como lo ndique <code>escala</code> a la izquierda
	 * 
	 * @param digito
	 * @param escala
	 * @return
	 */
	public static String normalizaDigito(int digito, int escala) {
		String ret = "";
		if(escala <= 10) {
			if(digito < 10) {
				ret = "0" + String.valueOf(digito);
			} else {
				ret = String.valueOf(digito);
			}
		} else {
			
		}
		return ret;
	}
	
	/**
	 * Reduce a los dos ultimos digitos de un año formado por AAAA
	 * 
	 * @param anho
	 * @return
	 */
	public static String reduceDigitos(int anho) {
		String ret = String.valueOf(anho);
		return ret.substring(ret.length()-2, ret.length());
	}
	
	
}
