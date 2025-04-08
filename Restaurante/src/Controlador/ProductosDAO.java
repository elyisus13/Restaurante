package Controlador;

import Conexion.ConexionDB;
import Modelo.Productos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductosDAO
{
    ConexionDB conexionDB = new ConexionDB();

    public void agregar(Productos producto)
    {
        Connection con = conexionDB.getConnection();

        String query = "INSERT INTO productos (nombre, categoria, precio, disponible ) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(query))
        {
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getDisponible());


            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Producto agregado con éxito");
            } else
            {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el producto");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al intentar agregar el producto");
        }
    }
    public void actualizar(Productos producto)
    {
        Connection con = conexionDB.getConnection();

        String query = "UPDATE productos SET nombre = ?, categoria = ?, precio = ?, disponible = ?  WHERE id_producto = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getDisponible());
            pst.setInt(5, producto.getIdProducto());


            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No fue posible actualizar el registro. Intenta nuevamente.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecución");
        }
    }
    public void eliminar(int id_producto)
    {
        Connection con = conexionDB.getConnection();
        String query = "DELETE FROM productos WHERE id_producto = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id_producto);

            int resultado = pst.executeUpdate();
            if (resultado > 0)
            {
                JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No fue posible eliminar el producto. Intenta nuevamente.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la ejecución");
        }
    }
}
