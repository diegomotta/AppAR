package com.appar;

import java.io.Serializable;

import android.app.PendingIntent;

public class RespuestaHTTP implements Serializable{
	public static final int NO_HAY_INTERNET = 0;
	private int errorCode;
	private int id;
	private String titulo;
	private String descripción;
	public RespuestaHTTP(){
		this.id = id;
		this.titulo = titulo;
		this.descripción = descripción;
	}
		
	public String getDescripcion() {
		return descripción;
	}

	public void setDescripcion(String descripción) {
		this.descripción = descripción;
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

	
	
