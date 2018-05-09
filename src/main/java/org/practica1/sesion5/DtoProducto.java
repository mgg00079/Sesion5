package org.practica1.sesion5;

public class DtoProducto {
	
	private int id;
	private String nombre;
	private double importe;
	private int unidades;
	private String concepto;
	private String imagen;
	
	public DtoProducto (int id, String nombre,double importe,String concepto,String imagen,int unidades){
		this.id=id;
		this.nombre=nombre;
		this.imagen=imagen;
		this.concepto=concepto;
		this.importe=importe;
		this.unidades=unidades;
	}
	
	public DtoProducto (){
		this.id=0;
		this.nombre="";
		this.concepto="";
		this.imagen="";
		this.importe=0.0;
		this.unidades=0;
		
	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public double getImporte() {
		return importe;
	}
	public String getConcepto() {
		return concepto;
	}
	public String getImagen() {
		return imagen;
	}
	public int getUnidades() {
		return unidades;
	}

	//Métodos set
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
}
