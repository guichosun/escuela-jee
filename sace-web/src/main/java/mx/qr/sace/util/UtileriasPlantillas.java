package mx.qr.sace.util;

import java.io.File;

import javax.enterprise.inject.IllegalProductException;


/**
 * Utilerias per-request para obtener del contexto de faces
 * 
 * @author <a href="mailto:alberto.delcampo@icloud.com">Luis "guichosun" del Campo</a>
 * @since Febrero 2016
 * @copyright Q & R
 */
public final class UtileriasPlantillas {

	private static final UtileriasPlantillas INSTANCE;
	
	private File directorioBasePlantillas;
	
	static {
		INSTANCE = new UtileriasPlantillas();
	}
	
	private UtileriasPlantillas() {
		String realPathPlantillas = UtileriasFaces.obtenContextoExterno().getRealPath(
	    		File.separator+"resources"+File.separator+"plantillas");
		directorioBasePlantillas = new File(realPathPlantillas);
		if(!directorioBasePlantillas.exists()) {
			throw new IllegalProductException("Directorio de plantillas no encontrado");
		}
	}

	public static UtileriasPlantillas getInstance() {
		return INSTANCE;
	}

	public File getDirectorioBasePlantillas() {
		return directorioBasePlantillas;
	}
	
	public File getDirectorioFichas() {
		return new File(getDirectorioBasePlantillas(), "fichas");
	}
	
	public File getPlantilaCompilada(File directorio, String nombreArchivo) {
		return new File(directorio, nombreArchivo);
	}
}
