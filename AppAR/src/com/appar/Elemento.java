package com.appar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import entradasproducto.Consulta;
import entradasproducto.DondeComprar;
import entradasproducto.FichaTecnica;
import entradasproducto.Imagen;
import entradasproducto.InformacionBasica;
import entradasproducto.Opinion;
import entradasproducto.Promocion;
import entradasproducto.RedSocial;
import entradasproducto.VideoProducto;

public class Elemento extends ActionBarActivity implements OnItemClickListener{
	
	public RespuestaHTTP  objetoProductoPublic;
	public SharedPreference preference;
	private boolean bol = false;

	ArrayList<ObjetoEntradas> items = new ArrayList<ObjetoEntradas>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elemento);
		ActionBar actionBar = getSupportActionBar();
		/**INDICAR TITULO Y SUBTITULO**/
		actionBar.setTitle("Información del producto");
		actionBar.setSubtitle("Menu Principal");
		
		preference = new SharedPreference(getApplicationContext());
		objetoProductoPublic = (RespuestaHTTP) getIntent().getExtras().getSerializable("sampleObject");
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
	    items.add(new ObjetoEntradas(1, "1. Información básica", null));
	    items.add(new ObjetoEntradas(2, "2. Ficha técnica", null));
	    items.add(new ObjetoEntradas(3, "3. Imagenes", null));
	    items.add(new ObjetoEntradas(4, "4. Videos",null));
	    items.add(new ObjetoEntradas(5, "5. Promociones",null));
	    items.add(new ObjetoEntradas(6, "6. ¿Donde comprar?",null));
	    items.add(new ObjetoEntradas(6, "6. Opiniones",null));
	    items.add(new ObjetoEntradas(6, "6. Redes sociales",null));
	    items.add(new ObjetoEntradas(6, "7. Consultas",null));
	}
	
	
/** INTERFACE ON ITEM CLICK LISTENER **/
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg2) {
			case 0:
				Intent i = new Intent(this, InformacionBasica.class);
				i.putExtra("objetoProducto",objetoProductoPublic);
				startActivity(i);
				break;
			case 1:
				startActivity(new Intent(this, FichaTecnica.class));
				break;
			case 2:
				startActivity(new Intent(this, Imagen.class));
				break;
			case 3:
				startActivity(new Intent(this, VideoProducto.class));
				break;
			case 4:
				startActivity(new Intent(this, Promocion.class));
				break;
			case 5:
				startActivity(new Intent(this, DondeComprar.class));
				break;
			case 6:
				startActivity(new Intent(this, RedSocial.class));
				break;
			case 7:
				startActivity(new Intent(this, Opinion.class));
				break;
			case 8:
				startActivity(new Intent(this, Consulta.class));
				break;	
		}
	}
	
/** OBJETO PARA CADA ITEM DEL LIST VIEW **/
	
	public class ObjetoEntradas {
		
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
	}
	
/** ADAPTADOR PARA EL LISTVIEW DEL MENU PRINCIPAL **/
	
	public class EntradasAdapter extends BaseAdapter {
		
		protected Activity activity;
		protected ArrayList<ObjetoEntradas> items;
		
		public EntradasAdapter(Activity activity, ArrayList<ObjetoEntradas> items) {
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
	        	vi = inflater.inflate(R.layout.plantilla_listview, null);
	        }
	        
	        ObjetoEntradas item = items.get(position);
	             
	        TextView titulo = (TextView) vi.findViewById(R.id.titulo);
	        titulo.setText(item.getTitulo());
	             
	        TextView descripcion = (TextView) vi.findViewById(R.id.descripcion);
	        descripcion.setText(item.getDescripcion());
	     
	        return vi;
		}
	}

}
