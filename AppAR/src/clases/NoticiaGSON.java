package clases;

import java.io.Serializable;

public class NoticiaGSON implements Serializable{
	private String titulo; 
	private String descripcion;
	private String image_url;
	private int empresa_id;
	
	public NoticiaGSON (){
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.image_url = image_url;
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

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}
	
	
}
