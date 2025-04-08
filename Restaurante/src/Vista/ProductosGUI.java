package Vista;

import Conexion.ConexionDB;
import Controlador.ProductosDAO;
import Modelo.Productos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductosGUI
{
    private JPanel main;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JTextField textField5;


    ProductosDAO productosDAO =new ProductosDAO();
    ConexionDB conexionDB = new ConexionDB();

    public ProductosGUI()
    {
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField2.getText();
                String categoria = textField3.getText();
                double precio = Double.parseDouble(textField4.getText());
                int disponible = Integer.parseInt(textField5.getText());

                Productos producto = new Productos(0, nombre, categoria, precio, disponible);
                productosDAO.agregar(producto);
                obtenerDatos();
            }
        });
        editarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombre = textField2.getText();
                String categoria = textField3.getText();
                double precio = Double.parseDouble(textField4.getText());
                int disponible = Integer.parseInt(textField5.getText());
                int id = Integer.parseInt(textField1.getText());

                Productos producto = new Productos(id, nombre, categoria, precio, disponible);
                productosDAO.actualizar(producto);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_producto = Integer.parseInt(textField1.getText());
                productosDAO.eliminar(id_producto);
                obtenerDatos();
            }
        });
        table1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int selectRow = table1.getSelectedRow();

                if (selectRow >= 0)
                {
                    textField1.setText((String) table1.getValueAt(selectRow, 0));
                    textField2.setText((String) table1.getValueAt(selectRow, 1));
                    textField3.setText((String) table1.getValueAt(selectRow, 2));
                    textField4.setText((String) table1.getValueAt(selectRow, 3));
                    textField5.setText((String) table1.getValueAt(selectRow, 4));
                }
            }
        });
    }

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id_producto");
        model.addColumn("Nombre");
        model.addColumn("Categoría");
        model.addColumn("Precio");
        model.addColumn("Disponible");

        table1.setModel(model);

        String[] datos = new String[5];

        Connection con = conexionDB.getConnection();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                model.addRow(datos);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Crud Productos");
        frame.setContentPane(new ProductosGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900,600);
        frame.setResizable(false);
    }
}
