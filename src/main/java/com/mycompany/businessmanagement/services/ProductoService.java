package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.ProductoDAO;
import com.mycompany.businessmanagement.modelos.Producto;

import java.util.List;

public class ProductoService {

    private ProductoDAO productoDAO = new ProductoDAO();

    public void eliminarProducto(Producto producto) {
        productoDAO.delete(producto.getId());
    }

    public List<Producto> selectAll() {
        return productoDAO.findAll();
    }

    public void crearProducto(Producto producto) throws IllegalArgumentException {
        if (!productoDAO.findByAll(null, producto.getCodigo(), null, null, null, null, null, null, null, null, null).isEmpty())
            throw new IllegalArgumentException("El codigo ya existe");
        productoDAO.insert(producto);
    }

    public void updateProducto(Producto producto) throws IllegalArgumentException {
        if (!productoDAO.findByAll(null, producto.getCodigo(), null, null, null, null, null, null, null, null, null).isEmpty())
            throw new IllegalArgumentException("El codigo ya existe");
        productoDAO.update(producto);
    }
}
