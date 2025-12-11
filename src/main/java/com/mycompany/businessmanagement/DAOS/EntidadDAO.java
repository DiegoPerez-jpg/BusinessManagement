package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Entidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntidadDAO {

public Entidad insert(Entidad entity) {
    String sql = "INSERT INTO entidad (codigo, nombre, fk_id_informacion, fk_id_direccion) VALUES (?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setInt(1, entity.getCodigo());
ps.setString(2, entity.getNombre());
ps.setInt(3, entity.getFk_id_informacion());
ps.setInt(4, entity.getFk_id_direccion());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Entidad entidad) {
    String sql = "UPDATE entidad SET codigo = ?, nombre = ?, fk_id_informacion = ?, fk_id_direccion = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, entidad.getCodigo());
        ps.setString(2, entidad.getNombre());
        ps.setInt(3, entidad.getFk_id_informacion());
        ps.setInt(4, entidad.getFk_id_direccion());
        ps.setInt(5, entidad.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM entidad WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Entidad> findAll() {
    List<Entidad> list = new ArrayList<>();
    String sql = "SELECT * FROM entidad";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
           // list.add(new Entidad(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("fk_id_informacion"), rs.getInt("fk_id_direccion")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Entidad findById(int id) {
    String sql = "SELECT * FROM entidad WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
         //   return new Entidad(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("fk_id_informacion"), rs.getInt("fk_id_direccion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public Entidad findByName(String name) {
        String sql = "SELECT * FROM entidad WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               // return new Entidad(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("fk_id_informacion"), rs.getInt("fk_id_direccion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, Integer codigo, String nombre, Integer fk_id_informacion, Integer fk_id_direccion) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, Integer codigo, String nombre, Integer fk_id_informacion, Integer fk_id_direccion){String baseSql = "SELECT * FROM entidad";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (codigo != null) {
            condiciones.add("codigo = ?");
            valores.add(codigo);
        }
        if (nombre != null) {
            condiciones.add("nombre = ?");
            valores.add(nombre);
        }
        if (fk_id_informacion != null) {
            condiciones.add("fk_id_informacion = ?");
            valores.add(fk_id_informacion);
        }
        if (fk_id_direccion != null) {
            condiciones.add("fk_id_direccion = ?");
            valores.add(fk_id_direccion);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Entidad> findByAll(Integer id, Integer codigo, String nombre, Integer fk_id_informacion, Integer fk_id_direccion) {
        List<Object> valores = new ArrayList<>();        List<Entidad> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,codigo,nombre,fk_id_informacion,fk_id_direccion))) {

            setupParameters(ps,valores, id,codigo,nombre,fk_id_informacion,fk_id_direccion);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Entidad(rs.getInt("id"), rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("fk_id_informacion"), rs.getInt("fk_id_direccion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}