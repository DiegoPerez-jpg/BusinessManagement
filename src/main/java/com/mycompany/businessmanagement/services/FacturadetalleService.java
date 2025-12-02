package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.FacturadetalleDAO;
import com.mycompany.businessmanagement.modelos.Facturadetalle;

import java.util.List;

public class FacturadetalleService {

    private FacturadetalleDAO facturadetalleDAO = new FacturadetalleDAO();

    public void eliminarFacturadetalle(Facturadetalle facturadetalle) {
        facturadetalleDAO.delete(facturadetalle.getFk_id_producto(), facturadetalle.getFk_id_factura());
    }

    public List<Facturadetalle> selectAll() {
        return facturadetalleDAO.findAll();
    }

    public void crearFacturadetalle(Facturadetalle facturadetalle) throws IllegalArgumentException {
        facturadetalleDAO.insert(facturadetalle);
    }

    public void updateFacturadetalle(Facturadetalle facturadetalle) throws IllegalArgumentException {
        facturadetalleDAO.update(facturadetalle);
    }
}
