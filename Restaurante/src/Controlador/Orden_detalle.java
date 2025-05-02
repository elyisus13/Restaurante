package Controlador;

import Conexion.ConexionDB;
import Modelo.Orden_detalle;

import javax.swing.*;
import java.sql.*;

public class Orden_detalleDAO {
    ConexionDB conexionDB = new ConexionDB();

    public void agregar(Orden_detalle orden_detalle) {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO orden_detalle (id_orden, id_producto, cantidad) VALUES (?,?,?)";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orden_detalle.getId_orden());
            pst.setInt(2, orden_detalle.getId_producto());
            pst.setInt(3, orden_detalle.getCantidad());

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro agregado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Registro NO agregado con éxito.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecución");
        }
    }

    public void actualizar(Orden_detalle orden_detalle) {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE orden_detalle SET id_orden = ?, id_producto = ?, cantidad = ? WHERE id_detalle = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orden_detalle.getId_orden());
            pst.setInt(2, orden_detalle.getId_producto());
            pst.setInt(3, orden_detalle.getCantidad());
            pst.setInt(4, orden_detalle.getId_detalle());

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Registro NO actualizado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecución.");
        }
    }

    public void eliminar(int id_detalle) {
        Connection con = conexionDB.getConnection();

        String query = "DELETE FROM orden_detalle WHERE id_detalle = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id_detalle);

            int resultado = pst.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Orden eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la orden.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecución");
        }
    }
}
