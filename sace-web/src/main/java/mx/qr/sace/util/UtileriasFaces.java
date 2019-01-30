package mx.qr.sace.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Utilerias per-request para obtener del contexto de faces
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public final class UtileriasFaces {

	/**
	 * Recupera la instancia actual del cotexto Faces
	 * @return
	 */
	public static FacesContext obtenInstanciaFacesActual() {
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Recupera a instancia del contexto externo al de Faces
	 * @return
	 */
	public static ExternalContext obtenContextoExterno() {
		return obtenInstanciaFacesActual().getExternalContext();
	}
}
