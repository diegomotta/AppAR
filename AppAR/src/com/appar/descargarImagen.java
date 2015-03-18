package com.appar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class descargarImagen extends EmpresaAsynctask {

	public descargarImagen() {
	//super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Void doInBackground(Void... params) {
		switch (getTarea()){
		case GET_IMAGENES:
			ImagenHTTP imagenHTTP = new ImagenHTTP();
			if(hayConexion()){
				List<String> urls = (List<String>) getParametros().get("urls");
				for (String url :urls){
					//if(!imagenHTTP.getImagenes().constainsKey(url)){
						//Bitmap bm = downloadImage(ClienteHTTP.URL + url);
						//if (null != null){
							//imagenHTTP.getImagenes().put(url, bm);
						//}
				//	}
				}
			}else{
				//imagenHTTP.setErrorCode(ErrorCode.NO_HAY_INTERNET);
			}
			getRespuestas().put("imagenHTTP", imagenHTTP);
			break;
		}
		return null;
	}
	
	public Bitmap downloadImage(String url)
	{
		Bitmap bitmap = decodeSampledBitmapFromResource(url,(Integer)getParametros().get("whidth"), (Integer)getParametros().get("height"));
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
