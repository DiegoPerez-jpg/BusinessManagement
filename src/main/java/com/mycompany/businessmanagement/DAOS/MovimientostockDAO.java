package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Movimientostock;

import java.sql.*;
import java.time.LocalDate;
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



    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, Integer fk_id_producto, LocalDate fecha, Double cantidad, String motivo, String tipo) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, Integer fk_id_producto, LocalDate fecha, Double cantidad, String motivo, String tipo){String baseSql = "SELECT * FROM movimientostock";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (fk_id_producto != null) {
            condiciones.add("fk_id_producto = ?");
            valores.add(fk_id_producto);
        }
        if (fecha != null) {
            condiciones.add("fecha = ?");
            valores.add(fecha);
        }
        if (cantidad != null) {
            condiciones.add("cantidad = ?");
            valores.add(cantidad);
        }
        if (motivo != null) {
            condiciones.add("motivo = ?");
            valores.add(motivo);
        }
        if (tipo != null) {
            condiciones.add("tipo = ?");
            valores.add(tipo);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Movimientostock> findByAll(Integer id, Integer fk_id_producto, LocalDate fecha, Double cantidad, String motivo, String tipo) {
        List<Object> valores = new ArrayList<>();        List<Movimientostock> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,fk_id_producto,fecha,cantidad,motivo,tipo))) {

            setupParameters(ps,valores, id,fk_id_producto,fecha,cantidad,motivo,tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Movimientostock(rs.getInt("id"), rs.getInt("fk_id_producto"), rs.getDate("fecha").toLocalDate(), rs.getDouble("cantidad"), rs.getString("motivo"), rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}

