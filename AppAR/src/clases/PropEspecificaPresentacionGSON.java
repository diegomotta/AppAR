package clases;

import java.io.Serializable;

public class PropEspecificaPresentacionGSON implements Serializable{
private int id;
private String item;
private String descripción;

	public PropEspecificaPresentacionGSON(){
		this.id = id;
		this.item = item;
		this.descripción = descripción;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
