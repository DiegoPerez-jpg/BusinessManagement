package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.DireccionDAO;
import com.mycompany.businessmanagement.exceptions.DireccionException;
import com.mycompany.businessmanagement.modelos.Direccion;

import java.util.List;

public class FacturaService {
    private DireccionDAO direccionDAO =  new DireccionDAO();

    public Direccion crearDireccion(Direccion direccion){
        return direccionDAO.insert(direccion);
    }

    public void eliminarDireccion(Direccion direccion) {
        direccionDAO.delete(direccion.getId());
    }

    public void updateDireccion(Direccion direccion) throws DireccionException {
        if (direccionDAO.findById(direccion.getId())==null)throw new DireccionException("El direccion no existe");
        direccionDAO.update(direccion);
    }
    public List<Direccion> selectAll(){
        return direccionDAO.findAll();
    }
}
