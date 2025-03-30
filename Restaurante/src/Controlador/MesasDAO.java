package Controlador;

import Conexion.ConexionDB;
import Modelo.Mesas;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MesasDAO 
{
    // Se crea una instancia de la clase ConexionDB para manejar la conexión con la base de datos
    ConexionDB conexionDB = new ConexionDB();

    // Método para agregar un nuevo registro de mesa en la base de datos
    public void agregar(Mesas mesas) 
  {
        Connection con = conexionDB.getConnection(); // Se obtiene la conexión a la BD

        // Consulta SQL para insertar un nuevo registro en la tabla 'mesas'
        String query = "INSERT INTO mesas (id_mesas, capacidad, estatus_mesa) VALUES (?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) // Se usa PreparedStatement para evitar inyecciones SQL
        {
            // Se asignan los valores a los parámetros de la consulta
            pst.setInt(1, mesas.getId_mesas());
            pst.setInt(2, mesas.getCapacidad());
            pst.setString(3, mesas.getEstatus_mesa());

            // Se ejecuta la consulta y se obtiene el resultado
            int resultado = pst.executeUpdate();
            if (resultado > 0) 
            {
                JOptionPane.showMessageDialog(null, "Registro agregado con éxito");
            } else 
            {
                JOptionPane.showMessageDialog(null, "Registro no agregado con éxito");
            }
        } 
          catch (SQLException e) 
        {
            e.printStackTrace(); // Imprime el error en la consola para depuración
            JOptionPane.showMessageDialog(null, "Error en la ejecución");
        }
    }

    // Método para actualizar un registro existente en la base de datos
    public void actualizar(Mesas mesas) 
  {
        // Consulta SQL para actualizar los datos de la mesa basada en su ID
        String query = "UPDATE mesas SET estatus_mesa = ?, capacidad = ? WHERE id_mesas = ?";

        Connection con = conexionDB.getConnection(); // Se obtiene la conexión a la BD

        try (PreparedStatement pst = con.prepareStatement(query)) 
        {
            // Se asignan los nuevos valores a los parámetros de la consulta
            pst.setString(1, mesas.getEstatus_mesa());
            pst.setInt(2, mesas.getCapacidad());
            pst.setInt(3, mesas.getId_mesas());

            // Se ejecuta la actualización y se verifica si se realizó correctamente
            int resultado = pst.executeUpdate();

            if (resultado > 0) 
            {
                JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
            } 
            else {
                JOptionPane.showMessageDialog(null, "Registro NO actualizado");
            }
        } catch (SQLException e) 
        {
            e.printStackTrace(); // Imprime el error en la consola para depuración
            JOptionPane.showMessageDialog(null, "Error en la ejecución: " + e.getMessage());
        }
    }

    // Método para eliminar un registro de la base de datos
    public void eliminar(int id) 
  {
        Connection con = conexionDB.getConnection(); // Se obtiene la conexión a la BD
        String query = "DELETE FROM mesas WHERE id_mesas = ?"; // Consulta SQL para eliminar una mesa por su ID

        try {
            PreparedStatement pst = con.prepareStatement(query); // Se prepara la consulta SQL
            pst.setInt(1, id); // Se asigna el valor del ID a eliminar

            // Se ejecuta la consulta y se verifica si se eliminó correctamente
            int resultado = pst.executeUpdate();
            if (resultado > 0) 
            {
                JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
            } 
            else {
                JOptionPane.showMessageDialog(null, "Registro NO eliminado con éxito");
            }
        } catch (SQLException e) 
          {
            e.printStackTrace(); // Imprime el error en la consola para depuración
            JOptionPane.showMessageDialog(null, "Error en la ejecución");
        }
    }
}
