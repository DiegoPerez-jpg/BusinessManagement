package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.EntidadDAO;
import com.mycompany.businessmanagement.modelos.Entidad;

import java.util.List;

public class EntidadService {

    private EntidadDAO entidadDAO = new EntidadDAO();

    public void eliminarEntidad(Entidad entidad) {
        entidadDAO.delete(entidad.getId());
    }

    public List<Entidad> selectAll() {
        return entidadDAO.findAll();
    }

    public void crearEntidad(Entidad entidad) throws IllegalArgumentException {
        if (!entidadDAO.findByAll(null, entidad.getCodigo(), null, null, null).isEmpty())
            throw new IllegalArgumentException("El codigo ya existe");
        if (!entidadDAO.findByAll(null, null, entidad.getNombre(), null, null).isEmpty())
            throw new IllegalArgumentException("El nombre ya existe");
        entidadDAO.insert(entidad);
    }

    public void updateEntidad(Entidad entidad) throws IllegalArgumentException {
        if (!entidadDAO.findByAll(null, entidad.getCodigo(), null, null, null).isEmpty())
            throw new IllegalArgumentException("El codigo ya existe");
        if (!entidadDAO.findByAll(null, null, entidad.getNombre(), null, null).isEmpty())
            throw new IllegalArgumentException("El nombre ya existe");
        entidadDAO.update(entidad);
    }
}
