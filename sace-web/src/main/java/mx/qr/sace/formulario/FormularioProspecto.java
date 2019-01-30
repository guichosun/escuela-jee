package mx.qr.sace.formulario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.TipoTramite;
import mx.qr.core.vista.MBDefecto;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.core.vista.MBDefecto.Accion;
import mx.qr.sace.Constantes;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.Comentario;
import mx.qr.sace.persistencia.entidades.DatoLaboral;
import mx.qr.sace.persistencia.entidades.Descuento;
import mx.qr.sace.persistencia.entidades.Empresa;
import mx.qr.sace.persistencia.entidades.EmpresaConvenio;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.ResponsablePago;
import mx.qr.sace.persistencia.entidades.Respuesta;
import mx.qr.sace.persistencia.entidades.RespuestaAPregunta;
import mx.qr.sace.persistencia.entidades.TramiteCarrera;

import org.primefaces.component.inputtext.InputText;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
public class FormularioProspecto extends FormularioAlumno {

	private static final long serialVersionUID = -4089357141253825214L;
	
	public static final int PREGUNTA_MEDIO_DE_CONTACTO = 1;
	public static final int PREGUNTA_MOTIVO_CANCELACION = 2;
	
	private Integer idEscolaridad;
	private Integer idModalidad;
	
	private boolean muestraResultado;

	/**
	 * Bandera para indicar si se le va hacer un presupuesto o queda por
	 * contactar
	 */
	private boolean porContactar = false;
	
	private boolean porRevalidar = false;
	private boolean soloLecturaOtraEmpresa = true;	
	private boolean porDescuentoIns = false;
	private boolean porDescuentoMens = false;
	private String[] descuentosSeleccionados;
	
	/* El dominio del formulario */
	private Alumno dominio;
	
	private boolean prospectosRegistrados = false;
	// Se obtendran de la beca seleccionada
	private Integer descuentoIns;
	private Integer descuentoMens;
	private Float porcentajeInscripcion;
	private Float porcentajeMensualidad;
	private Float montoTotalInscripcion;
	private Float montoTotalMensualidad;

	private TramiteCarrera tramiteInscripcion = new TramiteCarrera();
	private TramiteCarrera tramiteMensualidad = new TramiteCarrera();

	/* Son los tramites que seran parte del presupuesto */
	private Set<TramiteCarrera> tramitesAPagar;

	private Comentario comentario;

	private List<Beca> becas;
	private Beca becaSeleccionada;

	private List<Carrera> carreras;
	private List<Empresa> empresas;

	private Integer idRespuestaMkt;
	private RespuestaAPregunta respuestaAP;
	private List<Respuesta> respuestasMkt;
	private Respuesta respuestaMktSelecconada;
	private EmpresaConvenio empresaSeleccionada;

	private boolean tieneTutor = false;
	// INFORMACION DEL TUTOR DEL ALUMNO
	private ResponsablePago tutor;
	
	/* Los datos laborales del tutor o del alumno */
	private DatoLaboral datoLaboral;
	
	// La ficha Academica que se crea por primera vez
	private FichaAcademica fichaAcademica;
	
	private Integer idEmpresaDondeTrabaja;
	private String dondeTrabajaOtro;
	private Integer idRespuestaMedio;
	
