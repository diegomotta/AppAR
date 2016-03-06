package vistas;

import java.util.HashMap;

import servidor.EmpresaAsyncTask.Task;
import servidor.ServidorAsyncTasks;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import clases.EncuestaGSON;

import com.appar.QRUrl;
import com.appar.R;


public class Encuesta extends AppCompatActivity{
	private String calificacionProducto;
	private String precioProducto;
	private String enteroProducto;
	private String saborProducto;
	private String comoConsume;
	private String endulzante;
	private EditText hierbaMedicinal;
	private EditText otraMarca;
	private EditText justificacionimagen;
	private String imagenProducto;
	private QRUrl codigoqr;
	private static final String TAG = "MyActivity";
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.encuesta);
		setTitle("Encuesta");
		codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");
		final Spinner spinner = (Spinner) findViewById(R.id.spinner_calificacion_producto);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.array_calificacion_producto, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		final Spinner spinnerPrecioProducto = (Spinner) findViewById(R.id.spinner_precio_producto);
		ArrayAdapter<CharSequence> adapterPrecioProducto = ArrayAdapter.createFromResource(this,
		        R.array.array_precio_producto, android.R.layout.simple_spinner_item);
		adapterPrecioProducto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPrecioProducto.setAdapter(adapterPrecioProducto);
		
		final Spinner spinnerEnteroProducto = (Spinner) findViewById(R.id.spinner_entero_producto);
		ArrayAdapter<CharSequence> adapterEnteroProducto = ArrayAdapter.createFromResource(this,
		        R.array.array_entero_producto, android.R.layout.simple_spinner_item);
		adapterEnteroProducto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerEnteroProducto.setAdapter(adapterEnteroProducto);
		
		final Spinner spinnerSaborProducto = (Spinner) findViewById(R.id.spinner_sabor_producto);
		ArrayAdapter<CharSequence> adapterSaborProducto = ArrayAdapter.createFromResource(this,
		        R.array.array_otro_sabor, android.R.layout.simple_spinner_item);
		adapterSaborProducto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSaborProducto.setAdapter(adapterSaborProducto);		
		
		final Spinner spinnerComoConsume = (Spinner) findViewById(R.id.spinner_como_consume);
		ArrayAdapter<CharSequence> adapterComoConsume = ArrayAdapter.createFromResource(this,
		        R.array.array_como_consume, android.R.layout.simple_spinner_item);
		adapterComoConsume.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerComoConsume.setAdapter(adapterComoConsume);

		final Spinner spinnerImagenProducto = (Spinner) findViewById(R.id.spinner_imagen_producto);
		ArrayAdapter<CharSequence> adapterImagenProducto = ArrayAdapter.createFromResource(this,
		        R.array.array_imagen_producto, android.R.layout.simple_spinner_item);
		adapterImagenProducto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerImagenProducto.setAdapter(adapterImagenProducto);
		justificacionimagen = (EditText) findViewById(R.id.justificacion_imagen);	
		justificacionimagen.setEnabled(false);
		justificacionimagen.setVisibility(View.GONE);
		final Spinner spinnerEndulzante = (Spinner) findViewById(R.id.spinner_endulzante);
		ArrayAdapter<CharSequence> adapterEndulzante = ArrayAdapter.createFromResource(this,
		        R.array.array_endulzante, android.R.layout.simple_spinner_item);
		adapterEndulzante.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerEndulzante.setAdapter(adapterEndulzante);	
		spinnerEndulzante.setEnabled(false);
		spinnerEndulzante.setVisibility(View.GONE);
		spinnerComoConsume.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        if(position == 2){
		        	spinnerEndulzante.setEnabled(true);
		        	spinnerEndulzante.setVisibility(0);
		        	comoConsume = parentView.getSelectedItem().toString();
		        	
		        }
		        else{
		        	spinnerEndulzante.setEnabled(false);
		        	spinnerEndulzante.setVisibility((View.GONE));
		        	comoConsume = parentView.getSelectedItem().toString();
		        	endulzante = "";
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		
		spinnerImagenProducto.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        if(position == 0){
		        	justificacionimagen.setEnabled(true);
		        	justificacionimagen.setVisibility(0);
		        	if (justificacionimagen.getText().toString()!= ""){
		        		imagenProducto = parentView.getSelectedItem().toString() +" " +justificacionimagen.getText().toString() ;
		        	}
		        	else{
		        		imagenProducto = parentView.getSelectedItem().toString();
		        	}
		        }
		        else{
		        	justificacionimagen.setEnabled(false);
		        	justificacionimagen.setVisibility((View.GONE));
		        	imagenProducto = parentView.getSelectedItem().toString();
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	calificacionProducto = parentView.getSelectedItem().toString();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		});	    			

		spinnerPrecioProducto.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	precioProducto = parentView.getSelectedItem().toString();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		});	

		spinnerEnteroProducto.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	enteroProducto = parentView.getSelectedItem().toString();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		});	

		spinnerSaborProducto.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	saborProducto = parentView.getSelectedItem().toString();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		});	 

		if (spinnerEndulzante.isEnabled()==true){
			spinnerEndulzante.setOnItemSelectedListener(new OnItemSelectedListener() {
			    @Override
			    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			    	endulzante = parentView.getSelectedItem().toString();
			    }
			    @Override
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }
			});	     			
		}
				
		Button button = (Button) findViewById(R.id.btn_enviar_encuesta);
		button.setOnClickListener(new OnClickListener() {
	    @Override
        public void onClick(View view) {
     			  			
    			hierbaMedicinal = (EditText)findViewById(R.id.hierba_medicinal_txt);
    			otraMarca = (EditText) findViewById(R.id.otra_marca_txt);	
    			EncuestaGSON encuesta = new EncuestaGSON(
    					calificacionProducto,
    					precioProducto,
    					imagenProducto,
    					enteroProducto,
    					saborProducto,
    					comoConsume,
    					endulzante,
    					hierbaMedicinal.getText().toString(),
    					otraMarca.getText().toString());	
    			Log.i(TAG,encuesta.getCalifproducto());
    			Log.i(TAG,encuesta.getPrecio());
    			Log.i(TAG,encuesta.getImagenempresareg());
    			HashMap<String, Object> p = new HashMap<String,Object>();
    	  		ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),codigoqr);
    	  		p.put("encuesta", encuesta);
    	  		ProgressDialog progressDialogo = new ProgressDialog(Encuesta.this);
    	  		p.put("progresoEncuestaCliente", progressDialogo);
    	  		p.put("instanciaClase",Encuesta.this);
    			//ArrayList<Object> componentes = new ArrayList<Object>();
    	  		s.ejecutarTarea(Task.SET_ENCUESTA, p);
	    }
	    });
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		

}
