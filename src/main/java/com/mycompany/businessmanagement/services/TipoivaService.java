package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.TipoivaDAO;
import com.mycompany.businessmanagement.modelos.Tipoiva;

import java.util.List;

public class TipoivaService {

    private TipoivaDAO tipoivaDAO = new TipoivaDAO();

    public void eliminarTipoiva(Tipoiva tipoiva) {
        tipoivaDAO.delete(tipoiva.getId());
    }

    public List<Tipoiva> selectAll() {
        return tipoivaDAO.findAll();
    }

    public void crearTipoiva(Tipoiva tipoiva) throws IllegalArgumentException {
        tipoivaDAO.insert(tipoiva);
    }

    public void updateTipoiva(Tipoiva tipoiva) throws IllegalArgumentException {
        tipoivaDAO.update(tipoiva);
    }
}
