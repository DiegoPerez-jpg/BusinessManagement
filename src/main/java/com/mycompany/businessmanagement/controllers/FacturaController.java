package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Factura;
import com.mycompany.businessmanagement.util.RegexUtil;

public class FacturaController {

    public Factura crearFactura(Factura factura) throws Exception {

        RegexUtil.numFactura(factura.getNumero());
        RegexUtil.fecha(factura.getFecha_emision());
        RegexUtil.fecha(factura.getFecha_servicio());

        RegexUtil.concepto(factura.getConcepto());

        RegexUtil.decimal(factura.getBase_imponible());
        RegexUtil.decimal(factura.getIva_total());
        RegexUtil.decimal(factura.getTotal_factura());

        RegexUtil.estadoFactura(factura.getEstado());
        RegexUtil.textoOpcional(factura.getObservaciones());
        RegexUtil.tipoFactura(factura.getTipo());

        return factura;
    }

    public static FacturaController create() {
        return new FacturaController();
    }
}
