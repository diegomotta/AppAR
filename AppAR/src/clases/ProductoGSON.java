package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductoGSON implements Serializable{

	private int id;
	private String titulo;
	private String url;
	private List<PropGeneralProducto> prop_generals =new ArrayList<PropGeneralProducto> ();
	private List<ProcesoProductoGSON> galeria_procesos = new ArrayList<ProcesoProductoGSON>(); 
	private List<PreparacionGSON> preparar_mates = new ArrayList<PreparacionGSON>();
	private List<ImagePreparacionMateGSON> galeria_prep_mates = new ArrayList<ImagePreparacionMateGSON>();
	public ProductoGSON(){
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.prop_generals = prop_generals;
		this.preparar_mates = preparar_mates;
		this.galeria_prep_mates = galeria_prep_mates;
		this.galeria_procesos = galeria_procesos;
	}

	public List<ImagePreparacionMateGSON> getGaleria_prep_mates() {
		return galeria_prep_mates;
	}

	public void setGaleria_prep_mates(
			List<ImagePreparacionMateGSON> galeria_prep_mates) {
		this.galeria_prep_mates = galeria_prep_mates;
	}

	public List<PreparacionGSON> getPreparar_mates() {
		return preparar_mates;
	}

	public void setPreparar_mates(List<PreparacionGSON> preparar_mates) {
		this.preparar_mates = preparar_mates;
	}

	public List<ProcesoProductoGSON> getGaleria_procesos() {
		return galeria_procesos;
	}

	public void setGaleria_procesos(List<ProcesoProductoGSON> galeria_procesos) {
		this.galeria_procesos = galeria_procesos;
	}

	public List<PropGeneralProducto> getProp_generals() {
		return prop_generals;
	}

	public void setProp_generals(List<PropGeneralProducto> prop_generals) {
		this.prop_generals = prop_generals;
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
	
	
}
