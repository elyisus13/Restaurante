package Vista;

import Conexion.ConexionDB;
import Controlador.ReporteDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ResportesGUI
{

    private JPanel main;
    private JTable table1;
    private JButton ventasDiariasButton;
    private JButton clienteConMasCompraButton;
    private JButton productoMasVendidoButton;
    private JButton ventasMensualesButton;
    private JButton ventasSemanalesButton;
    private JTextField textField1;
    private JTextField textField2;

    ReporteDAO reporteDAO = new ReporteDAO();
    ConexionDB conexionDB = new ConexionDB();

    public ResportesGUI() {
        ventasDiariasButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double total = reporteDAO.ventasDiarias();
                textField1.setText("Ventas Diarias: $" + total);
            }
        });
        ventasSemanalesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double total = reporteDAO.ventasSemanales();
                textField1.setText("Ventas Semanales: $" + total);
            }
        });
        ventasMensualesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double total = reporteDAO.ventasMensuales();
                textField1.setText("Ventas Mensuales: $" + total);
            }
        });
        productoMasVendidoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String producto = reporteDAO.productoMasVendido();
                textField1.setText("Producto más vendido: " + producto);
            }
        });
        clienteConMasCompraButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cliente = reporteDAO.clienteConMasCompras();
                textField1.setText("Cliente con más compras: " + cliente);
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
                }
            }
        });
    }
    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Reporte");
        model.addColumn("Resultado");

        table1.setModel(model);

        Connection con = conexionDB.getConnection();
        String[] datos = new String[2];

        try {
            Statement stmt = con.createStatement();

            // Agregamos reportes predefinidos a la tabla
            datos[0] = "Ventas Diarias";
            datos[1] = String.valueOf(reporteDAO.ventasDiarias());
            model.addRow(datos);

            datos[0] = "Ventas Semanales";
            datos[1] = String.valueOf(reporteDAO.ventasSemanales());
            model.addRow(datos);

            datos[0] = "Ventas Mensuales";
            datos[1] = String.valueOf(reporteDAO.ventasMensuales());
            model.addRow(datos);

            datos[0] = "Producto Más Vendido";
            datos[1] = reporteDAO.productoMasVendido();
            model.addRow(datos);

            datos[0] = "Cliente con Más Compras";
            datos[1] = reporteDAO.clienteConMasCompras();
            model.addRow(datos);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Reportes de Restaurante");
        frame.setContentPane(new ResportesGUI().main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
    }
}
