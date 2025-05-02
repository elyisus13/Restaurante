package Controlador;

import Conexion.ConexionDB;

import javax.swing.*;
import java.sql.*;

public class ReporteDAO
{
        ConexionDB conexionDB = new ConexionDB();

        // Total de Ventas Diarias
        public double ventasDiarias()
        {
            Connection con = conexionDB.getConnection();
            String query = "SELECT SUM(total) FROM ordenes WHERE DATE(fecha) = CURDATE()";
            double total = 0.0;

            try {
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                if (rs.next())
                {
                    total = rs.getDouble(1);
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en reporte de ventas diarias.");
            }
            return total;
        }

        // Total de Ventas Semanales
        public double ventasSemanales()
        {
            Connection con = conexionDB.getConnection();
            String query = "SELECT SUM(total) FROM ordenes WHERE YEARWEEK(fecha, 1) = YEARWEEK(CURDATE(), 1)";
            double total = 0.0;

            try {
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                if (rs.next())
                {
                    total = rs.getDouble(1);
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en reporte de ventas semanales.");
            }
            return total;
        }

        // Total de Ventas Mensuales
        public double ventasMensuales()
        {
            Connection con = conexionDB.getConnection();
            String query = "SELECT SUM(total) FROM ordenes WHERE MONTH(fecha) = MONTH(CURDATE()) AND YEAR(fecha) = YEAR(CURDATE())";
            double total = 0.0;

            try {
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    total = rs.getDouble(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en reporte de ventas mensuales.");
            }
            return total;
        }

    public String productoMasVendido() {
        String producto = "";
        Connection con = conexionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT productos.nombre AS nombre_producto, SUM(orden_detalle.cantidad) AS total_vendido\n" +
                    "FROM orden_detalle\n" +
                    "JOIN productos ON productos.id_producto = orden_detalle.id_producto\n" +
                    "GROUP BY productos.id_producto\n" +
                    "ORDER BY total_vendido DESC\n" +
                    "LIMIT 1;\n";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                producto = rs.getString("nombre_producto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }


    // Cliente con más compras
        public String clienteConMasCompras()
        {
            Connection con = conexionDB.getConnection();
            String query = "SELECT clientes.nombre, COUNT(ordenes.id_Orden) AS total_compras " +
                    "FROM ordenes " +
                    "JOIN clientes ON ordenes.idclientes = clientes.idclientes " +
                    "GROUP BY clientes.nombre " +
                    "ORDER BY total_compras DESC LIMIT 1";
            String cliente = "";

            try {
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                if (rs.next())
                {
                    cliente = rs.getString("nombre");
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en reporte de cliente con más compras.");
            }
            return cliente;
        }
}
