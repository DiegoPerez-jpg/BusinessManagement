package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Proveedor;
import com.mycompany.businessmanagement.util.RegexUtil;

public class ProveedorController {

    public Proveedor crearProveedor(Proveedor proveedor) throws Exception {

        RegexUtil.nombre(proveedor.getNombre());

        // Los fk_id_* no se validan

        return proveedor;
    }

    public static ProveedorController create() {
        return new ProveedorController();
    }
}
