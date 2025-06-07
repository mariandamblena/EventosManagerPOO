package vista;

import javax.swing.*;
import java.awt.*;

public class FormularioAsistentes extends JDialog {
    public JTextField campoNombre;
    public JTextField campoEmail;
    public JButton btnAgregar;
    public JButton btnEditar;
    public JButton btnEliminar;
    public DefaultListModel<String> modeloAsistentes;
    public JList<String> listaAsistentes;

    public FormularioAsistentes(JFrame parent) {
        super(parent, "Asistentes", true);
        setSize(400, 350);
        setLayout(new BorderLayout());

        // Panel de ingreso
        JPanel panelSuperior = new JPanel(new GridLayout(2, 2));
        campoNombre = new JTextField();
        campoEmail = new JTextField();
        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(campoNombre);
        panelSuperior.add(new JLabel("Email:"));
        panelSuperior.add(campoEmail);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        // Lista de asistentes
        modeloAsistentes = new DefaultListModel<>();
        listaAsistentes = new JList<>(modeloAsistentes);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(listaAsistentes), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
