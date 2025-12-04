package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Movimientostock;
import com.mycompany.businessmanagement.util.RegexUtil;

public class MovimientostockController {

    public Movimientostock crearMovimiento(Movimientostock movimiento) throws Exception {

        // Validaciones
        RegexUtil.fecha(movimiento.getFecha());
        RegexUtil.decimal(movimiento.getCantidad());

        // Motivo y tipo
        RegexUtil.concepto(movimiento.getMotivo()); // reuso validador de concepto
        RegexUtil.tipoFactura(movimiento.getTipo()); // reuso validador de tipoFactura como ejemplo

        // fk_id_producto no se valida

        return movimiento;
    }

    public static MovimientostockController create() {
        return new MovimientostockController();
    }
}
