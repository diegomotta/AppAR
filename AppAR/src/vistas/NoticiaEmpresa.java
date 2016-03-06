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
import clases.NoticiaGSON;
import clases.RespuestaGSON;

import com.appar.R;

public class NoticiaEmpresa extends AppCompatActivity {
	private RespuestaGSON respuestaHTTP;
	private ExpandibleListAdapterProp listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, String> listDataChild;
	private List<NoticiaGSON> noticias;

        private EmpresaGSON empresa;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_item_producto);
		setTitle("Noticias");
		empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("objetoDatosProducto");
		noticias = empresa.getNoticia_empresas();
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
            	for (NoticiaGSON noticia : noticias){
                 	if (titulo.equals(noticia.getTitulo())){
                 		onArticuloSelected(noticia);
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
 
        // Adding child data

        for (NoticiaGSON noticia : noticias){
        	listDataHeader.add(noticia.getTitulo());
        }
    }
}