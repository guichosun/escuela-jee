package mx.qr.sace.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.util.Utilerias;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.ca.negocio.RegistroCicloEscolarLocal;
import mx.qr.sace.formulario.FormularioPlaneacionCiclo;
import mx.qr.sace.persistencia.PeriodoEscolar;
import mx.qr.sace.persistencia.entidades.CicloEscolar;

import org.apache.log4j.Logger;

/**
 * MB para el modulo de planeacion del ciclo escolar.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Abril 2016
 * @copyright Q & R
 */
public class MBPlaneacionCiclo extends MBDefectoBase {

	private static final long serialVersionUID = -5470093310963081475L;
	
	private static final Logger log = Logger.getLogger(MBPlaneacionCiclo.class);

	@EJB(mappedName="java:global/sace-ear/sace-controlacademico-ejb/RegistroCicloEscolarEJB")
	private RegistroCicloEscolarLocal beanCiclo;
	
	private FormularioPlaneacionCiclo form;

	@Override
	public void inicializa() {
		form = new FormularioPlaneacionCiclo();
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
	
	/**
	 * Backing bean metodo tras bambalinas para mostrar 
	 * la informacion de una carrera seleccionada.
	 */
	public void muestraInformacionCarrera() {
		form.codificaDatosCarrera();
		
		// DONE Armar los ciclos que corresponden con los datos
		
		int yyyy = Utilerias.obtenCampoDelCalendario(Calendar.YEAR);
		CicloEscolar cic = form.creaCicloCascaron(yyyy, (yyyy + 1));
		cic.setUsuario(obtenUsuario().getUsername());
		form.setDominio(cic);
		
		// TODO Armar los periodos con la constante que traiga la carrera
		List<PeriodoEscolar> periodos = new ArrayList<PeriodoEscolar>();
		for(int xx = 1; xx<=form.getCantidadPeriodosPorCiclo(); xx++) {
			PeriodoEscolar pe = new PeriodoEscolar();
			pe.setUsuario(obtenUsuario().getUsername());
			periodos.add(pe);
		}
		form.setPeriodos(periodos);
		cic.setCarrera(form.getCarreraSeleccionada());
		form.setBtnGuardarDisabled(false);
		
	}
	
	@Override
	public FormularioPlaneacionCiclo getForm() {
		return form;
	}

	@Override
	public String guardaHandler() {
		super.guardaHandler();
		
		// TODO Llamar al bean de CA para guardar el ciclo
		try {
			beanCiclo.registra(form.getDominio(), form.getPeriodos());
			
			log.info("Ya guardó el ciclo: "+form.getDominio().getClave());
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			
			form.limpia();
		} catch (ApplicationException e) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			return "";
		}
		return OUTCOME_GUARDA;
	}
}
