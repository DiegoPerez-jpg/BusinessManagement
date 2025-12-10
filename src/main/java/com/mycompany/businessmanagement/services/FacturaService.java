package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.FacturaDAO;
import com.mycompany.businessmanagement.DTO.FacturaDetalleDTO;
import com.mycompany.businessmanagement.modelos.Factura;
import com.mycompany.businessmanagement.modelos.Facturadetalle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<FacturaDetalleDTO> getFacturasCompletasDto(){
        List<Factura> listaFacturas = selectAll();
        List<Facturadetalle> listaFacturaDetalle = new FacturadetalleService().selectAll();

        return listaFacturaDetalle.stream().map(e->{
                Factura factura = listaFacturas.stream().filter(l->l.getId() == e.getFk_id_factura()).findFirst().orElse(null);
                if(factura==null){return null;}
            return new FacturaDetalleDTO(
                    // ---- datos de Factura ----
                    factura.getId(),
                    factura.getFk_id_empresa(),
                    factura.getFk_id_cliente(),
                    factura.getNumero(),
                    factura.getFecha_emision(),
                    factura.getFecha_servicio(),
                    factura.getConcepto(),
                    factura.getBase_imponible(),
                    factura.getIva_total(),
                    factura.getTotal_factura(),
                    factura.getEstado(),
                    factura.getObservaciones(),
                    factura.getTipo(),

                    // ---- datos de Facturadetalle ----
                    e.getFk_id_factura(),
                    e.getFk_id_producto(),
                    e.getCantidad(),
                    e.getPrecio_unitario(),
                    e.getTotal_linea()
            );
        }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
