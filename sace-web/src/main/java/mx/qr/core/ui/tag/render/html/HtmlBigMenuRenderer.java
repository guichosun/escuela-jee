package mx.qr.core.ui.tag.render.html;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import mx.qr.core.ui.Etapa;
import mx.qr.core.ui.Modulo;
import mx.qr.core.ui.Pestanha;
import mx.qr.core.ui.tag.component.UIAbstractoMenu;
import mx.qr.core.ui.tag.component.UIBigMenu;
import mx.qr.core.ui.tag.render.HtmlRenderer;
import mx.qr.core.util.Utilerias;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Mayo 2014
 * @copyright Direccion de sistemas - IFE
 */
@FacesRenderer(componentFamily="mx.ine.UIMenu", rendererType="mx.ine.component.menu.BigMenuRenderer")
public class HtmlBigMenuRenderer extends HtmlRenderer {
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		UIBigMenu uiBigMenu = (UIBigMenu)component;
		
		if(uiBigMenu.esDinamico()) {
			// Construir el big menu a partir del modelo.
			/*
			 * Ojo por lo apresurado no llega la idea de construir el menu con puras instancias UI
			 * Para una segunda version, considerar construir el menu agregando los elementos del modelo
			 * al arbol de hijos del TreeView.
			 * Para esta version se hara con el ResponseWriter y las class Pestnha, Etapa, Modulo
			 */
			
			encodeMenu(context, uiBigMenu);
		}
	}
	
	// Este metodo podra ser abstracto
	private void encodeMenu(FacesContext context, UIBigMenu uiBigMenu) throws IOException {
//		UIBigMenu uiBigMenu = (UIBigMenu)component;
		
		ResponseWriter writer = context.getResponseWriter();
		
		String clientId = uiBigMenu.getClientId();
		
		writer.startElement("div", uiBigMenu);
		writer.writeAttribute("id", clientId, "id");
		writer.writeAttribute("class", UIAbstractoMenu.BIGMENU_CLASS, null); //navbar yamm navbar-default
		writer.startElement("div", null);
		writer.writeAttribute("class", UIAbstractoMenu.CONTAINER, null);
		writer.startElement("div", null);
		writer.writeAttribute("class", UIAbstractoMenu.NAVBAR_HEADER_CLASS, null);
		writer.startElement("button", null);
		writer.writeAttribute("type", "button", null);
		writer.writeAttribute("data-toggle", "collapse", null);
		writer.writeAttribute("data-target", "#navbar-collapse-1", null);
		writer.writeAttribute("class", UIAbstractoMenu.NAVBAR_TOGGLE_CLASS, null);
		writer.startElement("span", null);
		writer.writeAttribute("class", UIAbstractoMenu.ICON_BAR_CLASS, null);
		writer.endElement("span");
		writer.startElement("span", null);
		writer.writeAttribute("class", UIAbstractoMenu.ICON_BAR_CLASS, null);
		writer.endElement("span");
		writer.startElement("span", null);
		writer.writeAttribute("class", UIAbstractoMenu.ICON_BAR_CLASS, null);
		writer.endElement("span");
		writer.endElement("button");
		writer.endElement("div"); // </div navbar-header
		
		writer.startElement("div", null);
		writer.writeAttribute("id", "navbar-collapse-1", null);
		writer.writeAttribute("class", UIAbstractoMenu.NAVBAR_COLLAPSE_CLASS, null);
		
		writer.startElement("ul", null);
		writer.writeAttribute("class", UIAbstractoMenu.NAVBAR_NAV_CLASS, null);
		
		// Las pestañas en un ciclo
		for(int x = 0; x < uiBigMenu.getModelo().getPestanhas().size(); x++) {
			Pestanha pes = uiBigMenu.getModelo().getPestanhas().get(x);
			if(pes.getEtapas().size() > 0) {
				encodeItemPestanha(context, pes);
			}
		}
		
		writer.endElement("ul");
		
		if(uiBigMenu.getRenderReportes()) {
			writer.startElement("ul", null);
			writer.writeAttribute("class", UIAbstractoMenu.NAVBAR_RIGHT_CLASS, null);
			writer.startElement("a", null);
			writer.writeAttribute("class", UIAbstractoMenu.BTN_REPORTES_CLASS, null);
			writer.writeAttribute("href", obtenUrlLink(context, "/app/reportes"), null);
			writer.writeText("Reportes", null);
			writer.endElement("a");
			writer.endElement("ul");
		}
		
		writer.endElement("div"); // </div navbar-collapse-1
		
		writer.endElement("div"); // </div row
		writer.endElement("div"); // </div navbar yamm navbar-default
		

	}
	
	protected void encodeItemPestanha(FacesContext context, Pestanha pes) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		// El titulo de la etapa
		writer.startElement("li", null);
		writer.writeAttribute("class", UIAbstractoMenu.PESTANHA_CLASS, null);
		writer.startElement("a", null);
		writer.writeAttribute("href", obtenUrlLink(context, "#"), null);
		writer.writeAttribute("data-toggle", "dropdown", null);
		writer.writeAttribute("class", UIAbstractoMenu.DROPDOWN_TOGGLE_CLASS, null);
