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
import clases.Informacion_empresa;
import clases.ItemDeInfos;

import com.appar.R;

public class Itemdeinfos extends AppCompatActivity {
    	private EmpresaGSON empresa;
    	private List <Informacion_empresa> informacion_empresas;
    	private Informacion_empresa informacion_empresa;
    	private List <ItemDeInfos> itemDeInfos;    	
    	private ExpandibleListAdapterProp listAdapter;
    	private ExpandableListView expListView;
    	private List<String> listDataHeader;
    	private HashMap<String, String> listDataChild;
    	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list_view_item_producto);
	    setTitle("¿Quienes somos?");
		empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("objetoDatosProducto");
		informacion_empresas = empresa.getInformacion_empresas();
		informacion_empresa = informacion_empresas.get(0);
		itemDeInfos = informacion_empresa.getItem_de_info();
		System.out.println(itemDeInfos.get(0).getTítulo());
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
	            	String	titulo = (String)listDataHeader.get(groupPosition);
	            	for (ItemDeInfos itemss : itemDeInfos){
	                 	if (titulo.equals(itemss.getTítulo())){
	                 		onArticuloSelected(itemss);
	                 		break;
	                 	}
	                 	
	                 }
	                return true;
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
	    public void onArticuloSelected(ItemDeInfos iteminfo) {
			Fragment fragmentCUATRO = new InformacionEmpresaFrag();
			Bundle args = new Bundle();
			args.putSerializable("iteminfoEmpresa", iteminfo);
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
	        listDataChild = new HashMap<String, String>();
	 
	        // Adding child data

	        for (ItemDeInfos itemss : itemDeInfos){
	        	listDataHeader.add(itemss.getTítulo());
	        }
	    }
}
