package mx.qr.core.ui.tag.component;

import java.util.List;

import javax.faces.component.FacesComponent;

import mx.qr.core.ui.ModeloBigMenu;

/**
 * Comportamiento especifico para el Componente: guardar y restaurar el estado del mismo
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Marzo 2014
 * @copyright Q&R
 */
@FacesComponent(value="mx.ine.component.BigMenu")
public class UIBigMenu extends UIAbstractoMenu {

	public static final String FAMILY_COMPONENT = "mx.ine.UIMenu";
	public static final String RENDERER_TYPE = "mx.ine.component.menu.BigMenuRenderer";
	
	private static final boolean RENDER_REPORTES_DEFAULT = false;
	
	private enum Propiedades {
		modelo, renderHome, renderReportes;
		Propiedades() {
		}
	}
	
	public UIBigMenu() {
		setRendererType(RENDERER_TYPE);
	}

	@Override
	public String getFamily() {
		return FAMILY_COMPONENT;
	}
	
	public ModeloBigMenu getModelo() {
		// La interfaz StateHelper, define metodos como un Map para determinar el estado del componente
		return (ModeloBigMenu)getStateHelper().eval(Propiedades.modelo, null);
	}
	
	public void setModelo(ModeloBigMenu modelo) {
		getStateHelper().put(Propiedades.modelo, modelo);
	}
	
	public Boolean getRenderHome() {
		return (Boolean)getStateHelper().eval(Propiedades.renderHome, null);
	}
	
	public void setRenderHome(Boolean renderHome) {
		getStateHelper().put(Propiedades.renderHome, renderHome);
	}
	
	public Boolean getRenderReportes() {
		if(getStateHelper().eval(Propiedades.renderReportes, null) != null) {
			return (Boolean)getStateHelper().eval(Propiedades.renderReportes, null);
		} else {
			return RENDER_REPORTES_DEFAULT;
		}
	}
	
	/* Los setters los llama JSF cuando en el componente está presente un atributo */
	public void setRenderReportes(Boolean renderHome) {
		getStateHelper().put(Propiedades.renderReportes, renderHome);
	}
	
	
	public void manipulaAtributos(String nombre, Object value) {
		//TODO Terminar bien este comportamiento
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
		
//		getValueExpression(name);
	}
}