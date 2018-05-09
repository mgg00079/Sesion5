<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<form class = "home" action="" method="post">
    	Email: </br><input type="text" name="name" size="20" required></br>
        Contraseña: </br><input type="pass" name="pass" size="20" required></br></br>
        <input type="submit" value="Enviar">
</form>

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