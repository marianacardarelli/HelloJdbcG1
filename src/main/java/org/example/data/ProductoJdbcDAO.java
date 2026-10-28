package org.example.data;

import org.example.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoJdbcDAO implements DAO<Producto> {

    private final String url = "jdbc:mysql://localhost:3309/ad";
    private final String username = "user";
    private final String password = "pwd";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM Equipo WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM Equipo";
    private static final String SQL_INSERT = "INSERT INTO Equipo (nombre, categoria, disponible) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE Equipo SET nombre = ?, categoria = ?, disponible = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Equipo WHERE id = ?";

    @Override
    public Producto findById(Long id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement(SQL_FIND_BY_ID)) {

            pst.setLong(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getLong("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setCategoria(rs.getString("categoria"));
                    producto.setDisponible(rs.getBoolean("disponible"));
                    return producto;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getLong("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setDisponible(rs.getBoolean("disponible"));
                productos.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public Producto save(Producto obj) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, obj.getNombre());
            pst.setString(2, obj.getCategoria());
            pst.setBoolean(3, obj.getDisponible());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    obj.setId(rs.getLong(1));
                }
            }

            return obj;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void upDate(Producto entity) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement(SQL_UPDATE)) {

            pst.setString(1, entity.getNombre());
            pst.setString(2, entity.getCategoria());
            pst.setBoolean(3, entity.getDisponible());
            pst.setLong(4, entity.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement(SQL_DELETE)) {

            pst.setLong(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
