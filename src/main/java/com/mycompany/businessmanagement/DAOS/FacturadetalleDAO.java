package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Facturadetalle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturadetalleDAO {



    public void insert(Facturadetalle entity) {
    String sql = "INSERT INTO facturadetalle (fk_id_factura,fk_id_producto, cantidad, precio_unitario, total_linea) VALUES (?, ?, ?, ?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, entity.getFk_id_factura());
        ps.setInt(2, entity.getFk_id_producto());
ps.setDouble(3, entity.getCantidad());
ps.setDouble(4, entity.getPrecio_unitario());
ps.setDouble(5, entity.getTotal_linea());        ps.executeUpdate();
} catch (SQLException e) {
        e.printStackTrace();
    }
}


//public void update(Facturadetalle facturadetalle) {
//    String sql = "UPDATE facturadetalle SET fk_id_factura = ?, fk_id_producto = ?, cantidad = ?, precio_unitario = ?, total_linea = ? WHERE id = ?";
//    try (Connection conn = Conexion.getConnection();
//         PreparedStatement ps = conn.prepareStatement(sql)) {
//        ps.setInt(1, facturadetalle.getFk_id_factura());
//        ps.setInt(2, facturadetalle.getFk_id_producto());
//        ps.setDouble(3, facturadetalle.getCantidad());
//        ps.setDouble(4, facturadetalle.getPrecio_unitario());
//        ps.setDouble(5, facturadetalle.getTotal_linea());
//        ps.setInt(6, facturadetalle.getId());
//        ps.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}


public void delete(int id) {
    String sql = "DELETE FROM facturadetalle WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Facturadetalle> findAll() {
    List<Facturadetalle> list = new ArrayList<>();
    String sql = "SELECT * FROM facturadetalle";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Facturadetalle(rs.getInt("fk_id_factura"), rs.getInt("fk_id_producto"), rs.getDouble("cantidad"), rs.getDouble("precio_unitario"), rs.getDouble("total_linea")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Facturadetalle findById(int id) {
    String sql = "SELECT * FROM facturadetalle WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Facturadetalle(rs.getInt("fk_id_factura"), rs.getInt("fk_id_producto"), rs.getDouble("cantidad"), rs.getDouble("precio_unitario"), rs.getDouble("total_linea"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}