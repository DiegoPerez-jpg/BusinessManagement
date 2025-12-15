package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Facturadetalle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturadetalleDAO {



    public void insert(Facturadetalle entity) {
    String sql = "INSERT INTO facturadetalle (fk_id_factura,fk_id_producto, cantidad, precio_unitario, total_linea) VALUES (?, ?, ?, ?,?)";
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


public void update(Facturadetalle facturadetalle) {
    String sql = "UPDATE facturadetalle SET cantidad = ?, precio_unitario = ?, total_linea = ? WHERE fk_id_factura = ? and fk_id_producto = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setDouble(1, facturadetalle.getCantidad());
        ps.setDouble(2, facturadetalle.getPrecio_unitario());
        ps.setDouble(3, facturadetalle.getTotal_linea());
        ps.setInt(4, facturadetalle.getFk_id_factura());
        ps.setInt(5, facturadetalle.getFk_id_producto());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int fk_id_producto, int fk_id_factura) {
    String sql = "DELETE FROM facturadetalle  WHERE fk_id_factura = ? and fk_id_producto = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, fk_id_producto);
        ps.setInt(2, fk_id_factura);
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

    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer fk_id_factura, Integer fk_id_producto, Double cantidad, Double precio_unitario, Double total_linea) throws SQLException {
        for (int i = 0; i < valores.size(); i++) {
            Object val = valores.get(i);
            if (val instanceof Integer) {
                ps.setInt(i + 1, (Integer) val);
            } else if (val instanceof Double) {
                ps.setDouble(i + 1, (Double) val);
            } else if (val instanceof Date) {
                ps.setDate(i + 1, (Date) val);
            } else if (val instanceof String) {
                ps.setString(i + 1, (String) val);
            }
        }
        return ps;}


    public String getFindByAllSql(List<Object> valores,Integer fk_id_factura, Integer fk_id_producto, Double cantidad, Double precio_unitario, Double total_linea){String baseSql = "SELECT * FROM facturadetalle";
        List<String> condiciones = new ArrayList<>();
        if (fk_id_factura != null) {
            condiciones.add("fk_id_factura = ?");
            valores.add(fk_id_factura);
        }
        if (fk_id_producto != null) {
            condiciones.add("fk_id_producto = ?");
            valores.add(fk_id_producto);
        }
        if (cantidad != null) {
            condiciones.add("cantidad = ?");
            valores.add(cantidad);
        }
        if (precio_unitario != null) {
            condiciones.add("precio_unitario = ?");
            valores.add(precio_unitario);
        }
        if (total_linea != null) {
            condiciones.add("total_linea = ?");
            valores.add(total_linea);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Facturadetalle> findByAll(Integer fk_id_factura, Integer fk_id_producto, Double cantidad, Double precio_unitario, Double total_linea) {
        List<Object> valores = new ArrayList<>();        List<Facturadetalle> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,fk_id_factura,fk_id_producto,cantidad,precio_unitario,total_linea))) {

            setupParameters(ps,valores, fk_id_factura,fk_id_producto,cantidad,precio_unitario,total_linea);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Facturadetalle(rs.getInt("fk_id_factura"), rs.getInt("fk_id_producto"), rs.getDouble("cantidad"), rs.getDouble("precio_unitario"), rs.getDouble("total_linea")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}