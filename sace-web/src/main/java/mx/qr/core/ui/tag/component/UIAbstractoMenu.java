package mx.qr.core.ui.tag.component;

import javax.faces.component.UIComponentBase;

import mx.qr.core.ui.ModeloBigMenu;

/**
 * Superclass para los componentes de Menu (MenuPrincipal y BigMenu)
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">
 * @since Julio 2014
 * @copyright Q&R
 */
public abstract class UIAbstractoMenu extends UIComponentBase {

	public static final String BIGMENU_CLASS = "yamm navbar-default";
	public static final String CONTAINER = "container";
    public static final String ROW_CLASS = "row";
    public static final String NAVBAR_HEADER_CLASS = "navbar-header";
    public static final String NAVBAR_TOGGLE_CLASS = "navbar-toggle";
    public static final String ICON_BAR_CLASS = "icon-bar";
    public static final String NAVBAR_COLLAPSE_CLASS = "navbar-collapse collapse";
    public static final String NAVBAR_NAV_CLASS = "nav navbar-nav";
    public static final String PESTANHA_CLASS = "dropdown"; //"dropdown yamm-fw";
    public static final String DROPDOWN_TOGGLE_CLASS = "dropdown-toggle";
    public static final String CARET_CLASS = "caret";
    public static final String DROPDOWN_MENU_CLASS = "dropdown-menu";
    public static final String YAMM_CONTENT_CLASS = "yamm-content";
    public static final String COL_4_CLASS = "col-md-4";
    public static final String COL_6_CLASS = "col-md-6";
    public static final String COL_12_CLASS = "col-md-12";
    public static final String NAVBAR_RIGHT_CLASS = "nav navbar-nav navbar-right";
    public static final String BTN_REPORTES_CLASS = "btn btn-default navbar-btn";
    
	public abstract ModeloBigMenu getModelo();
	
	public abstract void setModelo(ModeloBigMenu modelo);
	
	/**
	 * Para saber si el componente es dinamico o estatico.
	 * O sea si el atributo modelo está establecido o nel
	 * 
	 * @return true sii modelo tiene valor, delo cotrario false
	 */
	public boolean esDinamico() {
		return this.getValueExpression("modelo") != null;
	}
}