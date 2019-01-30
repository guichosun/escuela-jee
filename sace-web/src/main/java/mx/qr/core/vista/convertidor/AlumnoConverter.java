package mx.qr.core.vista.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.sace.core.negocio.BuscarAlumnoLocal;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Alumno;

import org.apache.log4j.Logger;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.picklist.PickList;

/**
 * Un simple convertidor de instancias de Alumno
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
@FacesConverter(value = "alumnoConverter", forClass=Alumno.class)
public class AlumnoConverter implements Converter {

	private static final Logger log = Logger.getLogger(AlumnoConverter.class);
	
	private BuscarAlumnoLocal beanBuscar;

	public AlumnoConverter() {

		/*
		 * Como una alternativa de la inyeccion de la dependencia EJB que no
		 * esta soportada por esta version de jsf 2.1.x sino hasta la 2.3
		 */
		try {
			InitialContext ic = new InitialContext();
			beanBuscar = (BuscarAlumnoLocal) ic
					.lookup("java:global/sace-ear/sace-marketing-ejb/BuscarProspectoEJB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		SistemaEstudio sisEstudios = null;
		EstatusAlumno estatus = null;

		// Los parametros requeridos del convertidor. Para consultar al alumno
		sisEstudios = (SistemaEstudio)component.getAttributes().get("sistemaEstudio");
		if(component.getAttributes().containsKey("estatusAlumno")) {
			estatus = (EstatusAlumno)component.getAttributes().get("estatusAlumno");
		}
		
		if(component instanceof HtmlSelectOneMenu) {

		} else if(component instanceof PickList) {
			// Para que funciones este convertidor para mas componentes
		} else if(component instanceof AutoComplete) {
			try {
				ret = beanBuscar.recuperaPorMatricula(sisEstudios, estatus, value);
			} catch (ApplicationException e) {
				log.error("En el convertidor de Alumnos",e);
			}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component,
			Object value) {
		String valueAsStr = null;
		if (value != null) {
			Alumno val = (Alumno) value;
			valueAsStr = String.valueOf(val.getMatricula());
		}
		return valueAsStr;
	}

}