package com.appar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;

public class ImagenGSON implements Serializable{

	private HashMap<String, Object> imagenes;
	private  int id;
	private String titulo;
	private String descripcion;
	private String image_url;
	public ImagenGSON()
	{
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.image_url = image_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public HashMap<String, Object> getImagenes() {
		return imagenes;
	}

	public void setImagenes(HashMap<String, Object> imagenes) {
		this.imagenes = imagenes;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
