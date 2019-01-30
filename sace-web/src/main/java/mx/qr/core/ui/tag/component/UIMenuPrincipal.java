package mx.qr.core.ui.tag.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

/**
 * Comportamiento especifico para el Componente: guardar y restaurar el estado del mismo
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Marzo 2014
 * @copyright Direcci�n de sistemas - IFE
 */
@FacesComponent(value="menuPrincipal")
public class UIMenuPrincipal extends UIComponentBase {

	public static final String FAMILY_COMPONENT = "mx.ine.UIMenu";
	
	public static final String RENDERER_TYPE = "mx.ine.menu.Principal";
	
	public UIMenuPrincipal() {
		setRendererType(RENDERER_TYPE);
	}

	@Override
	public String getFamily() {
		return FAMILY_COMPONENT;
	}
}