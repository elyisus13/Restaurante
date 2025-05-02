package Vista;

import Conexion.ConexionDB;
import Controlador.Orden_detalleDAO;
import Modelo.Orden_detalle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Objects;

public class Orden_detalleGUI
{
    private JTable table1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField2;
    private JButton editarButton;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JPanel main;

    ConexionDB conexionDB = new ConexionDB();
    Orden_detalleDAO ordenDetalleDAO = new Orden_detalleDAO();

    public Orden_detalleGUI()
    {
        obtenerDatos();
        mostrarProximoId();
        comb();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_orden = Integer.parseInt((String) Objects.requireNonNull(comboBox1.getSelectedItem()));
                int id_producto = Integer.parseInt((String) comboBox2.getSelectedItem());
                int cantidad = Integer.parseInt(textField2.getText());

                Orden_detalle orden_detalle = new Orden_detalle (0, id_orden, id_producto, cantidad);
                ordenDetalleDAO.agregar(orden_detalle);
                obtenerDatos();
            }
        });
        editarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_orden = Integer.parseInt((String) Objects.requireNonNull(comboBox1.getSelectedItem()));
                int id_producto = Integer.parseInt((String) comboBox2.getSelectedItem());
                int cantidad = Integer.parseInt(textField2.getText());
                int id_detalle = Integer.parseInt(textField1.getText());

                Orden_detalle orden_detalle = new Orden_detalle (id_detalle, id_orden,id_producto, cantidad);
                ordenDetalleDAO.agregar(orden_detalle);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_detalle = Integer.parseInt(textField1.getText());
                ordenDetalleDAO.eliminar(id_detalle);
                obtenerDatos();
            }
        });
        table1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                int selectedRow = table1.getSelectedRow();

                if (selectedRow >= 0)
                {
                    textField1.setText((String) table1.getValueAt(selectedRow, 0));
                    textField2.setText((String) table1.getValueAt(selectedRow, 3));
                    //Para los combox
                    Object orden = table1.getValueAt(selectedRow, 1);
                    Object producto = table1.getValueAt(selectedRow, 2);
                    comboBox1.setSelectedItem(orden);
                    comboBox2.setSelectedItem(producto);

                }
            }
        });
    }

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id_detalle");
        model.addColumn("id_orden");
        model.addColumn("nombreProdcuto");
        model.addColumn("cantidad");

        table1.setModel(model);
        String[] datos = new String[4];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT od.id_detalle, o.id_orden, p.nombre, od.cantidad " +
                    "FROM orden_detalle od " +
                    "JOIN ordenes o ON od.id_orden = o.id_orden " +
                    "JOIN productos p ON od.id_producto = p.id_producto");


            while (rs.next())
            {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                model.addRow(datos);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void mostrarProximoId()
    {
        try (Connection con = new ConexionDB().getConnection();
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'orden_detalle'"))
        {

            if (rs.next())
            {
                textField1.setText(String.valueOf(rs.getInt(1)));
            }
        } catch (SQLException e)
        {
            textField1.setText("ID Autogenerado");
        }
    }
    private void comb()
    {
        comboBox1.removeAllItems(); // orden
        comboBox2.removeAllItems(); // producto

        Connection con = conexionDB.getConnection();

        try (Statement stmt = con.createStatement())
        {
            // Consulta para ordenes
            String queryOrden = "SELECT id_orden FROM ordenes";
            ResultSet rsOrden = stmt.executeQuery(queryOrden);
            while (rsOrden.next())
            {
                comboBox1.addItem(rsOrden.getString("id_orden"));
            }
            // Consulta para productos
            String queryProducto = "SELECT id_producto FROM productos";
            ResultSet rsProducto = stmt.executeQuery(queryProducto);
            while (rsProducto.next())
            {
                comboBox2.addItem(rsProducto.getString("id_producto"));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar orden o producto");
        }


    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CRUD Orden_detalle");
        frame.setContentPane(new Orden_detalleGUI().main);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
    }
}
