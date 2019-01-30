package mx.qr.sace.formulario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.qr.core.vista.FormularioBase;
import mx.qr.core.vista.UtileriasMessageSource;
import mx.qr.sace.persistencia.PeriodoEscolar;
import mx.qr.sace.persistencia.entidades.CicloEscolar;
import mx.qr.sace.persistencia.entidades.Grupo;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Abril 2016
 * @copyright Q & R
 */
public class FormularioPlaneacionCiclo extends FormularioBase {

	private static final long serialVersionUID = -4089357143333825214L;

	/* El dominio de este formulario */
	private CicloEscolar dominio;
	
	/* 
	 * Para indicar si es un ciclo conveniente o no.
	 * El valor por default es true ya que configuracion 
	 * del ciclo es supuestamente la conveniente 
	 */
	private boolean cicloConveniente = true;
	
	private Integer duracion;
	private Integer cantidadCiclos;
	private Integer cantidadPeriodosPorCiclo;
	
	private CicloEscolar cicloSeleccionado;
	private List<CicloEscolar> ciclos;
	
	private List<PeriodoEscolar> periodos;
	
	public String mascaraGeneracion; 
	
	/**
	 * 
	 */
	public FormularioPlaneacionCiclo() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("planeacion_ciclo_migaja0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("planeacion_ciclo_migaja1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("planeacion_ciclo_migaja2"));

	}

	/**
	 * @param usuarioEnFormulario
	 */
	public FormularioPlaneacionCiclo(String usuarioEnFormulario) {
		this();
		this.usuarioEnFormulario = usuarioEnFormulario;
	}

	@Override
	public void configuraEnConsulta() {
		super.configuraEnConsulta();
	}

	@Override
	public void inicializa() {
		super.inicializa();
	}
	
	@Override
	public void limpia() {
		super.limpia();
		setCarreraSeleccionada(null);
		setMascaraGeneracion("");
		setDuracion(null);
		setCantidadCiclos(null);
		setCantidadPeriodosPorCiclo(null);
	}
	
	public void refrescarValor() {
		limpia();
	}
	
	/**
	 * Codifica los datos de la carrera para que se muestren en la pantalla.
	 */
	public void codificaDatosCarrera() {
		Calendar cal = Calendar.getInstance();
		int duracion = cal.get(Calendar.YEAR) + getCarreraSeleccionada().getDuracion();
		setMascaraGeneracion(String.valueOf(cal.get(Calendar.YEAR) + " - " + duracion));
		setDuracion(getCarreraSeleccionada().getDuracion());
		setCantidadCiclos(getCarreraSeleccionada().getCantidadCiclos());
		setCantidadPeriodosPorCiclo(getCarreraSeleccionada().getCantidadPeriodosPorCiclo());
	}
	
	public CicloEscolar creaCicloCascaron(int yyyy, int yyyy2) {
		CicloEscolar cic = new CicloEscolar();
		cic.setClave(generaClaveCiclo(yyyy, yyyy2));
		return cic;
	}
	
	public Date getMinDate() {
		return new Date();
	}
	public String generaClaveCiclo(int yyyy, int yyyy2) {
		return "Ciclo "+yyyy+"-"+yyyy2;
	}

	public String getMascaraGeneracion() {
		return mascaraGeneracion;
	}

	public void setMascaraGeneracion(String mascaraGeneracion) {
		this.mascaraGeneracion = mascaraGeneracion;
	}

	public Integer getCantidadCiclos() {
		return cantidadCiclos;
	}

	public void setCantidadCiclos(Integer cantidadCiclos) {
		this.cantidadCiclos = cantidadCiclos;
	}

	public Integer getCantidadPeriodosPorCiclo() {
		return cantidadPeriodosPorCiclo;
	}

	public void setCantidadPeriodosPorCiclo(Integer cantidadPeriodos) {
		this.cantidadPeriodosPorCiclo = cantidadPeriodos;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public boolean isCicloConveniente() {
		return cicloConveniente;
	}

	public void setCicloConveniente(boolean cicloConveniente) {
		this.cicloConveniente = cicloConveniente;
	}

	public CicloEscolar getCicloSeleccionado() {
		return cicloSeleccionado;
	}

	public void setCicloSeleccionado(CicloEscolar cicloSeleccionado) {
		this.cicloSeleccionado = cicloSeleccionado;
	}

	public List<CicloEscolar> getCiclos() {
		return ciclos;
	}

	public void setCiclos(List<CicloEscolar> ciclos) {
		this.ciclos = ciclos;
	}

	public List<PeriodoEscolar> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<PeriodoEscolar> periodos) {
		this.periodos = periodos;
	}

	public CicloEscolar getDominio() {
		return dominio;
	}

	public void setDominio(CicloEscolar dominio) {
		this.dominio = dominio;
	}
}