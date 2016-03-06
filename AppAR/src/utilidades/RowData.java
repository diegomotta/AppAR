package utilidades;

import android.graphics.Bitmap;

public class RowData {
	
	private int id; 
	private String titulo; 
	private String descripcion; 
	private Bitmap bm;
	private String url;
	private String nombre; 
	private String nacionalidad; 
	private String pais; 
	private String ciudad;
	private String comentario;
	public RowData (int id ,String titulo, String descripcion,Bitmap bm) { 
		this.id = id;
		this.titulo = titulo; 
	    this.descripcion = descripcion; 
	    this.bm = bm; 
	}

	public RowData (int id ,String titulo, String descripcion,Bitmap bm,String url) { 
		this.id = id;
		this.titulo = titulo; 
	    this.descripcion = descripcion; 
	    this.url = url; 
	    this.bm = bm;
	}
	//:nombre, :nacionalidad, :provincia, :ciudad, :comentario
	public RowData (int id ,String nombre, String nacionalidad,String pais,String ciudad,String comentario) { 
		this.id = id;
		this.nombre = nombre; 
	    this.nacionalidad = nacionalidad; 
	    this.pais = pais; 
	    this.ciudad = ciudad;
	    this.comentario = comentario;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
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

	public Bitmap getBm() {
		return bm;
	}

	public void setBm(Bitmap bm) {
		this.bm = bm;
	}

	
}
