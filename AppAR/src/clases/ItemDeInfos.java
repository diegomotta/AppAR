package clases;

import java.io.Serializable;

public class ItemDeInfos implements Serializable{
    private String título; private String descripción; String image_url;
    
    public ItemDeInfos(){
	this.título = título;
	this.descripción = descripción;
	this.image_url = image_url;
	
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getUrl_image() {
        return image_url;
    }

    public void setUrl_image(String url_image) {
        this.image_url = url_image;
    }
    
}
