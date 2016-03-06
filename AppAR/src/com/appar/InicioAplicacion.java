package com.appar;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.HashMap;

import servidor.EmpresaAsyncTask.Task;
import servidor.ServidorAsyncTasks;
import utilidades.SharedPreference;
import vistas.RutaDelMate;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.MapFragment;
public class InicioAplicacion extends Activity implements Serializable{

	    private static final long serialVersionUID = 1L;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);    
	    }   

		public void Escanear (View button) throws URISyntaxException{
			/*QRUrl direccion = null;
			HashMap<String, Object> p = new HashMap<String, Object>();
			String qrdata = "/empresas/1/productos/2/item_productos/2";
			String[] separador = qrdata.split("/");
		        //String direccionHost = "http://192.168.1.8:3000";
			String direccionHost = "http://desolate-cliffs-8987.herokuapp.com";	
			int codigoEmpresa = Integer.valueOf(separador[2]);
			int codigoCategoria =Integer.valueOf(separador[4]);
			int codigoProducto = Integer.valueOf(separador[6]);
			String terminacion = ".json";
			String urldelproducto = direccionHost + qrdata + terminacion;
			direccion = new QRUrl(urldelproducto,direccionHost,codigoEmpresa,codigoCategoria, codigoProducto);
			p.put("qrdata", direccion);
			ProgressDialog progreso = new ProgressDialog (InicioAplicacion.this);
			p.put("progresoQR",progreso);
			ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),direccion);
			s.ejecutarTarea(Task.BUSCAR_ELEMENTO, p);*/
			
        		Intent intent = new Intent("com.appar.SCAN");
        	    	intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        	    	startActivityForResult(intent,0);
	    }
		
		public void Salir (View button) {
			finish();
		}
		
	    public void Ayuda(View  button){
		Intent intent = new Intent(this, vistas.Ayuda.class);
	    	startActivity(intent) ;
	    	}
		
	    protected void onActivityResult (int requestCode, int resultCode, Intent intent){
	    	 QRUrl direccion = null;
	    	 HashMap<String, Object> p = new HashMap<String, Object>();
	    	 try{
		    	if (requestCode == 0){
		    		if (resultCode == RESULT_OK){
		    			try {
			    		    String qrData = intent.getStringExtra("SCAN_RESULT");
			    		    String[] separador = qrData.split("/");
			    		    if ((separador[1]).equals("empresas") && (separador[3]).equals("productos")){
				    			String direccionHost = "http://desolate-cliffs-8987.herokuapp.com";
				    			//String direccionHost = "http://192.168.1.7:3000";
			    		    	int codigoEmpresa = Integer.valueOf(separador[2]);
				    			int codigoCategoria =Integer.valueOf(separador[4]);
				    			int codigoProducto = Integer.valueOf(separador[6]);
				    			String terminacion = ".json";
				    			String urldelproducto = direccionHost + qrData + terminacion;
				    			System.out.println(urldelproducto);
				    			direccion = new QRUrl(urldelproducto,direccionHost,codigoEmpresa,codigoCategoria, codigoProducto);
				    			p.put("qrdata", direccion);
				    			ProgressDialog progreso = new ProgressDialog (InicioAplicacion.this);
				    			p.put("progresoQR",progreso);
				    			ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),direccion);
				    			s.ejecutarTarea(Task.BUSCAR_ELEMENTO, p);	
		    		    }
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							AlertDialog.Builder builder = new AlertDialog.Builder(this);
							builder.setMessage("El código QR no puede ser escaneado por la aplicación")
							        .setTitle("Atención")
							        .setCancelable(false)
							        .setNeutralButton("Aceptar",
							                new DialogInterface.OnClickListener() {
							                    public void onClick(DialogInterface dialog, int id) {
							                        dialog.cancel();
							                    }
							                });
							AlertDialog alert = builder.create();
							alert.show();
						}
		    		}
		    		else if (resultCode == RESULT_CANCELED){
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						builder.setMessage("El código QR no puede ser escaneado por la aplicación")
						        .setTitle("Atención")
						        .setCancelable(false)
						        .setNeutralButton("Aceptar",
						                new DialogInterface.OnClickListener() {
						                    public void onClick(DialogInterface dialog, int id) {
						                        dialog.cancel();
						                    }
						                });
						AlertDialog alert = builder.create();
						alert.show();
		    		}
		    	}	 
	    	 }catch (Exception e){
	    		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setMessage("El código QR no puede ser escaneado por la aplicación")
					        .setTitle("Atención")
					        .setCancelable(false)
					        .setNeutralButton("Aceptar",
					                new DialogInterface.OnClickListener() {
					                    public void onClick(DialogInterface dialog, int id) {
					                        dialog.cancel();
					                    }
					                });
					AlertDialog alert = builder.create();
					alert.show();
	    	 }
	    }


	  
}


 
