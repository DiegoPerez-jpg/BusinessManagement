package com.mycompany.businessmanagement.controllers;

import com.mycompany.businessmanagement.modelos.Facturadetalle;
import com.mycompany.businessmanagement.util.RegexUtil;

public class FacturaDetalleController {

    public Facturadetalle crearDetalle(Facturadetalle detalle) throws Exception {

        // Validaciones de campos numéricos
        RegexUtil.decimal(detalle.getCantidad());
        RegexUtil.decimal(detalle.getPrecio_unitario());
        RegexUtil.decimal(detalle.getTotal_linea());

        // Los fk_id_* no se validan, según indicación

        return detalle;
    }

    public static FacturaDetalleController create() {
        return new FacturaDetalleController();
    }
}
