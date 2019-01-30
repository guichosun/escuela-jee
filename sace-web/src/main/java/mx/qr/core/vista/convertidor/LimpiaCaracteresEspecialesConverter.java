package mx.qr.core.vista.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mx.qr.core.util.Utilerias;

/**
 * Un simple convertidor para limpiar una cadena de los caracteres especiales prohibidos! 
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@FacesConverter(value="limpiaCaracteresEspeciales")
public class LimpiaCaracteresEspecialesConverter implements Converter {

	char[] caracteresEspeciales = {'|', '\\','\"','<','>','@','&', '=', '(', ')'}; 
	
	// Se quito la comilla simple
	
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		
		if(!Utilerias.estaVacio(value)) {
			value =value.replace("\r", "");
			for(char c : caracteresEspeciales) {				
					value = value.replace(c, ' ');
			}
		}
		
		return value;
	}

	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		return value.toString();
	}

}