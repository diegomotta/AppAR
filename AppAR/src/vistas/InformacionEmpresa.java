package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utilidades.ExpandableListAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import clases.EmpresaGSON;
import clases.Informacion_empresa;
import clases.NoticiaGSON;
import clases.ProductoGSON;
import clases.VideoEmpresaGSON;

import com.appar.QRUrl;
import com.appar.R;

public class InformacionEmpresa extends AppCompatActivity {
    	private ExpandableListAdapter listAdapter;
    	private ExpandableListView expListView;
    	private List<String> listDataHeader;
    	private HashMap<String, List<Object>> listDataChild;
    	private EmpresaGSON empresa;
    	private List<ProductoGSON> productos;
    	private List<NoticiaGSON> noticias = null;
	private HashMap<String, Object> objetoImagenUnica;
	private Bitmap bm;
	private static final String TAG = "MyActivity";
	private List<VideoEmpresaGSON> videos;
	private List<Informacion_empresa> informacion_empresas;
	private QRUrl codigoqr;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_item_producto);
        setTitle("Nuestra Empresa");
        codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");
	empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("empresaDatoObjecto");
	//objetoImagenUnica = (HashMap<String, Object>) getIntent().getExtras().getSerializable("logo");
	//bm = (Bitmap)objetoImagenUnica.get(empresa.getImage());
	if (!empresa.getProductos().isEmpty()){
		productos = empresa.getProductos();
	}
	if(!empresa.getNoticia_empresas().isEmpty()){
		noticias = empresa.getNoticia_empresas();
	}
	if (!empresa.getVideos().isEmpty()){
		videos = empresa.getVideos();
	}
	if(!empresa.getInformacion_empresas().isEmpty()){
	    informacion_empresas = empresa.getInformacion_empresas();
	}
	expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        	boolean val = false;
               	if(!noticias.isEmpty()){
	            if(listDataHeader.get(groupPosition).equals("Noticias")){
	            	Intent inNoticia = new Intent(getApplicationContext(), NoticiaEmpresa.class);
	            	inNoticia.putExtra("objetoDatosProducto", empresa);
	    		startActivity(inNoticia);
	    		val = true;
    	            }
               	}
               /* if (!videos.isEmpty()){
                    if(listDataHeader.get(groupPosition).equals("Videos")){
                	HashMap<String, Object> q = new HashMap<String,Object>();
                	ArrayList<String> urlsvideos =  new ArrayList<String>();
        	        for (VideoEmpresaGSON elemento : videos){
        	            int intString = elemento.getUrl().length() -11;
    			    int finString = elemento.getUrl().length();
    			    urlsvideos.add(elemento.getUrl().substring(intString, finString));
                        }
    			q.put("width", 120);
    			q.put("height", 120);
    			q.put("urlVideos", urlsvideos);
    			q.put("objetoProducto", empresa);
    			ProgressDialog progV = new ProgressDialog(InformacionEmpresa.this);
    			q.put("progresoVideos", progV);
    			ImagenAsyncTask iniciarV = new ImagenAsyncTask(getApplicationContext(),codigoqr);
    			iniciarV.ejecutarTarea(Task.GET_VIDEOS, q);    
    			val = true;
                     }
                }*/
                if(!informacion_empresas.isEmpty()){
                    if(listDataHeader.get(groupPosition).equals("¿Quienes somos?")){
	            	Intent inNoticia = new Intent(getApplicationContext(), Itemdeinfos.class);
	            	inNoticia.putExtra("objetoDatosProducto", empresa);
	    		startActivity(inNoticia);
	    		val = true;      
                    }
                }
                if(listDataHeader.get(groupPosition).equals("Redes Sociales")){
	            	Intent i = new Intent(getApplicationContext(), RedesSociales.class);
	    		startActivity(i);
	    		val = true;   
                }
                return val;    
            }
        } );
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
              /* Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
               
            }
        });
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                       Toast.LENGTH_SHORT).show();*/
 
            }
        });

        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                int groupPosition, int childPosition, long id) {
            	int idProducto = -1;
            	String valor = (listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition)).toString();
            	if(!productos.isEmpty()){
	            	for (ProductoGSON prod : productos) {
	            		if(prod.getTitulo().equals(valor)){
	            			idProducto = prod.getId();
	            			if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals(prod.getTitulo())){
	                    		Intent inProducto = new Intent(getApplicationContext(), Producto.class);
	                    		inProducto.putExtra("objetoDatosProducto", empresa);
	                    		inProducto.putExtra("codigoQR",codigoqr);
	                    		inProducto.putExtra("identificadorProducto", idProducto);
    	            			startActivity(inProducto);;
	                		}
	            			break;
	            		}
	            	}
            	}
            	else
            	{
                    Toast.makeText(getApplicationContext(),"No hay producto cargados" , Toast.LENGTH_SHORT).show();           		
            	}
                return false;
            }
        });
    }
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
    private void prepareListData() {
    	
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Object>>();
        if (empresa != null){
	        listDataHeader.add("La empresa");
	        listDataHeader.add("Misión");
	        listDataHeader.add("Visión");
	        listDataHeader.add("¿Quienes somos?");
	        
	        List<Object> productosEmpresa = new ArrayList<Object>();
	        List<Object> datosEmpresa = new ArrayList<Object>();
	        datosEmpresa.add("Nombre: "+empresa.getNombre());
	        datosEmpresa.add("Actividad: "+empresa.getActividad());
	        datosEmpresa.add("Dirección: "+ empresa.getDirección() +" - "+empresa.getPaís()+" - "+ empresa.getProvincia()+ " - "+ empresa.getCiudad());
	        datosEmpresa.add("Teléfono: "+ empresa.getTelefono());
	        
	        List<Object> datosEmpresaMisión = new ArrayList<Object>();
	        datosEmpresaMisión.add("Misión: "+ empresa.getVisión());
	        
	        List<Object> datosEmpresaVision = new ArrayList<Object>();
	        datosEmpresaVision.add("Visión: "+ empresa.getVisión());
	        
        
	        if (!noticias.isEmpty()){
	        	listDataHeader.add("Noticias");
	        }
	  
	        listDataChild.put(listDataHeader.get(0), datosEmpresa); // Header, Child data
	        listDataChild.put(listDataHeader.get(1), datosEmpresaMisión);
	        listDataChild.put(listDataHeader.get(2), datosEmpresaVision);
	      //  listDataChild.put(listDataHeader.get(3), imagenProducto);
	        if (!productos.isEmpty()){
	        	listDataHeader.add("Nuestros productos");  
		        for(ProductoGSON prod : productos){
		            	System.out.println(prod.getTitulo());
		        	productosEmpresa.add(prod.getTitulo());
		        }
		        listDataChild.put(listDataHeader.get(5), productosEmpresa);
	        }  

	        listDataHeader.add("Redes Sociales");  


	        
        }
    }
}
