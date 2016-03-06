package clases;

import java.io.Serializable;

public class VideoGSON implements Serializable{
	private  int id;
	private String titulo;
	private String descripcion;
	private String url;

	public VideoGSON()
	{
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
