package mx.qr.core.ui;

import java.io.Serializable;
import java.util.List;

/**
 * Regresenta el modelo para pintar el big menu. 
 *
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 2014
 * @copyright Direccion de sistemas - IFE
 */
public class BigMenu implements Serializable, ModeloBigMenu {

	private static final long serialVersionUID = 1L;

	private List<Pestanha> pestanhas;

	public BigMenu() {
		super();
	}
	
	public BigMenu(List<Pestanha> pestanhas) {
		this();
		this.pestanhas = pestanhas;
	}

	public List<Pestanha> getPestanhas() {
		return pestanhas;
	}

	public void setPestanhas(List<Pestanha> pestanhas) {
		this.pestanhas = pestanhas;
	}
	
	
}
