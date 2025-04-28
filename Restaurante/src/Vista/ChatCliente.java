package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatCliente {
    private JPanel main;
    private JTextArea textArea1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton enviarMensajeButton;

    private PrintWriter out;

    public ChatCliente()
    {
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
        conectarAlServidor();
    }

    private void enviarMensaje()
    {
        String mensaje = textField1.getText() + ": " + textField2.getText();
        if (!mensaje.isEmpty())
        {
            out.println(mensaje);
            textArea1.append(mensaje + "\n");
            textField2.setText("");
        }
    }

    private void conectarAlServidor()
    {
        try
        {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);

            String nombreCliente = JOptionPane.showInputDialog("Ingresa tu nombre:");
            textField1.setText(nombreCliente);
            out.println(nombreCliente);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(() -> {
                String mensaje;
                try
                {
                    while ((mensaje = in.readLine()) != null)
                    {
                        textArea1.append(mensaje + "\n");
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Chat Cliente");
        frame.setContentPane(new ChatCliente().main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
    }
}