//		writer.writeText("NOMBRE DE LA PESTAÑA", null);
		writer.writeText(pes.getNombre(), null);
		writer.startElement("b", null);
		writer.writeAttribute("class", UIAbstractoMenu.CARET_CLASS, null);
		writer.endElement("b");
		writer.endElement("a");
		writer.startElement("ul", null);
		writer.writeAttribute("class", UIAbstractoMenu.DROPDOWN_MENU_CLASS, null);
		writer.startElement("li", null);
		writer.writeAttribute("class", UIAbstractoMenu.YAMM_CONTENT_CLASS, null);
		writer.startElement("div", null);
		writer.writeAttribute("class", UIAbstractoMenu.ROW_CLASS, null);
		
		// Las etapas en un ciclo
		// Verificar bien este algoritmo para evitar ArrayIndexException
		
		int id = 0;
		if( pes.getEtapas().size() <= 5) {
			writer.startElement("div", null);
			if(pes.getEtapas().size() == 1) 
				writer.writeAttribute("class", UIAbstractoMenu.COL_12_CLASS, null);
			else
				writer.writeAttribute("class", UIAbstractoMenu.COL_6_CLASS, null);
			int mitad = pes.getEtapas().size() / 2;
			if(pes.getEtapas().size() <= 1) {
				mitad = 1;
			}
			for(id = 0 ;id < mitad ; id++) {
				Etapa eta = pes.getEtapas().get(id);
				encodeItemEtapa(context, eta);
			}
			writer.endElement("div");
			
			if(id < pes.getEtapas().size()) {
				writer.startElement("div", null);
				writer.writeAttribute("class", UIAbstractoMenu.COL_6_CLASS, null);
				for( ;id < pes.getEtapas().size() ; id++) {
					Etapa eta = pes.getEtapas().get(id);
					encodeItemEtapa(context, eta);
				}
				writer.endElement("div");	
			}
		} else { // Para mas de 5 etapas tres columnas
			
			//TODO Optimizar este algoritmo
			
			writer.startElement("div", null);
			writer.writeAttribute("class", UIAbstractoMenu.COL_4_CLASS, null);
			// Primeros 3
			int mitad = 3;
			for(id = 0; id < mitad; id++) {
				Etapa eta = pes.getEtapas().get(id);
				encodeItemEtapa(context, eta);
			}
			writer.endElement("div");
			
			// Otros 3, ya serían 6
			mitad = mitad + 3;
			if(id < mitad) {
				writer.startElement("div", null);
				writer.writeAttribute("class", UIAbstractoMenu.COL_4_CLASS, null);
				if(mitad > pes.getEtapas().size()) {
					mitad = pes.getEtapas().size();
				}
				for( ;id < mitad ; id++) {
					Etapa eta = pes.getEtapas().get(id);
					encodeItemEtapa(context, eta);
				}
				writer.endElement("div");
				mitad = mitad + 3;
				if(id < mitad) {
					writer.startElement("div", null);
					writer.writeAttribute("class", UIAbstractoMenu.COL_4_CLASS, null);
					if(mitad > pes.getEtapas().size()) {
						mitad = pes.getEtapas().size();
					}
					for( ;id < mitad ; id++) {
						Etapa eta = pes.getEtapas().get(id);
						encodeItemEtapa(context, eta);
					}
					writer.endElement("div");	
				}
			}
			
		}
		writer.endElement("div");
		writer.endElement("li");
		writer.endElement("ul");
		writer.endElement("li");
		
	}
	
	protected void encodeItemEtapa(FacesContext context, Etapa eta) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		// El titulo de la etapa
		writer.startElement("label", null);
//		writer.writeText("NOMBRE DE LA ETAPA", null);
		writer.writeText(eta.getNombre(), null);
		writer.endElement("label");
		
		writer.startElement("ul", null);
		
		// Los modulos en un ciclo
		for(Modulo mod : eta.getModulos()) {
			encodeItemModule(context, mod);
		}
		writer.endElement("ul");
		
	}
	
	protected void encodeItemModule(FacesContext context, Modulo mod) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("a", null);
		String url = "#";
		if(!Utilerias.estaVacio(mod.getLiga())) {
			url = obtenUrlLink(context, mod.getLiga());
		}
		writer.writeAttribute("href", url, null);
//		writer.writeText("NOMBRE MODULO", null);
		writer.startElement("li", null);		
		writer.writeText(mod.getNombre(), null);
		writer.endElement("li");		
		writer.endElement("a");
	}	
}
