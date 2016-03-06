package vistas;

import java.util.ArrayList;
import java.util.HashMap;

import servidor.EmpresaAsyncTask.Task;
import servidor.ServidorAsyncTasks;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import clases.ConsultaGson;

import com.appar.QRUrl;
import com.appar.R;

public class Consulta extends AppCompatActivity{
		
	EditText nombre;
  	EditText correo;
	EditText telefono;
	EditText descripcion;
	private QRUrl codigoqr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_activity);
		setTitle("Consultas");
		Button button = (Button) findViewById(R.id.btn_nuevo_comentario);
		codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");
		button.setOnClickListener(new OnClickListener() {
	    @Override
        public void onClick(View view) {
  		   	nombre = (EditText)findViewById(R.id.nombre_consulta);
	    	correo = (EditText)findViewById(R.id.email_consulta);
	    	telefono = (EditText)findViewById(R.id.telefono_consulta);
	    	descripcion = (EditText)findViewById(R.id.descripcion_consulta);
	  		ConsultaGson consulta = new ConsultaGson (nombre.getText().toString(),correo.getText().toString(),telefono.getText().toString(), descripcion.getText().toString());
	  		HashMap<String, Object> p = new HashMap<String,Object>();
	  		ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),codigoqr);
	  		p.put("consulta", consulta);
	  		ProgressDialog progressDialogo = new ProgressDialog(Consulta.this);
	  		p.put("progresoConsultaCliente", progressDialogo);
	  		p.put("instanciaClase",Consulta.this);
			ArrayList<Object> componentes = new ArrayList<Object>();
			componentes.add(0, nombre);
			componentes.add(1, correo);
			componentes.add(2, telefono);
			componentes.add(3, descripcion);
			p.put("componenteConsulta", componentes);
	  		s.ejecutarTarea(Task.SET_CONSULTA, p);
    	}
      });

	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
		

}
