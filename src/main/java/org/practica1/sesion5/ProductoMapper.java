package org.practica1.sesion5;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductoMapper implements RowMapper<DtoProducto> {
	public DtoProducto mapRow(ResultSet rs, int rowNum) throws SQLException{
		DtoProducto p = new DtoProducto();
		
		p.setId(rs.getInt("codigo"));
		p.setImagen(rs.getString("imagen"));
		p.setNombre(rs.getString("nombre"));
		p.setConcepto(rs.getString("concepto"));
		p.setUnidades(rs.getInt("unidades"));
		p.setImporte(rs.getDouble("importe"));
		
		return p;
	}
}
