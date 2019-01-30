package mx.qr.sace.formulario;

import java.util.ArrayList;
import java.util.List;

import mx.qr.core.vista.UtileriasMessageSource;

/**
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del
 *         Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public class FormularioNuevaInscripcion extends FormularioAlumno {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1791470761652191375L;
	
	/** Banderas para saber los documentos entregados */
	private boolean[] documentosEntregados = {true, true, true, true, true, true};

	
	
//	Acta de nacimiento
	private boolean documentosEntregados0 = true;
//	Historial académico
	private boolean documentosEntregados1 = true;
//	4 fotográfias
	private boolean documentosEntregados2 = true;
//	Certificado de secundaria
	private boolean documentosEntregados3 = true;
//	Certificado de bachillerato
	private boolean documentosEntregados4 = true;
//	Otro documento
	private boolean documentosEntregados5 = true;
	
	/**
	 * 
	 */
	public FormularioNuevaInscripcion() {
		super();
		migajas = new ArrayList<String>();
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_nueva_inscripcion0"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_nueva_inscripcion1"));
		migajas.add(UtileriasMessageSource
				.mensajeProperties("lbl_nueva_inscripcion2"));
		
	}

	@Override
	public void codifica() {
		documentosEntregados[0] = isDocumentosEntregados0();
		documentosEntregados[1] = isDocumentosEntregados1();
		documentosEntregados[2] = isDocumentosEntregados2();
		documentosEntregados[3] = isDocumentosEntregados3();
		documentosEntregados[4] = isDocumentosEntregados4();
		documentosEntregados[5] = isDocumentosEntregados5();
	}

	@Override
	public void decodifica() {
		

	}

	@Override
	public void limpia() {

	}

	@Override
	public List<String> getMigajas() {
		return migajas;
	}

	@Override
	public void configuraEnConsulta() {
		super.configuraEnConsulta();
	}

	public boolean[] getDocumentosEntregados() {
		return documentosEntregados;
	}

	public void setDocumentosEntregados(boolean[] documentosEntregados) {
		this.documentosEntregados = documentosEntregados;
	}

	public boolean isDocumentosEntregados0() {
		return documentosEntregados0;
	}

	public void setDocumentosEntregados0(boolean documentosEntregados0) {
		this.documentosEntregados0 = documentosEntregados0;
	}

	public boolean isDocumentosEntregados1() {
		return documentosEntregados1;
	}

	public void setDocumentosEntregados1(boolean documentosEntregados1) {
		this.documentosEntregados1 = documentosEntregados1;
	}

	public boolean isDocumentosEntregados2() {
		return documentosEntregados2;
	}

	public void setDocumentosEntregados2(boolean documentosEntregados2) {
		this.documentosEntregados2 = documentosEntregados2;
	}

	public boolean isDocumentosEntregados3() {
		return documentosEntregados3;
	}

	public void setDocumentosEntregados3(boolean documentosEntregados3) {
		this.documentosEntregados3 = documentosEntregados3;
	}

	public boolean isDocumentosEntregados4() {
		return documentosEntregados4;
	}

	public void setDocumentosEntregados4(boolean documentosEntregados4) {
		this.documentosEntregados4 = documentosEntregados4;
	}

	public boolean isDocumentosEntregados5() {
		return documentosEntregados5;
	}

	public void setDocumentosEntregados5(boolean documentosEntregados5) {
		this.documentosEntregados5 = documentosEntregados5;
	}


}