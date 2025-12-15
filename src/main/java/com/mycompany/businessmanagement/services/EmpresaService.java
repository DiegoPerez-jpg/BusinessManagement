package com.mycompany.businessmanagement.services;

import com.mycompany.businessmanagement.DAOS.EmpresaDAO;
import com.mycompany.businessmanagement.modelos.Empresa;

import java.sql.Connection;
import java.util.List;

public class EmpresaService {

    private EmpresaDAO empresaDAO = new EmpresaDAO();

    public void eliminarEmpresa(Empresa empresa) {
        empresaDAO.delete(empresa.getId());
    }

    public List<Empresa> selectAll() {
        return empresaDAO.findAll();
    }

    public void crearEmpresa(Empresa empresa, Connection conn) throws IllegalArgumentException {
        if (!empresaDAO.findByAll(null, empresa.getCodigo(), null, null, null, null).isEmpty())
            throw new IllegalArgumentException("El codigo ya existe");
        if (!empresaDAO.findByAll(null, null, empresa.getNombre(), null, null, null).isEmpty())
            throw new IllegalArgumentException("El nombre ya existe");
        if (!empresaDAO.findByAll(null, null, null, empresa.getWeb(), null, null).isEmpty())
            throw new IllegalArgumentException("El web ya existe");
        empresaDAO.insert(empresa,conn);
    }

    public void updateEmpresa(Empresa empresa) throws IllegalArgumentException {
        if (!empresaDAO.findByAll(null, empresa.getCodigo(), null, null, null, null).isEmpty())
            throw new IllegalArgumentException("El codigo ya existe");
        if (!empresaDAO.findByAll(null, null, empresa.getNombre(), null, null, null).isEmpty())
            throw new IllegalArgumentException("El nombre ya existe");
        if (!empresaDAO.findByAll(null, null, null, empresa.getWeb(), null, null).isEmpty())
            throw new IllegalArgumentException("El web ya existe");
        empresaDAO.update(empresa);
    }
}
