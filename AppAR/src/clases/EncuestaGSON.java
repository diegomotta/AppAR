package clases;

import android.widget.EditText;

public class EncuestaGSON {
	
private	String califproducto;
private	String precio;
private	String imagenempresareg;  
private	String enteroprod;
private	String sabortoman;
private	String cuantoconsumo;
private	String justifprod;
private	String yerbamedicinal;
private	String otroproducto;
private int id;
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public EncuestaGSON(String califproducto
		, String precio, 
		String imagenempresareg,
		String enteroprod,
		String sabortoman,
		String cuantoconsumo,
		String justifprod,
		String yerbamedicinal,
		String otroproducto){
	 this.califproducto = califproducto;
	 this.precio =precio ;
	 this.imagenempresareg = imagenempresareg;  
	 this.enteroprod = enteroprod;
	 this.sabortoman = sabortoman;
	 this.cuantoconsumo = cuantoconsumo;
	 this.justifprod =justifprod;
	 this.yerbamedicinal = yerbamedicinal;
	 this.otroproducto = otroproducto;
}

public String getCalifproducto() {
	return califproducto;
}

public void setCalifproducto(String califproducto) {
	this.califproducto = califproducto;
}

public String getPrecio() {
	return precio;
}

public void setPrecio(String precio) {
	this.precio = precio;
}

public String getImagenempresareg() {
	return imagenempresareg;
}

public void setImagenempresareg(String imagenempresareg) {
	this.imagenempresareg = imagenempresareg;
}

public String getEnteroprod() {
	return enteroprod;
}

public void setEnteroprod(String enteroprod) {
	this.enteroprod = enteroprod;
}

public String getSabortoman() {
	return sabortoman;
}

public void setSabortoman(String sabortoman) {
	this.sabortoman = sabortoman;
}

public String getCuantoconsumo() {
	return cuantoconsumo;
}

public void setCuantoconsumo(String cuantoconsumo) {
	this.cuantoconsumo = cuantoconsumo;
}

public String getJustifprod() {
	return justifprod;
}

public void setJustifprod(String justifprod) {
	this.justifprod = justifprod;
}

public String getYerbamedicinal() {
	return yerbamedicinal;
}

public void setYerbamedicinal(String yerbamedicinal) {
	this.yerbamedicinal = yerbamedicinal;
}

public String getOtroproducto() {
	return otroproducto;
}

public void setOtroproducto(String otroproducto) {
	this.otroproducto = otroproducto;
}


}
