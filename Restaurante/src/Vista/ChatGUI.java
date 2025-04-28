package Vista;

import Conexion.ConexionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatGUI
{
    private JPanel main;
    private JButton enviarMensajeButton;
    private JTextField textField1;
    private JTextArea textArea1;
    private JTable table1;

    String clienteSeleccionado = null;
    private java.util.Map<String, PrintWriter> clientesConectados = new java.util.HashMap<>();

    ConexionDB conexionDB = new ConexionDB();
    public ChatGUI()
    {
        obtenerDatos();
        iniciarServidor();

        textArea1.setEditable(false);
        textArea1.setLineWrap(true);

        enviarMensajeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                enviarMensaje();
            }
        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                int filaSeleccionada = table1.getSelectedRow();
                if (filaSeleccionada >= 0)
                {
                    textArea1.setColumns(1);
                    clienteSeleccionado = (String) table1.getValueAt(filaSeleccionada, 1); // Nombre del cliente
                }
            }
        });
    }

    public void obtenerDatos()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Clientes");
        model.addColumn("Nombres");
        model.addColumn("Telefono");
        model.addColumn("Correo");

        table1.setModel(model);

        String[] datos = new String[4];
        Connection con = conexionDB.getConnection();

        try
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");

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

    private void enviarMensaje()
    {
        if (clienteSeleccionado != null && clientesConectados.containsKey(clienteSeleccionado))
        {
            String mensaje = "Restaurante: " + textField1.getText();
            clientesConectados.get(clienteSeleccionado).println(mensaje);
            textArea1.append(mensaje + "\n");
            textField1.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Selecciona un cliente en la tabla para enviar mensaje");
        }
    }

    private void iniciarServidor()
    {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345))
            {
                textArea1.append("Servidor iniciado en el puerto 12345\n");

                while (true)
                {
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    // Cliente envía su nombre al conectarse
                    String nombreCliente = in.readLine();
                    clientesConectados.put(nombreCliente, out);

                    new Thread(() -> {
                        String mensajeRecibido;
                        try
                        {
                            while ((mensajeRecibido = in.readLine()) != null)
                            {
                                textArea1.append(mensajeRecibido + "\n");
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Chat Restaurante");
        frame.setContentPane(new ChatGUI().main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
    }
}
