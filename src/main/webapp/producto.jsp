
<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 20/6/2024
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.elvis.login.logueo.models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto=(Producto)request.getAttribute("producto");
%>

<html>
    <head>
        <title>Formulario producto</title>
    </head>
    <body>
        <h1>Formulario Poducto</h1>
        <form action="<%=request.getContextPath()%>/formulario" method="post">
            <div>
                <label for="nombre">Ingrese el nombre del producto:</label>
                <div>
                    <input type="text" name="nombre" id="nombre" value="">
                </div>
                <% if (errores!=null && errores.containsKey("nombre")) { %>
                    <div style="color: red;" <%=errores.get("nombre")%>></div>
                <%}%>

            </div>
            <div>
                <label for="categoria">Categoria</label>
                <div>
                    <select name="categoria" id="categoria">
                        <option value="">------Seleccionar-------</option>
                        <%for (Categoria c: categorias){%>
                            <option value="<%=c.getIdCategoria()%>"><%=c.getNombre()%></option>
                        <%}%>

                    </select>
                </div>

            </div>
            
            <div>
                <label for="precio">precio</label>
                <div>
                    <input type="number" name="precio" id="precio" step="0.01" value="">
                </div>

            </div>
            
            <div>
                <label for="descripcion">Ingrese la descripción</label>
                <div>
                    <textarea name="descripcion" id="descripcion" cols="30" rows="10" value=""></textarea>
                </div>

            </div>

            <div><input type="submit" value="<%=(producto.getIdProducto()!=null && producto.getIdProducto()>0 ? "Editar" : "Crear")%>"></div>
            <input type="hidden" name="id" value="<%=producto.getIdProducto()%>">
        </form>



    </body>
</html>
