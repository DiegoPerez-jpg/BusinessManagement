package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

public Factura insert(Factura entity) {
    String sql = "INSERT INTO factura (fk_id_empresa, fk_id_cliente, numero, fecha_emision, fecha_servicio, concepto, base_imponible, iva_total, total_factura, estado, observaciones, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setInt(1, entity.getFk_id_empresa());
ps.setInt(2, entity.getFk_id_cliente());
ps.setString(3, entity.getNumero());
ps.setDate(4, Date.valueOf(entity.getFecha_servicio()));
ps.setDate(5, Date.valueOf(entity.getFecha_servicio()));
ps.setString(6, entity.getConcepto());
ps.setDouble(7, entity.getBase_imponible());
ps.setDouble(8, entity.getIva_total());
ps.setDouble(9, entity.getTotal_factura());
ps.setString(10, entity.getEstado());
ps.setString(11, entity.getObservaciones());
ps.setString(12, entity.getTipo());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Factura factura) {
    String sql = "UPDATE factura SET fk_id_empresa = ?, fk_id_cliente = ?, numero = ?, fecha_emision = ?, fecha_servicio = ?, concepto = ?, base_imponible = ?, iva_total = ?, total_factura = ?, estado = ?, observaciones = ?, tipo = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, factura.getFk_id_empresa());
        ps.setInt(2, factura.getFk_id_cliente());
        ps.setString(3, factura.getNumero());
        ps.setDate(4, Date.valueOf(factura.getFecha_servicio()));
        ps.setDate(5, Date.valueOf(factura.getFecha_servicio()));
        ps.setString(6, factura.getConcepto());
        ps.setDouble(7, factura.getBase_imponible());
        ps.setDouble(8, factura.getIva_total());
        ps.setDouble(9, factura.getTotal_factura());
        ps.setString(10, factura.getEstado());
        ps.setString(11, factura.getObservaciones());
        ps.setString(12, factura.getTipo());
        ps.setInt(13, factura.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM factura WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Factura> findAll() {
    List<Factura> list = new ArrayList<>();
    String sql = "SELECT * FROM factura";
    try (Connection conn = Conexion.getConnection();
         var st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Factura(rs.getInt("id"), rs.getInt("fk_id_empresa"), rs.getInt("fk_id_cliente"), rs.getString("numero"), rs.getDate("fecha_emision").toLocalDate(), rs.getDate("fecha_servicio").toLocalDate(), rs.getString("concepto"), rs.getDouble("base_imponible"), rs.getDouble("iva_total"), rs.getDouble("total_factura"), rs.getString("estado"), rs.getString("observaciones"), rs.getString("tipo")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Factura findById(int id) {
    String sql = "SELECT * FROM factura WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Factura(rs.getInt("id"), rs.getInt("fk_id_empresa"), rs.getInt("fk_id_cliente"), rs.getString("numero"), rs.getDate("fecha_emision").toLocalDate(), rs.getDate("fecha_servicio").toLocalDate(), rs.getString("concepto"), rs.getDouble("base_imponible"), rs.getDouble("iva_total"), rs.getDouble("total_factura"), rs.getString("estado"), rs.getString("observaciones"), rs.getString("tipo"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}