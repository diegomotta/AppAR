package utilidades;

import java.io.Serializable;
import java.util.ArrayList;

import com.appar.InicioAplicacion;

public class BarraProgresoCircular implements Serializable {

	private InicioAplicacion pDialog;
	public BarraProgresoCircular(){}
	public BarraProgresoCircular(InicioAplicacion pDialog){
		this.pDialog = pDialog;
	}
	public InicioAplicacion getpDialog() {
		return pDialog;
	}
	public void setpDialog(InicioAplicacion pDialog) {
		this.pDialog = pDialog;
	}

}
