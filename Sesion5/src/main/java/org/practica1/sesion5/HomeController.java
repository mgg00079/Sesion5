package org.practica1.sesion5;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private DaoUsuarioInterface dao;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		
		DtoUsuario user;
		Boolean siadmin; //Si es administrador.
		Boolean siuser;
		
		HttpSession session = request.getSession(false);
		
		//Si existe la sesion. Si existe, se almacena el usuario en la variable user.
		if(session!=null) {
			user = (DtoUsuario) session.getAttribute("usuario");
		} else {
			user = null;
		}
		
		//Si existe el usuario en la sesion:
		if(user!=null) {
			//Para llevar un control en consola.
			System.out.println("Existe sesion");
			System.out.println("Email: " + user.getEmail());
			
			//Comprueba si existe el usuario con email de la cookie en la BBDD:
			Boolean existeemail = dao.existeEmail(user.getEmail());
			
			if(existeemail) {
				
				if(user.getAdmin()) {
					
					siadmin = true;
					model.addAttribute("ListaUsuarios", dao.leeUsuarios());
					
					return "admin"; //Si es administrador, lleva a la pagina donde se muestra los datos de los usuarios.
					
				} else {
					
					siuser = true;
					
					return "welcome"; //No es admin, pero al estar registrado, manda a la pagina de productos.
				}
				
			} else {
				
				return "home"; 
				
			}
			
		//Si no existe el usuario en la sesion:
		} else {
			
			System.out.println("");
			
		}
		
		
		/*Conexion base de datos:
		 * List<DtoUsuario> listausuarios = new ArrayList<DtoUsuario>();
		listausuarios = dao.leeUsuarios();
		model.addAttribute("ListaUsuarios", listausuarios);*/
		

		return "home";
	}
	
}
