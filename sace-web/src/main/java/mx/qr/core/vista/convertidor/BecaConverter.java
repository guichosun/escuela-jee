package mx.qr.core.vista.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.util.Utilerias;
import mx.qr.sace.marketing.negocio.CatalogosMarketingLocal;
import mx.qr.sace.marketing.negocio.PropuestaDeMercadoLocal;
import mx.qr.sace.persistencia.entidades.Beca;

import org.primefaces.component.picklist.PickList;

/**
 * Un simple convertidor de instancias de Beca
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Septiembre 2015
 * @copyright Q & R
 */
@FacesConverter(value = "becaConverter", forClass = Beca.class)
public class BecaConverter implements Converter {

	private PropuestaDeMercadoLocal beanPP;
	
	private CatalogosMarketingLocal beanCM;
	
	public BecaConverter() {

		/*
		 * Como una alternativa de la inyeccion de la dependencia EJB que no
		 * esta soportada por esta version de jsf 2.1.x sino hasta la 2.3
		 */
		try {
			InitialContext ic = new InitialContext();
//			beanPP = (PropuestaDeMercadoLocal) ic
//					.lookup("java:global/sace-ear/sace-marketing-ejb/PropuestaDeMercadoEJB");
			beanCM = (CatalogosMarketingLocal) ic
					.lookup("java:global/sace-ear/sace-marketing-ejb/CatalogosMarketingEJB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		if (component instanceof HtmlSelectOneMenu) {
			// Con el value, obtener un partido
			if (!Utilerias.estaVacio(value)) {
				// Obtener la instancia de la base
				Beca carr = null;
				try {
					carr = beanCM.recuperaBeca(Integer.parseInt(value));
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				return carr;
			}
		} else if (component instanceof PickList) {
			// Para que funciones este convertidor para mas componentes
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component,
			Object value) {
		String valueAsStr = null;
		if (value != null) {
			Beca carr = (Beca) value;
			valueAsStr = String.valueOf(carr.getIdBeca());
		}
		return valueAsStr;
	}

}