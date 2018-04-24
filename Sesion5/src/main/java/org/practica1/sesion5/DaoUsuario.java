package org.practica1.sesion5;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//DaoUsuarioJdbc
@Repository
public class DaoUsuario implements DaoUsuarioInterface {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		   this.dataSource = dataSource;
		   this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<DtoUsuario> leeUsuarios(){
		
		String sql = "select * from usuarios";
		UsuarioMapper mapper = new UsuarioMapper();
		List<DtoUsuario> listausuarios = this.jdbcTemplate.query(sql, mapper);
		return listausuarios;
		
	}
	
}
