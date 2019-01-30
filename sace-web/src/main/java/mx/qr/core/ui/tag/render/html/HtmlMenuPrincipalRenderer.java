package mx.qr.core.ui.tag.render.html;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import mx.qr.core.ui.tag.render.HtmlRenderer;
import mx.qr.core.util.Utilerias;
import mx.qr.core.web.vista.OpcionMenuSistema;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Mayo 2014
 * @copyright Direccion de sistemas - IFE
 */
@FacesRenderer(componentFamily="mx.ine.UIMenu", rendererType="mx.ine.menu.Principal")
public class HtmlMenuPrincipalRenderer extends HtmlRenderer {
	
	/*
	 * Url del home del menu
	 */
	private static final String URL_HOME = "/app/home";
	private static final String LBL_HOME = "Home";
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		String clientId = component.getClientId();
		char sep = UINamingContainer.getSeparatorChar(context);
		
		writer.startElement("ul", component);
		writer.writeAttribute("class", "nav navbar-nav", null);
		
		encodeMenu(context, component, clientId + sep + "a");
		
		writer.endElement("ul");
		String renderMenu = (String)component.getAttributes().get("renderReportes");
		boolean b = Utilerias.toBoolean(renderMenu);
		
		if(b) {
			
			writer.startElement("ul", null);
			writer.writeAttribute("class", "nav navbar-nav navbar-right", null);
			writer.startElement("a", null);
			writer.writeAttribute("class", "btn btn-default navbar-btn", null);
			writer.writeAttribute("href", obtenUrlLink(context, "/app/reportes"), null);
			writer.writeText("Reportes", null);
			writer.endElement("a");
	        writer.endElement("ul");
		}
	}
	
	private void encodeMenu(FacesContext context, UIComponent component, String clientId) throws IOException {
		
		//TODO Hacer requerido el atributo opciones
		List<OpcionMenuSistema> opciones = (List<OpcionMenuSistema>)component.getAttributes().get("opciones");
		String renderMenu = (String)component.getAttributes().get("renderHome");
		String urlHome = (String)component.getAttributes().get("urlHome");
		String lblHome = (String)component.getAttributes().get("lblHome");
		
		if(Utilerias.estaVacio(urlHome)) {
			urlHome = URL_HOME;
		}
		if(Utilerias.estaVacio(lblHome)) {
			lblHome = LBL_HOME;
		}
		boolean b = Utilerias.toBoolean(renderMenu); 

		ResponseWriter writer = context.getResponseWriter();
		
		if(b) {
			encodeItemModule(context, component, clientId+":home", lblHome, urlHome);
		}

		Integer etapa = 0;		
		Integer modulo = 0;
		int x = 0;
		int etapaCount = 1;
		int moduloCount = 1;
//		for(OpcionMenuSistema ms : opciones) {
		
		for(int i = 1; i <= opciones.size() + 1; i++) {
			
			if( i > opciones.size()) {
				// Cierra los elementos de la ultima etapa pindada
				writer.endElement("ul");		
				writer.endElement("li");
				break;
			}
			
			OpcionMenuSistema ms = opciones.get(i - 1);
			
			if(!ms.isPrinteable()) {
				continue;
			}
			// Para pintar la etapa en el menu
			if(etapa != ms.getEtapa().getIdEtapa()) {
				etapa = ms.getEtapa().getIdEtapa();

				// Al cambio de menu cerrar la etapa
				if((x ^ 0) == 1) { 
					writer.endElement("ul");		
					writer.endElement("li");
				}
				
				encodeItemEtapa(context, component, clientId+"etapa" + etapaCount++, 
						ms.getEtapa().getDescrpcion(), "#");
				x = 1;
			}
			
			if(modulo != ms.getModulo().getIdModulo()) {
				
			
				if (ms.getModulo().getEstatus() != null){
					if (ms.getModulo().getEstatus().equalsIgnoreCase("N") && ms.getAccion().getIdAccion() == 2){
						
						modulo = ms.getModulo().getIdModulo();
						encodeItemModule(context, component, clientId+"modulo"+ moduloCount++, 
							ms.getModulo().getNombre(), ms.getModulo().getUrl());
					}
					if (ms.getModulo().getEstatus().equalsIgnoreCase("A") ){
						
						modulo = ms.getModulo().getIdModulo();
						encodeItemModule(context, component, clientId+"modulo"+ moduloCount++, 
							ms.getModulo().getNombre(), ms.getModulo().getUrl());
					}
				}
			}
			
//			x = 1;
		}
	
	}
	
	private void encodeItemEtapa(FacesContext context, UIComponent component, String clientId, String title, String url) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("li", component);
		writer.writeAttribute("class", "menu-item dropdown", null);
		// El titulo de la etapa
		writer.startElement("a", component);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("class", "dropdown-toggle", null);
		writer.writeAttribute("data-toggle", "dropdown", null);
		writer.writeAttribute("href", obtenUrlLink(context, url), null);
		writer.writeText(title, null);
		writer.startElement("b", component);
		writer.writeAttribute("class", "caret", null);
		writer.endElement("b");
		writer.endElement("a");
		
		writer.startElement("ul", component);
		writer.writeAttribute("class", "dropdown-menu", null);
	}
	
	private void encodeItemModule(FacesContext context, UIComponent component, String clientId, String title, String url) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("li", component);
		writer.startElement("a", component);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("href", obtenUrlLink(context, url), null);
		writer.writeText(title, null);
		writer.endElement("a");
		writer.endElement("li");
	}	
}
