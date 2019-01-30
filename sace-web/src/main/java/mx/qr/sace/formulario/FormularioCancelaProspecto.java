package mx.qr.sace.formulario;

import java.util.ArrayList;
import java.util.List;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.core.vista.MBDefecto.Accion;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Noviembre 2015
 * @copyright Q & R
 */
public class FormularioCancelaProspecto extends FormularioProspecto {

	private static final long serialVersionUID = -5160122193603273006L;

	private Integer idMotivo;
	
	/*
	 * El estatus del alumno que se maneja en el modulo, puede tomar
	 * los valores de PROSPECTO o CANCELADO
	 */
	private EstatusAlumno estatusAlumno;
	
	/**
	 * 
	 */
	public FormularioCancelaProspecto() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_cancela_prospectos_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_cancela_prospectos_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_cancela_prospectos_migaja2"));
	}

	@Override
	public void codifica() {
		setRespuestaAP(new RespuestaAPregunta());
		getRespuestaAP().setIdAlumno(getDominio().getIdAlumno());
		// La pregunta 2 es del motivo de cancelacion.
		getRespuestaAP().setIdPregunta(PREGUNTA_MOTIVO_CANCELACION);
		getRespuestaAP().setIdRespuesta(getIdRespuestaMkt());
	}

	@Override
	public void decodifica() {
		

	}

	@Override
	public void limpia() {

	}

	@Override
	public List<String> getMigajas() {
		return migajas;
	}

	public Integer getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}

	public EstatusAlumno getEstatusAlumno() {
		return estatusAlumno;
	}

	public void setEstatusAlumno(EstatusAlumno estatusAlumno) {
		this.estatusAlumno = estatusAlumno;
	}
	
	/**
	 * Listener para limpiar el formulario al seleccionar 
	 * la escolaridad
	 */
	public void limpiaSeleccionEscolaridad() {
		getSistemaEstudioFormulario().getModalidad().setIdModalidad(0);
		setCarreraSeleccionada(null);
		// Limpiar el dato laboral
		limpiaDatoLaboral();
		limpiaPresupuesto();
		
		if(!getAccionModulo().equals(Accion.CAPTURA)) {
			// Eliminar el dominio consultado
			setDominio(null);
		}
	}
	
	/**
	 * Listener para limpiar el formulario al seleccionar 
	 * la modalidad
	 */
	public void limpiaSeleccionModalidad() {
		setCarreraSeleccionada(null);
		// Limpiar el dato laboral
		limpiaDatoLaboral();
		limpiaPresupuesto();
	}
}
