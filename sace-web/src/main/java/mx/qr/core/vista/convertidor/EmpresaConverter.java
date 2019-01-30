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
import mx.qr.sace.persistencia.entidades.Empresa;

import org.primefaces.component.picklist.PickList;

/**
 * Un simple convertidor de instancias de instancias de Empresas
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@FacesConverter(value = "empresaConverter", forClass=Empresa.class)
public class EmpresaConverter implements Converter {

	private CatalogosMarketingLocal beanPP;

	public EmpresaConverter() {

		/*
		 * Como una alternativa de la inyeccion de la dependencia EJB que no
		 * esta soportada por esta version de jsf 2.1.x sino hasta la 2.3
		 */
		try {
			InitialContext ic = new InitialContext();
			beanPP = (CatalogosMarketingLocal) ic
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
			// Con el value, se trae la etiqueta generada en getAsString
			if (!Utilerias.estaVacio(value) && Utilerias.esNumero(value)) {
				int id = Integer.parseInt(value);
				// Obtener la carrera apartir de la llave primaria
				Empresa carr = null;
				if(id != 666) {
					 try {
						 carr = beanPP.recuperaEmpresa(id);
					 } catch (ApplicationException e) {
						 e.printStackTrace();
					 }
				} else {
					carr = new Empresa(666);
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
			Empresa carr = (Empresa) value;
			valueAsStr = String.valueOf(carr.getId());
		}
		return valueAsStr;
	}

}