package vista;

import javax.swing.*;
import java.awt.*;

public class FormularioEvento extends JDialog {
    public JTextField campoNombre;
    public JTextField campoFecha;
    public JTextField campoLugar;
    public JButton btnGuardar;

    public FormularioEvento(JFrame parent) {
        super(parent, "Nuevo Evento", true);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        campoNombre = new JTextField();
        campoFecha = new JTextField("dd/MM/yyyy");
        campoFecha.setForeground(Color.GRAY);

        campoFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campoFecha.getText().equals("dd/MM/yyyy")) {
                    campoFecha.setText("");
                    campoFecha.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (campoFecha.getText().isEmpty()) {
                    campoFecha.setForeground(Color.GRAY);
                    campoFecha.setText("dd/MM/yyyy");
                }
            }
        });

        campoLugar = new JTextField();
        btnGuardar = new JButton("Guardar");

        add(new JLabel("Nombre:"));
        add(campoNombre);
        add(new JLabel("Fecha (dd/MM/yyyy):"));
        add(campoFecha);
        add(new JLabel("Lugar:"));
        add(campoLugar);
        add(new JLabel(""));
        add(btnGuardar);
    }
}
