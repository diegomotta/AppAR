package vistas;


import java.util.HashMap;
import java.util.List;

import utilidades.SharedPreference;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import clases.ImagenGSON;
import clases.RespuestaGSON;

import com.appar.R;

public class GaleriaImagenProducto extends AppCompatActivity implements ViewFactory {
	private ImageSwitcher imageSwitcher;
	private List<ImagenGSON> elementos;
	private int curIndex= 0;
	private int downX, upX;
	private SharedPreference preference;
	private HashMap<String, Object> imagenes;
	private RespuestaGSON respuestaHTTP;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallacompletaimage);
		setTitle("Imágenes del Producto");
		respuestaHTTP = (RespuestaGSON) getIntent().getExtras().getSerializable("respuestaHTTP");
		imagenes = (HashMap<String, Object>) getIntent().getExtras().getSerializable("elementos");
		curIndex = getIntent().getIntExtra("posicion", 0);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imagenSwithcer);
	    imageSwitcher.setFactory(new ViewFactory() {
        public View makeView() {
               ImageView imageView = new ImageView(getApplicationContext());
               imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
               imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
               return imageView;
            }
        });
		//imageSwitcher.setFactory(this);
		//makeView() ;	
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		getImagen();
		imageSwitcher.setOnTouchListener(new OnTouchListener(){
		
			public boolean onTouch(View v, MotionEvent event){
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					downX = (int) event.getX();
					return true;
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					upX= (int) event.getX();
					if(upX - downX == 0){
						//ocultarMostrarTexto
					}
					else if (upX - downX > 100){
						curIndex--;
						if(curIndex <0){
							curIndex = imagenes.size() -1 ;
						}
						imageSwitcher.destroyDrawingCache();
						imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(GaleriaImagenProducto.this,android.R.anim.slide_in_left));
						imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(GaleriaImagenProducto.this,android.R.anim.slide_out_right));
						getImagen();
					}
					else if (upX - downX > -100)
					{
						curIndex++;
						if(curIndex > imagenes.size() -1){
							curIndex = 0 ;
						}
						imageSwitcher.destroyDrawingCache();
						imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(GaleriaImagenProducto.this,android.R.anim.slide_out_right));
						imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(GaleriaImagenProducto.this,android.R.anim.slide_in_left));
						getImagen();	
					}
					return true;
				}
				return false;
			}	
		});
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
	

	private void getImagen() {
		System.out.println(respuestaHTTP.getGaleria_imagenes().get(curIndex));
		imageSwitcher.setImageDrawable(new BitmapDrawable( getApplicationContext().getResources(),(Bitmap)imagenes.get((respuestaHTTP.getGaleria_imagenes().get(curIndex)).getImage_url())));
		TextView tituloImagen = (TextView) findViewById(R.id.tituloImagen);
		tituloImagen.setText(respuestaHTTP.getGaleria_imagenes().get(curIndex).getTitulo());
		TextView descripcionImagen = (TextView) findViewById(R.id.descripcionImagen);
		descripcionImagen.setText(respuestaHTTP.getGaleria_imagenes().get(curIndex).getDescripcion());
		//imageSwitcher.setImageDrawable(new BitmapDrawable((Bitmap)imagenes.get((respuestaHTTP.getGaleria_imaganes().get(curIndex)).getImage_url())));
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView i = new ImageView(this);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return i;
	}

}
