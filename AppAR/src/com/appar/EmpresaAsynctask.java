package com.appar;

import java.util.HashMap;

import android.os.AsyncTask;

public abstract class EmpresaAsynctask extends  AsyncTask<Void, Void, Void> {

	private HashMap<String, Object> parametros;
	private HashMap<String, Object> respuestas;
	private Task tarea;
	private boolean terminado;

	
	public EmpresaAsynctask(){
		setRespuestas(new HashMap<String,Object>());
		setParametros(new HashMap<String, Object>());
		setTerminado(false);
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
		
		
		public boolean isTerminado() {
			return terminado;
		}
		
		
		
		public void setTerminado(boolean terminado) {
			this.terminado = terminado;
		}
		
		
		public enum Task{
			BUSCAR_ELEMENTO,
			GET_EMPRESAS,
			GET_IMAGENES,
			GET_VIDEOS,
			
		
		}
		
		public void ejecutarTarea(Task task, HashMap<String,Object>parametros){
			setParametros(parametros);
			setTarea(task);
			execute();
		}
		
//@Override
//protected void onPreExecute(){
//	getActivity().mostrarEspera();
//}

//@Overrideo
//protected void onPostExecute(Void result){
//	setTerminado(true);
//	notifyActivityTastCompleted();
//}

//private void notifyActivityTaskCompleted(){
//	if(null!= getActivity())
//	{
//		getActivity().recibirRespuesta();
//	}
}
