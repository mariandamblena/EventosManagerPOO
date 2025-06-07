// controlador/EventoControlador.java
package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EventoControlador {
    private List<Evento> eventos;
    private List<Recurso> recursos;
    private VentanaPrincipal vista;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EventoControlador() {
        recursos = RepositorioRecursos.cargarRecursos();
        eventos = RepositorioEventos.cargarEventos();
        vista = new VentanaPrincipal();
        cargarEventosEnVista();
        verificarEventosProximos();
        RepositorioAsistentes.cargar(eventos);
        RepositorioRecursosEvento.cargar(eventos, recursos);



        vista.btnAgregarEvento.addActionListener(e -> mostrarFormularioNuevoEvento());
        vista.btnEditarEvento.addActionListener(e -> editarEventoSeleccionado());
        vista.btnEliminarEvento.addActionListener(e -> eliminarEventoSeleccionado());
        vista.btnVerAsistentes.addActionListener(e -> verAsistentes());
        vista.btnVerRecursos.addActionListener(e -> verRecursos());
        vista.btnVerCalendario.addActionListener(e -> new CalendarioControlador(eventos));

        vista.listaEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = vista.listaEventos.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Evento seleccionado = eventos.get(index);
                        new DetalleEvento(vista, seleccionado).setVisible(true);
                    }
                }
            }
        });

        vista.setVisible(true);
    }

    private void verificarEventosProximos() {
        List<Evento> proximos = eventos.stream()
                .filter(e -> {
                    long dias = ChronoUnit.DAYS.between(LocalDate.now(), e.getFecha());
                    return dias >= 0 && dias <= 3;
                })
                .toList();

        if (!proximos.isEmpty()) {
            StringBuilder mensaje = new StringBuilder("Eventos próximos:\n");
            for (Evento e : proximos) {
                mensaje.append("- ").append(e.getNombre()).append(" (" + e.getFecha().format(FORMATO_FECHA) + ")\n");
            }
            JOptionPane.showMessageDialog(vista, mensaje.toString(), "Notificación", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarEventosEnVista() {
        vista.modeloLista.clear();
        for (Evento e : eventos) {
            vista.modeloLista.addElement(e.getNombre() + " - " + e.getFechaComoTexto());
        }
    }

    private void mostrarFormularioNuevoEvento() {
        FormularioEvento form = new FormularioEvento(vista);
        form.btnGuardar.addActionListener(ae -> {
            String nombre = form.campoNombre.getText();
            String fechaTexto = form.campoFecha.getText();
            String lugar = form.campoLugar.getText();
            try {
                LocalDate fecha = LocalDate.parse(fechaTexto, FORMATO_FECHA);
                eventos.add(new Evento(nombre, fecha, lugar));
                RepositorioEventos.guardarEventos(eventos);
                form.dispose();
                cargarEventosEnVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto. Use dd/MM/yyyy");
            }
        });
        form.setVisible(true);
    }

    private void editarEventoSeleccionado() {
        int index = vista.listaEventos.getSelectedIndex();
        if (index >= 0) {
            Evento original = eventos.get(index);
            FormularioEvento form = new FormularioEvento(vista);
            form.campoNombre.setText(original.getNombre());
            form.campoFecha.setText(original.getFechaComoTexto());
            form.campoLugar.setText(original.getLugar());

            form.btnGuardar.addActionListener(ae -> {
                try {
                    String nuevoNombre = form.campoNombre.getText();
                    LocalDate nuevaFecha = LocalDate.parse(form.campoFecha.getText(), FORMATO_FECHA);
                    String nuevoLugar = form.campoLugar.getText();
                    Evento actualizado = new Evento(nuevoNombre, nuevaFecha, nuevoLugar);
                    eventos.set(index, actualizado);
                    RepositorioEventos.guardarEventos(eventos);
                    form.dispose();
                    cargarEventosEnVista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto. Use dd/MM/yyyy");
                }
            });

            form.setVisible(true);
        }
    }

    private void eliminarEventoSeleccionado() {
        int index = vista.listaEventos.getSelectedIndex();
        if (index >= 0) {
            int confirm = JOptionPane.showConfirmDialog(vista,
                    "¿Estás seguro de eliminar este evento?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Evento aEliminar = eventos.get(index);

                for (Recurso r : aEliminar.getRecursosAsignados()) {
                    r.liberar();
                }

                RepositorioRecursos.guardarRecursos(recursos);

                eventos.remove(index);
                RepositorioEventos.guardarEventos(eventos);
                cargarEventosEnVista();
            }
        }
    }

    private void verAsistentes() {
        int index = vista.listaEventos.getSelectedIndex();
        if (index >= 0) {
            Evento evento = eventos.get(index);
            new AsistenteControlador(vista, evento);
        }
    }

    private void verRecursos() {
        int index = vista.listaEventos.getSelectedIndex();
        if (index >= 0) {
            Evento evento = eventos.get(index);
            new RecursoControlador(vista, evento);
        }
    }
}
