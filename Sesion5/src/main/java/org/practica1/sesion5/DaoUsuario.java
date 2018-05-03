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
	
	//Para comprobar si existe el usuario en la BBDD mediante su email.
	public Boolean existeEmail(String email) {
		
		DtoUsuario usuario;
		String sql = "select * from usuarios where Email = ?";
		
		//Parametros para la consulta:
		Object[] parametros = {email};
		//Instancia de la clase de mapeo del usuario:
		UsuarioMapper mapper = new UsuarioMapper();
		//Devuele los usuarios con el nombre:
		usuario = (DtoUsuario) this.jdbcTemplate.query(sql, parametros, mapper);
		
		//Si no existe un usuario:
		if(usuario == null) return false;
		else return true;
		
	}
	
	public DtoUsuario extraerUser (String email) {
		
		String sql = "select * from usuarios where email = ? ";
		Object[] parametros = {email};
		UsuarioMapper mapper = new UsuarioMapper();
		List<DtoUsuario> datosuser = this.jdbcTemplate.query(sql, parametros, mapper);
		
		if (datosuser.isEmpty()) return null;
		else return datosuser.get(0); //Devuelve el primer objeto de la lista, el que estamos buscando.
		
	}
}
