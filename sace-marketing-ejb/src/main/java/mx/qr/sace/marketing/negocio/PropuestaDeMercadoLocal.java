package mx.qr.sace.marketing.negocio;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import mx.qr.core.exception.ApplicationException;
import mx.qr.sace.persistencia.entidades.Beca;
import mx.qr.sace.persistencia.entidades.Descuento;
import mx.qr.sace.persistencia.entidades.FichaAcademica;
import mx.qr.sace.persistencia.entidades.TramiteCarrera;

/**
 * Define el comportamiento que ofrece la direccion de marketing para ofrecer una propuesta
 * de mercado. Las operaciones van de los descuentos sobre tramites, registro de becas, realizar un presupuesto.
 * 
 * Todo lo relacionado para hacer venta.
 *  
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Julio 2015
 * @copyright Q & R
 */
@Local
public interface PropuestaDeMercadoLocal {

	/**
	 * Para hacerle el presupuesto a un alumno que se ha registrado..
	 * 
	 * Es responsabilidad de la logica del metodo, solamente seleccionar los tramites
	 * que se deben agregar al presupuesto.
	 * 
	 * @param prospecto
	 * @param becaSeleccionada
	 * @param montoTotalIns
	 * @param montoTotalMens
	 * @param tramitesAPagar
	 * @throws ApplicationException
	 */
	public void hacerPresupuesto(String matricula, FichaAcademica fA, Beca becaSeleccionada,
			Float montoTotalIns, Float montoTotalMens,
			Set<TramiteCarrera> tramitesAPagar) throws ApplicationException;
	
	
	/**
	 * 
	 * @param matricula
	 * @param fA
	 * @param becaSeleccionada
	 * @param montoTotalIns
	 * @param montoTotalMens
	 * @param tramitesAPagar
	 * @throws ApplicationException
	 */
	public void modificarPresupuesto(String matricula, FichaAcademica fA, Beca becaSeleccionada,
			Float montoTotalIns, Float montoTotalMens,
			Set<TramiteCarrera> tramitesAPagar) throws ApplicationException;
	
	/**
	 * Re
	 * @param beca
	 * @param descuentosPorBeca
	 * @throws ApplicationException
	 */
	public void registroBeca(Beca beca, List<Descuento> descuentosPorBeca) throws ApplicationException;
	
	/**
	 * Recupera todas las becas activas para ofrecer.
	 * 
	 * @return Todas las becas disponibles y activas.
	 * @throws ApplicationException
	 */
	public List<Beca> recuperaBecas() throws ApplicationException;
}
