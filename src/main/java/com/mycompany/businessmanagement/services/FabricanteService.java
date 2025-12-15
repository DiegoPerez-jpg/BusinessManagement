package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.FabricanteDAO;
import com.mycompany.businessmanagement.DTO.ClienteCompletoDTO;
import com.mycompany.businessmanagement.modelos.Fabricante;

import java.util.List;
import java.util.stream.Collectors;

public class FabricanteService {

    private FabricanteDAO fabricanteDAO = new FabricanteDAO();

    public void eliminarFabricante(Fabricante fabricante) {
        fabricanteDAO.delete(fabricante.getId());
    }

    public List<Fabricante> selectAll() {
        return fabricanteDAO.findAll();
    }

    public void crearFabricante(Fabricante fabricante) throws IllegalArgumentException {
        if (!fabricanteDAO.findByAll(null, fabricante.getNombre()).isEmpty())
            throw new IllegalArgumentException("El nombre ya existe");
        fabricanteDAO.insert(fabricante);
    }

    public void updateFabricante(Fabricante fabricante) throws IllegalArgumentException {
        if (!fabricanteDAO.findByAll(null, fabricante.getNombre()).isEmpty())
            throw new IllegalArgumentException("El nombre ya existe");
        fabricanteDAO.update(fabricante);
    }


    public List<ClienteCompletoDTO> getClienteDto(){
        return fabricanteDAO.findAll().stream().map(f->new ClienteCompletoDTO(f.getId(),0, f.getNombre(),null,null)).collect(Collectors.toList());
    }
    public boolean existeFabricante(int id){
        return fabricanteDAO.findById(id) != null;
    }
}
