package com.appar;

import com.google.gson.Gson;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ClienteGSON {
	private Gson gson;
	private Context context;
	public static final String TAG = "info";
	public static final String URLLocalHost = "http://10.0.2.2:3000";
	public ClienteGSON (Context context){
		setGson(new Gson());
		setContext(context);
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

	public boolean hayConexion() {
	  ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	  NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
	  return networkInfo != null && networkInfo.isConnectedOrConnecting();
	}
    
	private ConnectivityManager getSystemService(String connectivityService) {
	    return null;
	}

	public RespuestaHTTP getObjetoEmpresa(QRUrl parametros){
		RespuestaHTTP respuesta = new RespuestaHTTP();
		//if (hayConexion() == true){
			
			ConexionHTTP con = new ConexionHTTP(parametros);
			con.agregarParametros("idObjeto", String.valueOf(parametros.getIdQRUrl()));
			try {	
				con.ejecutarGet(parametros.getUrl());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String json_response = con.getResponse();
			respuesta = getGson().fromJson(json_response,RespuestaHTTP.class);
			return respuesta;

		}

}
