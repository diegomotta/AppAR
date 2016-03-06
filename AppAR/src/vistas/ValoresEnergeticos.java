package vistas;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import clases.ImagenGSON;
import clases.ProductoGSON;

import com.appar.R;

public class ValoresEnergeticos extends Activity implements ViewFactory {
	private ImageSwitcher imageSwitcher;
	private List<ImagenGSON> elementos;
	private int curIndex= 0;
	private int downX, upX;
	private ProductoGSON producto;
	private HashMap<String, Object> imagenValorEnergeticoHTTP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallacompletaimagen2);
	
		producto = (ProductoGSON) getIntent().getExtras().getSerializable("objetoValorEnergetico");
		setTitle("Propiedad del Producto " + producto.getTitulo());
		imagenValorEnergeticoHTTP = (HashMap<String, Object>) getIntent().getExtras().getSerializable("imagenValorEnergeticoHTTP");	
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
				if(event.getAction() == MotionEvent.ACTION_UP){
					    upX= (int) event.getX();
						curIndex++;
						if(curIndex > imagenValorEnergeticoHTTP.size() -1){
							curIndex = 0 ;
						}
						imageSwitcher.destroyDrawingCache();
						imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ValoresEnergeticos.this,android.R.anim.slide_in_left));
						imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ValoresEnergeticos.this,android.R.anim.slide_out_right));
						getImagen();	
					
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
		System.out.println(curIndex);
		System.out.println(producto.getProp_generals().get(0).getGaleria_props().get(curIndex).getImage_url());
		
		
		imageSwitcher.setImageDrawable(new BitmapDrawable( getApplicationContext().getResources(),(Bitmap) imagenValorEnergeticoHTTP.get(producto.getProp_generals().get(0).getGaleria_props().get(curIndex).getImage_url())));
		TextView tituloImagen = (TextView) findViewById(R.id.tituloImagen);
		TextView descripcionImagen = (TextView) findViewById(R.id.descripcionImagen);
		LinearLayout p = (LinearLayout) findViewById(R.id.textoImagen);
		p.setVisibility(View.GONE);
		tituloImagen.setVisibility(View.GONE);
		descripcionImagen.setVisibility(View.GONE);
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