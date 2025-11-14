package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Entidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntidadDAO {

public Entidad insert(Entidad entity) {
    String sql = "INSERT INTO entidad (codigo, nombre, fk_id_informacion, fk_id_direccion) VALUES (?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setInt(1, entity.getCodigo());
ps.setString(2, entity.getNombre());
ps.setInt(3, entity.getFk_id_informacion());
ps.setInt(4, entity.getFk_id_direccion());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Entidad entidad) {
    String sql = "UPDATE entidad SET codigo = ?, nombre = ?, fk_id_informacion = ?, fk_id_direccion = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, entidad.getCodigo());
        ps.setString(2, entidad.getNombre());
        ps.setInt(3, entidad.getFk_id_informacion());
        ps.setInt(4, entidad.getFk_id_direccion());
        ps.setInt(5, entidad.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM entidad WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Entidad> findAll() {
    List<Entidad> list = new ArrayList<>();
    String sql = "SELECT * FROM entidad";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Entidad(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("fk_id_informacion"), rs.getInt("fk_id_direccion")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Entidad findById(int id) {
    String sql = "SELECT * FROM entidad WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Entidad(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("fk_id_informacion"), rs.getInt("fk_id_direccion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}