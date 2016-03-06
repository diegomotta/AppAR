package utilidades;

import java.io.Serializable;
import java.util.HashMap;

public class ImagenHTTP implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private HashMap<String, Object> imagenesObt;
	public ImagenHTTP (){
		setImagenesObt(new HashMap<String, Object>());
	}
	public HashMap<String, Object> getImagenesObt() {
		return imagenesObt;
	}

	public void setImagenesObt(HashMap<String, Object> imagenesObt) {
		this.imagenesObt = imagenesObt;
	}
		
}
