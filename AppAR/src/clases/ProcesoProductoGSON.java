package clases;

import java.io.Serializable;

public class ProcesoProductoGSON implements Serializable{

	private int id;
	private String título;
	private String descrición;
	private String image_url;
	
	public ProcesoProductoGSON (){
		this.id = id;
		this.título = título;
		this.descrición = descrición;
		this.image_url = image_url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTítulo() {
		return título;
	}

	public void setTítulo(String título) {
		this.título = título;
	}

	public String getDescrición() {
		return descrición;
	}

	public void setDescrición(String descrición) {
		this.descrición = descrición;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
}
