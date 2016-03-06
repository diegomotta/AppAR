package com.appar;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

public  class QRUrl implements Serializable {
	private URI url;
	private int idQRUrl;
	private int codigoEmpresa;
	private int codigoCategoria;
	private int codigoProducto;
	private String direccionHost;
	public QRUrl(String qrurl) throws URISyntaxException{
		this.url = new URI (qrurl);
		//this.idQRUrl = idQRUrl;
	}
	
	public QRUrl(String qrurl,String direccionHost,int codigoEmpresa,int codigoCategoria, int codigoProducto) throws URISyntaxException{
		this.url = new URI (qrurl);
		this.codigoEmpresa = codigoEmpresa;
		this.codigoCategoria = codigoCategoria;
		this.codigoProducto = codigoProducto;
		this.direccionHost = direccionHost;
		//this.idQRUrl = idQRUrl;
	}
	
	
	public String getDireccionHost() {
		return direccionHost;
	}

	public void setDireccionHost(String direccionHost) {
		this.direccionHost = direccionHost;
	}

	public int getIdQRUrl() {
		return idQRUrl;
	}
	public void setIdQRUrl(int idQRUrl) {
		this.idQRUrl = idQRUrl;
	}
	public URI getUrl() {
		return url;
	}
	public void setUrl(URI url) {
		this.url = url;
	}

	public int getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(int codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public int getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
}
