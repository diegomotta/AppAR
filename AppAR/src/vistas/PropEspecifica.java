package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utilidades.ExpandibleListAdapterProp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import clases.DetalleGSON;
import clases.PropEspecificaPresentacionGSON;
import clases.RespuestaGSON;

import com.appar.R;


public class PropEspecifica extends AppCompatActivity {
	private RespuestaGSON respuestaHTTP;
	private ExpandibleListAdapterProp listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, String> listDataChild;
	private DetalleGSON detalles;
	private List<PropEspecificaPresentacionGSON> propEspecificas;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_item_producto);
		setTitle("Propiedades Específicas");
		respuestaHTTP = (RespuestaGSON) getIntent().getExtras().getSerializable("objetoRespuesta");
		detalles=respuestaHTTP.getDetalles().get(0);
		propEspecificas = detalles.getProp_especificas();
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
             //    Toast.makeText(getApplicationContext(),
               //  "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 
            @Override
            public void onGroupExpand(int groupPosition) {
             //   Toast.makeText(getApplicationContext(),
               //         listDataHeader.get(groupPosition) + " Expanded",
                 //       Toast.LENGTH_SHORT).show();
            }
        });
 
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
              //  Toast.makeText(getApplicationContext(),
                //        listDataHeader.get(groupPosition) + " Collapsed",
                  //      Toast.LENGTH_SHORT).show();
 
            }
        });
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
           //     Toast.makeText(
             //           getApplicationContext(),
               //         listDataHeader.get(groupPosition)
                 //               + " : "
                   //             + listDataChild.get(
                     //                   listDataHeader.get(groupPosition)).get(
                       //                 childPosition), Toast.LENGTH_SHORT)
                        //.show();
                return false;
            }
        });
    }
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
    /*
     * Preparing the list data
     */
    private void prepareListData() {
    	
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, String>();
 
        // Adding child data
        int i = 0;
        for (PropEspecificaPresentacionGSON propEspecifica : propEspecificas){
        	listDataHeader.add(propEspecifica.getItem());

        }
      
        
        for (PropEspecificaPresentacionGSON propEspecifica : propEspecificas){
        	 listDataChild.put(listDataHeader.get(i),propEspecifica.getDescripción()); // Header, Child data
           	 i++;
        }

    }


}
