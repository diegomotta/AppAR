package vistas;

import java.util.HashMap;

import servidor.EmpresaAsyncTask.Task;
import servidor.ImagenAsyncTask;
import utilidades.SharedPreference;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import clases.ItemDeInfos;

import com.appar.R;

public class InformacionEmpresaFrag extends android.app.Fragment{

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.descripcion_proceso, container, false);
		TextView textoTitulo = (TextView) view.findViewById(R.id.textTituloProceso);
		TextView textoProceso = (TextView) view.findViewById(R.id.textDescripcionProceso);
		ImageSwitcher imageProceso = (ImageSwitcher) view.findViewById(R.id.imageProceso);
		Bundle bundle = getArguments();
		ItemDeInfos recuperada = (ItemDeInfos) bundle.getSerializable("iteminfoEmpresa");
		SharedPreference contexto = (SharedPreference) bundle.getSerializable("contexto");
		HashMap<String, Object> p = new HashMap<String,Object>();
		p.put("width", 600);
		p.put("height", 600);
		if(!recuperada.getUrl_image().isEmpty()){
			p.put("urlImagenProc", recuperada.getUrl_image());
			p.put("objetoProceso", recuperada);
			p.put("textoTitulo", textoTitulo);
			p.put("textoDescripcion", textoProceso);
			p.put("cuadroImagen", imageProceso);
			p.put("contexto", contexto.getContext());
			ImagenAsyncTask iniciar = new ImagenAsyncTask();
			ProgressDialog pro = new ProgressDialog(getActivity());
			p.put("progresoInfo", pro);
			iniciar.ejecutarTarea(Task.GET_IMAGEN_INFO, p);	
		}
		else{
			textoTitulo.setText(recuperada.getTítulo());
			textoProceso.setText(recuperada.getDescripción());
			imageProceso.setVisibility(View.GONE);
		}
				

		return view;
	}
}
