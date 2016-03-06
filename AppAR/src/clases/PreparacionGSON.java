package clases;

import java.io.Serializable;

public class PreparacionGSON implements Serializable{

	private int id;
	private String descrición;
	private String título;
	
	public PreparacionGSON(){
		this.id = id;
		this.descrición = descrición;
		this.título = título;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrición() {
		return descrición;
	}

	public void setDescrición(String descrición) {
		this.descrición = descrición;
	}

	public String getTítulo() {
		return título;
	}

	public void setTítulo(String título) {
		this.título = título;
	}
	
	
}
