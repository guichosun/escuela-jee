package mx.qr.core.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.qr.core.persistencia.EstatusRegistro;
import mx.qr.core.persistencia.Genero;
import mx.qr.core.util.Utilerias;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Carrera;

/**
 * Class abstracta que define el comportamiento basico del formulario. Asi como
 * los elementos de entrada para los formularios
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
public abstract class FormularioBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8107666503079874203L;

	private static Map<String, String> meses;
	protected static Map<String, Boolean> respuestas;
	protected static Map<String, Genero> sexos;
	protected static Map<String, EstatusRegistro> estatusRegistro;
	
	/** Las opciones de las migajas */
	protected List<String> migajas;
	
	/** 
	 * El sistema de estudios que se utilizará en los
	 * formularios de los modulos 
	 */
	private SistemaEstudio sistemaEstudioFormulario;

	private String dia;
	private String mes;
	private String anho;
	
	/* indicador que los campos de edicion en consulta son de lectura */
	protected boolean campoEditable;

	private MBDefecto.Accion accionModulo;

	/**/
	private boolean btnGuardarDisabled = true;

	/**
	 * El usuario que solicita la creacion del formulario
	 */
	protected String usuarioEnFormulario;

	/**
	 * Bandara para indicar si hay registros capturados.
	 */
	protected boolean hayRegistros;

	/**
	 * Bandera para indicar si el campo esta en modo lectura. OjO En la captura
	 * esta bandera sera siempre false, esto para dar facilidad en que se
	 * muestren los campos como en modo captura
	 */
	protected boolean modoLectura = false;

	/**
	 */
	protected boolean muestraResultado = false;

	/***/
	protected Carrera carreraSeleccionada;

	/**
	 * Costruye un formulario vacio
	 */
	public FormularioBase() {
		migajas = new ArrayList<String>();
		migajas.add("Pestaña");
		migajas.add("Etapa");
		migajas.add("Modulo");
		
	}

	/**
	 * @param usuarioEnFormulario
	 */
	public FormularioBase(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}

	static {
		respuestas = new LinkedHashMap<String, Boolean>();
		respuestas.put("Si", new Boolean(true));
		respuestas.put("No", new Boolean(false));

		sexos = new LinkedHashMap<String, Genero>();
		sexos.put("F", Genero.F);
		sexos.put("M", Genero.M);

		estatusRegistro = new LinkedHashMap<String, EstatusRegistro>();
		estatusRegistro.put(EstatusRegistro.ACTIVO.toString(),
				EstatusRegistro.ACTIVO);
		estatusRegistro.put(EstatusRegistro.INACTIVO.toString(),
				EstatusRegistro.INACTIVO);
		
		meses = new LinkedHashMap<String, String>();
		meses.put("enero", "1");
		meses.put("febrero", "2");
		meses.put("marzo", "3");
		meses.put("abril", "4");
		meses.put("mayo", "5");
		meses.put("junio", "6");
		meses.put("julio", "7");
		meses.put("agosto", "8");
		meses.put("septiembre", "9");
		meses.put("octubre", "10");
		meses.put("noviembre", "11");
		meses.put("diciembre", "12");

	}

	/**
	 * Codifica la forma para transmitir los datos al dominio
	 */
	public void codifica() {
		
	}

	/**
	 * Decodifica los valores del dominio a la forma
	 */
	public void decodifica() {
		
	}

	/**
	 * Para limpiar los valores definidos en la forma
	 */
	public void limpia() {
		setBtnGuardarDisabled(true);
	}

	/**
	 * Inicializa los campos del formulario
	 */
	public void inicializa() {
		setCampoEditable(false);
	}

	/**
	 * Para establecer el formulario en el estado de consulta
	 */
	public void configuraEnConsulta() {
		// Al seleccionar un registro, simpre el modulo sera de consulta
		setAccionModulo(MBDefecto.Accion.CONSULTA);

		// Se regresan los campos a modo lectura
		setModoLectura(true);

		// Cada que haya una seleccion se tiene que poner en solo lectura los
		// campos
		setCampoEditable(false);
	}

	/**
	 * Muestra la etiqueta del genero guardado
	 * 
	 * @param strWrapper
	 * @return
	 */
	public String convertirValorGenero(String strWrapper) {
		if (!Utilerias.estaVacio(strWrapper)) {
			if ("M".equals(strWrapper)) {
				return "Masculino";
			} else if ("F".equals(strWrapper)) {
				return "Femenino";
			} else {
				return "N";
			}
		}
		return "N";
	}

	/**
	 * Para publicar las migajas.
	 * 
	 * @return
	 */
	public List<String> getMigajas() {
		return migajas;
	}

	/**
	 * Obtiene los meses.
	 * @return
	 */
	public Map<String, String> getMeses() {
		return meses;
	}
	
	public Map<String, Boolean> getRespuestas() {
		return respuestas;
	}

	public boolean isBtnGuardarDisabled() {
		return btnGuardarDisabled;
	}

	public void setBtnGuardarDisabled(boolean btnGuardarLectura) {
		this.btnGuardarDisabled = btnGuardarLectura;
	}

	public MBDefecto.Accion getAccionModulo() {
		return accionModulo;
	}

	public void setAccionModulo(MBDefecto.Accion accionModulo) {
		this.accionModulo = accionModulo;
	}

	public Map<String, Genero> getSexos() {
		return sexos;
	}

	public void setSexos(Map<String, Genero> sexos) {
		FormularioBase.sexos = sexos;
	}

	public boolean isCampoEditable() {
		return campoEditable;
	}

	public void setCampoEditable(boolean camposEditables) {
		this.campoEditable = camposEditables;
	}

	public Map<String, EstatusRegistro> getEstatusRegistro() {
		return estatusRegistro;
	}

	public String getUsuarioEnFormulario() {
		return usuarioEnFormulario;
	}

	public void setUsuarioEnFormulario(String usuarioEnFormulario) {
		this.usuarioEnFormulario = usuarioEnFormulario;
	}

	public boolean isHayRegistros() {
		return hayRegistros;
	}

	public void setHayRegistros(boolean hayRegistros) {
		this.hayRegistros = hayRegistros;
	}

	public boolean isModoLectura() {
		return modoLectura;
	}

	public void setModoLectura(boolean modoLectura) {
		this.modoLectura = modoLectura;
	}

	public boolean isMuestraResultado() {
		return muestraResultado;
	}

	public void setMuestraResultado(boolean muestraResultado) {
		this.muestraResultado = muestraResultado;
	}

	public Carrera getCarreraSeleccionada() {
		return carreraSeleccionada;
	}

	public void setCarreraSeleccionada(Carrera carreraSeleccionada) {
		this.carreraSeleccionada = carreraSeleccionada;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnho() {
		return anho;
	}

	public void setAnho(String anho) {
		this.anho = anho;
	}

	public SistemaEstudio getSistemaEstudioFormulario() {
		return sistemaEstudioFormulario;
	}

	public void setSistemaEstudioFormulario(SistemaEstudio sistemaEstudio) {
		this.sistemaEstudioFormulario = sistemaEstudio;
	}
}
