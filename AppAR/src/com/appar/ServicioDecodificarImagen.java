package com.appar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.appar.EmpresaAsynctask.Task;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Debug;

public class ServicioDecodificarImagen extends IntentService{
	public ServicioDecodificarImagen() {
		super("ServicioDecodificarImagen");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		RespuestaHTTP objetoProductoPublic = (RespuestaHTTP) intent.getExtras().getSerializable("productoLeido");
		List<ImagenGSON> imgList = objetoProductoPublic.getGaleria_imaganes();
		HashMap<String, Object> p = new HashMap<String,Object>();
		ArrayList<String> urls =  new ArrayList<String>();
		for (ImagenGSON elemento : imgList){
			urls.add(elemento.getImage_url());
		}
		p.put("width", 120);
		p.put("height", 120);
		p.put("urlImages", urls);
		p.put("objetoProducto", objetoProductoPublic);
		DescargarImagen iniciar = new DescargarImagen(getApplicationContext());
		iniciar.ejecutarTarea(Task.GET_IMAGENES, p);
		stopService(new Intent(getBaseContext(), ServicioDecodificarImagen.class));
		
	}
}




//iniciar serivicio o proceso para descargar las url de las imagnes
//ejecutarTarea(Task.GET_imagenes)