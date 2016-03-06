package clases;

import java.io.Serializable;

public class ImagePreparacionMateGSON implements Serializable {

	private String image_url;
	private String título;
	private String descrición;
	public ImagePreparacionMateGSON()
	{
		this.image_url = image_url;
		this.título = título;
		this.descrición = descrición;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	
}
