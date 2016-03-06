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

import clases.ProcesoProductoGSON;

import com.appar.R;
public class DescripcionProceso extends android.app.Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.descripcion_proceso, container, false);
		TextView textoTitulo = (TextView) view.findViewById(R.id.textTituloProceso);
		TextView textoProceso = (TextView) view.findViewById(R.id.textDescripcionProceso);
		ImageSwitcher imageProceso = (ImageSwitcher) view.findViewById(R.id.imageProceso);
		Bundle bundle = getArguments();
		ProcesoProductoGSON recuperada = (ProcesoProductoGSON) bundle.getSerializable("procesoProducto");
		SharedPreference contexto = (SharedPreference) bundle.getSerializable("contexto");
		HashMap<String, Object> p = new HashMap<String,Object>();
		p.put("width", 600);
		p.put("height", 600);
		p.put("urlImagenProc", recuperada.getImage_url());
		p.put("objetoProceso", recuperada);
		p.put("textoTitulo", textoTitulo);
		p.put("textoDescripcion", textoProceso);
		p.put("cuadroImagen", imageProceso);
		p.put("contexto", contexto.getContext());
		ProgressDialog pro = new ProgressDialog(getActivity());
		p.put("progresoElaboracion", pro);
		ImagenAsyncTask iniciar = new ImagenAsyncTask();
		iniciar.ejecutarTarea(Task.GET_IMAGEN_ELABORACION, p);	
				

		return view;
	}
}
