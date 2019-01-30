package mx.qr.sace.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import mx.qr.sace.UsuarioSACE;
import mx.qr.sace.ce.negocio.CatalogosCELocal;
import mx.qr.sace.dominio.SistemaEstudio;
import mx.qr.sace.persistencia.entidades.Carrera;
import mx.qr.sace.persistencia.entidades.Escolaridad;
import mx.qr.sace.persistencia.entidades.Modalidad;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * MB con el comportamiento base para los diferentes Home del sistema.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public abstract class MBHomeBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1064504206280230691L;
	
	protected static final Escolaridad ESCOLARIDAD_DEFAULT;
	
	/* La modalidad seleccionada del menu */
	protected static final Modalidad MODALIDAD_DEFAULT;
	
	static {
		ESCOLARIDAD_DEFAULT = new Escolaridad(1);
		MODALIDAD_DEFAULT = new Modalidad(1);
	}
	
	@EJB(mappedName="java:global/sace-ear/sace-controlescolar-ejb/catalogosControlEscolar")
	protected CatalogosCELocal beanCatalogoCE;
	
	protected SistemaEstudio sistemaEstudioUsuario;

	/**
	 * Recupera el usuario que esta registrado actualmente en el sistema.
	 * 
	 * @return El usuario que esta registrado en el sistema.
	 */
	public UsuarioSACE obtieneUsuario() {
		//Se obtienen el usuario que esta registrado en el sistema.
		
		SecurityContext context    = SecurityContextHolder.getContext();
		Authentication  auth       = context.getAuthentication();
		
		return (UsuarioSACE)auth.getPrincipal(); 
	}
	
	/**
	 * Para obtener todos las carreras de una sistema de estudios seleccionado.
	 * 
	 * En session
	 * 
	 * @return
	 */
	public List<Carrera> obtieneCarreras() {
		return beanCatalogoCE.obtenCarrerasPorEscolaridadYModalidad(
				getSistemaEstudioUsuario().getEscolaridad().getIdEscolaridad(),
				getSistemaEstudioUsuario().getModalidad().getIdModalidad());
	}
	
	public List<Carrera> obtieneCarreras(int escolaridad, int modalidad) {
		return beanCatalogoCE.obtenCarrerasPorEscolaridadYModalidad(
				escolaridad, modalidad);
	}
	
	/**
	 * las escolaridades del negocio
	 * @return
	 */
	public List<Escolaridad> lasEscolaridades() {
		return beanCatalogoCE.obtenEscolaridades();
	}
	
	public List<Modalidad> lasModalidadesPorEscolaridad(SistemaEstudio sisEstudio) {
		return beanCatalogoCE.obtenModalidadesPorEscolaridad(sisEstudio.getEscolaridad().getIdEscolaridad());
	}

	public SistemaEstudio getSistemaEstudioUsuario() {
		return sistemaEstudioUsuario;
	}

	public void setSistemaEstudioUsuario(SistemaEstudio sistemaEstudio) {
		this.sistemaEstudioUsuario = sistemaEstudio;
	}
	
}
