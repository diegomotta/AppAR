package entradasproducto;
import com.appar.*;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;



public class InformacionBasica extends ActionBarActivity {

	private RespuestaHTTP objetoProductoPublic; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacionbasica);
		ActionBar actionBar = getSupportActionBar();
		/**INDICAR TITULO Y SUBTITULO**/
		objetoProductoPublic = (RespuestaHTTP) getIntent().getExtras().getSerializable("objetoProducto");
		actionBar.setTitle("Descripción general");
		actionBar.setSubtitle(objetoProductoPublic.getTitulo());
		Bundle args = new Bundle();
        args.putString("str", objetoProductoPublic.getDescripcion());       
		FragmentManager FM = getSupportFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();
		Fragment fragment = new FragmentUNO();
		fragment.setArguments(args);
		FT.add(R.id.fragment_container, fragment);
		FT.commit();
	}




}
