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


    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, String concepto, Double porcentaje) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, String concepto, Double porcentaje){String baseSql = "SELECT * FROM tipoiva";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (concepto != null) {
            condiciones.add("concepto = ?");
            valores.add(concepto);
        }
        if (porcentaje != null) {
            condiciones.add("porcentaje = ?");
            valores.add(porcentaje);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Tipoiva> findByAll(Integer id, String concepto, Double porcentaje) {
        List<Object> valores = new ArrayList<>();        List<Tipoiva> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,concepto,porcentaje))) {

            setupParameters(ps,valores, id,concepto,porcentaje);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Tipoiva(rs.getInt("id"), rs.getString("concepto"), rs.getDouble("porcentaje")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}