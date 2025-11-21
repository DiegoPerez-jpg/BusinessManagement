package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

public Empresa insert(Empresa entity) {
    String sql = "INSERT INTO empresa (codigo, nombre, web, fk_id_direccion, fk_id_informacion) VALUES (?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setInt(1, entity.getCodigo());
ps.setString(2, entity.getNombre());
ps.setString(3, entity.getWeb());
ps.setInt(4, entity.getFk_id_direccion());
ps.setInt(5, entity.getFk_id_informacion());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Empresa empresa) {
    String sql = "UPDATE empresa SET codigo = ?, nombre = ?, web = ?, fk_id_direccion = ?, fk_id_informacion = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, empresa.getCodigo());
        ps.setString(2, empresa.getNombre());
        ps.setString(3, empresa.getWeb());
        ps.setInt(4, empresa.getFk_id_direccion());
        ps.setInt(5, empresa.getFk_id_informacion());
        ps.setInt(6, empresa.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM empresa WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Empresa> findAll() {
    List<Empresa> list = new ArrayList<>();
    String sql = "SELECT * FROM empresa";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Empresa(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getString("web"), rs.getInt("fk_id_direccion"), rs.getInt("fk_id_informacion")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Empresa findById(int id) {
    String sql = "SELECT * FROM empresa WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Empresa(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getString("web"), rs.getInt("fk_id_direccion"), rs.getInt("fk_id_informacion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public Empresa findByName(String name) {
        String sql = "SELECT * FROM empresa WHERE name = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Empresa(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getString("web"), rs.getInt("fk_id_direccion"), rs.getInt("fk_id_informacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}