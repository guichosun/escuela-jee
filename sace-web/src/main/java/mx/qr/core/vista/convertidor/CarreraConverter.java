package mx.qr.core.vista.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.qr.core.util.Utilerias;
import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.persistencia.entidades.Carrera;

import org.primefaces.component.picklist.PickList;

/**
 * Un simple convertidor de instancias de instancias de Carreras
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@FacesConverter(value = "carreraConverter", forClass=Carrera.class)
public class CarreraConverter implements Converter {

	private CatalogosCELocal beanPP;

	public CarreraConverter() {

		/*
		 * Como una alternativa de la inyeccion de la dependencia EJB que no
		 * esta soportada por esta version de jsf 2.1.x sino hasta la 2.3
		 */
		try {
			InitialContext ic = new InitialContext();
			beanPP = (CatalogosCELocal) ic
					.lookup("java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// @EJB(mappedName =
	// "java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar")
	// private CatalogosCEEJBLocal beanCatalogoCE;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		if (component instanceof HtmlSelectOneMenu) {
			// Con el value, obtener un partido
			if (!Utilerias.estaVacio(value)) {
				String[] ids = value.split(":");
				if (ids.length >= 3) {
					// Obtener la carrera apartir de la llave primaria
					Carrera carr = null;
					// try {
					carr = beanPP.obtenCarrera(Integer.valueOf(ids[0]),
							Integer.valueOf(ids[1]), Integer.valueOf(ids[2]));
					// } catch (ApplicationException e) {
					// e.printStackTrace();
					// }
					return carr;
				}
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
			Carrera carr = (Carrera) value;
			valueAsStr = carr.getId().getIdCarrera() + ":"
					+ carr.getId().getIdEscolaridad() + ":"
					+ carr.getId().getIdModalidad();
		}
		return valueAsStr;
	}

}