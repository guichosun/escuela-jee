package mx.qr.core.vista;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.qr.sace.mb.MBPagosOtros;

import org.apache.log4j.Logger;

/**
 * MB con comportamiento por defecto y definicion de comportamiento que podran implementar
 * cada uno de los MB
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Abril 2014
 */
public abstract class MBDefecto implements Serializable {

	private static final long serialVersionUID = 8763518865231636141L;
	
	private static final Logger log = Logger.getLogger(MBDefecto.class);
	
	public static final String OUTCOME_GUARDA = "guarda";
	public static final String OUTCOME_CONSULTA = "";
	
	/** 
	 * La accion de modifica no genera ninguna transion. 
	 * Se queda en la misma page.
	 */
	public static final String OUTCOME_MODIFICA = "";
	public static final String OUTCOME_ELIMINA = "elimina";
	public static final String OUTCOME_CANCELA = "cancela";
	
	/**
	 * Las acciones que puede realizar sobre un modulo
	 */
	public enum Accion {
		CAPTURA("captura", UtileriasMessageSource.mensajeProperties("lbl_captura")), 
		CONSULTA("consulta", UtileriasMessageSource.mensajeProperties("lbl_consulta")), 
		MODIFICA("modifica", UtileriasMessageSource.mensajeProperties("lbl_modifica"));
		
		private String name;
		private String label;
		
		private Accion(String name, String label) {
			this.name = name;
			this.label = label;
		}

		public String getName() {
			return name;
		}
		
		public String getLabel() {
			return label;
		}
	}
	
	/**
	 * Para el manejo de los mensajes que se le envian al usuario 
	 */
	protected enum TipoMensaje {
		ERROR_MENSAJE("mensajesError", 1), INFO_MENSAJE("mensajesInfo", 2),
		ADVERTENCIA_MENSAJE("mensajesAdvertencia", 3);
		
		private TipoMensaje(String nombreMensaje, int tipo) {
			this.nombreMensaje = nombreMensaje;
			this.tipo = tipo;
		}
		
		private String nombreMensaje;
		private final int tipo;

		public String getNombreMensaje() {
			return nombreMensaje;
		}

		public int getTipo() {
			return tipo;
		}
	}
	
	public MBDefecto() {
	}
	
	/**
	 * Metodo auxiliar para mostrar un mensaje de error o de informacion
	 */
	protected void agregaMensaje(TipoMensaje tipoMensaje, String mensaje) {
		agregaMensaje(tipoMensaje.getTipo(), tipoMensaje.getNombreMensaje(), mensaje);
	}
	
	/**
	 * Metodo auxiliar para mostrar un mensaje de error o de informacion
	 */
	protected void agregaMensaje(int tipoMensaje, String nombreMensaje, String mensaje) {
		FacesMessage message = new FacesMessage();
		
		if(TipoMensaje.ERROR_MENSAJE.getTipo() == tipoMensaje) {
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		} else if(TipoMensaje.INFO_MENSAJE.getTipo() == tipoMensaje) {
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		} else {
			message.setSeverity(FacesMessage.SEVERITY_WARN);
		}
		
		message.setSummary(mensaje);
		FacesContext.getCurrentInstance().addMessage(nombreMensaje, message);
	}
	
	/**
	 * El que decide que handler se ejecuta
	 * 
	 * @return
	 */
	public String actionHandler() {
		if(getForm().getAccionModulo() == MBDefecto.Accion.CAPTURA) {
			return guardaHandler();
		} else {
			return modificaHandler();
		}
	}
	
	/**
	 * Handler basico para guardar el registro  en los MB
	 * @return
	 */
	public String guardaHandler() {
		getForm().codifica();
		agregaMensaje(TipoMensaje.INFO_MENSAJE,
				UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
		log.debug("Registro guardado!");
		return OUTCOME_GUARDA;
	}
	
	/**
	 * 
	 * @return
	 */
	public String consultaHandler() {
		getForm().decodifica();
		return OUTCOME_CONSULTA;
	}

	/**
	 * La accion de modificar
	 * @return
	 */
	public String modificaHandler() {
		getForm().codifica();
		return OUTCOME_MODIFICA;
	}
	
	/**
	 * La accion de eliminar
	 * @return
	 */
	public String eliminaHandler() {
		return OUTCOME_ELIMINA;
	}
	
	/**
	 * Recupera la instancia del formulario de cada MB
	 * @return
	 */
	public abstract FormularioBase getForm();
}
