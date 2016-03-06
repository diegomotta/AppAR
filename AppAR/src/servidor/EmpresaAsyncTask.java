package servidor;

import java.util.HashMap;

import android.os.AsyncTask;

public abstract class EmpresaAsyncTask extends  AsyncTask<Void, Void, Object> {

	private HashMap<String, Object> parametros;
	private HashMap<String, Object> respuestas;
	private Task tarea;
	
	public EmpresaAsyncTask(){
		setRespuestas(new HashMap<String,Object>());
		setParametros(new HashMap<String, Object>());
	}	
	
	public enum Task{
		BUSCAR_ELEMENTO,
		GET_EMPRESAS,
		GET_IMAGENES,
		GET_VIDEOS,
		GET_VIDEO, 
		SET_CONSULTA, 
		SET_COMENTARIO, 
		GET_IMAGEN, 
		GET_EMPRESA, 
		GET_PRODUCTO, 
		GET_IMAGEN_PROCESO, 
		GET_IMAGENES_PREPARAR_MATE, 
		SET_ENCUESTA,  
		GET_IMAGENES_VALORES_ENERGETICOS, 
		GET_IMAGEN_ELABORACION,
		GET_COMENTARIOS, GET_IMAGEN_INFO, GET_IMAGENES_MAPA, GET_INTENT_COMENTARIOS,
		
	}
	
	public Object ejecutarTarea(Task task, HashMap<String,Object>parametros){
		setParametros(parametros);
		setTarea(task);
		return execute();
	}

			
		public HashMap<String, Object> getParametros() {
			return parametros;
		}
		
		
		public  void setParametros(HashMap<String, Object> parametros) {
			this.parametros = parametros;
		}
		
		
		public HashMap<String, Object> getRespuestas() {
			return respuestas;
		}
		
		
		public void setRespuestas(HashMap<String, Object> respuestas) {
			this.respuestas = respuestas;
		}
		
		
		public Task getTarea() {
			return tarea;
		}
		
		
		public void setTarea(Task tarea) {
			this.tarea = tarea;
		}
}
