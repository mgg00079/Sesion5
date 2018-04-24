<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
      width: 100%;
      height: 10%;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
      width: 100%;
      height: 40%;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
      width: 100%;
      height: 20%;
    }
    
    .contenido {
    	width: 100%;
        height: 30%;
    }
    
    .login {
    	
        text-align: center;
        margin-bottom: 20px;
    }
  </style>
</head>
<body>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Online Store</h1>      
    <p>Mission, Vission & Values</p>
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class = "contenido">
	<form class = "login" action="" method="post">
    	Email: </br><input type="text" name="name" size="20" required></br>
        Contraseña: </br><input type="pass" name="pass" size="20" required></br></br>
        <input type="submit" value="Enviar">
    </form>
</div>
<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>  
</footer>

</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
	table th, td{
		text-align: center;
	}
	</style>
	<title>Lista de usuarios:</title>
</head>
<body>
	<h1>Usuarios registrados</h1>
	<table border="1">
		<tr>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Email</th>
			<th>Contraseña</th>
			<th>Telefono</th>
			<th>Codigo Postal</th>
			<th>Direccion</th>
			<th>Admin</th>
		</tr>
		<c:forEach items="${ListaUsuarios}" var="user" varStatus="estado">
			<tr>
				<td><c:out value="${user.nombre}"/></td>
				<td><c:out value="${user.apellidos}"/></td>	
				<td><c:out value="${user.email}"/></td>
				<td><c:out value="${user.contraseña}"/></td>
				<td><c:out value="${user.telefono}"/></td>
				<td><c:out value="${user.cp}"/></td>
				<td><c:out value="${user.direccion}"/></td>
				<td><c:out value="${user.admin}"/></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="index.html">Desconectar</a>
</body>
</html>--%>