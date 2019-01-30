package mx.qr.sace.mb;

import java.util.List;

import javax.ejb.EJB;

import mx.qr.core.vista.MBDefecto;
import mx.qr.sace.core.negocio.AlumnoPaginadoLocal;
import mx.qr.sace.formulario.FormularioAlumno;
import mx.qr.sace.marketing.negocio.BuscadorCoincidenciasLocal;
import mx.qr.sace.persistencia.entidades.Alumno;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Comportamiento Base para el autocomplete de un alumno por la matricula.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public abstract class MBAutoCompleta extends MBDefectoBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3016782906194832312L;

	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/ProspectoCoincidenciaEJB")
	protected BuscadorCoincidenciasLocal buscadorCoincidenciaBean;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/AlumnoPaginadoEJB")
	protected AlumnoPaginadoLocal beanAlPaginado;
	
	@Autowired
	@Qualifier("mbHome")
	protected transient MBHome mbHome;
	
	/**
	 * Call-back para el autocompleate para la busqueda de de coincidencias en los alumnos
	 * 
	 * @param String El valor que se va tecleando en el campo
	 */
	public List<Alumno> completa(String query) {
		
		return buscadorCoincidenciaBean.recuperaCoincidencias(
				getForm().getSistemaEstudioFormulario(),
				getForm().getCarreraSeleccionada(), 
				getForm().getEstatusAlumnoEnUso(), query);
//		return buscadorCoincidenciaBean.recuperaCoincidencias(
//				mbHome.getSistemaEstudioUsuario(), getForm().getEstatusAlumnoEnUso(), query);
		
	}

	/**
	 * Call-back para seleccionar un item de la lista del autocomplete.
	 * 
	 * @param event
	 */
	public void onItemSelect(SelectEvent event) {
		// Consultar el registro completo
		consultaHandler();
		
		// Establece el estado de CONSULTA sii no sea CAPTURA
		if(getForm().getAccionModulo() != MBDefecto.Accion.CAPTURA) {
				
			getForm().configuraEnConsulta();
				
		}
	}
	
	public abstract FormularioAlumno getForm();
}
