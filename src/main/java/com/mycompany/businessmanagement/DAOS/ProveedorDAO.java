package com.mycompany.businessmanagement.DAOS;
import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

public Proveedor insert(Proveedor entity) {
    String sql = "INSERT INTO proveedor () VALUES ()";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Proveedor proveedor) {
    String sql = "UPDATE proveedor SET  WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, proveedor.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM proveedor WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Proveedor> findAll() {
    List<Proveedor> list = new ArrayList<>();
    String sql = "SELECT * FROM proveedor";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add( new Proveedor(
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


public Proveedor findById(int id) {
    String sql = "SELECT * FROM proveedor WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return     new Proveedor(
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


    public String getFindByAllSql(List<Object> valores,Integer id){String baseSql = "SELECT * FROM proveedor";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Proveedor> findByAll(Integer id) {
        List<Object> valores = new ArrayList<>();        List<Proveedor> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id))) {

            setupParameters(ps,valores, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add( new Proveedor(
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

        return lista;
    }


}