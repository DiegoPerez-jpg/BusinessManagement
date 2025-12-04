package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Direccion;
import com.mycompany.businessmanagement.util.RegexUtil;

public class DireccionController {

    public Direccion crearDireccion(Direccion direccion) throws Exception {

        RegexUtil.direccion(direccion.getDireccion());
        RegexUtil.codigoPostal(direccion.getCodigopostal());
        RegexUtil.ciudad(direccion.getCiudad());
        RegexUtil.provincia(direccion.getProvincia());
        RegexUtil.pais(direccion.getPais());
        RegexUtil.etiqueta(direccion.getEtiqueta());

        return direccion;
    }

    public static DireccionController create() {
        return new DireccionController();
    }
}
