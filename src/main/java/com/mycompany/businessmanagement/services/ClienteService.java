package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.ClienteDAO;
import com.mycompany.businessmanagement.DTO.ClienteCompletoDTO;
import com.mycompany.businessmanagement.modelos.Cliente;
import com.mycompany.businessmanagement.modelos.Entidad;

import java.util.List;
import java.util.stream.Collectors;

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

    public <T extends Entidad> List<ClienteCompletoDTO> getAllClientsDto(){
        InformacionService is = new InformacionService();
        DireccionService ds = new DireccionService();
        return selectAll().stream()
                .map(c->new ClienteCompletoDTO(c.getId(),c.getCodigo(),c.getNombre(),is.selectById(c.getFk_id_informacion()),ds.selectById(c.getFk_id_direccion())))
                .collect(Collectors.toList());
    }
}
