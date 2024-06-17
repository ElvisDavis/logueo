package com.elvis.login.logueo.repositories;

import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.models.Categoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery("SELECT p.*, c.nombre as categoria FROM producto as p " +
                     "inner join categoria as c ON (p.categoria_id = c.id) order by p.id ASC")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }

        return productos;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idcategoria"));
        return p;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }

    @Override
    public Producto activar(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Producto desactivar(Integer id) throws SQLException {
        return null;
    }
}
