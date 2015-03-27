package com.appar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import entradasproducto.Imagen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DescargarImagen extends EmpresaAsynctask {
	private Context context;
	public DescargarImagen(Context context) {
		this.context = context;
	}

	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}


	@Override
	protected Void doInBackground(Void... params) {
		switch (getTarea()){
		case GET_IMAGENES:
			ImagenHTTP imagenHTTP = new ImagenHTTP();
			//if(hayConexion()){
				List<String> urls =  (List<String>) getParametros().get("urlImages");
				if(urls != null)
				for (String url :urls){
					Bitmap bm = downloadImage(ClienteGSON.URLLocalHost + url);
					if(bm != null){
						imagenHTTP.getImagenesObt().put(url, bm);
					}
				}
				else
				{
					System.out.println("Vaciooo");
				}
		//}
			getRespuestas().put("imagenHTTP", imagenHTTP);
			getRespuestas().put("objetoProducto", (RespuestaHTTP)getParametros().get("objetoProducto"));
			break;
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenHTTP");
		RespuestaHTTP respuestaHTTP = (RespuestaHTTP) getRespuestas().get("objetoProducto");
		Intent i = new Intent(getContext(), Imagen.class); 
		i.putExtra("objetoImagenes", imagenHTTP.getImagenesObt());
		i.putExtra("objetoRespuesta", respuestaHTTP);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	public Bitmap downloadImage(String url)
	{
		Bitmap bitmap = decodeSampledBitmapFromResource(url,(Integer)getParametros().get("width"), (Integer)getParametros().get("height"));
		return bitmap;
	}
	
	public static Bitmap decodeSampledBitmapFromResource(String url, int reqWidth,int reqHeight){
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request  = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);
			InputStream stream = response.getEntity().getContent();
			BitmapFactory.decodeStream(stream, null, options);
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		try{
			return BitmapFactory.decodeStream(new URL (url).openConnection().getInputStream(), null, options);
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (OutOfMemoryError e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static int calculateInSampleSize (BitmapFactory.Options options, int reqWidth, int reqHeight){
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		
		if (height > reqHeight || width > reqWidth){
			if(width> height){
				inSampleSize = Math.round((float) height / (float) reqHeight);
			}
			else{
				inSampleSize = Math.round((float)height / (float)reqWidth);
			}
		}
		final float totalPixels = width * height;
		final float totalReqPixelsCap = reqWidth * reqHeight * 2;
		while (totalPixels / (inSampleSize * inSampleSize)> totalReqPixelsCap){
			inSampleSize++;
		}
		return inSampleSize;
	}
	
	public boolean hayConexion() {
		boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable()))
        {
            enabled = false;
        }
        return enabled; 
		}
	
	private ConnectivityManager getSystemService(String connectivityService) {
		return null;
	}
}
