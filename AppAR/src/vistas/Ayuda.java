package vistas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import com.appar.R;

public class Ayuda extends AppCompatActivity implements OnItemClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ayuda);
		setTitle("Ayuda");
		TextView textAyuda = (TextView)findViewById(R.id.ayudatxt);
		TextView textAyuda2 = (TextView)findViewById(R.id.ayudaTXT2);
		TextView textAyuda3 = (TextView)findViewById(R.id.ayudatxt3);
		TextView textAyuda4 = (TextView)findViewById(R.id.ayudatxt4);
		TextView textAyuda5 = (TextView)findViewById(R.id.ayudatxt5);
		textAyuda.setText("Para utilizar la aplicacion es necesario realizar las siguientes acciones:\n");
		textAyuda2.setText("1) Para ingresar al escaner de codigos QR, presion el botón Escanear.\n");
		textAyuda3.setText("2) Acercar la cámara del dispositivo movil a 15 centimetros aprox del codigo QR que se encuentra en el envase del producto");
		textAyuda4.setText("3) Esperar que cargue la toda la informacion del producto");
		textAyuda5.setText("4) Una vez visualizado el menu principal, puede acceder a los distintas secciones de información relacionado al producto");	
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
    @Override
    public void onItemClick(AdapterView<?> p_arg0, View p_arg1, int p_arg2, long p_arg3) {
	    // TODO Auto-generated method stub
	    
    }
		
}
