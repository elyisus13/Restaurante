package Vista;

import Conexion.ConexionDB;
import Controlador.MesasDAO;
import Modelo.Mesas;

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

public class MesasGUI {
    private JPanel main;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;

    MesasDAO mesasDAO = new MesasDAO();

    public MesasGUI()
    {
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    // Convertir los valores de los campos a enteros
                    int id = Integer.parseInt(textField1.getText().trim());
                    int capacidad = Integer.parseInt(textField2.getText().trim());
                    String estatus_mesa = textField3.getText().trim();


                    // Validar que el estatus no esté vacío
                    if (estatus_mesa.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "El estatus no puede estar vacío");
                        return;
                    }
                    // Crear objeto Mesas y agregarlo
                    Mesas mesas = new Mesas(id, capacidad, estatus_mesa);
                    mesasDAO.agregar(mesas);

                    // Recargar los datos en la tabla
                    obtenerDatos();
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos en ID y Capacidad");
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_mesas = Integer.parseInt(textField1.getText().trim());
                int capacidad = Integer.parseInt(textField2.getText().trim());
                String estatus_mesa = textField3.getText().trim();


                Mesas mesas = new Mesas(id_mesas,capacidad, estatus_mesa);
                mesasDAO.actualizar(mesas);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_mesas = Integer.parseInt(textField1.getText());
                mesasDAO.eliminar(id_mesas);
                obtenerDatos();
            }
        });
        table1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                int selectFile = table1.getSelectedRow();

                if (selectFile >= 0)
                {
                    textField1.setText((String) table1.getValueAt(selectFile, 0));
                    textField2.setText((String) table1.getValueAt(selectFile, 1));
                    textField3.setText((String) table1.getValueAt(selectFile, 2));

                }
            }
        });
    }
    ConexionDB conexionDB = new ConexionDB();
    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Mesas");
        model.addColumn("Capacidad");
        model.addColumn("Estatus");

        table1.setModel(model);

        String[] datos = new String[3];

        Connection con = conexionDB.getConnection();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mesas");

            while (rs.next())
            {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                model.addRow(datos);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Crud mesas");
        frame.setContentPane(new MesasGUI().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
