package vistas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import servidor.EmpresaAsyncTask.Task;
import servidor.ImagenAsyncTask;
import utilidades.ExpandableListAdapter;
import utilidades.SharedPreference;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
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
import clases.ImagePreparacionMateGSON;
import clases.Mapa;
import clases.PreparacionGSON;
import clases.ProcesoProductoGSON;
import clases.ProductoGSON;
import clases.PropGeneralProducto;
import clases.ValorEnergetico;
import clases.VideoGSON;

import com.appar.PrincipalPresentacionProducto;
import com.appar.QRUrl;
import com.appar.R;


public class Producto extends AppCompatActivity {
    private EmpresaGSON empresa;
    private List<ProductoGSON> productos;
    private List<PropGeneralProducto> propgenerals ;
    private List<ProcesoProductoGSON> procProductos;
    private List<PreparacionGSON> preparacions;
    private List<ImagePreparacionMateGSON> imagesPreps;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<Object>> listDataChild;
    private ProductoGSON productoSeleccionado; 
    private ProductoGSON producto;
    private QRUrl codigoqr;
    private int idproducto = -1;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_item_producto);

        try{
            empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("objetoDatosProducto");
            codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");
            idproducto = (Integer) getIntent().getExtras().get("identificadorProducto");
            productos = empresa.getProductos();
            System.out.println(idproducto);
            if((!productos.isEmpty()) && (idproducto > -1) ){
                for(ProductoGSON producto : productos){
                    if(producto.getId()==idproducto){
                	System.out.println("no entre");
                	setTitle("Producto " + producto.getTitulo());
                        propgenerals =producto.getProp_generals();
                        procProductos = producto.getGaleria_procesos();
                        preparacions = producto.getPreparar_mates();
                        imagesPreps = producto.getGaleria_prep_mates();
                        productoSeleccionado = producto;
                        break;
                    }
                }
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al obtener datos del producto",Toast.LENGTH_SHORT).show();
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
                if (listDataHeader.get(groupPosition).equals("Información Nutricional")){
                     
                    Intent inInformacionNutricional = new Intent(getApplicationContext(), InformacionNutricional.class);
                    inInformacionNutricional.putExtra("objetoDatosProducto", empresa);
                    inInformacionNutricional.putExtra("identificadorProducto", idproducto);
                    startActivity(inInformacionNutricional);;
                    val = true;
                }
                if (listDataHeader.get(groupPosition).equals("Propiedades")){
                    HashMap<String, Object> p = new HashMap<String,Object>();
                    ArrayList<String> urls =  new ArrayList<String>();
                    for (ValorEnergetico elemento : propgenerals.get(0).getGaleria_props()){
                        urls.add(elemento.getImage_url());
                    }
                    p.put("width", 400);
                    p.put("height", 400);
                    p.put("urlImagesValoresEnergeticos", urls);
                    p.put("objetoValorEnergetico", productoSeleccionado);
                    ImagenAsyncTask iniciar = new ImagenAsyncTask(getApplicationContext(),codigoqr);
                    iniciar.ejecutarTarea(Task.GET_IMAGENES_VALORES_ENERGETICOS,p);
                    val = true;
                }
                if(listDataHeader.get(groupPosition).equals("Ruta de la Yerba Mate")){
		    List<Mapa> mapaList = empresa.getUbicacion_geos();
		    HashMap<String, Object> w = new HashMap<String,Object>();
		    ArrayList<String> urlsmapas =  new ArrayList<String>();
		    for (Mapa elemento : mapaList){
				String urlimag = elemento.getImage_url();
				urlsmapas.add(urlimag);
		    }
		    w.put("width", 120);
		    w.put("height", 120);
		    w.put("urlImages", urlsmapas);
		    w.put("objetoProducto", empresa);
		    ProgressDialog progV = new ProgressDialog(Producto.this);
		    w.put("progresoImagenesMapa", progV);
		    ImagenAsyncTask iniciarV = new ImagenAsyncTask(getApplicationContext(),codigoqr);
		    iniciarV.ejecutarTarea(Task.GET_IMAGENES_MAPA, w);       
		    val = true;
                }
                return val;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
               // Toast.makeText(getApplicationContext(),
                 //       listDataHeader.get(groupPosition) + " Expanded",
                   //    Toast.LENGTH_SHORT).show();
            }
        });
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(),
                  //    listDataHeader.get(groupPosition) + " Collapsed",
                    //   Toast.LENGTH_SHORT).show();
 
            }
        });
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                String  titulo = (String)listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                if (listDataHeader.get(groupPosition).equals("Elaboración")){
                    for (ProcesoProductoGSON procesoprod : procProductos){
                        if (titulo.equals(procesoprod.getTítulo())){
                            onArticuloSelected(procesoprod);
                            break;
                        }
                        
                    }
                }
                else if (titulo.equals("Preparación de la yerba mate")){
                    Intent itemprep = new Intent (getApplicationContext(),ItemPreparacion.class);
                    itemprep.putExtra("itemspreparacion", (Serializable)preparacions);
                    startActivity(itemprep);
                }
                else if (titulo.equals("¿Como preparar un mate?")){
                    HashMap<String, Object> p = new HashMap<String,Object>();
                    ArrayList<String> urls =  new ArrayList<String>();
                    for (ImagePreparacionMateGSON elemento : imagesPreps){
                        urls.add(elemento.getImage_url());
                    }
                    p.put("width", 120);
                    p.put("height", 120);
                    p.put("urlImagesPrepMate", urls);
                    p.put("objetoPrepMate", productoSeleccionado);
                    ImagenAsyncTask iniciar = new ImagenAsyncTask(getApplicationContext(),codigoqr);
                    iniciar.ejecutarTarea(Task.GET_IMAGENES_PREPARAR_MATE, p);  
                }

                return false;
            }
        });
    }
 
    /*
     * Preparing the list data
     */

    public void onArticuloSelected(ProcesoProductoGSON procesoprod) {
        Fragment fragmentCUATRO = new DescripcionProceso();
        Bundle args = new Bundle();
        args.putSerializable("procesoProducto", procesoprod);
        args.putSerializable("contexto", new SharedPreference(getApplicationContext()));
        fragmentCUATRO.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentCUATRO);
        transaction.addToBackStack(null);
        transaction.commit();
    }
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
    private void prepareListData() {
        
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Object>>();
        
        List<Object> datosPropGeneral = new ArrayList<Object>();
        List<Object> datosProcProducto = new ArrayList<Object>();
        List<Object> datospreparacion = new ArrayList<Object>();
        // Adding child data
       
       
       if(!propgenerals.isEmpty()){
           listDataHeader.add("¿Qué es la Yerba Mate?");   
           for (PropGeneralProducto propgeneral : propgenerals)
           {
                datosPropGeneral.add(propgeneral.getDescripción());
                break;
           }
       }
       
        if (!procProductos.isEmpty()){
           listDataHeader.add("Elaboración");
           for (ProcesoProductoGSON procesoprod : procProductos){
               System.out.println(procesoprod.getTítulo());
               datosProcProducto.add(procesoprod.getTítulo());
           }
        }
        
        if(!propgenerals.isEmpty()){
            listDataHeader.add("Preparación"); 
            if (!propgenerals.get(0).getProp_general_items().isEmpty())
                listDataHeader.add("Propiedades"); 
            if (!propgenerals.get(0).getGaleria_props().isEmpty())
                listDataHeader.add("Información Nutricional"); 
        }
        if(!preparacions.isEmpty()){
            datospreparacion.add("Preparación de la yerba mate");
        }
        if(!imagesPreps.isEmpty()){
            datospreparacion.add("¿Como preparar un mate?");
        }
        if (!datosPropGeneral.isEmpty()){
            listDataChild.put(listDataHeader.get(0), datosPropGeneral);
        }
        if (!datospreparacion.isEmpty()){
            if (!datosProcProducto.isEmpty()){
        	listDataChild.put(listDataHeader.get(1), datosProcProducto);
            }
            if(!datospreparacion.isEmpty()){
        	listDataChild.put(listDataHeader.get(2), datospreparacion);
            }
        }
        if(!empresa.getUbicacion_geos().isEmpty()){
            listDataHeader.add("Ruta de la Yerba Mate");
        }

        
    }
}
