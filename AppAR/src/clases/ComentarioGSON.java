package clases;

import java.io.Serializable;

public class ComentarioGSON implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String nacionalidad;
	private String ciudad;
	private String pais;
	private String comentario;

	public ComentarioGSON () { 
		this.id = id;
		this.nombre = nombre; 
	    this.nacionalidad = nacionalidad; 
	    this.pais = pais; 
	    this.ciudad = ciudad;
	    this.comentario = comentario;
	}
	public ComentarioGSON (String nombre,String nacionalidad,String ciudad,String pais,String comentario) { 
		this.nombre = nombre; 
	    this.nacionalidad = nacionalidad; 
	    this.pais = pais; 
	    this.ciudad = ciudad;
	    this.comentario = comentario;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
