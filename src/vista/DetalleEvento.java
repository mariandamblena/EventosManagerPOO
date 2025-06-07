package vista;

import modelo.Evento;
import modelo.Asistente;
import modelo.Recurso;

import javax.swing.*;
import java.awt.*;

public class DetalleEvento extends JDialog {
    public DetalleEvento(JFrame parent, Evento evento) {
        super(parent, "Detalle del Evento", true);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panelInfo = new JPanel(new GridLayout(3, 1));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información general"));
        panelInfo.add(new JLabel("Nombre: " + evento.getNombre()));
        panelInfo.add(new JLabel("Fecha: " + evento.getFechaComoTexto()));
        panelInfo.add(new JLabel("Lugar: " + evento.getLugar()));

        // Lista de asistentes
        DefaultListModel<String> modeloAsistentes = new DefaultListModel<>();
        for (Asistente a : evento.getAsistentes()) {
            modeloAsistentes.addElement(a.getNombre() + " - " + a.getEmail());
        }
        JList<String> listaAsistentes = new JList<>(modeloAsistentes);
        JScrollPane scrollAsistentes = new JScrollPane(listaAsistentes);
        scrollAsistentes.setBorder(BorderFactory.createTitledBorder("Asistentes"));

        // Lista de recursos
        DefaultListModel<String> modeloRecursos = new DefaultListModel<>();
        for (Recurso r : evento.getRecursosAsignados()) {
            modeloRecursos.addElement(r.getNombre() + " (" + r.getTipo() + ")");
        }
        JList<String> listaRecursos = new JList<>(modeloRecursos);
        JScrollPane scrollRecursos = new JScrollPane(listaRecursos);
        scrollRecursos.setBorder(BorderFactory.createTitledBorder("Recursos asignados"));

        JPanel panelListas = new JPanel(new GridLayout(1, 2));
        panelListas.add(scrollAsistentes);
        panelListas.add(scrollRecursos);

        add(panelInfo, BorderLayout.NORTH);
        add(panelListas, BorderLayout.CENTER);
    }
}
