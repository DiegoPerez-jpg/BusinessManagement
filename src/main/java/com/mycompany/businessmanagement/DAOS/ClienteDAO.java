package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

public Cliente insert(Cliente entity) {
    String sql = "INSERT INTO cliente (id) VALUES (?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setLong(1, entity.getId());
        ps.executeUpdate();
        return entity;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Cliente cliente) {
    String sql = "UPDATE cliente SET  WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, cliente.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM cliente WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Cliente> findAll() {
    List<Cliente> list = new ArrayList<>();
    String sql = "SELECT * FROM cliente";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Cliente(rs.getInt("id")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Cliente findById(int id) {
    String sql = "SELECT * FROM cliente WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Cliente(rs.getInt("id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}