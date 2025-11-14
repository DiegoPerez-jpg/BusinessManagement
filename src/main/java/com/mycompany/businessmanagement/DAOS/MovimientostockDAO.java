package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Movimientostock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientostockDAO {

public Movimientostock insert(Movimientostock entity) {
    String sql = "INSERT INTO movimientostock (fk_id_producto, fecha, cantidad, motivo, tipo) VALUES (?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setInt(1, entity.getFk_id_producto());
ps.setDate(2, Date.valueOf(entity.getFecha()));
ps.setDouble(3, entity.getCantidad());
ps.setString(4, entity.getMotivo());
ps.setString(5, entity.getTipo());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Movimientostock movimientostock) {
    String sql = "UPDATE movimientostock SET fk_id_producto = ?, fecha = ?, cantidad = ?, motivo = ?, tipo = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, movimientostock.getFk_id_producto());
        ps.setDate(2, Date.valueOf(movimientostock.getFecha()));
        ps.setDouble(3, movimientostock.getCantidad());
        ps.setString(4, movimientostock.getMotivo());
        ps.setString(5, movimientostock.getTipo());
        ps.setInt(6, movimientostock.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM movimientostock WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Movimientostock> findAll() {
    List<Movimientostock> list = new ArrayList<>();
    String sql = "SELECT * FROM movimientostock";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Movimientostock(rs.getInt("id"), rs.getInt("fk_id_producto"), rs.getDate("fecha").toLocalDate(), rs.getDouble("cantidad"), rs.getString("motivo"), rs.getString("tipo")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Movimientostock findById(int id) {
    String sql = "SELECT * FROM movimientostock WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Movimientostock(rs.getInt("id"), rs.getInt("fk_id_producto"), rs.getDate("fecha").toLocalDate(), rs.getDouble("cantidad"), rs.getString("motivo"), rs.getString("tipo"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}