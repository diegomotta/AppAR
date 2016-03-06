package clases;

import java.io.Serializable;

public class VideoEmpresaGSON implements Serializable {
    private String titulo;
    private String url;
    private String descripcion;
    private int id;
    public VideoEmpresaGSON (){
    	this.id = id;
    	this.titulo = titulo;
    	this.url = url;
    	this.descripcion = descripcion;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    
}
