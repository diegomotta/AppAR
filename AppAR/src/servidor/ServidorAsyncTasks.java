package servidor;

import java.net.URISyntaxException;
import java.util.*;

import utilidades.RowData;
import video.YouTubeFragment;
import vistas.*;
import vistas.Comentario.EntradasAdapter;
import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import clases.*;

import com.appar.*;

public class ServidorAsyncTasks extends  EmpresaAsyncTask {

	private QRUrl codigoqr; 
	private ClienteHTTP cliente;
	private Context applicationContext;
	private RespuestaGSON  objetoProducto;
	private Activity activity;
	private ProgressDialog progressDialogo;
	private ArrayList<Object> componentesActivity ;
	private Object obj = null;
	public Activity activityComentario;
	public ServidorAsyncTasks(Context applicationContext,QRUrl direccion) {
		setCliente(new ClienteHTTP(applicationContext, direccion));
		setApplicationContext(applicationContext);
		setCodigoqr(direccion);
	}

	public ServidorAsyncTasks(QRUrl direccion){
	    setCodigoqr(direccion);
	}
	public QRUrl getCodigoqr() {
		return codigoqr;
	}


	public Activity getActivityComentario() {
		return activityComentario;
	}

	public void setActivityComentario(Activity p_activityComentario) {
		activityComentario = p_activityComentario;
	}

	public void setCodigoqr(QRUrl codigoqr) {
		this.codigoqr = codigoqr;
	}


	public ArrayList<Object> getComponentesActivity() {
		return componentesActivity;
	}


	public void setComponentesActivity(ArrayList<Object> componentesActivity) {
		this.componentesActivity = componentesActivity;
	}
	

	public Activity getActivity() {
		return activity;
	}


	public void setActivity(Activity activity) {
		this.activity = activity;
	}


	public ProgressDialog getProgressDialogo() {
		return progressDialogo;
	}

	public void setProgressDialogo(ProgressDialog progressDialogo) {
		this.progressDialogo = progressDialogo;
	}


	public RespuestaGSON getObjetoProducto() {
		return objetoProducto;
	}

	public void setObjetoProducto(RespuestaGSON objetoProducto) {
		this.objetoProducto = objetoProducto;
	}

