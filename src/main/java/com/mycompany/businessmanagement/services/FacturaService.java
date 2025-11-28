package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.FacturaDAO;
import com.mycompany.businessmanagement.modelos.Factura;

import java.util.List;

public class FacturaService {

    private FacturaDAO facturaDAO = new FacturaDAO();

    public void eliminarFactura(Factura factura) {
        facturaDAO.delete(factura.getId());
    }

    public List<Factura> selectAll() {
        return facturaDAO.findAll();
    }

    public void crearFactura(Factura factura) throws IllegalArgumentException {
        facturaDAO.insert(factura);
    }

    public void updateFactura(Factura factura) throws IllegalArgumentException {
        facturaDAO.update(factura);
    }
}
