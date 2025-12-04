package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Producto;
import com.mycompany.businessmanagement.util.RegexUtil;

public class ProductoController {

    public Producto crearProducto(Producto producto) throws Exception {

        RegexUtil.codProducto(producto.getCodigo());
        RegexUtil.desc_articulos(producto.getDescripcion());
        if (producto.getDescripcion_aux() != null && !producto.getDescripcion_aux().isBlank()) {
            RegexUtil.desc_articulos(producto.getDescripcion_aux());
        }
        RegexUtil.precioCosto(String.valueOf(producto.getPrecio_coste()));
        RegexUtil.precioCosto(String.valueOf(producto.getPrecio_venta()));
        RegexUtil.refProv(producto.getReferencia_proveedor());
        RegexUtil.decimal(producto.getStock());

        // Los fk_id_* no se validan

        return producto;
    }

    public static ProductoController create() {
        return new ProductoController();
    }
}
