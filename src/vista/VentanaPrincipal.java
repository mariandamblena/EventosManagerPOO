package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public JButton btnAgregarEvento;
    public JButton btnEditarEvento;
    public JButton btnVerAsistentes;
    public JButton btnVerRecursos;
    public JButton btnVerCalendario; // NUEVO
    public JList<String> listaEventos;
    public DefaultListModel<String> modeloLista;
    public JButton btnEliminarEvento; // nuevo

    public VentanaPrincipal() {
        setTitle("Gestión de Eventos");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        listaEventos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaEventos);

        btnAgregarEvento = new JButton("Agregar Evento");
        btnEditarEvento = new JButton("Editar Evento");
        btnEliminarEvento = new JButton("Eliminar Evento");
        btnVerAsistentes = new JButton("Asistentes");
        btnVerRecursos = new JButton("Recursos");
        btnVerCalendario = new JButton("Ver Calendario");


        JPanel botones = new JPanel(new GridLayout(2, 3, 10, 10));
        botones.add(btnAgregarEvento);
        botones.add(btnEditarEvento);
        botones.add(btnEliminarEvento);
        botones.add(btnVerAsistentes);
        botones.add(btnVerRecursos);
        botones.add(btnVerCalendario);


        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(botones, BorderLayout.SOUTH);
    }
}