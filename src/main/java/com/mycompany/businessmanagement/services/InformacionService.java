package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.InformacionDAO;
import com.mycompany.businessmanagement.modelos.Informacion;

import java.sql.Connection;
import java.util.List;

public class InformacionService {

    private InformacionDAO informacionDAO = new InformacionDAO();

    public void eliminarInformacion(Informacion informacion) {
        informacionDAO.delete(informacion.getId());
    }

    public List<Informacion> selectAll() {
        return informacionDAO.findAll();
    }

    public Informacion selectById(int id) {
        return informacionDAO.findById(id);
    }

    public void crearInformacion(Informacion informacion, Connection conn) throws IllegalArgumentException {
        if (!informacionDAO.findByAll(null, informacion.getNif(), null, null).isEmpty())
            throw new IllegalArgumentException("El nif ya existe");
        if (!informacionDAO.findByAll(null, null, informacion.getEmail(), null).isEmpty())
            throw new IllegalArgumentException("El email ya existe");
        if (!informacionDAO.findByAll(null, null, null, informacion.getTelefono()).isEmpty())
            throw new IllegalArgumentException("El telefono ya existe");
        informacionDAO.insert(informacion, conn);
    }

    public void updateInformacion(Informacion informacion) throws IllegalArgumentException {
        if (!informacionDAO.findByAll(null, informacion.getNif(), null, null).isEmpty())
            throw new IllegalArgumentException("El nif ya existe");
        if (!informacionDAO.findByAll(null, null, informacion.getEmail(), null).isEmpty())
            throw new IllegalArgumentException("El email ya existe");
        if (!informacionDAO.findByAll(null, null, null, informacion.getTelefono()).isEmpty())
            throw new IllegalArgumentException("El telefono ya existe");
        informacionDAO.update(informacion);
    }
}
