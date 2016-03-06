package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetalleGSON implements Serializable {

	private String descripción;
	private String	elaboración;
	private String presentación;
	private String packaging;
	private String origen;
	private String secanza;
	private String característica;
	private List<PropEspecificaPresentacionGSON> prop_especificas = new ArrayList<PropEspecificaPresentacionGSON>();
	
	public DetalleGSON() {
		
		this.descripción = descripción;
		this.elaboración = elaboración;
		this.presentación = presentación;
		this.packaging = packaging;
		this.origen = origen;
		this.secanza = secanza;
		this.característica = característica;
		this.prop_especificas = prop_especificas;
	}
	
	public List<PropEspecificaPresentacionGSON> getProp_especificas() {
		return prop_especificas;
	}

	public void setProp_especificas(List<PropEspecificaPresentacionGSON> prop_especificas) {
		this.prop_especificas = prop_especificas;
	}

	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	public String getElaboración() {
		return elaboración;
	}
	public void setElaboración(String elaboración) {
		this.elaboración = elaboración;
	}
	public String getPresentación() {
		return presentación;
	}
	public void setPresentación(String presentación) {
		this.presentación = presentación;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getSecanza() {
		return secanza;
	}
	public void setSecanza(String secanza) {
		this.secanza = secanza;
	}
	public String getCaracterística() {
		return característica;
	}
	public void setCaracterística(String característica) {
		this.característica = característica;
	}
	
}
