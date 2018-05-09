package org.practica1.sesion5;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private DaoUsuarioInterface daou;
	private DaoProductoInterface daop;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		// Fecha del sistema
		Date today = new Date();
		// Almacena datos de usuario
		DtoUsuario user;
		// Lista de productos inicializada
		List<DtoProducto> listaCompra = null;
		
		// Usuario que realizo la peticion inicializado
		DtoUsuario userDB = null;
		
		// Tipo de usuario, variables inicializadas
		Boolean siadmin = false;
		Boolean siuser = false;
		
		// Control de cookies
		Boolean haycookies = false;
		
		
		// Aviso de errores
		String error = null; //Por defecto sin errores.
		
		
		// Objeto sesion que devuelve null si no existe sesion en la peticion
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
			Boolean existeemail = daou.existeEmail(user.getEmail());
			
			if(existeemail) {
				
				if(user.getAdmin()) {
					
					siadmin = true;
					model.addAttribute("ListaUsuarios", daou.leeUsuarios());
					
					return "admin"; //Si es administrador, lleva a la pagina donde se muestra los datos de los usuarios.
					
				} else {
					
					siuser = true;
					
					return "welcome"; //No es admin, pero al estar registrado, manda a la pagina de productos.
				}
				
			} else {
				
				System.out.println("No existe usuario de sesión en base de datos");
				return "home"; 
				
			}
		
		} else { //Si no existe sesión
			
			System.out.println("NO Existe sesión");
			
			Cookie [] cookies = request.getCookies();
			
			if(cookies!=null) { //Si existen las cookies, las recorremos con un bucle for hasta que encontramos el email.
				for(Cookie cookie: cookies) {
					if("email".equals(cookie.getName())) {
						System.out.println("Existen cookies");
						
						cookie.setMaxAge(60*60); //Tiempo de la cookie: 1 hora.
						
						String cookiEmail = cookie.getValue(); //Obtenemos el valor de email.
						Boolean existemail = daou.existeEmail(cookiEmail);
						
						if(existemail) {
							
							System.out.println("Existe usuario de cookies");
							userDB = daou.extraerUser(cookiEmail); //Extraemos el objeto de Usuario.
							model.addAttribute("usuario", userDB); //Lo enviamos al jsp.
							
							//DtoUsuario usuario = daou.extraerUser(cookiEmail);
							
							if(userDB.getAdmin()) {
								siadmin = true;
								System.out.println("El usuario es admin");
							} else if (!userDB.getAdmin()) {
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
				
				if(siadmin == false && siuser == false) {
					// No hay cookies
					System.out.println("NO Existen cookies");
					
					haycookies=false;
				}				
			}
			
			if(cookies == null || haycookies == false) {
				// Peticion sin parametros
				if(request.getParameter("useremail") == null || request.getParameter("pass") == null) {
					return "home";
				}else {
				// Peticion con parametros
					String user_email = request.getParameter("email");
					String user_pass = request.getParameter("pass");
					
					System.out.println("email = " + user_email + " pass = " + user_pass);
					
					Boolean existeUsu = daou.existeEmail(user_email);
					
					if(!existeUsu) {
						System.out.println("Error en el usuario o no existe");
						
						error = "Error en el usuario o no existe";
						model.addAttribute("error", error);
						return "home";
					}else {
						System.out.println("Usuario existe");
						
						userDB = daou.extraerUser(user_email);
						
						if(!userDB.getContraseña().equals(user_pass)) {
							System.out.println("Error en la pass.");
							
							error = "Contrase&ntilde;a err&oacute;rena";
							model.addAttribute("error", error);
							return "home";
						}else {
							// Creamos la cookie
							System.out.println("Creamos cookie y sesion");
							Cookie cookie = new Cookie("email", userDB.getEmail());
							// Tiempo de la cookie 1 dia
							cookie.setMaxAge(60*60*24);
							
							// Si el usuario es administrador
							if(userDB.getAdmin()) {
								siadmin = true;
							}else {
								siuser = true;
								model.addAttribute("usuario", userDB);
							}
							
							response.addCookie(cookie);							
						}
					}
				}
			}
		} 
		
		if(siadmin) {
			
			//Creamos la sesion
			session = request.getSession(true);
			
			//Obtenemos fecha de creación de la sesión
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:SS");
        	String date = DATE_FORMAT.format(today);//Realizamos el formateo de la fecha.
        	
        	//Añadimos el usuario en la sesión
			session.setAttribute ("usuario",userDB);
			//Añadimos la fecha en la sesión
			session.setAttribute("date", date);
			//Establecemos la expiración de la sesión a 60 minutos
			session.setMaxInactiveInterval(30*60);
			
			// Obtenemos la lista de usuarios y la agregamos a la vista
			model.addAttribute("listaUsuario", daou.leeUsuarios());
			
			return "admin";
			
		}else if(siuser){
			try {
				session = request.getSession(true);
				
				DtoUsuario usuario = (DtoUsuario) session.getAttribute("usuario");
				
				System.out.println("Nombre: "+usuario.getNombre());
				
				listaCompra = (List<DtoProducto>) session.getAttribute("lista");
				
				return "welcome";
				
			}catch(NullPointerException e) {
								
				session = request.getSession(true);
				
				//Obtenemos fecha de creación de la sesión
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:SS");
		    	String date = DATE_FORMAT.format(today);
		    	System.out.println("Conexion: " + date);
		    	//Añadimos la fecha en la sesion
				session.setAttribute("date", date);
				
				//Añadimos el usuario en la sesión
				session.setAttribute ("usuario",userDB);
				//Establecemos la lista de productos a null
				session.setAttribute("lista", listaCompra);
				//Inicializamos el total de la sesión.
				Double total=0.0;
				session.setAttribute("total", total);
				//Establecemos la expiración de la sesión a 1 hora
				session.setMaxInactiveInterval(60*60);
				
				model.addAttribute("lista", listaCompra);
				
				return "welcome";
			}
		}
		
		return "home";
	}
	
}
