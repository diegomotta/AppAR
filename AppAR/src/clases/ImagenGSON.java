package clases;

import java.io.Serializable;

public class ImagenGSON implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
