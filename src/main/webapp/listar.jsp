<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 19/6/2024
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.elvis.login.logueo.models.*" %>
<%
List<Producto> productos =(List<Producto>) request.getAttribute("productos");
Optional<String> username =(Optional)request.getAttribute("username");

%>
<html>
<head>
    <title>Listado de Productos</title>
</head>
<body>
    <h1>LISTADO DE PRODUCTOS</h1>
    <%if(username.isPresent()){%>
        <div>Hola <%=username.get()%>,  Bienvenido!</div>
        <p><a href="<%=request.getContextPath()%>/productos/form"> CREAR NUEVO PRODUCTO </a></p>
    <%}%>
    <table>
        <tr>
            <th>id Producto</th>
            <th>Nombre</th>
            <th>Categoria</th>
            <%if (username.isPresent()){%>
                <th>precio</th>
                <th>agregar</th>
                <th>editar</th>
                <th>eliminar</th>
            <% } %>
        </tr>
        <%for (Producto p:productos){%>
            <tr>
                <td><%=p.getIdProducto()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getCategoria().getNombre()%></td>
                <% if (username.isPresent()){%>
                    <td><%=p.getPrecio()%></td>
                    <td><a href="<%=request.getContextPath()%>/agregar-carro?id=<%=p.getIdProducto()%>">Agregar al carro</a></td>
                    <td><a href="<%=request.getContextPath()%>/form?id=<%=p.getIdProducto()%>">Editar</a></td>
                    <td><a onclick="return confirm('Estas seguro que deseas eliminar el producto?');"
                    href="<%=request.getContextPath()%>/eliminar?id=<%=p.getIdProducto()%>">Eliminar</a></td>
                <% } %>
            </tr>
        <%}%>

    </table>



</body>
</html>
