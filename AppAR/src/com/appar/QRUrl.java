package com.appar;

import java.net.URI;
import java.net.URISyntaxException;

public  class QRUrl {
	private URI url;
	private int idQRUrl;
	public QRUrl(String qrurl) throws URISyntaxException{
		this.url = new URI (qrurl);
		//this.idQRUrl = idQRUrl;
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

	
	
}
