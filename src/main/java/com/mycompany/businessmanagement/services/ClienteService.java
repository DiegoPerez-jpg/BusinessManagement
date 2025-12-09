package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.ClienteDAO;
import com.mycompany.businessmanagement.modelos.Cliente;

import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public void eliminarCliente(Cliente cliente) {
        clienteDAO.delete(cliente.getId());
    }

    public List<Cliente> selectAll() {
        return clienteDAO.findAll();
    }

    public void crearCliente(Cliente cliente) throws IllegalArgumentException {
        clienteDAO.insert(cliente);
    }

    public void updateCliente(Cliente cliente) throws IllegalArgumentException {
        clienteDAO.update(cliente);
    }
    public boolean existeCliente(int id){
        return clienteDAO.findById(id) != null;
    }
}
