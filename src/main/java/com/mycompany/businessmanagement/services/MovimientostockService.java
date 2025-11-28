package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.MovimientostockDAO;
import com.mycompany.businessmanagement.modelos.Movimientostock;

import java.util.List;

public class MovimientostockService {

    private MovimientostockDAO movimientostockDAO = new MovimientostockDAO();

    public void eliminarMovimientostock(Movimientostock movimientostock) {
        movimientostockDAO.delete(movimientostock.getId());
    }

    public List<Movimientostock> selectAll() {
        return movimientostockDAO.findAll();
    }

    public void crearMovimientostock(Movimientostock movimientostock) throws IllegalArgumentException {
        movimientostockDAO.insert(movimientostock);
    }

    public void updateMovimientostock(Movimientostock movimientostock) throws IllegalArgumentException {
        movimientostockDAO.update(movimientostock);
    }
}
