package mx.qr.sace.mb;

import javax.ejb.EJB;

import mx.qr.core.vista.MBDefecto;
import mx.qr.sace.ce.negocio.ProcesoInscripcionLocal;
import mx.qr.sace.formulario.FormularioRegistroGrupo;

/**
 * MB para el proceso de registrar a un grupo
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Abril 2016
 * @copyright Q & R
 */
public class MBRegistroGrupo extends MBDefectoBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4173310045483132799L;

	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/ServicioInscripcionEJB")
	private ProcesoInscripcionLocal beanInscripcion;
	
	private FormularioRegistroGrupo form;

	@Override
	public void inicializa() {
		form = new FormularioRegistroGrupo();
	}

	@Override
	public void inicializaForm(Integer como) {
		
		if (como == 1) {
			form.setBtnGuardarDisabled(true);
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if (como == 3) {
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		}
	}

	@Override
	public FormularioRegistroGrupo getForm() {
		return form;
	}
}
