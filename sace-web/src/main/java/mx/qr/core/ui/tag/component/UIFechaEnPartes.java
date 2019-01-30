package mx.qr.core.ui.tag.component;

import javax.faces.component.UIComponentBase;

/**
 * Superclass para los componentes de Menu (MenuPrincipal y BigMenu)
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Abril 2015
 * @copyright Direccion de sistemas - IFE
 */
public class UIFechaEnPartes extends UIComponentBase {

	public static final String FAMILY_COMPONENT = "mx.ine.UIFecha";
    
	@Override
	public String getFamily() {
		return FAMILY_COMPONENT;
	}
	
	/*
	 * - Un atributo donde se pasara el Date ya convertido
	 * - Una lista de meses
	 * - (opcionale) una lista de años o un año por default.
	 * - El mes por seleccionar
	 * 
	 */
}