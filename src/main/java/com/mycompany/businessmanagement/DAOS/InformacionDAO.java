package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Informacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformacionDAO {

public Informacion insert(Informacion entity) {
    String sql = "INSERT INTO informacion (nif, email, telefono) VALUES (?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, entity.getNif());
ps.setString(2, entity.getEmail());
ps.setString(3, entity.getTelefono());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Informacion informacion) {
    String sql = "UPDATE informacion SET nif = ?, email = ?, telefono = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, informacion.getNif());
        ps.setString(2, informacion.getEmail());
        ps.setString(3, informacion.getTelefono());
        ps.setInt(4, informacion.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM informacion WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Informacion> findAll() {
    List<Informacion> list = new ArrayList<>();
    String sql = "SELECT * FROM informacion";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Informacion(rs.getInt("id"), rs.getString("nif"), rs.getString("email"), rs.getString("telefono")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


    public Informacion findById(int id) {
        String sql = "SELECT * FROM informacion WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Informacion(rs.getInt("id"), rs.getString("nif"), rs.getString("email"), rs.getString("telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Informacion> filterByAll(String nif, String email, String telefono) {

        String baseSql = "SELECT * FROM informacion";
        List<String> condiciones = new ArrayList<>();
        List<String> valores = new ArrayList<>();

        if (nif != null && !nif.isEmpty()) {
            condiciones.add("nif = ?");
            valores.add(nif);
        }

        if (email != null && !email.isEmpty()) {
            condiciones.add("email = ?");
            valores.add(email);
        }

        if (telefono != null && !telefono.isEmpty()) {
            condiciones.add("telefono = ?");
            valores.add(telefono);
        }

        List<Informacion> resultado = new ArrayList<>();

        if (condiciones.isEmpty()) {
            return resultado;
        }

        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < valores.size(); i++) {
                ps.setString(i + 1, valores.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultado.add(new Informacion(
                        rs.getInt("id"),
                        rs.getString("nif"),
                        rs.getString("email"),
                        rs.getString("telefono")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }



    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, String nif, String email, String telefono) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, String nif, String email, String telefono){String baseSql = "SELECT * FROM informacion";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (nif != null) {
            condiciones.add("nif = ?");
            valores.add(nif);
        }
        if (email != null) {
            condiciones.add("email = ?");
            valores.add(email);
        }
        if (telefono != null) {
            condiciones.add("telefono = ?");
            valores.add(telefono);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Informacion> findByAll(Integer id, String nif, String email, String telefono) {
        List<Object> valores = new ArrayList<>();        List<Informacion> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,nif,email,telefono))) {

            setupParameters(ps,valores, id,nif,email,telefono);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Informacion(rs.getInt("id"), rs.getString("nif"), rs.getString("email"), rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}