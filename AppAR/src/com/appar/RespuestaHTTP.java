package com.appar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RespuestaHTTP implements Serializable{
	public static final int NO_HAY_INTERNET = 0;
	private int errorCode;
	private int id;
	private String titulo;
	private String descripción;
	private String image_url;
	private List<ImagenGSON> galeria_imaganes = new ArrayList<ImagenGSON>();
	public RespuestaHTTP(){
		this.id = id;
		this.titulo = titulo;
		this.descripción = descripción;
		this.image_url = image_url;
		this.galeria_imaganes = galeria_imaganes ;
	}
		
	public List<ImagenGSON> getGaleria_imaganes() {
		return galeria_imaganes;
	}


	public void setGaleria_imaganes(List<ImagenGSON> galeria_imaganes) {
		this.galeria_imaganes = galeria_imaganes;
	}


	public String getDescripción() {
		return descripción;
	}


	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

	public void setImage(String image_url) {
		this.image_url = image_url;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	

	//private List<String> list ;

	@Override
	public String toString() {
		return "DataObject [id=" + this.id + ", titulo=" + this.titulo + ", descripción=" + this.descripción +"]";

	}
}

	
	
