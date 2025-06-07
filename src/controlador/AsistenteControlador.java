package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AsistenteControlador {
    private FormularioAsistentes vista;
    private Evento evento;

    public AsistenteControlador(JFrame parent, Evento evento) {
        this.evento = evento;
        this.vista = new FormularioAsistentes(parent);
        cargarAsistentes();

        vista.btnAgregar.addActionListener(e -> {
            String nombre = vista.campoNombre.getText();
            String email = vista.campoEmail.getText();
            if (!nombre.isEmpty() && !email.isEmpty()) {
                Asistente nuevo = new Asistente(nombre, email);
                evento.agregarAsistente(nuevo);

                // Guardar cambios
                RepositorioAsistentes.guardar(List.of(evento));
                RepositorioEventos.guardarEventos(List.of(evento));

                cargarAsistentes();
                limpiarCampos();
            }
        });

        vista.btnEditar.addActionListener(e -> editarAsistenteSeleccionado());
        vista.btnEliminar.addActionListener(e -> eliminarAsistenteSeleccionado());

        vista.setVisible(true);
    }

    private void cargarAsistentes() {
        vista.modeloAsistentes.clear();
        for (Asistente a : evento.getAsistentes()) {
            vista.modeloAsistentes.addElement(a.getNombre() + " - " + a.getEmail());
        }
    }

    private void limpiarCampos() {
        vista.campoNombre.setText("");
        vista.campoEmail.setText("");
    }

    private void editarAsistenteSeleccionado() {
        int index = vista.listaAsistentes.getSelectedIndex();
        if (index >= 0) {
            String nuevoNombre = vista.campoNombre.getText();
            String nuevoEmail = vista.campoEmail.getText();
            if (!nuevoNombre.isEmpty() && !nuevoEmail.isEmpty()) {
                evento.getAsistentes().set(index, new Asistente(nuevoNombre, nuevoEmail));

                // Guardar cambios
                RepositorioAsistentes.guardar(List.of(evento));
                RepositorioEventos.guardarEventos(List.of(evento));

                cargarAsistentes();
                limpiarCampos();
            }
        }
    }

    private void eliminarAsistenteSeleccionado() {
        int index = vista.listaAsistentes.getSelectedIndex();
        if (index >= 0) {
            evento.getAsistentes().remove(index);

            // Guardar cambios
            RepositorioAsistentes.guardar(List.of(evento));
            RepositorioEventos.guardarEventos(List.of(evento));

            cargarAsistentes();
            limpiarCampos();
        }
    }
}
