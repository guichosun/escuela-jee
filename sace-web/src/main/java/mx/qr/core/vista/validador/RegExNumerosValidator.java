package mx.qr.core.vista.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.RegexValidator;
import javax.faces.validator.ValidatorException;

/**
 * Validador que permite valiar formatos numericos.
 * 
 * FYI: Es necesario pasarle la expresion en un attribute.
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Septiembre 2015
 * @copyright Q & R
 */
@FacesValidator(value="regExNumerosValidator")
public class RegExNumerosValidator extends RegexValidator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		String pattern = (String) component.getAttributes().get("pattern");

        if (pattern != null) {
            setPattern(pattern);
            String decimalStr = "";
            if(value != null) { 
	            if(value instanceof Integer) {
	            	Integer valor = (Integer)value;
	            	if(valor > 100) {
	            		FacesMessage message = new FacesMessage();
	            		throw new ValidatorException(message);
	            	}
	            	decimalStr = String.valueOf(valor);
	            } else if(value instanceof Long) {
	            	long valor = (Long)value;
	            	if(valor > 10.000) {
	            		FacesMessage message = new FacesMessage();
	            		throw new ValidatorException(message);
	            	}
	            	decimalStr = String.valueOf(valor);
	             }
	    		
	            super.validate(context, component, decimalStr);
            }
        }
	}
}
