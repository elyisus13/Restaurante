package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalGUI {
    private JPanel main;
    private JButton abrirChatButton;
    private JButton chatButton;
    private JButton gestionDeVentasButton;
    private JButton gestionDeEmpleadosButton;
    private JButton generacionDeResportesButton;
    private JButton clientesButton;
    private JButton gestionDeOrdenesButton;
    private JButton gestionDeProductosButton;
    private JButton gestiosDeMesasButton;

    public MenuPrincipalGUI() {
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Vista.ClientesGUI.main(null);
            }
        });
        gestionDeEmpleadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        gestiosDeMesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Vista.MesasGUI.main(null);
            }
        });
        gestionDeProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Vista.ProductosGUI.main(null);
            }
        });
        gestionDeOrdenesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        generacionDeResportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        gestionDeVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Gestion de Restaurante");
        frame.setContentPane(new MenuPrincipalGUI().main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setResizable(false);
    }
}
