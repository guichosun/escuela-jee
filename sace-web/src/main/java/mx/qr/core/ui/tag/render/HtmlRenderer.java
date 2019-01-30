package mx.qr.core.ui.tag.render;

import javax.faces.application.ViewHandler;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

/**
 * Superclass para todos los renders que pintan componentes HTML
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Marzo 2014
 * @copyright Direcci�n de sistemas - IFE
 */
public abstract class HtmlRenderer extends Renderer {

	/**
	 * Constructo vacio.
	 */
	public HtmlRenderer() {
	}

	/**
	 * Codifica el recurso agregandole el contextPath
	 * @param context
	 * @return
	 */
	protected String obtenUrlLink(FacesContext context, String link) {
		ViewHandler handler = context.getApplication().getViewHandler();
		String url = handler.getResourceURL(context, link); 
		
		return context.getExternalContext().encodeResourceURL(url);
	}
	
}
