package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Factura;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

public Factura insert(Factura entity) {
    String sql = "INSERT INTO factura (fk_id_empresa, fk_id_cliente, numero, fecha_emision, fecha_servicio, concepto, base_imponible, iva_total, total_factura, estado, observaciones, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, entity.getFk_id_empresa());
        ps.setInt(2, entity.getFk_id_cliente());
        ps.setString(3, entity.getNumero());
        ps.setDate(4, Date.valueOf(entity.getFecha_emision()));
        ps.setDate(5, Date.valueOf(entity.getFecha_servicio()));
        ps.setString(6, entity.getConcepto());
        ps.setDouble(7, entity.getBase_imponible());
        ps.setDouble(8, entity.getIva_total());
        ps.setDouble(9, entity.getTotal_factura());
        ps.setString(10, entity.getEstado());
        ps.setString(11, entity.getObservaciones());
        ps.setString(12, entity.getTipo());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Factura factura) {
    String sql = "UPDATE factura SET fk_id_empresa = ?, fk_id_cliente = ?, numero = ?, fecha_emision = ?, fecha_servicio = ?, concepto = ?, base_imponible = ?, iva_total = ?, total_factura = ?, estado = ?, observaciones = ?, tipo = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, factura.getFk_id_empresa());
        ps.setInt(2, factura.getFk_id_cliente());
        ps.setString(3, factura.getNumero());
        ps.setDate(4, Date.valueOf(factura.getFecha_servicio()));
        ps.setDate(5, Date.valueOf(factura.getFecha_servicio()));
        ps.setString(6, factura.getConcepto());
        ps.setDouble(7, factura.getBase_imponible());
        ps.setDouble(8, factura.getIva_total());
        ps.setDouble(9, factura.getTotal_factura());
        ps.setString(10, factura.getEstado());
        ps.setString(11, factura.getObservaciones());
        ps.setString(12, factura.getTipo());
        ps.setInt(13, factura.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM factura WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Factura> findAll() {
    List<Factura> list = new ArrayList<>();
    String sql = "SELECT * FROM factura";
    try (Connection conn = Conexion.getConnection();
         var st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Factura(rs.getInt("id"), rs.getInt("fk_id_empresa"), rs.getInt("fk_id_cliente"), rs.getString("numero"), rs.getDate("fecha_emision").toLocalDate(), rs.getDate("fecha_servicio").toLocalDate(), rs.getString("concepto"), rs.getDouble("base_imponible"), rs.getDouble("iva_total"), rs.getDouble("total_factura"), rs.getString("estado"), rs.getString("observaciones"), rs.getString("tipo")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Factura findById(int id) {
    String sql = "SELECT * FROM factura WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Factura(rs.getInt("id"), rs.getInt("fk_id_empresa"), rs.getInt("fk_id_cliente"), rs.getString("numero"), rs.getDate("fecha_emision").toLocalDate(), rs.getDate("fecha_servicio").toLocalDate(), rs.getString("concepto"), rs.getDouble("base_imponible"), rs.getDouble("iva_total"), rs.getDouble("total_factura"), rs.getString("estado"), rs.getString("observaciones"), rs.getString("tipo"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public List<Factura> findByAll(Integer id, Integer fk_id_empresa, Integer fk_id_cliente,
                                   String numero, String fecha_emision, String fecha_servicio,
                                   String concepto, Double base_imponible, Double iva_total,
                                   Double total_factura, String estado, String observaciones,
                                   String tipo) {

        String baseSql = "SELECT * FROM factura";
        List<String> condiciones = new ArrayList<>();
        List<Object> valores = new ArrayList<>();

        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (fk_id_empresa != null) {
            condiciones.add("fk_id_empresa = ?");
            valores.add(fk_id_empresa);
        }
        if (fk_id_cliente != null) {
            condiciones.add("fk_id_cliente = ?");
            valores.add(fk_id_cliente);
        }
        if (numero != null && !numero.isEmpty()) {
            condiciones.add("numero = ?");
            valores.add(numero);
        }
        if (fecha_emision != null && !fecha_emision.isEmpty()) {
            condiciones.add("fecha_emision = ?");
            valores.add(Date.valueOf(fecha_emision));
        }
        if (fecha_servicio != null && !fecha_servicio.isEmpty()) {
            condiciones.add("fecha_servicio = ?");
            valores.add(Date.valueOf(fecha_servicio));
        }
        if (concepto != null && !concepto.isEmpty()) {
            condiciones.add("concepto = ?");
            valores.add(concepto);
        }
        if (base_imponible != null) {
            condiciones.add("base_imponible = ?");
            valores.add(base_imponible);
        }
        if (iva_total != null) {
            condiciones.add("iva_total = ?");
            valores.add(iva_total);
        }
        if (total_factura != null) {
            condiciones.add("total_factura = ?");
            valores.add(total_factura);
        }
        if (estado != null && !estado.isEmpty()) {
            condiciones.add("estado = ?");
            valores.add(estado);
        }
        if (observaciones != null && !observaciones.isEmpty()) {
            condiciones.add("observaciones = ?");
            valores.add(observaciones);
        }
        if (tipo != null && !tipo.isEmpty()) {
            condiciones.add("tipo = ?");
            valores.add(tipo);
        }

        // Si no hay filtros, devuelve lista vacía
        if (condiciones.isEmpty()) {
            return new ArrayList<>();
        }

        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        List<Factura> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Factura(
                        rs.getInt("id"),
                        rs.getInt("fk_id_empresa"),
                        rs.getInt("fk_id_cliente"),
                        rs.getString("numero"),
                        rs.getDate("fecha_emision").toLocalDate(),
                        rs.getDate("fecha_servicio").toLocalDate(),
                        rs.getString("concepto"),
                        rs.getDouble("base_imponible"),
                        rs.getDouble("iva_total"),
                        rs.getDouble("total_factura"),
                        rs.getString("estado"),
                        rs.getString("observaciones"),
                        rs.getString("tipo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, Integer fk_id_empresa, Integer fk_id_cliente, String numero, LocalDate fecha_emision, LocalDate fecha_servicio, String concepto, Double base_imponible, Double iva_total, Double total_factura, String estado, String observaciones, String tipo) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, Integer fk_id_empresa, Integer fk_id_cliente, String numero, LocalDate fecha_emision, LocalDate fecha_servicio, String concepto, Double base_imponible, Double iva_total, Double total_factura, String estado, String observaciones, String tipo){String baseSql = "SELECT * FROM factura";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (fk_id_empresa != null) {
            condiciones.add("fk_id_empresa = ?");
            valores.add(fk_id_empresa);
        }
        if (fk_id_cliente != null) {
            condiciones.add("fk_id_cliente = ?");
            valores.add(fk_id_cliente);
        }
        if (numero != null) {
            condiciones.add("numero = ?");
            valores.add(numero);
        }
        if (fecha_emision != null) {
            condiciones.add("fecha_emision = ?");
            valores.add(fecha_emision);
        }
        if (fecha_servicio != null) {
            condiciones.add("fecha_servicio = ?");
            valores.add(fecha_servicio);
        }
        if (concepto != null) {
            condiciones.add("concepto = ?");
            valores.add(concepto);
        }
        if (base_imponible != null) {
            condiciones.add("base_imponible = ?");
            valores.add(base_imponible);
        }
        if (iva_total != null) {
            condiciones.add("iva_total = ?");
            valores.add(iva_total);
        }
        if (total_factura != null) {
            condiciones.add("total_factura = ?");
            valores.add(total_factura);
        }
        if (estado != null) {
            condiciones.add("estado = ?");
            valores.add(estado);
        }
        if (observaciones != null) {
            condiciones.add("observaciones = ?");
            valores.add(observaciones);
        }
        if (tipo != null) {
            condiciones.add("tipo = ?");
            valores.add(tipo);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Factura> findByAll(Integer id, Integer fk_id_empresa, Integer fk_id_cliente, String numero, LocalDate fecha_emision, LocalDate fecha_servicio, String concepto, Double base_imponible, Double iva_total, Double total_factura, String estado, String observaciones, String tipo) {
        List<Object> valores = new ArrayList<>();        List<Factura> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,fk_id_empresa,fk_id_cliente,numero,fecha_emision,fecha_servicio,concepto,base_imponible,iva_total,total_factura,estado,observaciones,tipo))) {

            setupParameters(ps,valores, id,fk_id_empresa,fk_id_cliente,numero,fecha_emision,fecha_servicio,concepto,base_imponible,iva_total,total_factura,estado,observaciones,tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Factura(rs.getInt("id"), rs.getInt("fk_id_empresa"), rs.getInt("fk_id_cliente"), rs.getString("numero"), rs.getDate("fecha_emision").toLocalDate(), rs.getDate("fecha_servicio").toLocalDate(), rs.getString("concepto"), rs.getDouble("base_imponible"), rs.getDouble("iva_total"), rs.getDouble("total_factura"), rs.getString("estado"), rs.getString("observaciones"), rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}