	public Context getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(Context applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public ClienteHTTP getCliente() {
		return cliente;
	}

	public void setCliente(ClienteHTTP cliente) {
		this.cliente = cliente;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub

		if (getRespuestas().containsKey("respuestaHTTP")){
			objetoProducto =(RespuestaGSON)  getRespuestas().get("respuestaHTTP");
			Intent i = new Intent(applicationContext, PrincipalPresentacionProducto.class); 
			i.putExtra("sampleObject", objetoProducto); 
			i.putExtra("codigoQR", getCodigoqr());
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			progressDialogo.dismiss();
			applicationContext.startActivity(i);
		}
		
		else if (getRespuestas().containsKey("videoHTTP")){
			String urlFactory = (String) getRespuestas().get("videoHTTP");
			Intent i = new Intent(applicationContext, YouTubeFragment.class); 
			HashMap<String, Object> p = new HashMap<String,Object>();
			p.put("urlFactory",urlFactory);
			i.putExtra("objetoUrlFactory", p); 
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			applicationContext.startActivity(i);
	
		}
		else if (getRespuestas().containsKey("consultaHTTP")){
			progressDialogo.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Consulta enviada")
			        .setTitle("Atención")
			        .setCancelable(false)
			        .setNeutralButton("Aceptar",
			                new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog, int id) {
			                        dialog.cancel();
			                    }
			                });
			AlertDialog alert = builder.create();
			alert.show();
			ArrayList<Object> componetesConsulta = getComponentesActivity();
			for( Object componente : componetesConsulta){
				TextView comp = (TextView) componente;
				comp.setText(null);
			}
			getActivity().finish();
		}
		else if (getRespuestas().containsKey("encuestaHTTP")){
			progressDialogo.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Encuesta enviada")
			        .setTitle("Atención")
			        .setCancelable(false)
			        .setNeutralButton("Aceptar",
			                new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog, int id) {
			                        dialog.cancel();
			                    }
			                });
			AlertDialog alert = builder.create();
			alert.show();
			getActivity().finish();
		}
		else if (getRespuestas().containsKey("comentarioHTTP")){
			progressDialogo.dismiss();
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Comentario enviado")
			        .setTitle("Atención")
			        .setCancelable(false)
			        .setNeutralButton("Aceptar",
			                new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog, int id) {
			                        dialog.cancel();
			                    }
			                });
			AlertDialog alert = builder.create();
			alert.show();
			ArrayList<Object> componetesComentario = getComponentesActivity();
			for( Object componente : componetesComentario){
				TextView comp = (TextView) componente;
				comp.setText(null);
			}	
			             
            ServidorAsyncTasks s = new ServidorAsyncTasks (getApplicationContext(),codigoqr);
            s.ejecutarTarea(Task.GET_COMENTARIOS,null);
            getActivity().finish();
              		        
		}
		else if(getRespuestas().containsKey("datoEmpresaHTTP"))
		{
		        progressDialogo.dismiss();
		        Intent i = new Intent(applicationContext, InformacionEmpresa.class); 
		        EmpresaGSON empresa = (EmpresaGSON) getRespuestas().get("datoEmpresaHTTP");	
		        i.putExtra("codigoQR",getCodigoqr());
	     		i.putExtra("empresaDatoObjecto", empresa); 
		    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		applicationContext.startActivity(i);
		}
		
		else if(getRespuestas().containsKey("comentariosHTTP"))
		{
			 
		        RespuestaGSON empresa = (RespuestaGSON) getRespuestas().get("comentariosHTTP");
        		Intent intComentario = new Intent(applicationContext, Comentario.class);
        		intComentario.putExtra("objetoRespuesta", empresa);
        		intComentario.putExtra("codigoQR",codigoqr);
        		intComentario.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    applicationContext.startActivity(intComentario);
			
		}	
		
		else if (getRespuestas().containsKey("intentComentario")) {
			Intent in = (Intent) getRespuestas().get("intentComentario");
			in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			applicationContext.startActivity(in);
			getActivityComentario().finish();
		}
	}


	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		if (getParametros().containsKey("progresoConsultaCliente")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Enviando consulta");
			progressDialogo.show();
		}
		else if (getParametros().containsKey("progresoQR")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Extrayendo información sobre el producto");
			progressDialogo.show();
		}
		else if (getParametros().containsKey("progresoComentario")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Enviando comentario");
			progressDialogo.show();
		}
		else if (getParametros().containsKey("progresoEncuestaCliente")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Enviando encuesta");
			progressDialogo.show();
		}
		else if (getParametros().containsKey("progresoEmpresa")){
			progressDialogo = getProgressDialogo();
			progressDialogo.setProgressStyle(ProgressDialog.STYLE_SPINNER);				
			progressDialogo.setMessage("Cargando información de la empresa");
			progressDialogo.show();
		}
	}

	@Override
	protected Object doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		switch (getTarea()){
		case BUSCAR_ELEMENTO:
			setProgressDialogo((ProgressDialog) getParametros().get("progresoQR"));
			publishProgress();
			setCodigoqr((QRUrl) getParametros().get("qrdata"));
			getRespuestas().put("respuestaHTTP",cliente.getObjetoEmpresa((QRUrl) getParametros().get("qrdata")));
			break;
		case GET_EMPRESA:
			try {
				setProgressDialogo((ProgressDialog) getParametros().get("progresoEmpresa"));
				publishProgress();
				getRespuestas().put("datoEmpresaHTTP",cliente.getDatosEmpresa(getCodigoqr()));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case GET_VIDEO:
			getRespuestas().put("videoHTTP",cliente.getVideosProducto((String)getParametros().get("urlVideo")));
			
			break;
		case SET_CONSULTA:	
			setProgressDialogo((ProgressDialog) getParametros().get("progresoConsultaCliente"));
			setActivity((Consulta) getParametros().get("instanciaClase"));
			setComponentesActivity((ArrayList<Object>) getParametros().get("componenteConsulta"));
			publishProgress();
			getRespuestas().put("consultaHTTP",cliente.seConsulta((ConsultaGson)getParametros().get("consulta"), getCodigoqr()));
			
			break;
		case SET_ENCUESTA:	
			setProgressDialogo((ProgressDialog) getParametros().get("progresoEncuestaCliente"));
			setActivity((Encuesta) getParametros().get("instanciaClase"));
			publishProgress();
			getRespuestas().put("encuestaHTTP",cliente.seEncuesta((EncuestaGSON)getParametros().get("encuesta"),getCodigoqr()));
			
			break;			
		case SET_COMENTARIO:	
			setProgressDialogo((ProgressDialog) getParametros().get("progresoComentario"));
			setActivity((NuevoComentario) getParametros().get("instanciaClase"));
			setComponentesActivity((ArrayList<Object>) getParametros().get("componenteComentario"));
			publishProgress();
			getRespuestas().put("comentarioHTTP",cliente.seComenta((ComentarioGSON)getParametros().get("comentario"),getCodigoqr()));	
			break;	
		case GET_COMENTARIOS:
		    try {
			getRespuestas().put("comentariosHTTP",cliente.getComentarios(getCodigoqr()));
		    } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
			
		    break;
		case GET_INTENT_COMENTARIOS:
		    	setActivityComentario((Comentario )(getParametros().get("activityComentario")));
                getRespuestas().put("intentComentario",(Intent) getParametros().get("nuevoComentario"));
               break;
		default:
			break;
		}
		return obj;
	}

}
