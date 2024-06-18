package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.repositories.ProductoRepositoryJdbcImpl;
import com.elvis.login.logueo.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet","/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn= (Connection) req.getAttribute("conn");
        ProductoService service= new ProductoServiceJdbcImplment(conn);
        List<Producto> productos = service.listar();
        LoginService auth = new LoginServiceImplement();
        Optional<String> usernameOptional= auth.getUserName(req);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<meta charset=\"UTF-8\">");
            out.print("<title>Listado de productos</title>");
            out.print("</head>");
            out.println("<body>");
            out.print("<h1>Listado de productos</h1>");
            if (usernameOptional.isPresent()){
                out.print("<div style='color:blue;' > HOLA " + usernameOptional.get() + " Bienvenido!</div>");
            }
            out.print("<table>");
            out.print("<tr>");
            out.print("<th>id</th>");
            out.print("<th>nombre</th>");
            out.print("<th>categoria</th>");
            out.print("<th>descripción</th>");
            if (usernameOptional.isPresent()) {
                out.print("<th>valor</th>");
                out.print("<th>agregar</th>");
            }
            out.print("</tr>");
            productos.forEach(p ->{
                out.print("<tr>");
                out.print("<td>"+ p.getIdProducto()+"</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getCategoria() + "</td>");
                out.print("<td>" + p.getDescripcion() + "</td>");
                if(usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td><a href=\""
                            + req.getContextPath()
                            +"/agregar-carro?id=" + p.getIdProducto()
                            +"\">agrega al carro</a></td>");

                }
                out.println("</tr>");
            });

            out.print("</table>");
            out.print("");
            out.print("");
            out.println("</body>");
            out.print("</html>");
        }
    }
}
