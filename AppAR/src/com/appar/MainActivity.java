package com.appar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.appar.EstadoActivity;
import com.appar.EstadoActivity.Estado;
public class MainActivity extends Activity {
	ListView lista;
	ArrayAdapter adaptador;
	HttpURLConnection con;
	private static final String TAG = "MyTag";
	private Button btnQR;
	private Object objeto;
	private SharedPreference preference;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	       
	    }

	    
		//Método para escanear el código QR
		public void Escanear (View button){
	    
			//Intent intent = new Intent(this, Elemento.class );
			//intent.putExtra("valor", "http://10.0.2.2:3000/empresas/1/productos/1/item_productos/1.json");
	    	//startActivity(intent);
		    Intent msgIntent = new Intent(MainActivity.this, ServicioLectura.class);
	        msgIntent.putExtra("productoLeido", "http://10.0.2.2:3000/empresas/1/productos/1/item_productos/1.json");
	        startService(msgIntent);
	    	//Intent intent = new Intent("com.appar.SCAN")
	    	//intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
	    	//startActivityForResult(intent,0);
	    }

		
	  /*  protected void onActivityResult (int requestCode, int resultCode, Intent intent){
	    	if (requestCode == 0){
	    		if (resultCode == RESULT_OK){
	    			//String contents = intent.getStringExtra("SCAN_RESULT")
	    			String contents = intent.getStringExtra("valor");
	    			QRParser parser = new QRParser(contents);
	    			
	    			QRUrl dataurl = null;
	    			try {
						dataurl = new QRUrl(parser.getParse());
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			//Log.i(TAG, contents);
	    			//String formato = intent.getStringExtra("SCAN_RESULT_FORMAT");
	    	    	//Intent i = new Intent(getApplicationContext(), Elemento.class );
	    	    	//i.putExtra("productoLeido", contents);
	    		    //startActivity(i);
	    		    
	    		    Intent msgIntent = new Intent(getApplicationContext(), ServicioLectura.class);
	    	        msgIntent.putExtra("productoLeido", contents);
	    	        startService(msgIntent);
	    		    
	    		}
	    	}	    	
	    }*/
	    
	    public void lanzar(View view) throws SAXException, IOException {
    	 //Intent i = new Intent(this, VideoPlayer.class );
	    //startActivity(i);
	    /*	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = null;
	        try{
	        	builder = factory.newDocumentBuilder();
	        }
	        catch (ParserConfigurationException e){
	        	e.printStackTrace();
	        }
	        String url = null;
	        	Document doc = builder.parse("https://gdata.youtube.com/feeds/api/videos/" + "t0ewsr3s7yM");
	        	for (int i = 0; i < doc.getElementsByTagName("media:content").getLength();i++){
	        		if(doc.getElementsByTagName("media:content").item(i).getAttributes().getNamedItem("type").getNodeValue().equals("video/3gpp")){
	        			url = doc.getElementsByTagName("media:content").item(i).getAttributes().getNamedItem("url").getNodeValue();
	        			break;
	        		}
	        	}
	        	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	            startActivity(intent);      */
	    	Intent i = new Intent(this, Elemento.class );
		    startActivity(i);
	  }    
	    
	    
}
