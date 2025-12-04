package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Tipoiva;
import com.mycompany.businessmanagement.util.RegexUtil;

public class TipoivaController {

    public Tipoiva crearTipoiva(Tipoiva tipoiva) throws Exception {

        RegexUtil.concepto(tipoiva.getConcepto());
        RegexUtil.decimal(tipoiva.getPorcentaje());

        return tipoiva;
    }

    public static TipoivaController create() {
        return new TipoivaController();
    }
}
