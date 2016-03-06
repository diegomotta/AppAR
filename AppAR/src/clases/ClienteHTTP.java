package clases;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;

import servidor.ConexionHTTP;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.appar.QRUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClienteHTTP {
	private Gson gson;
	private Context context;
	private static final String TAG = "MyActivity";
	private QRUrl codigoqr;
	//"http://10.0.2.2:3000" Emulador por defecto de android
	//192.168.1.7:3000 genimotion
	public static final String URLLocalHost = "http://desolate-cliffs-8987.herokuapp.com";
	//public static final String URLLocalHost = "http://192.168.1.8:3000";

	public ClienteHTTP (Context context, QRUrl codigoqr){
		setGson(new Gson());
		setContext(context);
		setCodigoqr(codigoqr);
	}

	public boolean hayConexion() {
	  ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	  NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
	  return networkInfo != null && networkInfo.isConnectedOrConnecting();
	}
    
	private ConnectivityManager getSystemService(String connectivityService) {
	    return null;
	}

	public QRUrl getCodigoqr() {
		return codigoqr;
	}
	public void setCodigoqr(QRUrl codigoqr) {
		this.codigoqr = codigoqr;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
	public RespuestaGSON getObjetoEmpresa(QRUrl parametros){
		   RespuestaGSON respuesta = new RespuestaGSON();
		//if (hayConexion() == true){
			ConexionHTTP con = new ConexionHTTP(parametros);
			try {	
				con.ejecutarGet(parametros.getUrl());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String json_response = con.getResponse();
			respuesta = getGson().fromJson(json_response,RespuestaGSON.class);
			return respuesta;
			
		}
	public EmpresaGSON getDatosEmpresa (QRUrl codigoqr) throws URISyntaxException
	{
		EmpresaGSON empresa = new EmpresaGSON ();
		ConexionHTTP con = new ConexionHTTP(codigoqr);
		int codigoEmpresa = codigoqr.getCodigoEmpresa();
		String codigoEmpresaString = String.valueOf(codigoEmpresa);
		URI urlEmpresa =new URI (ClienteHTTP.URLLocalHost + "/empresas/"+codigoEmpresaString+".json");
		try {
			con.ejecutarGetEmpresa(urlEmpresa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json_response = con.getResponse();
		empresa = getGson().fromJson(json_response,EmpresaGSON.class);
		return empresa;
	}
	

	
	public String getVideosProducto(String urlRecibida) {
		return urlRecibida;
	}
	
	public String seConsulta(ConsultaGson consultaGson, QRUrl codigoqr){
	    ConexionHTTP con = new ConexionHTTP(consultaGson,codigoqr);
		try {
			con.ejecutarRequestConsulta();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String seEncuesta(EncuestaGSON encuestaGson, QRUrl codigoqr){
	    ConexionHTTP con = new ConexionHTTP(encuestaGson,codigoqr);
		try {
			con.ejecutarRequestEncuesta();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String seComenta(ComentarioGSON comentarioGson,QRUrl codigoqr){
	    ConexionHTTP con = new ConexionHTTP(comentarioGson,codigoqr);
		try {
			con.ejecutarRequestPostComentario();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public RespuestaGSON getComentarios (QRUrl codigoqr) throws URISyntaxException{
	    RespuestaGSON empresa = new RespuestaGSON ();
		ConexionHTTP con = new ConexionHTTP(codigoqr);
		int codigoEmpresa = codigoqr.getCodigoEmpresa();
		int codigoProducto = codigoqr.getCodigoProducto();
		int codigoCategoira = codigoqr.getCodigoCategoria();
		String codigoEmpresaString = String.valueOf(codigoEmpresa);
		URI urlComentario =new URI (ClienteHTTP.URLLocalHost + "/empresas/"+codigoEmpresaString+"/productos/"+ String.valueOf(codigoCategoira)+ "/item_productos/"+String.valueOf(codigoProducto)+"/"+"opinions.json");

		try {
			con.ejecutarGetComentario(urlComentario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json_response = con.getResponse();
		
	        Gson gson = new Gson();
	        // create the type for the collection. In this case define that the collection is of type Dataset
	        Type datasetListType = new TypeToken<Collection<ComentarioGSON>>() {}.getType();
	        List<ComentarioGSON> datasets = gson.fromJson(json_response, datasetListType);
	        empresa.setComentarios_producto(datasets);
		return empresa;
	}


}
