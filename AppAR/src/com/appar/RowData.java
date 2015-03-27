package com.appar;

import android.graphics.Bitmap;

public class RowData {
	
	private int id; 
	private String titulo; 
	private String descripcion; 
	private Bitmap bm;
	
	public RowData (int id ,String titulo, String descripcion,Bitmap bm) { 
		this.id = id;
		this.titulo = titulo; 
	    this.descripcion = descripcion; 
	    this.bm = bm; 
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

	public Bitmap getBm() {
		return bm;
	}

	public void setBm(Bitmap bm) {
		this.bm = bm;
	}

	
}
