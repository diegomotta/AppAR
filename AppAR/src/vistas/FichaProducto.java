package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utilidades.ExpandableListAdapter;
import utilidades.SharedPreference;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import clases.DetalleGSON;
import clases.RespuestaGSON;

import com.appar.R;

public class FichaProducto extends Activity{

	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Object>> listDataChild;
	private RespuestaGSON objetoProductoPublic;
	private HashMap<String, Object> objetoImagenUnica;
	private Bitmap bm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_item_producto);
		SharedPreference preference = new SharedPreference(getApplicationContext());
		objetoImagenUnica = (HashMap<String, Object>) getIntent().getExtras().getSerializable("objetoImagenUnica");
		objetoProductoPublic = (RespuestaGSON) getIntent().getExtras().getSerializable("objetoRespuesta");
		bm = (Bitmap)objetoImagenUnica.get(objetoProductoPublic.getImage_url());
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		  
	        // preparing list data
	        prepareListData();
	 
	        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
	 
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
	    	DetalleGSON detalle=objetoProductoPublic.getDetalles().get(0);
	        listDataHeader = new ArrayList<String>();
	        listDataChild = new HashMap<String, List<Object>>();
	 
	        // Adding child data
	        listDataHeader.add("Marca");
	        listDataHeader.add("Imagen del producto");
	        listDataHeader.add("Precio unitario");
	        listDataHeader.add("Tipo");
	        listDataHeader.add("Descripción");
	        listDataHeader.add("Elaboración");
	        listDataHeader.add("Presentación");
	        listDataHeader.add("Packaging");
	        listDataHeader.add("Secanza");
	        listDataHeader.add("Característica del sabor");
	        // Adding child data
	        List<Object> marca = new ArrayList<Object>();
	        marca .add(objetoProductoPublic.getMarca());

	        List<Object> imagenProducto = new ArrayList<Object>();
	        imagenProducto.add(bm);

	        List<Object> precio = new ArrayList<Object>();
	        precio.add(objetoProductoPublic.getPrecio());
	        
	        List<Object> tipo = new ArrayList<Object>();
	        tipo.add(objetoProductoPublic.getTipo());
	        
	        List<Object> descripcionProd = new ArrayList<Object>();
	        descripcionProd.add(detalle.getDescripción());
	 
	        List<Object> elaboración = new ArrayList<Object>();
	        elaboración.add(detalle.getElaboración());
	        
	        List<Object> presentación = new ArrayList<Object>();
	        presentación.add(detalle.getPresentación());
	        
	        List<Object> packaging = new ArrayList<Object>();
	        packaging.add(detalle.getPackaging());
	        
	        List<Object> secanza = new ArrayList<Object>();
	        secanza.add(detalle.getSecanza());
	        
	        List<Object> característica = new ArrayList<Object>();
	        característica.add(detalle.getCaracterística());
	        

	        listDataChild.put(listDataHeader.get(0), marca); // Header, Child data
	        listDataChild.put(listDataHeader.get(1), imagenProducto);
	        listDataChild.put(listDataHeader.get(2), precio);
	        listDataChild.put(listDataHeader.get(3), tipo);
	        listDataChild.put(listDataHeader.get(4), descripcionProd);
	        listDataChild.put(listDataHeader.get(5), elaboración);
	        listDataChild.put(listDataHeader.get(6), presentación);
	        listDataChild.put(listDataHeader.get(7), packaging);
	        listDataChild.put(listDataHeader.get(8), secanza);
	        listDataChild.put(listDataHeader.get(9), característica);
	        
	    }
	
}