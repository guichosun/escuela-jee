package mx.qr.core.vista;

import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Utility class para la aplicacion
 * 
 * @author 
 */
public final class UtileriasMessageSource {

	/**
	 * Extrae las variables del properties
	 * 
	 * @param llave - Identificador del valor extraer
	 * @return mensaje en forma de cadena
	 */
	public static String mensajeProperties(String llave) {
		Object[] params = null;
		return mensajeProperties(llave, params);
	}

	/**
	 * Extrae la variable del properties con parametros del archivo properties
	 * @param llave
	 * @param parametros
	 * @return
	 */
	public static String mensajeProperties(String llave, Object... parametros) {
		ResourceBundleMessageSource messageSource = ((ResourceBundleMessageSource) UtileriasSpringContext
				.getApplicationContext().getBean("messageSource"));
		
		return messageSource.getMessage(llave, parametros, null);
	}

}
