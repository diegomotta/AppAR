package com.appar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import servidor.EmpresaAsyncTask.Task;
import servidor.ImagenAsyncTask;
import servidor.ServidorAsyncTasks;
import utilidades.RowData;
import utilidades.SharedPreference;
import vistas.Consulta;
import vistas.Encuesta;
import vistas.PropEspecifica;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import clases.ImagenGSON;
import clases.RespuestaGSON;
import clases.VideoGSON;

import com.google.gson.Gson;

public class PrincipalPresentacionProducto extends AppCompatActivity implements OnItemClickListener{
	
	public RespuestaGSON  objetoProductoPublic;
	public SharedPreference preference;
	private boolean bol = false;
	private QRUrl codigoqr;
	//ArrayList<ObjetoEntradas> items = new ArrayList<ObjetoEntradas>();
	
	ArrayList<RowData> items = new ArrayList<RowData>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elemento);
		setTitle("Menú Principal");
		preference = new SharedPreference(getApplicationContext());
		objetoProductoPublic = (RespuestaGSON) getIntent().getExtras().getSerializable("sampleObject");
		codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");
		System.out.println(codigoqr.getCodigoEmpresa());
		String json_response = new Gson().toJson(objetoProductoPublic);
		preference.guardarObjeto(json_response);
		crearEntradas();  
	    EntradasAdapter adapter = new EntradasAdapter(this, items);
	    ListView lv = (ListView)findViewById(R.id.list);
	    lv.setAdapter(adapter);   
	    lv.setOnItemClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
	private void crearEntradas() 
	{
//ObjetoEntradas
	    Bitmap caracteristica = BitmapFactory.decodeResource(this.getResources(), R.drawable.caract);
	    Bitmap imagen = BitmapFactory.decodeResource(this.getResources(), R.drawable.imagenes);
	    Bitmap video = BitmapFactory.decodeResource(this.getResources(), R.drawable.videos);
	    Bitmap consulta = BitmapFactory.decodeResource(this.getResources(), R.drawable.consulta);
	    Bitmap coment = BitmapFactory.decodeResource(this.getResources(), R.drawable.coment);
	    Bitmap prop = BitmapFactory.decodeResource(this.getResources(), R.drawable.prop);
	    Bitmap empresa = BitmapFactory.decodeResource(this.getResources(), R.drawable.empresa);
	    Bitmap encuesta = BitmapFactory.decodeResource(this.getResources(), R.drawable.encuesta);
	    items.add(new RowData(2, " Características", null,caracteristica));
	    items.add(new RowData(3, " Imagenes", null,imagen));
	    items.add(new RowData(4, " Videos",null,video));
	    items.add(new RowData(5, " Consultas",null,consulta));
	    items.add(new RowData(6, " Comentarios",null,coment));
	    items.add(new RowData(6, " Propiedades",null,prop));
	    items.add(new RowData(7, " Nuestra empresa",null,empresa));
	    items.add(new RowData(8, " Encuesta",null,encuesta));
	}
	
	
