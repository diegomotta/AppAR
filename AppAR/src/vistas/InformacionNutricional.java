package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utilidades.ExpandibleListAdapterProp;
import utilidades.SharedPreference;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import clases.EmpresaGSON;
import clases.InformacionNutricionalGSON;
import clases.NoticiaGSON;
import clases.ProductoGSON;
import clases.PropGeneralProducto;
import clases.RespuestaGSON;

import com.appar.R;

public class InformacionNutricional extends AppCompatActivity {
	private RespuestaGSON respuestaHTTP;
	private ExpandibleListAdapterProp listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, String> listDataChild;
	private Integer idproducto;
	private List<ProductoGSON> productos;
    private EmpresaGSON empresa;
    private PropGeneralProducto propgeneral;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_item_producto);
		setTitle("Información Nutricional");
		//A PARTIR DE ACA TRAE LOS OBJETOS DE LA INTERFAZ "Producto.java"
		empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("objetoDatosProducto");
		idproducto = (Integer) getIntent().getExtras().get("identificadorProducto");
		productos = empresa.getProductos();
		for(ProductoGSON producto : productos){
			if(producto.getId()==idproducto){
				propgeneral =producto.getProp_generals().get(0);

			}
		}
		
		empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("objetoDatosProducto");
		expListView = (ExpandableListView) findViewById(R.id.lvExp);		  
        // preparing list data
        prepareListData();
        listAdapter = new ExpandibleListAdapterProp(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {

                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
			
	public void onArticuloSelected(NoticiaGSON noticia) {
		Fragment fragmentCUATRO = new DescripcionNoticia();
		Bundle args = new Bundle();
        args.putSerializable("noticiaEmpresa", noticia);
        args.putSerializable("contexto", new SharedPreference(getApplicationContext()));
        fragmentCUATRO.setArguments(args);
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, fragmentCUATRO);
		transaction.addToBackStack(null);
		transaction.commit();
	}
    private void prepareListData() {
    	
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, String>();
 
        for (InformacionNutricionalGSON infoNutricional :propgeneral.getProp_general_items()){
        	listDataHeader.add(infoNutricional.getItem());
        }
        // Header, Child data
        int i=0;
        for (InformacionNutricionalGSON infoNutricional :propgeneral.getProp_general_items()){
            listDataChild.put(listDataHeader.get(i),infoNutricional.getDescripción()); // Header, Child data
         	 i++;
        }      

    }

}