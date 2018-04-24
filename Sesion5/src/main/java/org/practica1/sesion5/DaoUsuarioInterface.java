package org.practica1.sesion5;

import java.util.List;

public interface DaoUsuarioInterface {

	public List<DtoUsuario> leeUsuarios();
	public Boolean existeEmail(String email);
}
