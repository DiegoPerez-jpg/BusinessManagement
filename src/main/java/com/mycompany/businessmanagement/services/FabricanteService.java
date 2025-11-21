/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.businessmanagement.services;
import com.mycompany.businessmanagement.DAOS.FabricanteDAO;
import com.mycompany.businessmanagement.exceptions.FabricanteException;
import com.mycompany.businessmanagement.modelos.Fabricante;
import com.mycompany.businessmanagement.modelos.Informacion;
import com.mycompany.businessmanagement.util.Factory;

import java.util.List;

/**
 *
 * @author alexd
 */
public class FabricanteService{
    private FabricanteDAO fabricanteDAO;
    public FabricanteService(){
        this.fabricanteDAO = new FabricanteDAO();
    }
    
    public Fabricante crearFabricante(Fabricante fabricante) throws FabricanteException{
    if (fabricanteDAO.findByName(fabricante.getNombre())!=null){
        throw new FabricanteException("El fabricante ya existe");
    }
     return fabricanteDAO.insert(fabricante);
    }
    
    public void eliminarFabricante(Fabricante fabricante) throws FabricanteException{
    if (fabricanteDAO.findByName(fabricante.getNombre())==null){
        throw new FabricanteException("El fabricante no existe");
    }
      fabricanteDAO.delete(fabricante.getId());
    }
    
    public void updateFabricante(Fabricante fabricante) throws FabricanteException{
        if (fabricanteDAO.findById(fabricante.getId())==null)throw new FabricanteException("El fabricante no existe");
      fabricanteDAO.update(fabricante);
    }

    public List<Fabricante> selectAll(){
        return fabricanteDAO.findAll();
    }
}
