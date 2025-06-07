
package controlador;

import modelo.Evento;
import vista.VistaCalendario;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarioControlador {
    private List<Evento> eventos;
    private VistaCalendario vista;
    private YearMonth mesActual;

    public CalendarioControlador(List<Evento> eventos) {
        this.eventos = eventos;
        this.mesActual = YearMonth.now();
        this.vista = new VistaCalendario();

        actualizarVista();

        vista.btnAnterior.addActionListener(e -> {
            mesActual = mesActual.minusMonths(1);
            actualizarVista();
        });

        vista.btnSiguiente.addActionListener(e -> {
            mesActual = mesActual.plusMonths(1);
            actualizarVista();
        });

        vista.setVisible(true);
    }

    private void actualizarVista() {
        vista.lblMes.setText(mesActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es")) + " " + mesActual.getYear());
        vista.limpiarDias();

        LocalDate primerDia = mesActual.atDay(1);
        int diaSemanaInicio = primerDia.getDayOfWeek().getValue();
        if (diaSemanaInicio == 7) diaSemanaInicio = 0; // domingo como 0

        // Rellenar espacios vacíos antes del primer día
        for (int i = 0; i < diaSemanaInicio; i++) {
            vista.agregarDia(new JPanel());
        }

        int diasEnMes = mesActual.lengthOfMonth();
        for (int dia = 1; dia <= diasEnMes; dia++) {
            LocalDate fecha = mesActual.atDay(dia);
            JPanel panelDia = new JPanel();
            panelDia.setLayout(new BoxLayout(panelDia, BoxLayout.Y_AXIS));
            panelDia.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel lblDia = new JLabel(String.valueOf(dia));
            lblDia.setFont(new Font("Arial", Font.BOLD, 12));
            panelDia.add(lblDia);

            List<Evento> eventosDelDia = eventos.stream()
                    .filter(ev -> ev.getFecha().equals(fecha))
                    .collect(Collectors.toList());

            for (Evento ev : eventosDelDia) {
                JLabel lblEvento = new JLabel("- " + ev.getNombre());
                lblEvento.setFont(new Font("Arial", Font.PLAIN, 11));
                panelDia.add(lblEvento);
            }

            vista.agregarDia(panelDia);
        }

        vista.panelDias.revalidate();
        vista.panelDias.repaint();
    }
}
