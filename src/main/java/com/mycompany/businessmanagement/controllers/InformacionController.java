package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Informacion;
import com.mycompany.businessmanagement.util.RegexUtil;

public class InformacionController {

    public Informacion crearInformacion(Informacion info) throws Exception {

        // Validaciones usando RegexUtil existentes
        RegexUtil.regexCIF_NIE_NIF(info.getNif());
        RegexUtil.email(info.getEmail());
        RegexUtil.telefonoEspanol(info.getTelefono());

        return info;
    }

    public static InformacionController create() {
        return new InformacionController();
    }
}
