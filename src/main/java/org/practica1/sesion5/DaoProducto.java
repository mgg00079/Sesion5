package org.practica1.sesion5;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class DaoProducto implements DaoProductoInterface {
	
	//Para trabajar con JDBC que nos proporciona spring
	private JdbcTemplate jdbcTemplate;
	//Hace referencia al datasource de la base de datos que sera sostenida en la tabla
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		   this.dataSource = dataSource;
		   this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<DtoProducto> leeproducto(){
		
		String sql = "select * from productos";
		
		ProductoMapper mapper = new ProductoMapper();
		
		List<DtoProducto> lista = this.jdbcTemplate.query(sql, mapper);
		
		return lista;
	}
	
	public void actualizar (int unidades, int id) {
		
		String sql = "update productos set unidades = ? where ref = ?";
		
		this.jdbcTemplate.update(sql, unidades, id);
				
	}
	
	
	
}
