package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.DireccionDAO;
import com.mycompany.businessmanagement.modelos.Direccion;

import java.util.List;

public class DireccionService {

    private DireccionDAO direccionDAO = new DireccionDAO();

    public void eliminarDireccion(Direccion direccion) {
        direccionDAO.delete(direccion.getId());
    }

    public List<Direccion> selectAll() {
        return direccionDAO.findAll();
    }

    public void crearDireccion(Direccion direccion) throws IllegalArgumentException {
        direccionDAO.insert(direccion);
    }

    public void updateDireccion(Direccion direccion) throws IllegalArgumentException {
        direccionDAO.update(direccion);
    }

    public Direccion selectById(int id) {
        return direccionDAO.findById(id);
    }
}
