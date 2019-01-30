package mx.qr.sace.mb;

import javax.ejb.EJB;

import mx.qr.core.exception.ApplicationException;
import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.ce.negocio.ManagerDeFichaAcademicaLocal;
import mx.qr.sace.ce.negocio.ProcesoInscripcionLocal;
import mx.qr.sace.formulario.FormularioAlumno;
import mx.qr.sace.formulario.FormularioNuevaInscripcion;
import mx.qr.sace.persistencia.entidades.DatoSalud;

/**
 * MB para el proceso de inscripcion.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public class MBInscripcion extends MBAutoCompleta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4173310745483132790L;

	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/ManagerDeFichaAcademicaEJB")
	private ManagerDeFichaAcademicaLocal managerFA;
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/ServicioInscripcionEJB")
	private ProcesoInscripcionLocal beanInscripcion;
	
	private FormularioNuevaInscripcion form;

	@Override
	public void inicializa() {
		form = new FormularioNuevaInscripcion();
	}

	@Override
	public void inicializaForm(Integer como) {
//		Solo en desarrollo. Quitarlos
//		form.setFichaAcademica(new FichaAcademica());
//		form.getFichaAcademica().setDatoSalud(new DatoSalud());
		
		if (como == 1) {
			// DONE Revisar si hay prospectos registrados.
			int cantidad = beanAlPaginado.recuperaTotalDeRegistros(mbHome
					.getSistemaEstudioUsuario().getEscolaridad(), mbHome
					.getSistemaEstudioUsuario().getModalidad(),
					EstatusAlumno.INSCRITO);

			if (cantidad > 0) {
				form.setHayRegistros(true);
			} else {
				form.setHayRegistros(false);
			}
			form.setMuestraResultado(false);
			form.setEstatusAlumnoEnUso(EstatusAlumno.INSCRITO);
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if (como == 3) {
			// DONE Revisar si ya hay estudiantes.
			// int cantidad = 0;
			int cantidad = beanAlPaginado.recuperaTotalDeRegistros(
					mbHome.getSistemaEstudioUsuario().getEscolaridad(), 
					mbHome.getSistemaEstudioUsuario().getModalidad(),
					EstatusAlumno.ESTUDIANTE);

			if (cantidad > 0) {
				form.setHayRegistros(true);
			} else {
				form.setHayRegistros(false);
			}
			
			form.setMuestraResultado(false);
			
			// Los campos se ponen en modo lectura
			form.setModoLectura(true);
			form.setEstatusAlumnoEnUso(EstatusAlumno.ESTUDIANTE);
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		}
	}

	@Override
	public String guardaHandler() {
		// TODO Registrar la inscripcion, realizar la inscripcion, hacer la inscripcion.
		
		form.codifica();
		
		try {
			
			beanInscripcion.hacerInscripcion(form.getDominio(), 
					form.getFichaAcademica(), 
					form.getDocumentosEntregados());
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			form.limpia();
			
		} catch(ApplicationException e) {
			agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			form.limpia();
			return "";
		}
		
		return OUTCOME_GUARDA;
	}

	@Override
	public String consultaHandler() {
		// TODO Traer la ficha academica del alumno seleccionado y del sistema de estudios seleccionado
		try {
			form.setFichaAcademica(
					managerFA.recuperaFichaDelAlumno(
							form.getDominio(), form.getCarreraSeleccionada()));
			
			// Crear la instancia de salud en captura
			if(form.getAccionModulo() == MBDefecto.Accion.CAPTURA) {
				form.getFichaAcademica().setDatoSalud(new DatoSalud());
				form.getFichaAcademica().getDatoSalud().setUsuario(
						obtenUsuario().getUsername());
			}
			
			form.setMuestraResultado(true);
			
		} catch (RegistroNoEncontradoException e) {
			form.setMuestraResultado(false);
		}
		return null;
	}
	
	@Override
	public String modificaHandler() {
		return null;
	}

	@Override
	public String eliminaHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormularioAlumno getForm() {
		return form;
	}
}
