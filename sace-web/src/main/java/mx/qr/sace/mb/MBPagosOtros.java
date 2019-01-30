package mx.qr.sace.mb;

import java.util.List;

import javax.ejb.EJB;

import mx.qr.core.exception.RegistroNoEncontradoException;
import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.util.Utilerias;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.ce.negocio.ManagerDeFichaAcademicaLocal;
import mx.qr.sace.finanzas.negocio.CatalogosFinanzasLocal;
import mx.qr.sace.finanzas.negocio.RegistroPagosVariosLocal;
import mx.qr.sace.formulario.FormularioPagoConcepto;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.PagoDiverso;
import mx.qr.sace.persistencia.entidades.PrecioConcepto;
import mx.qr.sace.persistencia.entidades.Tramite;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Comportamiento para modelar el contolador del los otros pagos o pagos diversos.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Diciembre 2016
 * @copyright Q & R
 */
public class MBPagosOtros extends MBAutoCompleta {

	private static final long serialVersionUID = 8096941728656161666L;
	
	private static final Logger log = Logger.getLogger(MBPagosOtros.class);
	
	@Autowired
	@Qualifier("mbHome")
	private transient MBHome mbHome;
	
	@EJB(mappedName="java:global/sace-ear/sace-finanzas-ejb/CatalogosFinanzasEJB")
	private CatalogosFinanzasLocal beanCatalogosFNZ;
	
	@EJB(mappedName="java:global/sace-ear/sace-finanzas-ejb/ServicioPagosVariosEJB")
	private RegistroPagosVariosLocal beanPago;
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/ManagerDeFichaAcademicaEJB")
	private ManagerDeFichaAcademicaLocal managerFA;
	
	private FormularioPagoConcepto form;
	
	public void inicializa() {
		form = new FormularioPagoConcepto(obtenUsuario().getUsername());
	}
	
	/**
	 * Inicializa los elementos necesarios para el formulario, dependiendo si es captura o modifica
	 */
	public void inicializaForm(Integer como) {
		form.setEstatusAlumnoEnUso(EstatusAlumno.ESTUDIANTE);
		
		if(como == 1) {
			
			// DONE CREAR LOS OBJETOS DE DOMINIO
			form.inicializa();
			form.getRegistroPago().setFechaPago(Utilerias.diaActual());
			
			form.setAccionModulo(MBDefecto.Accion.CAPTURA);
		} else if(como == 3) {
			
			// Los campos se ponen en modo lectura
			form.setModoLectura(true);
			
			form.setAccionModulo(MBDefecto.Accion.CONSULTA);
		}
	}
	
	@Override
	public List<Alumno> completa(String query) {
		// En la busqueda de este modulo los estatus que puede buscar son PROSPECRO y CONTACTARE
		// DONE Sacar la lista de los PROSPECTOS luego agregar la lista de CONTACTAR
		form.setEstatusAlumnoEnUso(EstatusAlumno.PROSPECTO);
		List<Alumno> coincidencias = super.completa(query);
		
		form.setEstatusAlumnoEnUso(EstatusAlumno.CANDIDATO);
		coincidencias.addAll(super.completa(query));
		
		return coincidencias;
	}

	@Override
	public void onItemSelect(SelectEvent event) {
		// TODO Auto-generated method stub
		super.onItemSelect(event);
		
		form.setBtnGuardarDisabled(false);
	}

	/**
	 * Trae de BD el catalogo de tramite de tipo concepto
	 * @return
	 */
	public List<Tramite> traeConceptos() {
//		DONE Recuperar todos los tramites de tipo CONCEPTO
		return beanCatalogosFNZ.obtenConceptos();
	}
	
	public void traeValorConcepto() {
		Tramite concepto = new Tramite();
		concepto.setIdTramite(form.getIdTramite());

		PrecioConcepto pc = beanCatalogosFNZ.obtenPrecioConcepto(concepto);

//		DONE Recuperar la instancia de PrecioConcepto MEDIANTE EL IDTRAMITE Y QUE ADEMAS ESTE ACTIVO.
		if(pc != null) {
			form.getRegistroPago().setValor(pc.getValor());
			form.setBtnGuardarDisabled(false);
		} else {
			form.getRegistroPago().setValor(null);
			
			agregaMensaje(TipoMensaje.ADVERTENCIA_MENSAJE.getTipo(),
					"msgSinPrecio",
					"No existe un precio actual configurado para este concepto");
			form.setBtnGuardarDisabled(true);
		}
	}

	@Override
	public FormularioPagoConcepto getForm() {
		return form;
	}

	@Override
	public String consultaHandler() {
		// TODO Traer el o los pagos diverzos asociados con el alumno
		FichaAcademica fichaAcademica = null;
		try {
			fichaAcademica = managerFA.recuperaFichaDelAlumno(
					form.getDominio(), form.getCarreraSeleccionada());
			
			// TODO: Traer todos los pagos de la ficha academica.Solo en CONSULTA
			if(getForm().getAccionModulo() != MBDefecto.Accion.CAPTURA) {
				getForm().setFichaAcademica(fichaAcademica);
				List<PagoDiverso> pagos = managerFA.recuperaPagosDiversosDeLaFicha(fichaAcademica);
				
				// Para pruebas con el link modificar de la hamburguesa.
				// Se tiene que validar pagos en realidad.
				form.setHayRegistros(true);
			}
		} catch (RegistroNoEncontradoException e) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", e.getMessage());
			log.error("Al consultar", e);
		}
		return "";
	}

	@Override
	public String guardaHandler() {
		try {
			// DONE Antes de registrar el pago, hay que obtener la ficha academica del alumno.
			FichaAcademica fichaAcademica = managerFA.recuperaFichaDelAlumno(
					form.getDominio(), form.getCarreraSeleccionada());
			
			// DONE Ejecutar el negocio del registro del pago
			form.getRegistroPago().setTramite(new Tramite(form.getIdTramite()));
			beanPago.realizaPago(form.getRegistroPago(), fichaAcademica);
			
			agregaMensaje(TipoMensaje.INFO_MENSAJE,
					UtileriasMessageSource.mensajeProperties("msg_confirmacion_guarda"));
			
			form.inicializa();
			form.getRegistroPago().setFechaPago(Utilerias.diaActual());
			log.debug("El pago se registro con exito!");
		} catch (Exception ex) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "mensajesError", ex.getMessage());
			log.error("Al guardar el pago", ex);
		}
		
		return "";
	}

	
	
}
