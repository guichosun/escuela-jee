package mx.qr.sace.mb;

import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.MBDefecto;
import mx.qr.sace.formulario.FormularioPagoConcepto;
import mx.qr.sace.formulario.FormularioRegistroBeca;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Comportamiento para modelar el contolador del registro de la ficha de cobros.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Noviembre 2016
 * @copyright Q & R
 */
public class MBPagosFichas extends MBDefectoBase {

	private static final long serialVersionUID = 8096941728656161666L;
	
	private static final Logger log = Logger.getLogger(MBPagosFichas.class);
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	private FormularioPagoConcepto form;
	
	public void inicializa() {
		form = new FormularioPagoConcepto(obtenUsuario().getUsername());
	}
	
	/**
	 * Inicializa los elementos necesarios para el formulario, dependiendo si es captura o modifica
	 */
	public void inicializaForm(Integer como) {

		if(como == 1) {
			
			// DONE CREAR LOS OBJETOS DE DOMINIO
			form.inicializa();
			
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if(como == 3) {
			
			// Los campos se ponen en modo lectura
			form.setModoLectura(true);
			
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		}
	}

	@Override
	public FormularioBase getForm() {
		return form;
	}
}