/** INTERFACE ON ITEM CLICK LISTENER **/;
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
			/*case 0:
				Intent iInfoBasica = new Intent(this, InformacionBasica.class);
				//iInfoBasica.putExtra("objetoProducto",objetoProductoPublic);
				startActivity(iInfoBasica);
				break;*/
			case 0:
				String urlImagenUnica = objetoProductoPublic.getImage_url();
				HashMap<String, Object> f = new HashMap<String,Object>();
				f.put("width", 240);
				f.put("height", 240);
				f.put("urlImagenUnica", urlImagenUnica);
				f.put("objetoProducto", objetoProductoPublic);
				ProgressDialog pro = new ProgressDialog(PrincipalPresentacionProducto.this);
				f.put("progresoCaracteristicas", pro);
				ImagenAsyncTask iniciarFichaTecnica = new ImagenAsyncTask(getApplicationContext(),codigoqr);
				iniciarFichaTecnica.ejecutarTarea(Task.GET_IMAGEN, f);
				break;
			case 1:
				if(!objetoProductoPublic.getGaleria_imagenes().isEmpty()){
					List<ImagenGSON> imgList = objetoProductoPublic.getGaleria_imagenes();
					HashMap<String, Object> p = new HashMap<String,Object>();
					ArrayList<String> urls =  new ArrayList<String>();
					for (ImagenGSON elemento : imgList){
						urls.add(elemento.getImage_url());
					}
					p.put("width", 120);
					p.put("height", 120);
					p.put("urlImages", urls);
					p.put("objetoProducto", objetoProductoPublic);
					ProgressDialog prog = new ProgressDialog(PrincipalPresentacionProducto.this);
					p.put("progresoImagenes", prog);
					ImagenAsyncTask iniciar = new ImagenAsyncTask(getApplicationContext(),codigoqr);
					iniciar.ejecutarTarea(Task.GET_IMAGENES, p);	
				}
				else{
	                Toast.makeText(getApplicationContext(),"No hay imagenes del producto",Toast.LENGTH_SHORT).show();
				}
				break;
			case 2:
		
				if(!objetoProductoPublic.getVideos_productos().isEmpty()){
					List<VideoGSON> videoList = objetoProductoPublic.getVideos_productos();
					HashMap<String, Object> q = new HashMap<String,Object>();
					ArrayList<String> urlsvideos =  new ArrayList<String>();
					for (VideoGSON elemento : videoList){
						int intString = elemento.getUrl().length() -11;
						int finString = elemento.getUrl().length();
						urlsvideos.add(elemento.getUrl().substring(intString, finString));
					}
					q.put("width", 120);
					q.put("height", 120);
					q.put("urlVideos", urlsvideos);
					q.put("objetoProducto", objetoProductoPublic);
					ProgressDialog progV = new ProgressDialog(PrincipalPresentacionProducto.this);
					q.put("progresoVideos", progV);
					ImagenAsyncTask iniciarV = new ImagenAsyncTask(getApplicationContext(),codigoqr);
					iniciarV.ejecutarTarea(Task.GET_VIDEOS, q);
				}
				break;
			case 3:
			    Intent intConsulta = new Intent(this, Consulta.class);
			    intConsulta.putExtra("codigoQR",codigoqr);
			    intConsulta.putExtra("objetoRespuesta", objetoProductoPublic);
			    startActivity(intConsulta);
				break;
			case 4:
			   /* Intent intComentario = new Intent(this, Comentario.class);
			    intComentario.putExtra("objetoRespuesta", objetoProductoPublic);
			    intComentario.putExtra("codigoQR",codigoqr);
			    startActivity(intComentario);*/
			    ServidorAsyncTasks coment = new ServidorAsyncTasks(getApplicationContext(),codigoqr);
			    coment.ejecutarTarea(Task.GET_COMENTARIOS, null);
				break;
			case 5:
				Intent intPropEspecifica = new Intent(this, PropEspecifica.class);
				intPropEspecifica.putExtra("objetoRespuesta", objetoProductoPublic);
				startActivity(intPropEspecifica);;
				break;
			case 6:
				HashMap<String, Object> a = new HashMap<String,Object>();
				ProgressDialog proEmpresa = new ProgressDialog(PrincipalPresentacionProducto.this);
				a.put("progresoEmpresa", proEmpresa);				
				ServidorAsyncTasks empresa = new ServidorAsyncTasks(getApplicationContext(),codigoqr);
				empresa.ejecutarTarea(Task.GET_EMPRESA, a);

			   	break;
			case 7:
			    Intent intEncuesta = new Intent(this, Encuesta.class);
			    intEncuesta.putExtra("codigoQR",codigoqr);
			    startActivity(intEncuesta);				
				break;
		}
	}
	
/** OBJETO PARA CADA ITEM DEL LIST VIEW **/
	
	/*public class ObjetoEntradas {
		
		private int id;
		private String titulo;
		private String descripcion;
		
		public ObjetoEntradas(int id, String titulo, String descripcion) {
			this.id = id;
			this.titulo = titulo;
			this.descripcion = descripcion;
			
		}
		
		public int getId() {return id;}
		public String getTitulo(){return titulo;}
		public String getDescripcion(){return descripcion;}
	}*/
	
/** ADAPTADOR PARA EL LISTVIEW DEL MENU PRINCIPAL **/
	
	public class EntradasAdapter extends BaseAdapter {
		
		protected Activity activity;
	
		protected ArrayList<RowData> items;
		
		public EntradasAdapter(Activity activity, ArrayList<RowData> items) {
			this.activity = activity;
			this.items = items;
		}
		
		public int getCount() {return items.size();}
		public Object getItem(int position) {return items.get(position);}
		public long getItemId(int position) {return items.get(position).getId();}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			
	        if(vi == null) {
	        	LayoutInflater inflater = activity.getLayoutInflater();
	        	vi = inflater.inflate(R.layout.plantillalistimage2, null);
	        }
	        
	        RowData item = items.get(position);
	             
	        TextView titulo = (TextView) vi.findViewById(R.id.textView_superior);
	        titulo.setText(item.getTitulo());
	             

	     
	        ImageView imagenll = (ImageView)vi.findViewById(R.id.imageView_imagen);
	        imagenll.setImageBitmap(item.getBm());
	       
	        return vi;
		}
	}

}
