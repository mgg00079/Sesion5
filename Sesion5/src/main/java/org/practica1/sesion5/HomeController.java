package org.practica1.sesion5;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		DtoUsuario user;
		Boolean siadmin; //Si es administrador.
		Boolean siuser;
		
		DtoUsuario userDB = null;
		
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
		
		} else { //Si no existe sesión
			
			Cookie [] cookies = request.getCookies();
			
			if(cookies!=null) { //Si existen las cookies, las recorremos con un bucle for hasta que encontramos el email.
				for(Cookie cookie: cookies) {
					if("email".equals(cookie.getName())) {
						System.out.println("Existen cookies");
						
						cookie.setMaxAge(60*60); //Tiempo de la cookie: 1 hora.
						
						String cookievalue = cookie.getValue(); //Obtenemos el valor de email.
						Boolean existemail = dao.existeEmail(cookievalue);
						
						if(existemail) {
							
							System.out.println("Existe usuario de cookies");
							userDB = dao.extraerUser(cookievalue); //Extraemos el objeto de Usuario.
							model.addAttribute("usuario", userDB); //Lo enviamos al jsp.
							
							DtoUsuario usuario = dao.extraerUser(cookievalue);
							
							if(usuario.getAdmin()) {
								siadmin = true;
								System.out.println("El usuario es admin");
							} else if (!usuario.getAdmin()) {
								siuser = true;
								
								try {
									
									session = request.getSession(false); //Obtiene la sesión de la petición y si no existe, da error.
									DtoUsuario usuario1 = (DtoUsuario) session.getAttribute("usuario"); //Extraemos el objeto usuario de la sesión.
									System.out.println("Hay sesion");
									System.out.println("Nombre: " + usuario1.getNombre()); //Mostramos el nombre del usuario. 
									
									return "welcome";
									
								}catch(NullPointerException e){ //Si no hay sesión.
									
									System.out.println("No hay sesión");
									
									SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
									String date = DATE_FORMAT.format(today);
									System.out.println("Conexion: " + date);
									
									session = request.getSession(true); //Creamos la sesión.
									session.setAttribute ("usuario", userDB); //Introducimos el usuario en la sesión.
									session.setAttribute("date", date);
									
									session.setAttribute("lista", listaCompra);
									Double total = 0.0;
									session.setAttribute("total", total);
									session.setMaxInactiveInterval(60*60);
									
									model.addAttribute("lista", listaCompra);
									
									return "welcome";
									
								}
							}						
							
							response.addCookie(cookie); //Cookie de respuesta.
							model.addAttribute("usuario", userDB); //Enviamos el usuario al jsp.
							
						} else {
							System.out.println("No existe usuario en la cookie");
						}
					}
				}
				
				
				
				
			}
			
		//Si no existe el usuario en la sesion:
		} else {
			
			System.out.println("Este usuario no existe");
			
		}
		
		
		/*Conexion base de datos:
		 * List<DtoUsuario> listausuarios = new ArrayList<DtoUsuario>();
		listausuarios = dao.leeUsuarios();
		model.addAttribute("ListaUsuarios", listausuarios);*/
		

		return "home";
	}
	
}
