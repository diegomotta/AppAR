package vistas;

import java.util.ArrayList;

import utilidades.RowData;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.appar.R;

public class RedesSociales  extends AppCompatActivity implements OnItemClickListener{
	ArrayList<RowData> items = new ArrayList<RowData>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.elemento);
		setTitle("Redes Sociales");
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
	    Bitmap caracteristica = BitmapFactory.decodeResource(this.getResources(), R.drawable.face);
	    Bitmap imagen = BitmapFactory.decodeResource(this.getResources(), R.drawable.twit);
	    Bitmap video = BitmapFactory.decodeResource(this.getResources(), R.drawable.insta);
	    items.add(new RowData(2, "Facebook", null,caracteristica));

	}
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	// TODO Auto-generated method stub
	if(position == 0){
        	Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("http://www.facebook.com/RAThesis-Yerba-Mate-507235692783746/timeline/"));
        	startActivity(intent);
	}
	
    }
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
