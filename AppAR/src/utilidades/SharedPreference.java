package utilidades;

import java.io.Serializable;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Context context;
		private SharedPreferences preferencias;		
		private static final String COLECCION = "com.appar";
		private static final String ID_EMPRESA = "id_empresa";
		private static final String OBJETO_EMPRESA = "objeto_empresa";
		private static final String URL_QR = "url_qr";
		public SharedPreference (Context contex){
			setContext(contex);
			preferencias = context.getSharedPreferences(COLECCION, Context.MODE_PRIVATE);
			
		}
	
		public Context getContext() {
			return context;
		}
		
		public void setContext(Context context) {
			this.context = context;
		}

		public SharedPreferences getPreferencias() {
			return preferencias;
		}

		public void setPreferencias(SharedPreferences preferencias) {
			this.preferencias = preferencias;
		}

		public static String getColeccion() {
			return COLECCION;
		}

		public void guardarIdEmpresa (Integer idEmpresa){
			SharedPreferences.Editor editor = preferencias.edit();
			editor.putInt(ID_EMPRESA, idEmpresa);
			editor.commit();
		}
		
		public void guardarObjeto(String objeto){
			SharedPreferences.Editor editor = preferencias.edit();
			editor.putString(OBJETO_EMPRESA, objeto);
			
			editor.commit();
		}
		
		public void guardarQR(String objeto){
			SharedPreferences.Editor editor = preferencias.edit();
			editor.putString(URL_QR, objeto);
			editor.commit();
		}
		
		
		public Integer getIdEmpresa(){
			return preferencias.getInt(ID_EMPRESA, 0);
		}
		
		public String getObjetoEmpresa(){
			return preferencias.getString(OBJETO_EMPRESA, null);
		}

		public String getQRURL(){
			return preferencias.getString(URL_QR, null);
		}
}
