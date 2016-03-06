package clases;

import java.io.Serializable;

public class Mapa implements Serializable{
private String latitude;
private String longitude;
private String addres;
private String description;
private String title;
private String image_url;
public Mapa (){
	this.latitude = latitude;
	this.longitude = longitude;
	this.addres = addres;
	this.description = description;
	this.title = title;
	this.image_url = image_url;
}

public String getImage_url() {
    return image_url;
}

public void setImage_url(String image_url) {
    this.image_url = image_url;
}

public String getLatitude() {
	return latitude;
}

public void setLatitude(String latitude) {
	this.latitude = latitude;
}

public String getLongitude() {
	return longitude;
}

public void setLongitude(String longitude) {
	this.longitude = longitude;
}



public String getAddres() {
	return addres;
}

public void setAddres(String addres) {
	this.addres = addres;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}


}
