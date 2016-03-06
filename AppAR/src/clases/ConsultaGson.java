package clases;

public class ConsultaGson {

	private String nombre;
	private String correo;
	private String telefono;
	private String descripcion;
	private int id;
	public ConsultaGson(String nombre,String correo, String telefono, String descripcion){
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
