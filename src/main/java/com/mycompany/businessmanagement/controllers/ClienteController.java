package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Cliente;
import com.mycompany.businessmanagement.util.RegexUtil;

public class ClienteController {

    public Cliente crearCliente(Cliente cliente) throws Exception {

        RegexUtil.nombre(cliente.getNombre());

        // Los fk_id_* no se validan

        return cliente;
    }

    public static ClienteController create() {
        return new ClienteController();
    }
}
