package com.appar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.DownloadManager.Request;
import android.content.SharedPreferences;

public class ConexionHTTP {
	private QRUrl url;
	private int responseCode;
	private String message;
	private String response;
	private ArrayList<NameValuePair> parametros;
	private ArrayList<NameValuePair> cabeceras;
	
	public ConexionHTTP(QRUrl qrurl){
		setUrl( qrurl);
		setParametros(new ArrayList<NameValuePair>());
		setCabeceras(new ArrayList<NameValuePair>());
	}
	
	public ArrayList<NameValuePair> getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList<NameValuePair> parametros) {
		this.parametros = parametros;
	}

	public ArrayList<NameValuePair> getCabeceras() {
		return cabeceras;
	}

	public void setCabeceras(ArrayList<NameValuePair> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public QRUrl getUrl() {
		return url;
	}
	public void setUrl(QRUrl url) {
		this.url = url;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public  String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void ejecutarGet (URI url) throws Exception{
		HttpGet request = new HttpGet(url);
		/*String combinedParams ="";
		for(NameValuePair p : parametros){
			String paramString = p.getName()+ "=" + URLEncoder.encode(p.getValue(),"UTF-8");
			if(combinedParams.length()>1){
				combinedParams += "&" + paramString;
			}
			else{
				combinedParams += paramString;
			}
		}*/
		ejecutarRequest(request);
	}
	
	public void ejecutarRequest(HttpUriRequest request){
		HttpClient cliente = new DefaultHttpClient();
		HttpResponse httpResponse;
		try{
			httpResponse = cliente.execute(request);
			responseCode = httpResponse.getStatusLine().getStatusCode();
			message = httpResponse.getStatusLine().getReasonPhrase();
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null){
				InputStream instream = entity.getContent();
				response = convertStreamToString(instream);	

			}
		}	
		catch (Exception e){
			e.printStackTrace();
		}

	}
	private String convertStreamToString(InputStream instream) {
		if (instream != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			return sb.toString();
		} else 
		{
			return null;
	    }
		
	}

	public void agregarParametros(String id, String valueOf) {
		this.parametros.add(new BasicNameValuePair(id, valueOf));
		
	}
	
}
