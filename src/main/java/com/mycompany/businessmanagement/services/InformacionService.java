package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.InformacionDAO;
import com.mycompany.businessmanagement.exceptions.FabricanteException;
import com.mycompany.businessmanagement.exceptions.InformacionException;
import com.mycompany.businessmanagement.modelos.Fabricante;
import com.mycompany.businessmanagement.modelos.Informacion;

import java.util.List;

public class InformacionService {
    InformacionDAO informacionDAO = new InformacionDAO();

    public Informacion crearInformacion(Informacion informacion) throws InformacionException {
        if(!informacionDAO.filterByAll(informacion.getNif(),null,null).isEmpty())throw new InformacionException("El Nif ya existe");
        if(!informacionDAO.filterByAll(null,informacion.getEmail(),null).isEmpty())throw new InformacionException("El email ya existe");
        if(!informacionDAO.filterByAll(null,null,informacion.getTelefono()).isEmpty())throw new InformacionException("El telefono ya existe");

        return informacionDAO.insert(informacion);
    }

    public List<Informacion> selectAll(){
        return informacionDAO.findAll();
    }
    public void eliminarInformacion(Informacion informacion) throws InformacionException{
        if(informacionDAO.filterByAll(informacion.getNif(),null,null).isEmpty())throw new InformacionException("El Nif no ya existe");
        if(informacionDAO.filterByAll(null,informacion.getEmail(),null).isEmpty())throw new InformacionException("El email no ya existe");
        if(informacionDAO.filterByAll(null,null,informacion.getTelefono()).isEmpty())throw new InformacionException("El telefono no ya existe");

        informacionDAO.delete(informacion.getId());
    }

    public void updateInformacion(Informacion informacion) throws InformacionException{
        if (informacionDAO.findById(informacion.getId())==null)throw new InformacionException("El objeto no existe");
        informacionDAO.update(informacion);
    }
}
