package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Fabricante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricanteDAO {

public Fabricante insert(Fabricante entity) {
    String sql = "INSERT INTO fabricante (nombre) VALUES (?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, entity.getNombre());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Fabricante fabricante) {
    String sql = "UPDATE fabricante SET nombre = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, fabricante.getNombre());
        ps.setInt(2, fabricante.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM fabricante WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Fabricante> findAll() {
    List<Fabricante> list = new ArrayList<>();
    String sql = "SELECT * FROM fabricante";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Fabricante(rs.getInt("id"), rs.getString("nombre")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Fabricante findById(int id) {
    String sql = "SELECT * FROM fabricante WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Fabricante(rs.getInt("id"), rs.getString("nombre"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}