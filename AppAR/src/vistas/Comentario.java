package vistas;

import java.util.*;

import servidor.EmpresaAsyncTask.Task;
import servidor.ServidorAsyncTasks;
import utilidades.RowData;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import clases.ComentarioGSON;
import clases.RespuestaGSON;

import com.appar.QRUrl;
import com.appar.R;

@SuppressLint("InflateParams") public class Comentario extends AppCompatActivity implements OnItemClickListener{
    private ArrayList<RowData> items = new ArrayList<RowData>();
    private QRUrl codigoqr;
    private HashMap<String, Object> comentarios;
    private RespuestaGSON respuestaHTTP;
    private EntradasAdapter adapter;
    private  ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comentariolist);
		setTitle("Comentarios del Producto");
		respuestaHTTP = (RespuestaGSON) getIntent().getExtras().getSerializable("objetoRespuesta");
		codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");
	    crearEntradas();
	     
		 adapter = new EntradasAdapter(this, items);
	     lv = (ListView)findViewById(R.id.listComentario);
		 lv.setAdapter(adapter);   
		 lv.setOnItemClickListener(this);
			Button button = (Button) findViewById(R.id.btn_nuevo_comentario);
			button.setOnClickListener(new OnClickListener() {
			          @Override
			            public void onClick(View view) {
			      		Intent nuevoComentario = new Intent(Comentario.this,NuevoComentario.class);
			      		HashMap<String, Object> p = new HashMap<String,Object>();
			      		nuevoComentario.putExtra("codigoQR",codigoqr);
			      		p.put("activityComentario",Comentario.this);
			      		p.put("nuevoComentario",nuevoComentario );
			      		ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),codigoqr);
			      		s.ejecutarTarea(Task.GET_INTENT_COMENTARIOS, p);

			        	  }
			          });
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
	
	public QRUrl getCodigoqr() {
	    return codigoqr;
	}



	public void setCodigoqr(QRUrl codigoqr) {
	    this.codigoqr = codigoqr;
	}



	public HashMap<String, Object> getComentarios() {
	    return comentarios;
	}



	public void setComentarios(HashMap<String, Object> comentarios) {
	    this.comentarios = comentarios;
	}



	public RespuestaGSON getRespuestaHTTP() {
	    return respuestaHTTP;
	}



	public void setRespuestaHTTP(RespuestaGSON respuestaHTTP) {
	    this.respuestaHTTP = respuestaHTTP;
	}



	public EntradasAdapter getAdapter() {
	    return adapter;
	}



	public void setAdapter(EntradasAdapter adapter) {
	    this.adapter = adapter;
	}



	public ListView getLv() {
	    return lv;
	}



	public void setLv(ListView lv) {
	    this.lv = lv;
	}



	public ArrayList<RowData> getItems() {
		return items;
	}


	public void setItems(ArrayList<RowData> items) {
		this.items = items;
	}


	public void crearEntradas() 
	{
		if(!respuestaHTTP.getComentarios_producto().equals(null)){
			List<ComentarioGSON> respList = respuestaHTTP.getComentarios_producto();
			for (ComentarioGSON comentario : respList ){
				items.add(new RowData(comentario.getId(),comentario.getNombre(), comentario.getNacionalidad(),comentario.getPais(),comentario.getCiudad(),comentario.getComentario()));
			}
			setItems(items);
		}
		else
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			final AlertDialog alert = builder.create();
			builder.setMessage("Look at this dialog!")
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   alert.closeOptionsMenu(); 
		           }
		       });
			alert.show();
			
		}
	}

	
	public class EntradasAdapter extends BaseAdapter {
		
	    
		protected Activity activity;
		protected ArrayList<RowData> items;
		
		public EntradasAdapter(Activity activity, ArrayList<RowData> items) {
			this.activity = activity;
			this.items = getItems();
		}
		
		public int getCount() {return items.size();}
		public Object getItem(int position) {return items.get(position);}
		public long getItemId(int position) {return items.get(position).getId();}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			
	        if(vi == null) {
	        	LayoutInflater inflater = activity.getLayoutInflater();
	        	vi = inflater.inflate(R.layout.comentario, null);
	        }
	        
	        RowData item = items.get(position);
	             
	        TextView nombre = (TextView) vi.findViewById(R.id.nombreComentario);
	        nombre.setText(item.getNombre());
	             
	        TextView nacionalidad = (TextView) vi.findViewById(R.id.nacionalidadComentario);
	        nacionalidad.setText(item.getNacionalidad());
	        
	        TextView pais = (TextView) vi.findViewById(R.id.paisComentario);
	        pais.setText(item.getPais());
	        
	       
	        
	        TextView ciudad = (TextView) vi.findViewById(R.id.ciudadComentario);
	        ciudad.setText(item.getCiudad());

	        TextView comentario = (TextView) vi.findViewById(R.id.comentario_txt);
	        comentario.setText(item.getComentario());
	        
	        
	        return vi;
		}
	}
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	
}
}
