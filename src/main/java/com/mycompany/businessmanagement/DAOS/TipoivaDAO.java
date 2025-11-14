package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Tipoiva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoivaDAO {

public Tipoiva insert(Tipoiva entity) {
    String sql = "INSERT INTO tipoiva (concepto, porcentaje) VALUES (?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, entity.getConcepto());
ps.setDouble(2, entity.getPorcentaje());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Tipoiva tipoiva) {
    String sql = "UPDATE tipoiva SET concepto = ?, porcentaje = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, tipoiva.getConcepto());
        ps.setDouble(2, tipoiva.getPorcentaje());
        ps.setInt(3, tipoiva.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM tipoiva WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Tipoiva> findAll() {
    List<Tipoiva> list = new ArrayList<>();
    String sql = "SELECT * FROM tipoiva";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Tipoiva(rs.getInt("id"), rs.getString("concepto"), rs.getDouble("porcentaje")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Tipoiva findById(int id) {
    String sql = "SELECT * FROM tipoiva WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Tipoiva(rs.getInt("id"), rs.getString("concepto"), rs.getDouble("porcentaje"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}