package Vista;

import Conexion.ConexionDB;
import Controlador.OrdenesDAO;
import Modelo.Ordenes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class OrdenesGUI {
    private JPanel main;
    private JTable table1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton detalleDeOrdenesButton;

    ConexionDB conexionDB = new ConexionDB();
    OrdenesDAO ordenesDAO = new OrdenesDAO();

    public OrdenesGUI()
    {
        comboBox1.addItem("En preparacion");
        comboBox1.addItem("Servida");
        comboBox1.addItem("Pagada");
        comb();
        obtenerDatos();
        agregarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int idclientes = Integer.parseInt((String) comboBox2.getSelectedItem());
                int id_empleado = Integer.parseInt((String) comboBox3.getSelectedItem());
                int id_mesa = Integer.parseInt((String) comboBox4.getSelectedItem());
                Double total = Double.parseDouble(textField3.getText());
                String fecha = textField2.getText();
                String estado = (String) comboBox1.getSelectedItem();

                Ordenes ordenes = new Ordenes(0,idclientes, id_empleado, id_mesa, total, fecha, estado);
                ordenesDAO.agregar(ordenes);
                obtenerDatos();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int idclientes = Integer.parseInt((String) comboBox2.getSelectedItem());
                int id_empleado = Integer.parseInt((String) comboBox3.getSelectedItem());
                int id_mesa = Integer.parseInt((String) comboBox4.getSelectedItem());
                double total = Double.parseDouble(textField3.getText());
                String fecha = textField2.getText();
                String estado = (String) comboBox1.getSelectedItem();
                int id_orden = Integer.parseInt(textField1.getText());

                Ordenes ordenes = new Ordenes(id_orden, idclientes, id_empleado, id_mesa, total, fecha, estado);
                ordenesDAO.actualizar(ordenes);
                obtenerDatos();
            }
        });
        eliminarButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id_orden = Integer.parseInt(textField1.getText());
                ordenesDAO.eliminar(id_orden);
                obtenerDatos();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int selectedRow = table1.getSelectedRow();

                if (selectedRow >= 0)
                {
                    //Para los combox
                    Object combox2 = table1.getValueAt(selectedRow, 1);
                    Object combox3 = table1.getValueAt(selectedRow, 2);
                    Object combox4 = table1.getValueAt(selectedRow, 3);
                    comboBox2.setSelectedItem(combox2);
                    comboBox3.setSelectedItem(combox3);
                    comboBox4.setSelectedItem(combox4);
                    textField1.setText((String) table1.getValueAt(selectedRow, 0));
                    textField2.setText((String) table1.getValueAt(selectedRow, 5));
                    textField3.setText((String) table1.getValueAt(selectedRow, 4));
                }
            }
        });
        detalleDeOrdenesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Vista.Orden_detalleGUI.main(null);
            }
        });
    }

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id_orden");
        model.addColumn("idcleinte");
        model.addColumn("id_empleado");
        model.addColumn("id_mesa");
        model.addColumn("total");
        model.addColumn("fecha");
        model.addColumn("estado");

        table1.setModel(model);
        String[] dato = new String[7];

        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT o.id_orden, o.idclientes, o.id_empleado, o.id_mesa, o.total, o.fecha, o.estado " +
                            "FROM ordenes o " +
                            "JOIN clientes c ON o.idclientes = c.idclientes " +
                            "JOIN empleados e ON o.id_empleado = e.id_empleado " +
                            "JOIN mesas m ON o.id_mesa = m.id_mesas");

            while (rs.next())
            {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                dato[6] = rs.getString(7);

                model.addRow(dato);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void comb() {
        comboBox2.removeAllItems(); // clientes
        comboBox3.removeAllItems(); // empleados

        Connection con = conexionDB.getConnection();

        try (Statement stmt = con.createStatement())
        {
            // Consulta para clientes
            String queryClientes = "SELECT idclientes FROM clientes";
            ResultSet rsClientes = stmt.executeQuery(queryClientes);
            while (rsClientes.next())
            {
                comboBox2.addItem(rsClientes.getString("idclientes"));
            }
            // Consulta para empleados
            String queryEmpleados = "SELECT id_empleado FROM empleados";
            ResultSet rsEmpleados = stmt.executeQuery(queryEmpleados);
            while (rsEmpleados.next())
            {
                comboBox3.addItem(rsEmpleados.getString("id_empleado"));
            }
            // Consulta para mesas
            String querymesas = "SELECT id_mesas FROM mesas";
            ResultSet rsMesas = stmt.executeQuery(querymesas);
            while (rsMesas.next())
            {
                comboBox4.addItem(rsMesas.getString("id_mesas"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar clientes o empleados");
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CRUD Ordenes");
        frame.setContentPane(new OrdenesGUI().main);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
    }
}
