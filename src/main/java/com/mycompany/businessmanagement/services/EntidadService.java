package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.EntidadDAO;
import com.mycompany.businessmanagement.DTO.ClienteCompletoDTO;
import com.mycompany.businessmanagement.modelos.Entidad;

import java.util.List;
import java.util.stream.Collectors;

public class EntidadService {

    private EntidadDAO entidadDAO = new EntidadDAO();

    public void eliminarEntidad(Entidad entidad) {
        entidadDAO.delete(entidad.getId());
    }

    public <T> List<Entidad> selectAll(Class<T> clazz) {
        return entidadDAO.findAll(clazz);
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

    public <T extends Entidad> List<ClienteCompletoDTO> getAllClientsDto(Class<T> clazz) {
        InformacionService is = new InformacionService();
        DireccionService ds = new DireccionService();
        return selectAll(clazz).stream()
                .map(c->new ClienteCompletoDTO(c.getId(),c.getCodigo(),c.getNombre(),is.selectById(c.getFk_id_informacion()),ds.selectById(c.getFk_id_direccion())))
                .collect(Collectors.toList());
    }
}
