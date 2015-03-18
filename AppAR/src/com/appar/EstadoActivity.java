package com.appar;
public class EstadoActivity {

	public enum Estado{
		INICIANDO,
		VALIDANDO,
		EJECUTANDO,
		FINALIZANDO,
		INACTIVO
	}
	
	private Estado estado;
	private EmpresaAsynctask task;
	//private CacheImagen caheImagen;
	private int operacion;
	private Boolean noHayInternet = false;
	
	public  Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public EmpresaAsynctask getTask() {
		return task;
	}
	public void setTask(EmpresaAsynctask task) {
		this.task = task;
	}
	public int getOperacion() {
		return operacion;
	}
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}
	public Boolean getNoHayInternet() {
		return noHayInternet;
	}
	public void setNoHayInternet(Boolean noHayInternet) {
		this.noHayInternet = noHayInternet;
	}
	
	
}
