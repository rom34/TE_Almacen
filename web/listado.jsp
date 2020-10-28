<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>  <b>Productos</h1>
        <p><a href="Inicio?action=add">Nuevo Producto</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <c:forEach var="item" items="${productos}">
            <tr>
                <td>${item.id}</td>
                <td>${item.nombre_producto}</td>
                <td>${item.precio}</td>
                <td>${item.stock}</td> 
                <td><a href="Inicio?action=edit&id=${item.id}">Modificar</a></td>
                <td><a href="Inicio?action=delete&id=${item.id}">Eliminar</a></td>       
            </tr>
            </c:forEach>
        </table>
    </body>
</html>

