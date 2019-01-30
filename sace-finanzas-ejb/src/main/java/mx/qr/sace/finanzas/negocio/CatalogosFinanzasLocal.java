package mx.qr.sace.finanzas.negocio;

import java.util.List;

import javax.ejb.Local;

import mx.qr.core.persistencia.TipoTramite;
import mx.qr.sace.persistencia.entidades.PrecioConcepto;
import mx.qr.sace.persistencia.entidades.Tramite;

/**
 * Define el comportamiento para obtener toda la informacion de los catalogos del 
 * area de finanzas.
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Enero 2017
 * @copyright Q & R
 */
@Local
public interface CatalogosFinanzasLocal {
	
	/**
	 * Obtiene todas los tramites por el tipo tramite.
	 * 
	 * @param tipoTramite
	 * @return
	 */
	public List<Tramite> obtenTramitesPorTipo(TipoTramite tipoTramite);
	
	/**
	 * Obtiene todos los conceptos necesarios que tienen 
	 * un precio que no depende de la carrera.
	 * 
	 * @return
	 */
	public List<Tramite> obtenConceptos();
	
	/**
	 * Obtiene el precio que tiene el concepto. <br/>
	 * El precio del concepto será el que esté activo.<br/>
	 *  
	 * @param concepto
	 * @return
	 */
	public PrecioConcepto obtenPrecioConcepto(Tramite concepto);
}
