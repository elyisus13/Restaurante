package Controlador;
import Modelo.Orden;
import conexion.ConexionDB;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenDAO
{
    ConexionDB conexionDB = new ConexionDB();
    //Agregar

    public void agregar (Orden orden)
    {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO orden (mesa, empleado) VALUES (?,?)";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, orden.getIdMesas());
            pst.setInt(2, orden.getIdEmpleados());

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Orden agregada con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Orden NO agreada con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }
    public void actualizar (Orden orden)
    {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE orden SET idMesas = ?, idEmpleados = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, orden.getIdMesas());
            pst.setInt(2, orden.getIdEmpleados());


            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro actualizado con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Regostro NO actualizado con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }

    public void eliminar (int id)
    {
        Connection con = conexionDB.getConnection();

        String query = "DELETE FROM orden WHERE idOrden = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1,id);

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Orden eliminada con exito.");
            } else
            {
                JOptionPane.showMessageDialog(null, "Orden NO eliminada con exito.");
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecucion");
        }
    }

}
