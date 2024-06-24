package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.models.Categoria;
import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.services.ProductoService;
import com.elvis.login.logueo.services.ProductoServiceJdbcImplment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/formulario")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);
        Integer id ;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e) {
            id=0;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id >0){
            Optional<Producto> o = service.porId(id);
            if(o.isPresent()){
                producto = o.get();
            }
        }
        req.setAttribute("categorias", service.listarCategorias());
        req.setAttribute("producto", producto);
        getServletContext().getRequestDispatcher("/producto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtenemos la conexión
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);
        String nombre = req.getParameter("nombre");
        Integer idCategoria;
        try{
            idCategoria = Integer.parseInt(req.getParameter("categoria"));

        } catch (NumberFormatException e) {
            idCategoria=0;
        }

        String descripcion = req.getParameter("descripcion");
        double precio;

        try{
            precio = Double.parseDouble(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio=0;
        }

        Integer idProducto;
        try{
            idProducto = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e) {
            idProducto=0;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre ==null || nombre.isBlank()){
            errores.put("nombre", "El campo nombre debe ser requerido");
        }
        if (descripcion == null || descripcion.isBlank()){
            errores.put("descripcion","El campo descripción es requerido");
        }
        if (precio == 0 || precio < 0 ){
            errores.put("precio","El precio es obligatorio");
        }

        if (idCategoria.equals(0)){
            errores.put("idcategoria", "El campo categoria es obligatiorio");
        }

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        producto.setCategoria(categoria);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        //Si el error esta vacio ahi me va permitir guardar elpoducto
        if (errores.isEmpty()){
            service.guardar(producto);
            resp.sendRedirect("/productos");
        }else{
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategorias());
            req.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/producto.jsp").forward(req,resp);
        }



    }
}
