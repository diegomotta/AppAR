package clases;

import java.io.Serializable;
import java.util.List;

public class PropGeneralProducto implements Serializable {
	private int id;
	private String descripción;
	private List<InformacionNutricionalGSON> prop_general_items;
	private List<ValorEnergetico> galeria_props;

	public PropGeneralProducto() {
		this.id = id;
		this.descripción = descripción;
		this.prop_general_items = prop_general_items;
		this.galeria_props = galeria_props;
	}

	public List<InformacionNutricionalGSON> getProp_general_items() {
		return prop_general_items;
	}

	public void setProp_general_items(
			List<InformacionNutricionalGSON> prop_general_items) {
		this.prop_general_items = prop_general_items;
	}

	public List<ValorEnergetico> getGaleria_props() {
		return galeria_props;
	}

	public void setGaleria_props(List<ValorEnergetico> galeria_props) {
		this.galeria_props = galeria_props;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

}
