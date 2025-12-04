package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Fabricante;
import com.mycompany.businessmanagement.util.RegexUtil;

public class FabricanteController {

    public Fabricante crearFabricante(Fabricante fabricante) throws Exception {

        RegexUtil.fabNombre(fabricante.getNombre());

        return fabricante;
    }

    public static FabricanteController create() {
        return new FabricanteController();
    }
}
