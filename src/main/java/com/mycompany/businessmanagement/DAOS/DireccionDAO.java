package com.mycompany.businessmanagement.DAOS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Direccion;

public class DireccionDAO {

public Direccion insert(Direccion entity) {
    String sql = "INSERT INTO direccion (direccion, codigopostal, ciudad, provincia, pais, etiqueta) VALUES (?, ?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
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
    public Direccion findByAll(String direccion, String codigopostal, String ciudad,
                               String provincia, String pais, String etiqueta) {

        String baseSql = "SELECT * FROM direccion";
        List<String> condiciones = new ArrayList<>();
        List<String> valores = new ArrayList<>();

        if (direccion != null && !direccion.isEmpty()) {
            condiciones.add("direccion = ?");
            valores.add(direccion);
        }
        if (codigopostal != null && !codigopostal.isEmpty()) {
            condiciones.add("codigopostal = ?");
            valores.add(codigopostal);
        }
        if (ciudad != null && !ciudad.isEmpty()) {
            condiciones.add("ciudad = ?");
            valores.add(ciudad);
        }
        if (provincia != null && !provincia.isEmpty()) {
            condiciones.add("provincia = ?");
            valores.add(provincia);
        }
        if (pais != null && !pais.isEmpty()) {
            condiciones.add("pais = ?");
            valores.add(pais);
        }
        if (etiqueta != null && !etiqueta.isEmpty()) {
            condiciones.add("etiqueta = ?");
            valores.add(etiqueta);
        }

        if (condiciones.isEmpty()) {
            return null;
        }

        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < valores.size(); i++) {
                ps.setString(i + 1, valores.get(i));
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Direccion(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getString("codigopostal"),
                        rs.getString("ciudad"),
                        rs.getString("provincia"),
                        rs.getString("pais"),
                        rs.getString("etiqueta")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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


}