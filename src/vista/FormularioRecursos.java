package vista;

import javax.swing.*;
import java.awt.*;

public class FormularioRecursos extends JDialog {
    public DefaultListModel<String> modeloRecursos;
    public JList<String> listaRecursos;
    public JButton btnAgregar;
    public JButton btnEditar;
    public JButton btnEliminar;
    public JButton btnAsignar;
    public JButton btnDesasignar;

    public JTextField campoNombre;
    public JTextField campoTipo;

    public FormularioRecursos(JFrame parent) {
        super(parent, "Gestión de Recursos", true);
        setSize(500, 400);
        setLayout(new BorderLayout());

        modeloRecursos = new DefaultListModel<>();
        listaRecursos = new JList<>(modeloRecursos);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(new JScrollPane(listaRecursos), BorderLayout.CENTER);

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2));
        campoNombre = new JTextField();
        campoTipo = new JTextField();
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(campoNombre);
        panelFormulario.add(new JLabel("Tipo:"));
        panelFormulario.add(campoTipo);
        panelCentro.add(panelFormulario, BorderLayout.SOUTH);

        // Botonera
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnAsignar = new JButton("Asignar");
        btnDesasignar = new JButton("Desasignar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAsignar);
        panelBotones.add(btnDesasignar);

        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
