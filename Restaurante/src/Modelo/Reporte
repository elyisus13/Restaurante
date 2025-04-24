package Controlador;

import Conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    private Connection conn;

    public ReporteDAO() {
        conn = ConexionDB.getConnection();
    }




    public double totalVentas(String desde, String hasta) {
        double total = 0.0;
        String query = "SELECT SUM(total) FROM ventas WHERE fecha BETWEEN ? AND ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, desde);
            stmt.setString(2, hasta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }


    public List<String[]> productosMasVendidos() {
        List<String[]> productos = new ArrayList<>();
        String query = "SELECT p.nombre, SUM(v.cantidad) FROM ventas v JOIN productos p ON v.id_producto = p.id GROUP BY p.id ORDER BY SUM(v.cantidad) DESC LIMIT 10";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                productos.add(new String[]{rs.getString(1), String.valueOf(rs.getInt(2))});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }


    public List<String[]> clientesConMasCompras() {
        List<String[]> clientes = new ArrayList<>();
        String query = "SELECT c.nombre, COUNT(*) FROM ventas v JOIN clientes c ON v.id_cliente = c.id GROUP BY c.id ORDER BY COUNT(*) DESC LIMIT 10";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                clientes.add(new String[]{rs.getString(1), String.valueOf(rs.getInt(2))});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}

