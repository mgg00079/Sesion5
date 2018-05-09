package org.practica1.sesion5;

import java.util.List;

public interface DaoProductoInterface {
	
	public List<DtoProducto> leeproducto();
	public void actualizar (int unidades, int id);

}
