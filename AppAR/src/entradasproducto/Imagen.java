package entradasproducto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.appar.ImagenGSON;
import com.appar.PantallaCompletaImage;
import com.appar.R;
import com.appar.RespuestaHTTP;
import com.appar.RowData;

public class Imagen extends ActionBarActivity implements OnItemClickListener{
	private ArrayList<RowData> items = new ArrayList<RowData>();
    private Bitmap bmp;
    private HashMap<String, Object> imagenes;
    private RespuestaHTTP respuestaHTTP;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagen);
		ActionBar actionBar = getSupportActionBar();
		/**INDICAR TITULO Y SUBTITULO**/
		imagenes = (HashMap<String, Object>) getIntent().getExtras().getSerializable("objetoImagenes");
		respuestaHTTP = (RespuestaHTTP) getIntent().getExtras().getSerializable("objetoRespuesta");
		actionBar.setTitle("Imagenes del producto");
	    crearEntradas();
		EntradasAdapter adapter = new EntradasAdapter(this, items);
	    ListView lv = (ListView)findViewById(R.id.ListView_listado);
	    lv.setAdapter(adapter);   
	    lv.setOnItemClickListener(this);
	}

	
	private void crearEntradas() 
	{
		List<ImagenGSON> respList = respuestaHTTP.getGaleria_imaganes();
		
		for (ImagenGSON img : respList ){
			Bitmap bm = (Bitmap)imagenes.get(img.getImage_url());
			items.add(new RowData(img.getId(),img.getTitulo(), img.getDescripcion(), bm));
		}
	}
/** OBJETO PARA CADA ITEM DEL LIST VIEW **/
	

	
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
	        	vi = inflater.inflate(R.layout.plantillalistimagen, null);
	        }
	        
	        RowData item = items.get(position);
	             
	        TextView titulo = (TextView) vi.findViewById(R.id.textView_superior);
	        titulo.setText(item.getTitulo());
	             
	        TextView descripcion = (TextView) vi.findViewById(R.id.textView_inferior);
	        descripcion.setText(item.getDescripcion());
	     
	        ImageView imagenll = (ImageView)vi.findViewById(R.id.imageView_imagen);
	        imagenll.setImageBitmap(item.getBm());
	       
	        return vi;
		}
	}
	
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	//RowData elegido = (RowData) parent.getItemAtPosition(position); 
	
	Intent iInfoBasica = new Intent(this, PantallaCompletaImage.class);
	iInfoBasica.putExtra("respuestaHTTP", respuestaHTTP);
	iInfoBasica.putExtra("elementos", imagenes);
	iInfoBasica.putExtra("posicion", position);
	startActivity(iInfoBasica);
	//CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
    //Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
    //toast.show();
}

}
