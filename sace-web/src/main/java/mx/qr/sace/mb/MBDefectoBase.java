package mx.qr.sace.mb;

import javax.faces.event.ActionEvent;

import mx.qr.core.vista.MBDefecto;
import mx.qr.sace.UsuarioSACE;
import mx.qr.sace.dominio.SistemaEstudio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class MBDefectoBase extends MBDefecto {

	private static final long serialVersionUID = 3089673976522918170L;
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	/**
	 * 
	 */
	public abstract void inicializa();
	
	/**
	 * Inicializa los elementos necesarios para el formulario, dependiendo si es captura o modifica
	 */
	public abstract void inicializaForm(Integer como);

	/**
	 * Recupera el usuario que esta registrado actualmente en el sistema.
	 * 
	 * @return El usuario que est�� registrado en el sistema.
	 */
	public UsuarioSACE obtenUsuario() {
		//Se obtienen el usuario que est�� registrado en el sistema.
		
		SecurityContext context    = SecurityContextHolder.getContext();
		Authentication  auth       = context.getAuthentication();
		
		return (UsuarioSACE)auth.getPrincipal(); 
	}
	
	/**
	 * Accion que se llama de la hamburguesa paara activar los campos para modificar
	 * @return
	 */
	public String activaParaModificar() {
		// sii tiene que cambiar de estatus el formulario si hubiera registros.
		if(getForm().isHayRegistros()) {
			if(getForm().getAccionModulo() == MBDefecto.Accion.CONSULTA) {
				getForm().setModoLectura(false);
				getForm().setAccionModulo(MBDefecto.Accion.MODIFICA);
			}
		}
		return null;
	}

	/**
	 * Listener en el boton de cancelar la modificada y regresar a la consulta.
	 * 
	 * @param event
	 */
	public void actionListenerCancelaModifica(ActionEvent event) {
		// Regrsar al estado de CONSULTA
		getForm().configuraEnConsulta();
	}
	
	/**
	 * Para inicializar el sistema de estudios.
	 * 
	 * Al entrar a un modulo.
	 * De primera vez, el sistema sera tomado del usuario que entro en el sistema y
	 * para despues de la accion de guardar serà tomado de 
	 * la seleccion del componente en el modulo
	 * @param sistema
	 */
	public void inicializaSistemaEstudios(SistemaEstudio sistema) {
		if(sistema == null) {
			// El sistema de estudios que se obtiene al entrar al sistema
			getForm().setSistemaEstudioFormulario(mbHome.getSistemaEstudioUsuario());
		} else {
			getForm().setSistemaEstudioFormulario(sistema);
		}
	}
}
