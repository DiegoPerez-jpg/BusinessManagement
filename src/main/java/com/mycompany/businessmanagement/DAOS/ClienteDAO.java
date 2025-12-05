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
            list.add( new Cliente(
                rs.getInt("id"),
                    rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getInt("fk_id_direccion"),
                rs.getInt("fk_id_informacion")
            ));
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
            return  new Cliente(
                rs.getInt("id"),
                    rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getInt("fk_id_direccion"),
                rs.getInt("fk_id_informacion")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id){String baseSql = "SELECT * FROM cliente";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public Cliente findById(Integer id) {
    Cliente cliente = null;
    String sql = "SELECT id, nombre, apellido, email FROM cliente WHERE id = ?";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cliente = new Cliente(
                rs.getInt("id"),
                    rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getInt("fk_id_direccion"),
                rs.getInt("fk_id_informacion")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return cliente;
}


}