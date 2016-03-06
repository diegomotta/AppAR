package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmpresaGSON implements Serializable{
private int id;
private String nombre;
private String actividad;
private String país;
private String provincia;
private String localidad;
private String dirección;
private String telefono;
private String misión;
private String visión;
private String image_url;
private List<ProductoGSON> productos = new ArrayList<ProductoGSON>();
private List<NoticiaGSON> noticia_empresas = new ArrayList<NoticiaGSON>();
private List<Mapa> ubicacion_geos = new ArrayList<Mapa>();
private List<VideoEmpresaGSON> videos = new ArrayList<VideoEmpresaGSON>();
private List<Informacion_empresa>  informacion_empresas =new ArrayList<Informacion_empresa>();
	public EmpresaGSON()
	{
		this.id = id;
		this.nombre = nombre;
		this.actividad= actividad;
		this.país = país;
		this.provincia = provincia;
		this.localidad = localidad;
		this.dirección = dirección;
		this.telefono = telefono;
		this.misión = misión;
		this.visión = visión;
		this.image_url = image_url;
		this.productos = productos;
		this.noticia_empresas = noticia_empresas;
		this.ubicacion_geos = ubicacion_geos;
		this.videos = videos;
		this.informacion_empresas = informacion_empresas;
	}

	public String getLocalidad() {
	    return localidad;
	}

	public void setLocalidad(String localidad) {
	    this.localidad = localidad;
	}

	public String getImage_url() {
	    return image_url;
	}

	public void setImage_url(String image_url) {
	    this.image_url = image_url;
	}

	public List<Informacion_empresa> getInformacion_empresas() {
	    return informacion_empresas;
	}

	public void setInformacion_empresas(
		List<Informacion_empresa> informacion_empresas) {
	    this.informacion_empresas = informacion_empresas;
	}

	public String getImage() {
	    return image_url;
	}

	public void setImage(String image_url) {
	    this.image_url = image_url;
	}

	public List<VideoEmpresaGSON> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoEmpresaGSON> videos) {
		this.videos = videos;
	}

	public List<Mapa> getUbicacion_geos() {
		return ubicacion_geos;
	}

	public void setUbicacion_geos(List<Mapa> ubicacion_geos) {
		this.ubicacion_geos = ubicacion_geos;
	}

	public List<NoticiaGSON> getNoticia_empresas() {
		return noticia_empresas;
	}

	public void setNoticia_empresas(List<NoticiaGSON> noticia_empresas) {
		this.noticia_empresas = noticia_empresas;
	}

	public List<ProductoGSON> getProductos() {
		return productos;
	}


	public void setProductos(List<ProductoGSON> productos) {
		this.productos = productos;
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

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getPaís() {
		return país;
	}

	public void setPaís(String país) {
		this.país = país;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCiudad() {
		return localidad;
	}

	public void setCiudad(String ciudad) {
		this.localidad = localidad;
	}

	public String getDirección() {
		return dirección;
	}

	public void setDirección(String dirección) {
		this.dirección = dirección;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMisión() {
		return misión;
	}

	public void setMisión(String misión) {
		this.misión = misión;
	}

	public String getVisión() {
		return visión;
	}

	public void setVisión(String visión) {
		this.visión = visión;
	}
	
	
}
