package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import utilidades.ImagenHTTP;
import utilidades.VideoHTTP;
import vistas.DescripcionGaleriaImagen;
import vistas.FichaProducto;
import vistas.ImagenPrepMate;
import vistas.Producto;
import vistas.RutaDelMate;
import vistas.ValoresEnergeticos;
import vistas.VideoProducto;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import clases.ClienteHTTP;
import clases.EmpresaGSON;
import clases.ItemDeInfos;
import clases.NoticiaGSON;
import clases.ProcesoProductoGSON;
import clases.ProductoGSON;
import clases.RespuestaGSON;

import com.appar.QRUrl;

public class ImagenAsyncTask extends EmpresaAsyncTask {
	private Context context;
	private ProgressDialog progressDialogo;
	private QRUrl codigoqr;
	public ImagenAsyncTask(Context context, QRUrl codigoqr) {
		this.context = context;
		this.codigoqr = codigoqr;
	}
	
	public ImagenAsyncTask(){}
	
	
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
	public ProgressDialog getProgressDialogo() {
		return progressDialogo;
	}

	public void setProgressDialogo(ProgressDialog progressDialogo) {
		this.progressDialogo = progressDialogo;
	}
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		if (getParametros().containsKey("progresoImagenes")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
           		progressDialogo.setMessage("Cargando imágenes el producto");
			progressDialogo.show();
		}
		else if (getParametros().containsKey("progresoVideos")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Cargando videos del producto");
			progressDialogo.show();	
		}
		else if (getParametros().containsKey("progresoCaracteristicas")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Cargando caracteristicas del producto");
			progressDialogo.show();			    
		}
		else if (getParametros().containsKey("progresoNoticia")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Procesando...");
			progressDialogo.show();		
		}
		else if (getParametros().containsKey("progresoElaboracion")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Procesando...");
			progressDialogo.show();		
		}
		else if (getParametros().containsKey("progresoInfo")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Procesando...");
			progressDialogo.show();		
		}		
		else if (getParametros().containsKey("progresoImagenesMapa")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Procesando...");
			progressDialogo.show();		
		}		
				
		
	}

	@Override
	protected Void doInBackground(Void... params) {
		switch (getTarea()){
		case GET_IMAGENES:
			setProgressDialogo((ProgressDialog) getParametros().get("progresoImagenes"));
			publishProgress();
			ImagenHTTP imagenHTTP = new ImagenHTTP();
			//if(hayConexion()){
				List<String> urls =  (List<String>) getParametros().get("urlImages");
				if(urls != null)
				{
					for (String url :urls){
						
						Bitmap bm = downloadImage(ClienteHTTP.URLLocalHost + url);
						if(bm != null){
							imagenHTTP.getImagenesObt().put(url, bm);
						}
					}
				}
				else
				{
					System.out.println("Vacioo");
				}
			getRespuestas().put("bandera", "imagen");
			getRespuestas().put("imagenHTTP", imagenHTTP);
			getRespuestas().put("objetoProducto", (RespuestaGSON)getParametros().get("objetoProducto"));
			break;
		case GET_IMAGENES_PREPARAR_MATE:
		
			ImagenHTTP imagenPrepMateHTTP = new ImagenHTTP();
			//if(hayConexion()){
				List<String> urlsPrepMate =  (List<String>) getParametros().get("urlImagesPrepMate");
				if(urlsPrepMate != null)
				{
					for (String url :urlsPrepMate){
						System.out.println(url);
						Bitmap bm = downloadImage(ClienteHTTP.URLLocalHost + url);
						if(bm != null){
							imagenPrepMateHTTP.getImagenesObt().put(url, bm);
						}
					}
				}
				else
				{
					System.out.println("Vacioo");
				}
		//}
			getRespuestas().put("bandera", "imagenPrepMate");
			getRespuestas().put("imagenPrepMateHTTP", imagenPrepMateHTTP);
			getRespuestas().put("objetoPrepMate", (ProductoGSON)getParametros().get("objetoPrepMate"));
			break;	
			
		case GET_IMAGENES_VALORES_ENERGETICOS:
			ImagenHTTP imagenValorEnergeticoHTTP = new ImagenHTTP();
			//if(hayConexion()){
				List<String> urlsValoresEnergeticos =  (List<String>) getParametros().get("urlImagesValoresEnergeticos");
				if(!urlsValoresEnergeticos.isEmpty())
				{
					
					for (String url :urlsValoresEnergeticos){
						System.out.println(url);
						System.out.println(ClienteHTTP.URLLocalHost + url);
						Bitmap bm = downloadImage(ClienteHTTP.URLLocalHost + url);
						if(bm != null){
							imagenValorEnergeticoHTTP.getImagenesObt().put(url, bm);
						}
					}
				}
				else
				{
					System.out.println("Vacioo");
				}
			getRespuestas().put("bandera", "imagenValorEnergetico");
			getRespuestas().put("imagenValorEnergeticoHTTP", imagenValorEnergeticoHTTP);
			getRespuestas().put("objetoValorEnergetico", (ProductoGSON)getParametros().get("objetoValorEnergetico"));	
			break;
		case GET_IMAGEN:
			setProgressDialogo((ProgressDialog) getParametros().get("progresoCaracteristicas"));
			publishProgress();
			getRespuestas().put("bandera", "imagenUnica");
			ImagenHTTP imagenUnicaHTTP = new ImagenHTTP();
			//if(hayConexion()){
			String urlImagenUnica =  (String) getParametros().get("urlImagenUnica");
			Bitmap bm1 = downloadImage(ClienteHTTP.URLLocalHost + urlImagenUnica);
			imagenUnicaHTTP.getImagenesObt().put(urlImagenUnica, bm1);		
			getRespuestas().put("imagenUnicaHTTP", imagenUnicaHTTP);
			getRespuestas().put("objetoProducto", (RespuestaGSON)getParametros().get("objetoProducto"));
			break;			

		case GET_IMAGEN_PROCESO:
		        setProgressDialogo((ProgressDialog) getParametros().get("progresoNoticia"));
		        publishProgress();
			getRespuestas().put("bandera", "procesoObtencion");
			ImagenHTTP imagenProcesoHTTP = new ImagenHTTP();
			String urlimagenProcesoHTTP =  (String) getParametros().get("urlImagenProc");
			Bitmap bm2 = downloadImage(ClienteHTTP.URLLocalHost + urlimagenProcesoHTTP);
			imagenProcesoHTTP.getImagenesObt().put(urlimagenProcesoHTTP, bm2);		
			getRespuestas().put("imagenProcesoHTTP", imagenProcesoHTTP);
			setProgressDialogo((ProgressDialog) getParametros().get("progresoNoticia"));
			publishProgress();
			getRespuestas().put("objetoProceso", (NoticiaGSON)getParametros().get("objetoProceso"));
			break;		
		case GET_IMAGEN_INFO:
		        setProgressDialogo((ProgressDialog) getParametros().get("progresoInfo"));
		        publishProgress();
			getRespuestas().put("bandera", "procesoObtencion");
			ImagenHTTP imagenProcesoHTTP1 = new ImagenHTTP();
			String urlimagenProcesoHTTP1 =  (String) getParametros().get("urlImagenProc");
			Bitmap bm21 = downloadImage(ClienteHTTP.URLLocalHost + urlimagenProcesoHTTP1);
			imagenProcesoHTTP1.getImagenesObt().put(urlimagenProcesoHTTP1, bm21);		
			getRespuestas().put("imagenProcesoHTTP", imagenProcesoHTTP1);
			setProgressDialogo((ProgressDialog) getParametros().get("progresoInfo"));
			publishProgress();
			getRespuestas().put("objetoProceso", (ItemDeInfos)getParametros().get("objetoProceso"));
			break;				
		case GET_IMAGEN_ELABORACION:
		        setProgressDialogo((ProgressDialog) getParametros().get("progresoElaboracion"));
		        publishProgress();
			getRespuestas().put("bandera", "procesoObtencion");
			ImagenHTTP imagenProcesoHTTP2 = new ImagenHTTP();
			String urlimagenProcesoHTTP2 =  (String) getParametros().get("urlImagenProc");
			Bitmap bm22 = downloadImage(ClienteHTTP.URLLocalHost + urlimagenProcesoHTTP2);
			imagenProcesoHTTP2.getImagenesObt().put(urlimagenProcesoHTTP2, bm22);		
			getRespuestas().put("imagenProcesoHTTP", imagenProcesoHTTP2);
			getRespuestas().put("objetoProceso", (ProcesoProductoGSON)getParametros().get("objetoProceso"));
			break;	
		case GET_VIDEOS:
			VideoHTTP videoHTTP = new VideoHTTP();
			setProgressDialogo((ProgressDialog) getParametros().get("progresoVideos"));
			publishProgress();			
			//if(hayConexion()){
				List<String> urlsvid =  (List<String>) getParametros().get("urlVideos");
				if(urlsvid != null)
				{
					for (String url :urlsvid){
					    System.out.println(url);
						Bitmap bm = downloadImage("http://img.youtube.com/vi/"+url+"/0.jpg" );
						if(bm != null){
							videoHTTP.getVideosObt().put(url, bm);
						}
					}
				}
				else
				{
					System.out.println("Vaciooo");
				}
			getRespuestas().put("bandera", "video");
			getRespuestas().put("videoHTTP", videoHTTP);
			//if(getParametros().get("objetoProducto") instanceof RespuestaGSON){
				getRespuestas().put("objetoProducto", (RespuestaGSON)getParametros().get("objetoProducto"));
			//}
			/*else if (getParametros().get("objetoProducto") instanceof EmpresaGSON){
				getRespuestas().put("objetoProducto", (EmpresaGSON)getParametros().get("objetoProducto"));
			}*/
			break;
		case GET_IMAGENES_MAPA:
			setProgressDialogo((ProgressDialog) getParametros().get("progresoImagenesMapa"));
			publishProgress();
			ImagenHTTP imagenHTTP1 = new ImagenHTTP();
			//if(hayConexion()){
				List<String> urls1 =  (List<String>) getParametros().get("urlImages");
				if(urls1 != null)
				{
					for (String url :urls1){
						
						Bitmap bm = downloadImage(ClienteHTTP.URLLocalHost + url);
						if(bm != null){
							imagenHTTP1.getImagenesObt().put(url, bm);
						}
					}
				}
				else
				{
					System.out.println("Vacioo");
				}
			getRespuestas().put("bandera", "imagenMapa");
			getRespuestas().put("imagenMapaHTTP", imagenHTTP1);
			getRespuestas().put("objetoProducto", (EmpresaGSON)getParametros().get("objetoProducto"));		    
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		String valor =(String) getRespuestas().get("bandera");
		if(valor.equals("imagen")){
			ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenHTTP");
			RespuestaGSON respuestaHTTP = (RespuestaGSON) getRespuestas().get("objetoProducto");
			Intent i = new Intent(getContext(), DescripcionGaleriaImagen.class); 
			i.putExtra("objetoImagenes", imagenHTTP.getImagenesObt());
			i.putExtra("objetoRespuesta", respuestaHTTP);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			progressDialogo.dismiss();
			context.startActivity(i);
		}
		else if(valor.equals("imagenUnica"))
		{
			ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenUnicaHTTP");
			RespuestaGSON respuestaHTTP = (RespuestaGSON) getRespuestas().get("objetoProducto");
			Intent i = new Intent(getContext(), FichaProducto.class); 
			i.putExtra("objetoImagenUnica", imagenHTTP.getImagenesObt());
			i.putExtra("objetoRespuesta", respuestaHTTP);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			progressDialogo.dismiss();
			context.startActivity(i);	
		}
		else if(valor.equals("video")){
			VideoHTTP videoHTTP =  (VideoHTTP) getRespuestas().get("videoHTTP");
			
			/*if(getRespuestas().get("objetoProducto") instanceof EmpresaGSON){
				EmpresaGSON respuestaHTTP = (EmpresaGSON) getRespuestas().get("objetoProducto");
				Intent i = new Intent(getContext(), VideoProducto.class); 
				i.putExtra("objetoVideos", videoHTTP.getVideosObt());
				i.putExtra("objetoRespuesta", respuestaHTTP);
				i.putExtra("codigoQR", getCodigoqr());
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				progressDialogo.dismiss();
				context.startActivity(i);				
			}*/
			// if (getRespuestas().get("objetoProducto") instanceof RespuestaGSON){
				RespuestaGSON respuestaHTTP = (RespuestaGSON) getRespuestas().get("objetoProducto");
				Intent i = new Intent(getContext(), VideoProducto.class); 
				i.putExtra("objetoVideos", videoHTTP.getVideosObt());
				i.putExtra("objetoRespuesta", respuestaHTTP);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getContext().startActivity(i);	
				progressDialogo.dismiss();

			//}

		}
		else if (valor.equals("procesoObtencion")){
			if (getRespuestas().get("objetoProceso") instanceof ProcesoProductoGSON){
				ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenProcesoHTTP");
				ProcesoProductoGSON procesoprod = (ProcesoProductoGSON) getRespuestas().get("objetoProceso");
				TextView textoTitulo = (TextView) getParametros().get("textoTitulo");
				textoTitulo.setText(procesoprod.getTítulo());
				TextView textoProceso = (TextView) getParametros().get("textoDescripcion");
				textoProceso.setText(procesoprod.getDescrición());
				ImageSwitcher imageProceso = (ImageSwitcher) getParametros().get("cuadroImagen");
				final Context contexto = (Context)getParametros().get("contexto");
				HashMap<String, Object> urlProcesoImage=(HashMap<String, Object>) imagenHTTP.getImagenesObt();
				imageProceso.setFactory(new ViewFactory() {
			        public View makeView() {
			               ImageView imageView = new ImageView(contexto);
			               imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			               imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			               return imageView;
			            }
			        });			
				
				imageProceso.setImageDrawable(new BitmapDrawable(contexto.getResources(),(Bitmap)urlProcesoImage.get(procesoprod.getImage_url())));
			}
			else if (getRespuestas().get("objetoProceso") instanceof NoticiaGSON){
				ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenProcesoHTTP");
				NoticiaGSON procesoprod = (NoticiaGSON) getRespuestas().get("objetoProceso");
				TextView textoTitulo = (TextView) getParametros().get("textoTitulo");
				textoTitulo.setText(procesoprod.getTitulo());
				TextView textoProceso = (TextView) getParametros().get("textoDescripcion");
				textoProceso.setText(procesoprod.getDescripcion());
				ImageSwitcher imageProceso = (ImageSwitcher) getParametros().get("cuadroImagen");
				final Context contexto = (Context)getParametros().get("contexto");
				HashMap<String, Object> urlProcesoImage=(HashMap<String, Object>) imagenHTTP.getImagenesObt();
				imageProceso.setFactory(new ViewFactory() {
			        public View makeView() {
			               ImageView imageView = new ImageView(contexto);
			               imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			               imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			               return imageView;
			            }
			        });			
				
				imageProceso.setImageDrawable(new BitmapDrawable(contexto.getResources(),(Bitmap)urlProcesoImage.get(procesoprod.getImage_url())));		
			}
			else if (getRespuestas().get("objetoProceso") instanceof ItemDeInfos){
			        ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenProcesoHTTP");
			        ItemDeInfos procesoprod = (ItemDeInfos) getRespuestas().get("objetoProceso");
				TextView textoTitulo = (TextView) getParametros().get("textoTitulo");
				textoTitulo.setText(procesoprod.getTítulo());
				TextView textoProceso = (TextView) getParametros().get("textoDescripcion");
				textoProceso.setText(procesoprod.getDescripción());
				ImageSwitcher imageProceso = (ImageSwitcher) getParametros().get("cuadroImagen");
				final Context contexto = (Context)getParametros().get("contexto");
				HashMap<String, Object> urlProcesoImage=(HashMap<String, Object>) imagenHTTP.getImagenesObt();
				imageProceso.setFactory(new ViewFactory() {
			        public View makeView() {
			               ImageView imageView = new ImageView(contexto);
			               imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			               imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			               return imageView;
			            }
			        });			
				
				imageProceso.setImageDrawable(new BitmapDrawable(contexto.getResources(),(Bitmap)urlProcesoImage.get(procesoprod.getUrl_image())));		
			}
			progressDialogo.dismiss();
		}
		else if (valor.equals("imagenPrepMate")){
			ImagenHTTP imagenPrepMateHTTP = (ImagenHTTP)getRespuestas().get("imagenPrepMateHTTP");
			ProductoGSON producto =(ProductoGSON) getRespuestas().get("objetoPrepMate");
	
			Intent i = new Intent(getContext(), ImagenPrepMate.class); 
			i.putExtra("objetoPrepMate",producto);
			i.putExtra("imagenPrepMateHTTP",imagenPrepMateHTTP.getImagenesObt());

			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(i);
		}
		else if (valor.equals("imagenValorEnergetico")){
			ImagenHTTP imagenPrepMateHTTP = (ImagenHTTP)getRespuestas().get("imagenValorEnergeticoHTTP");
			ProductoGSON producto =(ProductoGSON) getRespuestas().get("objetoValorEnergetico");
			Intent i = new Intent(getContext(), ValoresEnergeticos.class); 
			i.putExtra("objetoValorEnergetico",producto);
			i.putExtra("imagenValorEnergeticoHTTP",imagenPrepMateHTTP.getImagenesObt());
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(i);	
			

		}
		else if (valor.equals("imagenMapa")){
                        ImagenHTTP imagenHTTP =  (ImagenHTTP) getRespuestas().get("imagenMapaHTTP");
			EmpresaGSON emp =(EmpresaGSON) getRespuestas().get("objetoProducto");
		        Intent i = new Intent(getContext(), RutaDelMate.class); 
			i.putExtra("empresa",emp);
			i.putExtra("imagenMapa",imagenHTTP.getImagenesObt());
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(i);	 
			progressDialogo.dismiss();
		}
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
