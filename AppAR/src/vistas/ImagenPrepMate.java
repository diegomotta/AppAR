package vistas;

import java.util.HashMap;
import java.util.List;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import clases.ImagenGSON;
import clases.ProductoGSON;

import com.appar.R;

public class ImagenPrepMate  extends AppCompatActivity implements ViewFactory {
	private ImageSwitcher imageSwitcher;
	private List<ImagenGSON> elementos;
	private int curIndex= 0;
	private int downX, upX;
	private ProductoGSON producto;
	private HashMap<String, Object> imagenPrepMateHTTP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantallacompletaimagen2);
		setTitle("Preparación del Mate");
		producto = (ProductoGSON) getIntent().getExtras().getSerializable("objetoPrepMate");
		imagenPrepMateHTTP = (HashMap<String, Object>) getIntent().getExtras().getSerializable("imagenPrepMateHTTP");	
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
						if(curIndex > imagenPrepMateHTTP.size() -1){
							curIndex = 0 ;
						}
						imageSwitcher.destroyDrawingCache();
						imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(ImagenPrepMate.this,android.R.anim.slide_in_left));
						imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(ImagenPrepMate.this,android.R.anim.slide_out_right));
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
		imageSwitcher.setImageDrawable(new BitmapDrawable( getApplicationContext().getResources(),(Bitmap) imagenPrepMateHTTP.get((producto.getGaleria_prep_mates().get(curIndex)).getImage_url())));
		TextView tituloImagen = (TextView) findViewById(R.id.tituloImagen);
		TextView descripcionImagen = (TextView) findViewById(R.id.descripcionImagen);
		LinearLayout p = (LinearLayout) findViewById(R.id.textoImagen);
		tituloImagen.setVisibility(0);
		descripcionImagen.setVisibility(0);
		p.setVisibility(0);
		if (!producto.getGaleria_prep_mates().get(curIndex).getTítulo().equals("")){
			tituloImagen.setText("");
			descripcionImagen.setText("");
			tituloImagen.setVisibility(1);
			p.setVisibility(1);
			tituloImagen.setText(producto.getGaleria_prep_mates().get(curIndex).getTítulo());
			descripcionImagen.setText(producto.getGaleria_prep_mates().get(curIndex).getDescrición());
		}
		else{
			tituloImagen.setText("");
			descripcionImagen.setText("");
		}
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
