package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RespuestaGSON implements Serializable{
	public static final int NO_HAY_INTERNET = 0;
	private int errorCode;
	private int id;
	private String tipo;
	private String marca;
	private String image_url;
	private String precio;

	private List<ImagenGSON> galeria_imagenes = new ArrayList<ImagenGSON>();
	private List<VideoGSON> videos_productos = new ArrayList<VideoGSON>();
	private List<ComentarioGSON> opinions = new ArrayList<ComentarioGSON>();
	private List<DetalleGSON> detalles = new ArrayList<DetalleGSON>();
	public List<DetalleGSON> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleGSON> detalles) {
		this.detalles = detalles;
	}

	public RespuestaGSON(){
		this.id = id;
		this.marca = marca;
		this.tipo = tipo;
		this.precio = precio;
		this.image_url = image_url;
		this.galeria_imagenes = galeria_imagenes ;
		this.videos_productos = videos_productos;
		this.opinions = opinions;
		this.detalles = detalles;
	}
	public String getPrecio() {
	    return precio;
	}

	public void setPrecio(String precio) {
	    this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public List<ComentarioGSON> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<ComentarioGSON> opinions) {
		this.opinions = opinions;
	}

	public List<ComentarioGSON> getComentarios_producto() {
		return opinions;
	}

	public void setComentarios_producto(List<ComentarioGSON> comentarios_producto) {
		this.opinions = comentarios_producto;
	}

	public List<VideoGSON> getVideos_productos() {
		return videos_productos;
	}

	public void setVideos_productos(List<VideoGSON> videos_productos) {
		this.videos_productos = videos_productos;
	}

	public List<ImagenGSON> getGaleria_imagenes() {
		return galeria_imagenes;
	}

	public void setGaleria_imagenes(List<ImagenGSON> galeria_imagenes) {
		this.galeria_imagenes = galeria_imagenes;
	}


	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	public void setImage(String image_url) {
		this.image_url = image_url;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "DataObject [id=" + this.id + ", titulo=" + this.marca + ", descripción=" + this.tipo +"]";

	}
}

	
	
