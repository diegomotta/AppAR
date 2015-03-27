package entradasproducto;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.appar.FragmentUNO;
import com.appar.R;
import com.appar.RespuestaHTTP;
import com.appar.SharedPreference;
import com.google.gson.Gson;



public class InformacionBasica extends ActionBarActivity {

	private RespuestaHTTP objetoProductoPublic;
	private SharedPreference preference; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacionbasica);
		ActionBar actionBar = getSupportActionBar();
		/**INDICAR TITULO Y SUBTITULO**/
		preference = new SharedPreference(getApplicationContext());
		RespuestaHTTP obj = (new Gson()).fromJson(preference.getObjetoEmpresa(), RespuestaHTTP.class); 
		//objetoProductoPublic = (RespuestaHTTP) getIntent().getExtras().getSerializable("objetoProducto");
		actionBar.setTitle("Descripción general");
		//actionBar.setSubtitle(objetoProductoPublic.getTitulo());
		actionBar.setSubtitle(obj.getTitulo());
		Bundle args = new Bundle();
        //args.putString("str", objetoProductoPublic.getDescripción());
		args.putString("str", obj.getDescripción());
		FragmentManager FM = getSupportFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();
		Fragment fragment = new FragmentUNO();
		fragment.setArguments(args);
		FT.add(R.id.fragment_container, fragment);
		FT.commit();
	}
}
