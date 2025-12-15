package com.mycompany.businessmanagement.DAOS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Direccion;

public class DireccionDAO {

public Direccion insert(Direccion entity, Connection conn) {
    String sql = "INSERT INTO direccion (direccion, codigopostal, ciudad, provincia, pais, etiqueta) VALUES (?, ?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, entity.getDireccion());
ps.setString(2, entity.getCodigopostal());
ps.setString(3, entity.getCiudad());
ps.setString(4, entity.getProvincia());
ps.setString(5, entity.getPais());
ps.setString(6, entity.getEtiqueta());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public void update(Direccion direccion) {
    String sql = "UPDATE direccion SET direccion = ?, codigopostal = ?, ciudad = ?, provincia = ?, pais = ?, etiqueta = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, direccion.getDireccion());
        ps.setString(2, direccion.getCodigopostal());
        ps.setString(3, direccion.getCiudad());
        ps.setString(4, direccion.getProvincia());
        ps.setString(5, direccion.getPais());
        ps.setString(6, direccion.getEtiqueta());
        ps.setInt(7, direccion.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM direccion WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Direccion> findAll() {
    List<Direccion> list = new ArrayList<>();
    String sql = "SELECT * FROM direccion";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Direccion(rs.getInt("id"), rs.getString("direccion"), rs.getString("codigopostal"), rs.getString("ciudad"), rs.getString("provincia"), rs.getString("pais"), rs.getString("etiqueta")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Direccion findById(int id) {
    String sql = "SELECT * FROM direccion WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Direccion(rs.getInt("id"), rs.getString("direccion"), rs.getString("codigopostal"), rs.getString("ciudad"), rs.getString("provincia"), rs.getString("pais"), rs.getString("etiqueta"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, String direccion, String codigopostal, String ciudad, String provincia, String pais, String etiqueta) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, String direccion, String codigopostal, String ciudad, String provincia, String pais, String etiqueta){String baseSql = "SELECT * FROM direccion";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (direccion != null) {
            condiciones.add("direccion = ?");
            valores.add(direccion);
        }
        if (codigopostal != null) {
            condiciones.add("codigopostal = ?");
            valores.add(codigopostal);
        }
        if (ciudad != null) {
            condiciones.add("ciudad = ?");
            valores.add(ciudad);
        }
        if (provincia != null) {
            condiciones.add("provincia = ?");
            valores.add(provincia);
        }
        if (pais != null) {
            condiciones.add("pais = ?");
            valores.add(pais);
        }
        if (etiqueta != null) {
            condiciones.add("etiqueta = ?");
            valores.add(etiqueta);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Direccion> findByAll(Integer id, String direccion, String codigopostal, String ciudad, String provincia, String pais, String etiqueta) {
        List<Object> valores = new ArrayList<>();        List<Direccion> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,direccion,codigopostal,ciudad,provincia,pais,etiqueta))) {

            setupParameters(ps,valores, id,direccion,codigopostal,ciudad,provincia,pais,etiqueta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Direccion(rs.getInt("id"), rs.getString("direccion"), rs.getString("codigopostal"), rs.getString("ciudad"), rs.getString("provincia"), rs.getString("pais"), rs.getString("etiqueta")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}