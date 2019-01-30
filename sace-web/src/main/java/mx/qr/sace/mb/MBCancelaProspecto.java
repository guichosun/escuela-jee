package mx.qr.sace.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.core.negocio.AlumnoPaginadoLocal;
import mx.qr.sace.formulario.FormularioCancelaProspecto;
import mx.qr.sace.formulario.FormularioProspecto;
import mx.qr.sace.marketing.negocio.BuscadorCoincidenciasLocal;
import mx.qr.sace.marketing.negocio.PreguntaEstadisticaMercadoLocal;
import mx.qr.sace.marketing.negocio.ProspectosPaginadoLocal;
import mx.qr.sace.marketing.negocio.RegistroProspectoLocal;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Pregunta;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Comportamiento para cancelar a un Prospecto.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
public class MBCancelaProspecto extends MBDefectoBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8096941728656161534L;
	
	private static final Logger log = Logger.getLogger(MBCancelaProspecto.class);
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/RegistroProspectoEJB")
	private RegistroProspectoLocal bean;
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar")
	private CatalogosCELocal beanCatalogoCE;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/ProspectosPaginadoEJB")
	private ProspectosPaginadoLocal paginadoProspectoBean;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/ProspectoCoincidenciaEJB")
	private BuscadorCoincidenciasLocal buscadorCoincidenciaBean;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/PreguntaEstadisticaMercadoEJB")
	private PreguntaEstadisticaMercadoLocal beanEstadistico;
	
	@EJB(mappedName="java:global/sace-ear/sace-marketing-ejb/AlumnoPaginadoEJB")
	private AlumnoPaginadoLocal beanAlPaginado;
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	private FormularioCancelaProspecto form;
	
	public void inicializa() {
		form = new FormularioCancelaProspecto();
	}
	
	/**
	 * Inicializa los elementos necesarios para el formulario, dependiendo si es captura o modifica
	 */
	public void inicializaForm(Integer como) {
		// DONE Recuperar las respuestas
		form.setRespuestasMkt(beanEstadistico.recuperaRespuestasAPregunta(
				new Pregunta(FormularioProspecto.PREGUNTA_MOTIVO_CANCELACION)));
		
		if(como == 1) {
			// DONE Revisar si hay prospectos registrados.
			int cantidad = beanAlPaginado.recuperaTotalDeRegistros(
					mbHome.getSistemaEstudioUsuario().getEscolaridad(),
					mbHome.getSistemaEstudioUsuario().getModalidad(), EstatusAlumno.PROSPECTO);
			
			if(cantidad > 0) {
				form.setProspectosRegistrados(true);
			} else {
				form.setProspectosRegistrados(false);
			}
			form.setMuestraResultado(true);
			form.setEstatusAlumno(EstatusAlumno.PROSPECTO);
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if(como == 3) {
			// DONE Revisar si hay prospectos cancelados
//			int cantidad = 0;
			int cantidad = beanAlPaginado.recuperaTotalDeRegistros(
					mbHome.getSistemaEstudioUsuario().getEscolaridad(),
					mbHome.getSistemaEstudioUsuario().getModalidad(), EstatusAlumno.CANCELADO);
			if(cantidad > 0) {
				form.setProspectosRegistrados(true);
			} else {
				form.setProspectosRegistrados(false);
			}
			form.setBtnGuardarDisabled(true);
			form.setMuestraResultado(false);
			form.setEstatusAlumno(EstatusAlumno.CANCELADO);
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		} else {
		}
	}

	/**
	 * Reemplaza al prospecto del formulario por el <code>prospectoDeAlerta</code>
	 */
	public void inicializaProspecto(Alumno prospectoDeAlerta) {
		//TODO Ir por la ficha academica de un alumno.
	}
	
	/**
	 * Call-back para el autocompleate para la busqueda de de coincidencias en los alumnos
	 * 
	 * @param String El valor que se va tecleando en el campo
	 */
	public List<Alumno> completa(String query) {
		EstatusAlumno estatus = EstatusAlumno.PROSPECTO;
		if(form.getAccionModulo() == MBDefecto.Accion.CONSULTA || 
			form.getAccionModulo() == MBDefecto.Accion.MODIFICA	) {
			
			estatus = EstatusAlumno.CANCELADO;
			
		}
		
		List<Alumno> res = buscadorCoincidenciaBean.recuperaCoincidencias(
				mbHome.getSistemaEstudioUsuario(),
				form.getCarreraSeleccionada(),
				estatus, query);
		
		return res;
	}

	/**
	 * Se usa tanto en captura como en consulta
	 * 
	 * @param event
	 */
	public void onItemSelect(SelectEvent event) {
		// Consultar el registro completo
		consultaHandler();
		
		// Establece el estado de CONSULTA sii no sea CAPTURA
		if(form.getAccionModulo() != MBDefecto.Accion.CAPTURA) {
				
			form.configuraEnConsulta();
				
		}
		
		
	}

	/**
	 * Accion para activar los campos para que sean editables.
	 * Basicamente tiene que cambiar la accion del modulo a MODIFICA, en caso de no estar puesta.
	 * 
	 * @return
	 */
	public String activaParaModificar() {
		
		if(form.getAccionModulo() == MBDefecto.Accion.CONSULTA) {
			form.setAccionModulo(MBDefecto.Accion.MODIFICA);
			
		}
		return null;
	}
	
	@Override
	public String guardaHandler() {
		// TODO Guarda el cambio de estatus del prospecto a cancelado
		form.codifica();
		
		form.getRespuestaAP().setUsuario(obtenUsuario().getUsername());
		
		try {
			bean.cancela(form.getDominio(), form.getRespuestaAP());
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			form.limpia();
		} catch (ApplicationException e) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			return "";
		}
		return OUTCOME_GUARDA;
	}

	@Override
	public String consultaHandler() {

		try {
			// Establece el estado de la respuesta sii no sea CAPTURA
			if(form.getAccionModulo() != MBDefecto.Accion.CAPTURA) {
				// TODO Recuperar el motivo de cancelacion para el alumno
				form.setRespuestaMktSelecconada(beanEstadistico.recuperaRespuestaDelAlumno(
						form.getDominio(),
						new Pregunta(FormularioProspecto.PREGUNTA_MOTIVO_CANCELACION)));
				form.setIdRespuestaMkt(form.getRespuestaMktSelecconada().getId().getIdRespuesta());
			}
		} catch (RegistroNoEncontradoException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String modificaHandler() {
		// TODO Para poder modificar el motivo de la cancelacion.
		
		form.codifica();
		
		try {
			beanEstadistico.modificaRespuesta(form.getDominio(),
					form.getRespuestaAP());
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE, 
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_modifica"));

			// Consultar el registro completo
			consultaHandler();
			
			// Regresa el formulario a consulta
			form.configuraEnConsulta();
			
		} catch (ApplicationException e) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", e.getMessage());
		}
		
		// Se queda en la misma pantalla
		return null;
	}

	@Override
	public String eliminaHandler() {
		return null;
	}
	
	public void actionListenerCancelaModifica(ActionEvent event) {
		// Regrsar al estado de CONSULTA
		form.configuraEnConsulta();
	}
	
	@Override
	public FormularioBase getForm() {
		return form;
	}
}