	/**
	 * 
	 */
	public FormularioProspecto() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_registro_prospectos_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_registro_prospectos_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_registro_prospectos_migaja2"));
		
	}

	@Override
	public void codifica() {
		
		// Empresa pivote para señalizaciòn
		if(getDatoLaboral().getEmpresa().getId() == 666) {
			getDatoLaboral().setEmpresa(null);
		}
		// La carrera seleccionada va a la ficha
		getFichaAcademica().setCarrera(getCarreraSeleccionada());
		
		if (isPorContactar()) {
			getDominio().setEstatus(EstatusAlumno.PROSPECTO);
		} else {
			getDominio().setEstatus(EstatusAlumno.CANDIDATO);
		}
		
		getTutor().setDatoLaboral(getDatoLaboral());
		getDominio().setTieneTutor(isTieneTutor());
		
		// Si no tiene tutor el responsable del pago sera el mismo alumno
		if(!isTieneTutor()) {
			ResponsablePago rP = getTutor();
			rP.setApeMaterno(getDominio().getDatoPersona().getApeMaterno());
			rP.setApePaterno(getDominio().getDatoPersona().getApePaterno());
			rP.setNombre(getDominio().getDatoPersona().getNombre());
			rP.setCelular(getDominio().getDatoPersona().getCelular());
			rP.setCorreo(getDominio().getDatoPersona().getEmail());
		}
	}

	@Override
	public void decodifica() {

		setTieneTutor(getDominio().isTieneTutor());
		setTutor(getDominio().getResponsable());
		setDatoLaboral(getDominio().getResponsable().getDatoLaboral());
		
		// Indicar la empresa pivote
		if(getDatoLaboral().getEmpresa() == null) {
			getDatoLaboral().setEmpresa(new Empresa(666));
		}
		
		if(getDominio().getEstatus() == EstatusAlumno.PROSPECTO) {
			setPorContactar(true);
		} 
		
		// El idRespuesta no lo puedo poner aqui, porque no tengo manera de conocerlo en este momento.
	}

	@Override
	public void limpia() {
//		getCarreraSeleccionada().setTramites(null);
		setCarreraSeleccionada(null);
		setDominio(null);
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
	
	/**
	 * Listener para limpiar el presupuesto
	 */
	public void limpiaPresupuesto() {
		setBecaSeleccionada(null);
		setPorContactar(false);
		setPorDescuentoIns(true);
		setPorDescuentoMens(true);
		setDescuentoIns(null);
		setDescuentoMens(null);
		setTramiteInscripcion(null);
		setPorcentajeInscripcion(null);
		setMontoTotalInscripcion(null);
		setTramiteMensualidad(null);
		setPorcentajeMensualidad(null);
		setMontoTotalMensualidad(null);
	}
	
	public void limpiaDatoLaboral() {
		if(getDatoLaboral() != null) {
			if(getAccionModulo().equals(Accion.CAPTURA)) {
				setSoloLecturaOtraEmpresa(true);
				setDatoLaboral(new DatoLaboral(
						getDatoLaboral().getUsuario()));
			}
			
		}
	}
	
	/**
	 * Accion para activar o desactivar el area de presupuestos
	 */
	public void activaPresupuesto() {
		if (porContactar) {
			porDescuentoIns = false;
			porDescuentoMens = false;
		}
	}

	/**
	 * 
	 */
	public void muestraCarrera() {
		// Limpiar el dato laboral
		limpiaDatoLaboral();
		
		// Primero se limpia el presupuesto
		limpiaPresupuesto();
		
		// Prepara las cuotas de la carrera seleccionada
		ponerCuotasEnTramites();
		
		// Solo en consulta. Limpiar el combo de busqueda
		if(!getAccionModulo().equals(MBDefecto.Accion.CAPTURA)) {
			setDominio(null);
			setBecaSeleccionada(null);
		}
	}
	
	/**
	 * Pone las cuotas en los tramites de una carrera
	 */
	public void ponerCuotasEnTramites() {
		if (getCarreraSeleccionada() != null) {
			Set<TramiteCarrera> cuotas = new HashSet<TramiteCarrera>();
			
			for (TramiteCarrera tc : getCarreraSeleccionada().getTramites()) {
				// - Que el estatus del tramite sea el actual
				if (tc.getEstatus() == EstatusRegistro.ACTIVO) {
					// el tramite de la inscripion o la mensualidad
					if (tc.getTramite().getIdTramite() == Constantes.INSCRIPCION) {
						setTramiteInscripcion(tc);
					} else if (tc.getTramite().getIdTramite() == Constantes.MENSUALIDAD) {
						setTramiteMensualidad(tc);
					}
					
					// Solo agregar aquellos tramites que sean cuotas para la
					// venta a los prospectos
					if (tc.getTramite().getTipoTramite() == TipoTramite.CUOTA) {
						cuotas.add(tc);
					}
				}
			}
			// Ojo Para el registro de prospectos los tramites seran vistos como
			// cuotas
			getCarreraSeleccionada().setTramites(cuotas);
			
			setTramitesAPagar(cuotas);
		}
	}

	/**
	 * Al seleccionar una beca, configurarla
	 */
	public void configuraBeca() {
		for (Descuento d : becaSeleccionada.getDescuentos()) {
			// Descomponer los decuentos para saber en que tramite aplica
			if (d.getTramite().getIdTramite() == 1) {
				descuentoIns = d.getValor();
				// Activo el check
				porDescuentoIns = true;
			} else {
				descuentoMens = d.getValor();
				// Activo el check
				porDescuentoMens = true;
			}
		}

		descuentoIns = descuentoIns != null ? descuentoIns : 0;
		descuentoMens = descuentoMens != null ? descuentoMens : 0;

		// Calcula los montos
		calculoMonto(Constantes.INSCRIPCION); // 1
		calculoMonto(Constantes.MENSUALIDAD); // 4

	}

	/**
	 * 
	 * @param event
	 */
	public void calculoPorcentaje(AjaxBehaviorEvent event) {
		InputText component = (InputText) event.getSource();

		// El porcentaje a descontar de la inscripcion
		if (component.getId().equalsIgnoreCase("pordesins")) {
			// solo es el comodin, no importa cual
			calculoMonto(Constantes.INSCRIPCION);
		} else {
			calculoMonto(Constantes.MENSUALIDAD);
		}
	}

	/*
	 * Metodo de ayuda
	 */
	private void calculoMonto(int tramite) {
		if (tramite == Constantes.INSCRIPCION) {
			float desc = getDescuentoIns() / 100f;
			porcentajeInscripcion = getTramiteInscripcion().getCuota() * desc;
			montoTotalInscripcion = getTramiteInscripcion().getCuota()
					- porcentajeInscripcion;
		} else {
			float desc = getDescuentoMens() / 100f;
			porcentajeMensualidad = getTramiteMensualidad().getCuota() * desc;
			montoTotalMensualidad = getTramiteMensualidad().getCuota()
					- porcentajeMensualidad;
		}
	}
	
	public void verificaEmpresa(EmpresaConvenio ec) {
		String mensaje = "";
		soloLecturaOtraEmpresa = true;
		
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Sugerencia de beca: ");
		
		mensaje = ec.getBeca().getNombre();
		message.setDetail(mensaje);
		FacesContext.getCurrentInstance().addMessage("infoEmpresaConBeca", message);
		
	}
	
	/**
	 * Para activa o desactivar el check de tutor segun el caso de modo lectura
	 * o para cuando no se necesita el check. Ejemplo, en las ejecutivas
	 * @return
	 */
	public boolean activaCheckTutor() {
		// Si no es modo de lectura, entonces verificar el sistema de estudios
		if(!isModoLectura()) {
			if(!(getSistemaEstudioFormulario().getEscolaridad().getIdEscolaridad() == 1 ||
					getSistemaEstudioFormulario().getEscolaridad().getIdEscolaridad() == 2) &&
					getSistemaEstudioFormulario().getModalidad().getIdModalidad() == 1) {
				
				return true;		
			}
		} else {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Listener para el cambio del valor de la empresa
	 */
	public void cambiaValorEmpresa(ValueChangeEvent ce) {
		Empresa e = (Empresa)ce.getNewValue();
		if(e.getId() != 666) {
			getDatoLaboral().setCualEmpresa(null);
			setSoloLecturaOtraEmpresa(true);
		} else {
			setSoloLecturaOtraEmpresa(false);
		}
		
	}

	@Override
	public List<String> getMigajas() {
		return migajas;
	}

	public Alumno getDominio() {
		return dominio;
	}

	public void setDominio(Alumno entidad) {
		if(entidad == null) {
			setSoloLecturaOtraEmpresa(true);
		}
		this.dominio = entidad;
	}

	public boolean isProspectosRegistrados() {
		return prospectosRegistrados;
	}

	public void setProspectosRegistrados(boolean prospectosRegistrados) {
		this.prospectosRegistrados = prospectosRegistrados;
	}

	public Integer getIdEscolaridad() {
		return idEscolaridad;
	}

	public void setIdEscolaridad(Integer idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	public Integer getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(Integer idModalidad) {
		this.idModalidad = idModalidad;
	}

	public boolean isPorContactar() {
		return porContactar;
	}

	public void setPorContactar(boolean porContactar) {
		this.porContactar = porContactar;
	}

	public String[] getDescuentosSeleccionados() {
		return descuentosSeleccionados;
	}

	public void setDescuentosSeleccionados(String[] descuentosSeleccionados) {
		this.descuentosSeleccionados = descuentosSeleccionados;
	}

	public boolean isPorDescuentoIns() {
		return porDescuentoIns;
	}

	public void setPorDescuentoIns(boolean porDescuentoIns) {
		this.porDescuentoIns = porDescuentoIns;
	}

	public boolean isPorDescuentoMens() {
		return porDescuentoMens;
	}

	public void setPorDescuentoMens(boolean porDescuentoMens) {
		this.porDescuentoMens = porDescuentoMens;
	}

	public Integer getDescuentoIns() {
		return descuentoIns;
	}

	public void setDescuentoIns(Integer descuentoIns) {
		this.descuentoIns = descuentoIns;
	}

	public Integer getDescuentoMens() {
		return descuentoMens;
	}

	public void setDescuentoMens(Integer descuentoMens) {
		this.descuentoMens = descuentoMens;
	}

	public TramiteCarrera getTramiteInscripcion() {
		return tramiteInscripcion;
	}

	public void setTramiteInscripcion(TramiteCarrera tramiteCarrera) {
		this.tramiteInscripcion = tramiteCarrera;
	}

	public TramiteCarrera getTramiteMensualidad() {
		return tramiteMensualidad;
	}

	public void setTramiteMensualidad(TramiteCarrera tramiteMensualidad) {
		this.tramiteMensualidad = tramiteMensualidad;
	}

	public Float getMontoTotalInscripcion() {
		return montoTotalInscripcion;
	}

	public void setMontoTotalInscripcion(Float montoTotalInscripcion) {
		this.montoTotalInscripcion = montoTotalInscripcion;
	}

	public Float getMontoTotalMensualidad() {
		return montoTotalMensualidad;
	}

	public void setMontoTotalMensualidad(Float montoTotalMensualidad) {
		this.montoTotalMensualidad = montoTotalMensualidad;
	}

	public Float getPorcentajeInscripcion() {
		return porcentajeInscripcion;
	}

	public void setPorcentajeInscripcion(Float porcentajeInscripcion) {
		this.porcentajeInscripcion = porcentajeInscripcion;
	}

	public Float getPorcentajeMensualidad() {
		return porcentajeMensualidad;
	}

	public void setPorcentajeMensualidad(Float porcentajeMensualidad) {
		this.porcentajeMensualidad = porcentajeMensualidad;
	}

	public List<Beca> getBecas() {
		return becas;
	}

	public void setBecas(List<Beca> becas) {
		this.becas = becas;
	}

	public Beca getBecaSeleccionada() {
		return becaSeleccionada;
	}

	public void setBecaSeleccionada(Beca becaSeleccionada) {
		this.becaSeleccionada = becaSeleccionada;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Set<TramiteCarrera> getTramitesAPagar() {
		return tramitesAPagar;
	}

	public void setTramitesAPagar(Set<TramiteCarrera> tramitesAPagar) {
		this.tramitesAPagar = tramitesAPagar;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Integer getIdEmpresaDondeTrabaja() {
		return idEmpresaDondeTrabaja;
	}

	public void setIdEmpresaDondeTrabaja(Integer idDondeTrabaja) {
		this.idEmpresaDondeTrabaja = idDondeTrabaja;
	}

	public String getDondeTrabajaOtro() {
		return dondeTrabajaOtro;
	}

	public void setDondeTrabajaOtro(String dondeTrabajaOtro) {
		this.dondeTrabajaOtro = dondeTrabajaOtro;
	}

	public boolean isSoloLecturaOtraEmpresa() {
		return soloLecturaOtraEmpresa;
	}

	public void setSoloLecturaOtraEmpresa(boolean soloLecturaOtraEmpresa) {
		this.soloLecturaOtraEmpresa = soloLecturaOtraEmpresa;
	}

	public Integer getIdRespuestaMedio() {
		return idRespuestaMedio;
	}

	public void setIdRespuestaMedio(Integer idRespuestaMedio) {
		this.idRespuestaMedio = idRespuestaMedio;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresasConvenio) {
		this.empresas = empresasConvenio;
	}

	public EmpresaConvenio getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(EmpresaConvenio empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<Respuesta> getRespuestasMkt() {
		return respuestasMkt;
	}

	public void setRespuestasMkt(List<Respuesta> respuestasMedio) {
		this.respuestasMkt = respuestasMedio;
	}

	public ResponsablePago getTutor() {
		return tutor;
	}

	public void setTutor(ResponsablePago tutor) {
		this.tutor = tutor;
	}

	public FichaAcademica getFichaAcademica() {
		return fichaAcademica;
	}

	public void setFichaAcademica(FichaAcademica fichaAcademica) {
		this.fichaAcademica = fichaAcademica;
	}

	public DatoLaboral getDatoLaboral() {
		return datoLaboral;
	}

	public void setDatoLaboral(DatoLaboral datoLaboral) {
		this.datoLaboral = datoLaboral;
	}

	public boolean isTieneTutor() {
		return tieneTutor;
	}

	public void setTieneTutor(boolean tieneTutor) {
		this.tieneTutor = tieneTutor;
	}

	public Integer getIdRespuestaMkt() {
		return idRespuestaMkt;
	}

	public void setIdRespuestaMkt(Integer idRespuesta) {
		this.idRespuestaMkt = idRespuesta;
	}

	public RespuestaAPregunta getRespuestaAP() {
		return respuestaAP;
	}

	public void setRespuestaAP(RespuestaAPregunta rAp) {
		this.respuestaAP = rAp;
	}

	public boolean isMuestraResultado() {
		return muestraResultado;
	}

	public void setMuestraResultado(boolean muestraResultado) {
		this.muestraResultado = muestraResultado;
	}

	@Override
	public void configuraEnConsulta() {
		super.configuraEnConsulta();
		
		// Al seleccionar el registro, mostrar las secciones.
		setMuestraResultado(true);
	}

	public Respuesta getRespuestaMktSelecconada() {
		return respuestaMktSelecconada;
	}

	public void setRespuestaMktSelecconada(Respuesta respuestaMktSelecconada) {
		this.respuestaMktSelecconada = respuestaMktSelecconada;
	}
	public boolean isPorRevalidar() {
		return porRevalidar;
	}

	public void setPorRevalidar(boolean porRevalidar) {
		this.porRevalidar = porRevalidar;
	}
}