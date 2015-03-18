package com.appar;

import java.net.URISyntaxException;
import java.util.HashMap;

import com.appar.EmpresaAsynctask.Task;

import android.app.IntentService;
import android.content.Intent;

public class ServicioLectura extends IntentService{

	public ServicioLectura() {
		super("ServicioLectura");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//realiza la lectura del lo que viene en el getStrin
		String qrData = intent.getStringExtra("productoLeido");
		HashMap<String, Object> p = new HashMap<String, Object>();
		QRUrl direccion;
		try {
			direccion = new QRUrl(qrData);
			p.put("qrdata", direccion);
			ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext());
			System.out.println("ESTOY EN SERVICIO");
			s.ejecutarTarea(Task.BUSCAR_ELEMENTO, p);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
