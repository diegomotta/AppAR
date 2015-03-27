package com.appar;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServidorAsyncTasks extends  EmpresaAsynctask {

	private ClienteGSON cliente;
	private Context applicationContext;
	private Dialog connectionProgressDialog;
	private RespuestaHTTP  objetoProducto;
	private SharedPreference preference;
	private ActionBar activity;
	
	public ServidorAsyncTasks(Context applicationContext, ActionBar actionBar) {
		setCliente(new ClienteGSON(applicationContext));
		setApplicationContext(applicationContext);
		setSharedPreference(new SharedPreference(getApplicationContext()));	
		
	}

	public ServidorAsyncTasks(Context applicationContext) {
		setCliente(new ClienteGSON(applicationContext));
		setApplicationContext(applicationContext);
		setSharedPreference(new SharedPreference(getApplicationContext()));	
		
	}

	public ActionBar getActivity() {
		return activity;
	}

	public void setActivity(ActionBar activity) {
		this.activity = activity;
	}

	public RespuestaHTTP getObjetoProducto() {
		return objetoProducto;
	}

	public void setObjetoProducto(RespuestaHTTP objetoProducto) {
		this.objetoProducto = objetoProducto;
	}

	public SharedPreference getPreference() {
		return preference;
	}

	public void setPreference(SharedPreference preference) {
		this.preference = preference;
	}

	private void setSharedPreference(SharedPreference sharedPreference) {
		preference = sharedPreference;
		
	}
	public Context getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(Context applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public ClienteGSON getCliente() {
		return cliente;
	}

	public void setCliente(ClienteGSON cliente) {
		this.cliente = cliente;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		objetoProducto =(RespuestaHTTP)  getRespuestas().get("respuestaHTTP");
		Intent i = new Intent(applicationContext, Elemento.class); i.putExtra("sampleObject", objetoProducto); 
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		applicationContext.startActivity(i);
		
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		switch (getTarea()){
		case BUSCAR_ELEMENTO:
			getRespuestas().put("respuestaHTTP",cliente.getObjetoEmpresa((QRUrl) getParametros().get("qrdata")));
		case GET_EMPRESAS:
			break;
		case GET_IMAGENES:
			break;
		case GET_VIDEOS:
			break;
		default:
			break;
		}
		return null;
	}

}
