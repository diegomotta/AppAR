package clases;

import java.io.Serializable;

public class InformacionNutricionalGSON implements Serializable{

	private String item;
	private String descripción;
	
	public InformacionNutricionalGSON(){
		this.item = item;
		this.descripción = descripción;
		
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
	
}
