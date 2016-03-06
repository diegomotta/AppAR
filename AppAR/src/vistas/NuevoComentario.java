package vistas;

import java.util.ArrayList;
import java.util.HashMap;

import servidor.EmpresaAsyncTask.Task;
import servidor.ServidorAsyncTasks;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import clases.ComentarioGSON;

import com.appar.QRUrl;
import com.appar.R;

public class NuevoComentario  extends AppCompatActivity{
	private QRUrl codigoqr;
	private HashMap<String, Object> activityComent;
	private Comentario listcomentario;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nuevo_comentario);
		setTitle("Nuevo Comentario");
		codigoqr = (QRUrl) getIntent().getExtras().getSerializable("codigoQR");

	
		
		
	}
	
    public void onClickComentario(View view) throws InterruptedException {	
		EditText nombre = (EditText)findViewById(R.id.nombre_comentario_txt);
		EditText nacionalidad = (EditText)findViewById(R.id.nacionalidad_comentario_txt);
		EditText pais = (EditText)findViewById(R.id.pais_comentario_txt);
		EditText ciudad = (EditText)findViewById(R.id.ciudad_comentario_txt);
		EditText comentario = (EditText)findViewById(R.id.comentario_txt);
		ComentarioGSON enviarComentario = new ComentarioGSON (nombre.getText().toString(),nacionalidad.getText().toString(),pais.getText().toString(), ciudad.getText().toString(),comentario.getText().toString());
		HashMap<String, Object> p = new HashMap<String,Object>();
		ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),codigoqr);
		p.put("comentario", enviarComentario);
		ProgressDialog progressDialogo = new ProgressDialog(NuevoComentario.this);
		p.put("progresoComentario", progressDialogo);
		p.put("instanciaClase", NuevoComentario.this);
		p.put("listcomentario", listcomentario);
		ArrayList<Object> componenteComentario = new ArrayList<Object>();
		componenteComentario.add(0, nombre);
		componenteComentario.add(1, nacionalidad);
		componenteComentario.add(2, pais);
		componenteComentario.add(3, ciudad);
		componenteComentario.add(4, comentario);
		p.put("codigoQR",codigoqr);
		p.put("componenteComentario", componenteComentario);
		s.ejecutarTarea(Task.SET_COMENTARIO, p);
	
		
	  }
    
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
