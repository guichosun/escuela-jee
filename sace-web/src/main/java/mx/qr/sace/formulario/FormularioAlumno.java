package mx.qr.sace.formulario;

import mx.qr.core.persistencia.EstatusAlumno;
import mx.qr.core.vista.FormularioBase;
import mx.qr.sace.persistencia.entidades.Alumno;
import mx.qr.sace.persistencia.entidades.FichaAcademica;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public abstract class FormularioAlumno extends FormularioBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1345470761652191375L;
	
	/* El dominio del formulario */
	protected Alumno dominio;
	
	/**
	 * La ficha academica que le corresponde al alumno, pero
	 * del sistema de estudios seleccionado.
	 */
	protected FichaAcademica fichaAcademica;
	
	/**
	 * El estatus del alumno segun el modulo donde es invocado
	 */
	protected EstatusAlumno estatusAlumnoEnUso;
	
	/**
	 * 
	 */
	public FormularioAlumno() {
		super();
	}

	public Alumno getDominio() {
		return dominio;
	}

	public void setDominio(Alumno entidad) {
		this.dominio = entidad;
	}

	public EstatusAlumno getEstatusAlumnoEnUso() {
		return estatusAlumnoEnUso;
	}

	public void setEstatusAlumnoEnUso(EstatusAlumno estatusAlumnoEnUso) {
		this.estatusAlumnoEnUso = estatusAlumnoEnUso;
	}

	public FichaAcademica getFichaAcademica() {
		return fichaAcademica;
	}

	public void setFichaAcademica(FichaAcademica fichaAcademica) {
		this.fichaAcademica = fichaAcademica;
	}
}