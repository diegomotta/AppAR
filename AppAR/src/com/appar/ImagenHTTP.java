package com.appar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImagenHTTP  {
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
