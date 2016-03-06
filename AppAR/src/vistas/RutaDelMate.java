package vistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import clases.EmpresaGSON;
import clases.Mapa;

import com.appar.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class RutaDelMate extends Activity{
//public class RutaDelMate extends FragmentActivity implements OnMapReadyCallback  {

	private GoogleMap mMap;
	private HashMap<Marker, MyMarker> mMarkersHashMap;
	private ArrayList<MyMarker> mMyMarkersArray = new ArrayList<MyMarker>();
	private MapFragment mMapFragment;	 
	private EmpresaGSON empresa;
	private Context mContext;
	private Marker marker;
	private Hashtable<String, String> markers;
	private List<Mapa> puntosgeograficos;
	private  HashMap<String, Object> imagenes;
	@Override  
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	  //  setContentView(R.layout.contenedor_map);
	    setContentView(R.layout.ruta_del_mate);
	    empresa = (EmpresaGSON) getIntent().getExtras().getSerializable("empresa");
	    imagenes = (HashMap<String, Object>) getIntent().getExtras().getSerializable("imagenMapa");
	    if(!empresa.getUbicacion_geos().isEmpty()){
	 	puntosgeograficos = empresa.getUbicacion_geos();
	 	 mMarkersHashMap = new HashMap<Marker, MyMarker>();
			for (Mapa puntogeografico : puntosgeograficos){
			        mMyMarkersArray.add(new MyMarker(puntogeografico.getTitle(),puntogeografico.getDescription(), puntogeografico.getImage_url(), Double.parseDouble(puntogeografico.getLatitude()), Double.parseDouble(puntogeografico.getLongitude())));
			}
	    }
	    setUpMap();

	        plotMarkers(mMyMarkersArray);
		        //cargar();	
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		
	/*public void cargar(){
		mMapFragment = MapFragment.newInstance();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.contenedor, mMapFragment);
		transaction.commit();
		mMapFragment.getMapAsync(this);
		
	}*/
	/*@Override
	public void onMapReady(GoogleMap map) {
		if(!puntosgeograficos.isEmpty()){
		    mMarkersHashMap = new HashMap<Marker, MyMarker>();
			for (Mapa puntogeografico : puntosgeograficos){
			        mMyMarkersArray.add(new MyMarker(puntogeografico.getTitle(), "icon1", Double.parseDouble(puntogeografico.getLatitude()), Double.parseDouble(puntogeografico.getLongitude())));
			    
			        map.addMarker(new MarkerOptions()
		        .position(new LatLng(Double.parseDouble(puntogeografico.getLatitude()),Double.parseDouble(puntogeografico.getLongitude()))).snippet(puntogeografico.getDescription())
		        .title(puntogeografico.getTitle()).icon(BitmapDescriptorFactory.fromResource(R.drawable.map)));				
			}
			 }
			 setUpMap();
			 plotMarkers(mMyMarkersArray);
		}*/
		
	    /*map.addMarker(new MarkerOptions()
	        .title("Sydney")
                .snippet("The most populous city in Australia.").position(new LatLng(39.900062, -122.684818)));
	     	    
	    }*/	
	
	    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
	    {
	        public MarkerInfoWindowAdapter()
	        {
	        }

	        @Override
	        public View getInfoWindow(Marker marker)
	        {
	            return null;
	        }

	        @Override
	        public View getInfoContents(Marker marker)
	        {
	            View v  = getLayoutInflater().inflate(R.layout.infowindow_layout, null);

	            MyMarker myMarker = mMarkersHashMap.get(marker);

	            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);

	            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);
	            Bitmap bitmap = (Bitmap) imagenes.get(myMarker.getmIcon());
	            System.out.println(myMarker.getmIcon());
	            markerIcon.setImageDrawable(new BitmapDrawable (getApplicationContext().getResources(),bitmap));
	            markerLabel.setText(myMarker.getTitulo());
	            markerLabel.setTextColor(Color.BLACK);
	            if(!myMarker.getDescription().isEmpty()){
        	            TextView anotherLabel = (TextView)v.findViewById(R.id.another_label);
        	            anotherLabel.setText(myMarker.getDescription());
        	            anotherLabel.setTextColor(Color.BLACK);
	            }
	            return v;
	        }
	    }
	    private void plotMarkers(ArrayList<MyMarker> markers)
	    {
	        if(markers.size() > 0)
	        {
	            for (MyMarker myMarker : markers)
	            {

	                // Create user marker with custom icon and other options
	                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(myMarker.getmLatitude(), myMarker.getmLongitude()));
	                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.map));

	                Marker currentMarker = mMap.addMarker(markerOption);
	                mMarkersHashMap.put(currentMarker, myMarker);

	                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
	            }
	        }
	    }
	    private void setUpMap()
	    {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (mMap == null)
	        {
	            // Try to obtain the map from the SupportMapFragment.
	            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

	            // Check if we were successful in obtaining the map.

	            if (mMap != null)
	            {
	                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
	                {
	                    @Override
	                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker)
	                    {
	                        marker.showInfoWindow();
	                        return true;
	                    }
	                });
	            }
	            else
	                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
	        }
	    }
	    
	    private int manageMarkerIcon(String markerIcon)
	    {
	        if (markerIcon.equals("icon1"))
	            return R.drawable.map;
	        else
	            return R.drawable.map;
	    }
}

