package org.practica1.sesion5;

import java.io.Serializable;

public class DtoUsuario implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String contrase�a;
	private int telefono;
	private int cp;
	private String direccion;
	private Boolean admin;	
	
	public DtoUsuario(  String nombre, String apellidos, String email,
			String contrase�a, int telefono, int cp, String direccion, Boolean admin) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contrase�a = contrase�a;
		this.telefono = telefono;
		this.cp = cp;
		this.direccion = direccion;
		this.admin = admin;
		
	}

	public DtoUsuario() {
		
		this.nombre = "";
		this.apellidos = "";
		this.email = "";
		this.contrase�a = "";
		this.telefono = 0;
		this.cp = 0;
		this.direccion = "";
		this.admin = false;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
}
