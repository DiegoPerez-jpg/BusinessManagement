package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Empresa;
import com.mycompany.businessmanagement.util.RegexUtil;

public class EmpresaController {

    public Empresa crearEmpresa(Empresa empresa) throws Exception {

        RegexUtil.nombre(empresa.getNombre());
        RegexUtil.web(empresa.getWeb());

        return empresa;
    }

    public static EmpresaController create() {
        return new EmpresaController();
    }
}
