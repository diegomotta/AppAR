package utilidades;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appar.R;

public class AdaptadorPersonalizado extends BaseAdapter {
	
	protected Activity activity;
	protected ArrayList<RowData> items;
	
	public AdaptadorPersonalizado(Activity activity, ArrayList<RowData> items) {
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
             
       // TextView descripcion = (TextView) vi.findViewById(R.id.textView_inferior);
        //descripcion.setText(item.getDescripcion());
        if(item.getBm()!= null){
	        ImageView imagenll = (ImageView)vi.findViewById(R.id.imageView_imagen);
	        imagenll.setImageBitmap(item.getBm());
        }
        
        return vi;
	}
}


