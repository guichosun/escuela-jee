package mx.qr.core.vista;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("applicationContextUtils")
public class UtileriasSpringContext implements ApplicationContextAware {

	/**
	 * Objeto que contiene la referencia al Applicaction Contex de Spring Framework.
	 */
	private static ApplicationContext applicationContext;	
	
	/**
	 * Regresa el objeto que contiene la referencia al Applicaction Contex de 
	 * Spring Framework.
	 * 
	 * @return El valor del atributo <code>applicationContext</code>.
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * Establece el objeto que contiene la referencia al Applicaction Contex de 
	 * Spring Framework.
	 * 
	 * @param applicationContext Objeto que contiene la referencia al Applicaction Contex de 
	 * Spring Framework.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 UtileriasSpringContext.applicationContext = applicationContext;
	}	 	 
}
