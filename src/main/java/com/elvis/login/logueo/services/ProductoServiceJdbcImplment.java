package com.elvis.login.logueo.services;

import com.elvis.login.logueo.models.Categoria;
import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.repositories.CategoriaRepositoryJdbImplment;
import com.elvis.login.logueo.repositories.ProductoRepositoryJdbcImpl;
import com.elvis.login.logueo.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplment implements ProductoService {
    private Repository<Producto> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;


    public ProductoServiceJdbcImplment(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryJdbImplment(connection);

    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Integer id) {
       try {
           return Optional.ofNullable(repositoryJdbc.porId(id));
       } catch (SQLException throwables) {
           throw  new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
       }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }

    @Override
    public void eliminar(Integer id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }

    }

    @Override
    public List<Categoria> listarCategorias() {
        try{
            return  repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }


}
