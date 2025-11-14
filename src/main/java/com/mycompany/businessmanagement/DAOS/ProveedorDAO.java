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
            list.add(new Proveedor(rs.getInt("id")));
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
            return new Proveedor(rs.getInt("id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


}