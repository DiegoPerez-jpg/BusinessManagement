package com.mycompany.businessmanagement.DAOS;

import com.mycompany.businessmanagement.conexion.Conexion;
import com.mycompany.businessmanagement.modelos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

public Producto insert(Producto entity) {
    String sql = "INSERT INTO producto (codigo, descripcion, descripcion_aux, precio_coste, precio_venta, referencia_proveedor, stock, fk_id_proveedor, fk_id_fabricante, fk_id_tipoiva) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
long idGenerado = -1;
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
ps.setString(1, entity.getCodigo());
ps.setString(2, entity.getDescripcion());
ps.setString(3, entity.getDescripcion_aux());
ps.setDouble(4, entity.getPrecio_coste());
ps.setDouble(5, entity.getPrecio_venta());
ps.setString(6, entity.getReferencia_proveedor());
ps.setDouble(7, entity.getStock());
ps.setInt(8, entity.getFk_id_proveedor());
ps.setInt(9, entity.getFk_id_fabricante());
ps.setInt(10, entity.getFk_id_tipoiva());        ps.executeUpdate();
try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
            idGenerado = rs.getLong(1);
        }
    }entity.setId((int)idGenerado);        return entity;    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}


public void update(Producto producto) {
    String sql = "UPDATE producto SET codigo = ?, descripcion = ?, descripcion_aux = ?, precio_coste = ?, precio_venta = ?, referencia_proveedor = ?, stock = ?, fk_id_proveedor = ?, fk_id_fabricante = ?, fk_id_tipoiva = ? WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, producto.getCodigo());
        ps.setString(2, producto.getDescripcion());
        ps.setString(3, producto.getDescripcion_aux());
        ps.setDouble(4, producto.getPrecio_coste());
        ps.setDouble(5, producto.getPrecio_venta());
        ps.setString(6, producto.getReferencia_proveedor());
        ps.setDouble(7, producto.getStock());
        ps.setInt(8, producto.getFk_id_proveedor());
        ps.setInt(9, producto.getFk_id_fabricante());
        ps.setInt(10, producto.getFk_id_tipoiva());
        ps.setInt(11, producto.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void delete(int id) {
    String sql = "DELETE FROM producto WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public List<Producto> findAll() {
    List<Producto> list = new ArrayList<>();
    String sql = "SELECT * FROM producto";
    try (Connection conn = Conexion.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            list.add(new Producto(rs.getInt("id"), rs.getString("codigo"), rs.getString("descripcion"), rs.getString("descripcion_aux"), rs.getDouble("precio_coste"), rs.getDouble("precio_venta"), rs.getString("referencia_proveedor"), rs.getDouble("stock"), rs.getInt("fk_id_proveedor"), rs.getInt("fk_id_fabricante"), rs.getInt("fk_id_tipoiva")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


public Producto findById(int id) {
    String sql = "SELECT * FROM producto WHERE id = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Producto(rs.getInt("id"), rs.getString("codigo"), rs.getString("descripcion"), rs.getString("descripcion_aux"), rs.getDouble("precio_coste"), rs.getDouble("precio_venta"), rs.getString("referencia_proveedor"), rs.getDouble("stock"), rs.getInt("fk_id_proveedor"), rs.getInt("fk_id_fabricante"), rs.getInt("fk_id_tipoiva"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public PreparedStatement setupParameters( PreparedStatement ps,List<Object> valores,Integer id, String codigo, String descripcion, String descripcion_aux, Double precio_coste, Double precio_venta, String referencia_proveedor, Double stock, Integer fk_id_proveedor, Integer fk_id_fabricante, Integer fk_id_tipoiva) throws SQLException {
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


    public String getFindByAllSql(List<Object> valores,Integer id, String codigo, String descripcion, String descripcion_aux, Double precio_coste, Double precio_venta, String referencia_proveedor, Double stock, Integer fk_id_proveedor, Integer fk_id_fabricante, Integer fk_id_tipoiva){String baseSql = "SELECT * FROM producto";
        List<String> condiciones = new ArrayList<>();
        if (id != null) {
            condiciones.add("id = ?");
            valores.add(id);
        }
        if (codigo != null) {
            condiciones.add("codigo = ?");
            valores.add(codigo);
        }
        if (descripcion != null) {
            condiciones.add("descripcion = ?");
            valores.add(descripcion);
        }
        if (descripcion_aux != null) {
            condiciones.add("descripcion_aux = ?");
            valores.add(descripcion_aux);
        }
        if (precio_coste != null) {
            condiciones.add("precio_coste = ?");
            valores.add(precio_coste);
        }
        if (precio_venta != null) {
            condiciones.add("precio_venta = ?");
            valores.add(precio_venta);
        }
        if (referencia_proveedor != null) {
            condiciones.add("referencia_proveedor = ?");
            valores.add(referencia_proveedor);
        }
        if (stock != null) {
            condiciones.add("stock = ?");
            valores.add(stock);
        }
        if (fk_id_proveedor != null) {
            condiciones.add("fk_id_proveedor = ?");
            valores.add(fk_id_proveedor);
        }
        if (fk_id_fabricante != null) {
            condiciones.add("fk_id_fabricante = ?");
            valores.add(fk_id_fabricante);
        }
        if (fk_id_tipoiva != null) {
            condiciones.add("fk_id_tipoiva = ?");
            valores.add(fk_id_tipoiva);
        }
        String sql = baseSql + " WHERE " + String.join(" AND ", condiciones);
        return sql;
    }



    public List<Producto> findByAll(Integer id, String codigo, String descripcion, String descripcion_aux, Double precio_coste, Double precio_venta, String referencia_proveedor, Double stock, Integer fk_id_proveedor, Integer fk_id_fabricante, Integer fk_id_tipoiva) {
        List<Object> valores = new ArrayList<>();        List<Producto> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByAllSql(valores,id,codigo,descripcion,descripcion_aux,precio_coste,precio_venta,referencia_proveedor,stock,fk_id_proveedor,fk_id_fabricante,fk_id_tipoiva))) {

            setupParameters(ps,valores, id,codigo,descripcion,descripcion_aux,precio_coste,precio_venta,referencia_proveedor,stock,fk_id_proveedor,fk_id_fabricante,fk_id_tipoiva);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Producto(rs.getInt("id"), rs.getString("codigo"), rs.getString("descripcion"), rs.getString("descripcion_aux"), rs.getDouble("precio_coste"), rs.getDouble("precio_venta"), rs.getString("referencia_proveedor"), rs.getDouble("stock"), rs.getInt("fk_id_proveedor"), rs.getInt("fk_id_fabricante"), rs.getInt("fk_id_tipoiva")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}