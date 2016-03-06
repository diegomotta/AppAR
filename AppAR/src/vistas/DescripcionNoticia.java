package vistas;

import java.util.HashMap;

import servidor.ImagenAsyncTask;
import servidor.EmpresaAsyncTask.Task;
import utilidades.SharedPreference;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import clases.NoticiaGSON;
import clases.ProcesoProductoGSON;

import com.appar.PrincipalPresentacionProducto;
import com.appar.R;

public class DescripcionNoticia extends android.app.Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.descripcion_proceso, container, false);
		TextView textoTitulo = (TextView) view.findViewById(R.id.textTituloProceso);
		TextView textoProceso = (TextView) view.findViewById(R.id.textDescripcionProceso);
		ImageSwitcher imageProceso = (ImageSwitcher) view.findViewById(R.id.imageProceso);
		Bundle bundle = getArguments();
		NoticiaGSON recuperada = (NoticiaGSON) bundle.getSerializable("noticiaEmpresa");
		SharedPreference contexto = (SharedPreference) bundle.getSerializable("contexto");
		HashMap<String, Object> p = new HashMap<String,Object>();
		p.put("width", 600);
		p.put("height", 600);
		if(!recuperada.getImage_url().isEmpty()){
			p.put("urlImagenProc", recuperada.getImage_url());
			p.put("objetoProceso", recuperada);
			p.put("textoTitulo", textoTitulo);
			p.put("textoDescripcion", textoProceso);
			p.put("cuadroImagen", imageProceso);
			p.put("contexto", contexto.getContext());
			ImagenAsyncTask iniciar = new ImagenAsyncTask();
			ProgressDialog pro = new ProgressDialog(getActivity());
			p.put("progresoNoticia", pro);
			iniciar.ejecutarTarea(Task.GET_IMAGEN_PROCESO, p);	
		}
		else{
			textoTitulo.setText(recuperada.getTitulo());
			textoProceso.setText(recuperada.getDescripcion());
			imageProceso.setVisibility(View.GONE);
		}
				

		return view;
	}
	

		
}
