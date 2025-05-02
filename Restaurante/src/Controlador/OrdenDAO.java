package Controlador;

import Conexion.ConexionDB;
import Modelo.Ordenes;

import javax.swing.*;
import java.sql.*;

public class OrdenesDAO
{
    ConexionDB conexionDB =new ConexionDB();
    public void agregar(Ordenes ordenes)
    {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO ordenes (idclientes, id_empleado, id_mesa, total, estado) VALUES (?,?,?,?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, ordenes.getIdclientes());
            pst.setInt(2, ordenes.getId_empleado());
            pst.setInt(3, ordenes.getId_mesa());
            pst.setDouble(4, ordenes.getTotal());
            pst.setString(5, ordenes.getEstado());

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro agregado con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Regostro NO agreado con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }

    public void actualizar(Ordenes ordenes)
    {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE ordenes SET idclientes = ?, id_empleado = ?, id_mesa = ?, total = ?, fecha = ?, estado = ? WHERE id_orden = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, ordenes.getIdclientes());
            pst.setInt(2, ordenes.getId_empleado());
            pst.setInt(3, ordenes.getId_mesa());
            pst.setDouble(4, ordenes.getTotal());
            pst.setString(5, ordenes.getFecha());
            pst.setString(6, ordenes.getEstado());
            pst.setInt(7, ordenes.getId_orden());

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Registro NO actualizado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecución.");
        }
    }


    public void eliminar (int id_orden)
    {
        Connection con = conexionDB.getConnection();

        String query = "DELETE FROM ordenes WHERE id_orden = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id_orden);

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Orden eliminada con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la orden.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }
}
