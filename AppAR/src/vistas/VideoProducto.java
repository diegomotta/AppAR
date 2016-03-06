package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import servidor.EmpresaAsyncTask.Task;
import servidor.ServidorAsyncTasks;
import utilidades.AdaptadorPersonalizado;
import utilidades.RowData;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import clases.EmpresaGSON;
import clases.RespuestaGSON;
import clases.VideoEmpresaGSON;
import clases.VideoGSON;

import com.appar.QRUrl;
import com.appar.R;


public class VideoProducto extends AppCompatActivity implements OnItemClickListener{
	private ArrayList<RowData> items = new ArrayList<RowData>();

	private RespuestaGSON respuestaHTTP = null;
	private EmpresaGSON empresagson = null;

  private HashMap<String, Object> videos;
    private QRUrl codigoqr;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagen);
		codigoqr = (QRUrl)getIntent().getExtras().getSerializable("codigoQR");
		videos = (HashMap<String, Object>) getIntent().getExtras().getSerializable("objetoVideos");
		if (getIntent().getExtras().getSerializable("objetoRespuesta") instanceof RespuestaGSON){
		        setTitle("Videos del Producto");
			respuestaHTTP = (RespuestaGSON) getIntent().getExtras().getSerializable("objetoRespuesta");
		}
		else if (getIntent().getExtras().getSerializable("objetoRespuesta") instanceof EmpresaGSON){
		        setTitle("Videos de la Empresa");
			empresagson = (EmpresaGSON) getIntent().getExtras().getSerializable("objetoRespuesta");
		}
	    crearEntradas();
	    AdaptadorPersonalizado adapter = new AdaptadorPersonalizado(this, items);
	    ListView lv = (ListView)findViewById(R.id.ListView_listado);
	    lv.setAdapter(adapter);   
	    lv.setOnItemClickListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
		private void crearEntradas() 
		{
			if(respuestaHTTP!=null){
				List<VideoGSON> respList = respuestaHTTP.getVideos_productos();
				for (VideoGSON vid : respList ){
					int intString = vid.getUrl().length() -11;
					int finString = vid.getUrl().length();
					Bitmap bm = (Bitmap)videos.get(vid.getUrl().substring(intString, finString));
					items.add(new RowData(vid.getId(),vid.getTitulo(), vid.getDescripcion(), bm, vid.getUrl()));
				}
			}
			else if (empresagson!= null){
				List<VideoEmpresaGSON> respList = empresagson.getVideos();
				for (VideoEmpresaGSON vid : respList ){
					int intString = vid.getUrl().length() -11;
					int finString = vid.getUrl().length();
					Bitmap bm = (Bitmap)videos.get(vid.getUrl().substring(intString, finString));
					items.add(new RowData(vid.getId(),vid.getTitulo(), vid.getDescripcion(), bm, vid.getUrl()));
				}				
			}
		}


		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id)
		{
			if(respuestaHTTP!= null){
				VideoGSON videoGSON = respuestaHTTP.getVideos_productos().get(position);
				int intString = videoGSON.getUrl().length() -11;
				int finString = videoGSON.getUrl().length();
			        String url = videoGSON.getUrl().substring(intString,finString);
				HashMap<String, Object> p = new HashMap<String,Object>();
				p.put("urlVideo", url);
				ServidorAsyncTasks iniciar = new ServidorAsyncTasks(getApplicationContext(),codigoqr);
				iniciar.ejecutarTarea(Task.GET_VIDEO, p);
			}
			else if(empresagson != null){
				VideoEmpresaGSON videoGSON = empresagson.getVideos().get(position);
				int intString = videoGSON.getUrl().length() -11;
				int finString = videoGSON.getUrl().length();
			        String url = videoGSON.getUrl().substring(intString,finString);
				HashMap<String, Object> p = new HashMap<String,Object>();
				p.put("urlVideo", url);
				ServidorAsyncTasks iniciar = new ServidorAsyncTasks(getApplicationContext(),codigoqr);
				iniciar.ejecutarTarea(Task.GET_VIDEO, p);				
			}
		}
}
