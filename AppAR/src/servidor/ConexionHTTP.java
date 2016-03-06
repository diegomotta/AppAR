package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import servidor.EmpresaAsyncTask.Task;

import android.util.Log;

import clases.ClienteHTTP;
import clases.ComentarioGSON;
import clases.ConsultaGson;
import clases.EmpresaGSON;
import clases.EncuestaGSON;

import com.appar.QRUrl;
import com.google.gson.Gson;


public class ConexionHTTP {
	private QRUrl url;
	private ConsultaGson consulta;
	private EncuestaGSON encuestaGson;
	private ComentarioGSON comentario;
	private EmpresaGSON empresa;
	private int responseCode;
	private String message;
	private String response;
	private ArrayList<NameValuePair> parametros;
	private ArrayList<NameValuePair> cabeceras;
	private static final String TAG = "MyActivity";
	public ConexionHTTP(QRUrl qrurl){
		setUrl(qrurl);
		setParametros(new ArrayList<NameValuePair>());
		
	}
	
	public ConexionHTTP(ConsultaGson consulta,QRUrl qrurl){
		setConsulta(consulta);
		setUrl(qrurl);
		setParametros(new ArrayList<NameValuePair>());	
	}

	public ConexionHTTP(EncuestaGSON encuestaGson,QRUrl qrurl){
		setEncuestaGson(encuestaGson);
		setUrl(qrurl);
		setParametros(new ArrayList<NameValuePair>());	
	}

	public ConexionHTTP(ComentarioGSON comentario,QRUrl qrurl){
		setComentario(comentario);
		setUrl(qrurl);
		setParametros(new ArrayList<NameValuePair>());	
	}	

	public ConexionHTTP(){
		
	}
	
	public ConexionHTTP(EmpresaGSON empresa,QRUrl qrurl){
		setEmpresa(empresa);
		setUrl(qrurl);
		setParametros(new ArrayList<NameValuePair>());	
	}	
	
	public EmpresaGSON getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaGSON empresa) {
		this.empresa = empresa;
	}

	public ComentarioGSON getComentario() {
		return comentario;
	}
	

	public void setComentario(ComentarioGSON comentario) {
		this.comentario = comentario;
	}
	
	public void setEncuestaGson(EncuestaGSON encuestaGson) {
		this.encuestaGson = encuestaGson;
	}
	
	public EncuestaGSON getEncuestaGson() {
		return encuestaGson;
	}
	public ConsultaGson getConsulta() {
		return consulta;
	}

	private void setConsulta(ConsultaGson consulta) {
		this.consulta = consulta;
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
		ejecutarRequest(request);
	}
	
	public void ejecutarGetEmpresa (URI url) throws Exception{
		HttpGet request = new HttpGet(url);
		ejecutarRequest(request);
	}
	
	public void ejecutarGetComentario(URI urlComentario) {
		HttpGet request = new HttpGet(urlComentario);
		ejecutarRequest(request);
	    
	}
	//POST
	public void ejecutarRequestConsulta() throws JSONException{
		int TIMEOUT_MILLISEC = 10000; 
		String codigoEmpresa = String.valueOf((getUrl().getCodigoEmpresa()));
	    HttpPost httppost = new HttpPost(ClienteHTTP.URLLocalHost + "/empresas/"+ codigoEmpresa+"/consulta_clientes");
	    try
	    {
	        HttpParams httpParams = new BasicHttpParams();
	    	HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
	    	HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	    	HttpClient client = new DefaultHttpClient(httpParams);
            StringEntity se = new StringEntity(new Gson().toJson(getConsulta()));           
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(se);
            HttpResponse response = client.execute(httppost);
            HttpEntity ent = response.getEntity();
            String text = EntityUtils.toString(ent);
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }

	}
	
	public void ejecutarRequestEncuesta() throws JSONException{
		int TIMEOUT_MILLISEC = 10000; 
		String codigoEmpresa = String.valueOf((getUrl().getCodigoEmpresa()));
	    HttpPost httppost = new HttpPost(ClienteHTTP.URLLocalHost + "/empresas/"+codigoEmpresa+"/estadisticas");
	    try
	    {
	        HttpParams httpParams = new BasicHttpParams();
	    	HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
	    	HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	    	HttpClient client = new DefaultHttpClient(httpParams);
            StringEntity se = new StringEntity(new Gson().toJson(getEncuestaGson()), "UTF-8");
            se.setContentType("application/json;charset=UTF-8");//text/plain;charset=UTF-8
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));            
            //se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            httppost.setEntity(se);
            HttpResponse response = client.execute(httppost);
            HttpEntity ent = response.getEntity();
            String text = EntityUtils.toString(ent);
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }

	}	
	public void ejecutarRequestPostComentario() throws JSONException{
		int TIMEOUT_MILLISEC = 10000; 
		String codigoEmpresa = String.valueOf(getUrl().getCodigoEmpresa());
		String codigoCategoria = String.valueOf(getUrl().getCodigoCategoria());
		String codigoProducto = String.valueOf(getUrl().getCodigoProducto());
		//HttpPost httppost = new HttpPost(ClienteGSON.URLLocalHost + "/empresas/1/productos/1/item_productos/1/opinions");
	    HttpPost httppost = new HttpPost(ClienteHTTP.URLLocalHost + "/empresas/"+codigoEmpresa+"/productos/"+codigoCategoria+"/item_productos/"+codigoProducto+"/opinions");
	    try
	    {
	        HttpParams httpParams = new BasicHttpParams();
	    	HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
	    	HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
	    	HttpClient client = new DefaultHttpClient(httpParams);
	        //ConsultaJson consulta =this.getConsulta();
            StringEntity se = new StringEntity(new Gson().toJson(getComentario()));           
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(se);
            HttpResponse response = client.execute(httppost);
            HttpEntity ent = response.getEntity();
            String text = EntityUtils.toString(ent);
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }

	}
	
	//GET
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
