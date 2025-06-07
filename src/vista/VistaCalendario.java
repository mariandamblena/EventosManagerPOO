package vista;

import javax.swing.*;
import java.awt.*;

public class VistaCalendario extends JFrame {
    public JLabel lblMes;
    public JButton btnAnterior;
    public JButton btnSiguiente;
    public JPanel panelDias;

    public VistaCalendario() {
        setTitle("Calendario de Eventos");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior (Mes y botones)
        JPanel panelSuperior = new JPanel(new BorderLayout());

        btnAnterior = new JButton("<");
        btnSiguiente = new JButton(">");
        lblMes = new JLabel("Mes Actual", SwingConstants.CENTER);
        lblMes.setFont(new Font("Arial", Font.BOLD, 20));

        panelSuperior.add(btnAnterior, BorderLayout.WEST);
        panelSuperior.add(lblMes, BorderLayout.CENTER);
        panelSuperior.add(btnSiguiente, BorderLayout.EAST);

        // Panel central (grilla de días)
        panelDias = new JPanel();
        panelDias.setLayout(new GridLayout(0, 7, 5, 5));
        panelDias.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Agregar encabezados de días
        String[] diasSemana = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            panelDias.add(label);
        }

        add(panelSuperior, BorderLayout.NORTH);
        add(panelDias, BorderLayout.CENTER);
    }

    public void limpiarDias() {
        Component[] componentes = panelDias.getComponents();
        for (int i = 7; i < componentes.length; i++) {
            panelDias.remove(componentes[i]);
        }
    }

    public void agregarDia(JPanel diaPanel) {
        panelDias.add(diaPanel);
    }
}
