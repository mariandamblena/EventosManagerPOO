package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import java.util.List;

public class RecursoControlador {
    private FormularioRecursos vista;
    private Evento evento;
    private List<Recurso> recursos;

    public RecursoControlador(JFrame parent, Evento evento) {
        this.evento = evento;
        this.vista = new FormularioRecursos(parent);
        this.recursos = RepositorioRecursos.cargarRecursos();

        cargarRecursos();

        vista.btnAgregar.addActionListener(e -> agregarRecurso());
        vista.btnEditar.addActionListener(e -> editarRecurso());
        vista.btnEliminar.addActionListener(e -> eliminarRecurso());
        vista.btnAsignar.addActionListener(e -> asignarRecurso());
        vista.btnDesasignar.addActionListener(e -> desasignarRecurso());

        vista.setVisible(true);
    }

    private void cargarRecursos() {
        vista.modeloRecursos.clear();
        for (Recurso r : recursos) {
            String asignado = "";
            if (evento.getRecursosAsignados().contains(r)) {
                asignado = " (Asignado)";
            }

            String estado;
            if (r.estaDisponible()) {
                estado = "Disponible";
            } else {
                estado = "Ocupado";
            }

            vista.modeloRecursos.addElement(r.getNombre() + " - " + r.getTipo() + " - " + estado + asignado);
        }
    }

    private void agregarRecurso() {
        String nombre = vista.campoNombre.getText();
        String tipo = vista.campoTipo.getText();
        if (!nombre.isEmpty() && !tipo.isEmpty()) {
            Recurso r = new Recurso(nombre, tipo);
            recursos.add(r);
            RepositorioRecursos.guardarRecursos(recursos);
            cargarRecursos();
            limpiarCampos();
        }
    }

    private void editarRecurso() {
        int index = vista.listaRecursos.getSelectedIndex();
        if (index >= 0) {
            String nombre = vista.campoNombre.getText();
            String tipo = vista.campoTipo.getText();
            if (!nombre.isEmpty() && !tipo.isEmpty()) {
                Recurso original = recursos.get(index);
                boolean disponible = original.estaDisponible();
                recursos.set(index, new Recurso(nombre, tipo));
                if (!disponible) recursos.get(index).asignar(); // mantener estado
                RepositorioRecursos.guardarRecursos(recursos);
                cargarRecursos();
                limpiarCampos();
            }
        }
    }

    private void eliminarRecurso() {
        int index = vista.listaRecursos.getSelectedIndex();
        if (index >= 0) {
            Recurso r = recursos.get(index);
            recursos.remove(index);
            evento.getRecursosAsignados().remove(r); // si estaba asignado
            RepositorioRecursos.guardarRecursos(recursos);
            cargarRecursos();
        }
    }

    private void asignarRecurso() {
        int index = vista.listaRecursos.getSelectedIndex();
        if (index >= 0) {
            Recurso r = recursos.get(index);
            if (r.estaDisponible()) {
                evento.asignarRecurso(r);
                r.asignar();
                RepositorioRecursos.guardarRecursos(recursos);
                RepositorioRecursosEvento.guardar(List.of(evento));// ← persistir relación
                cargarRecursos();
            }
        }
    }

    private void desasignarRecurso() {
        int index = vista.listaRecursos.getSelectedIndex();
        if (index >= 0) {
            Recurso seleccionado = recursos.get(index);

            Recurso asignado = evento.getRecursosAsignados().stream()
                    .filter(r -> r.getNombre().equals(seleccionado.getNombre()) &&
                            r.getTipo().equals(seleccionado.getTipo()))
                    .findFirst().orElse(null);

            if (asignado != null) {
                evento.getRecursosAsignados().remove(asignado);
                seleccionado.liberar();
                RepositorioRecursos.guardarRecursos(recursos);
                RepositorioRecursosEvento.guardar(List.of(evento));// ← persistir relación
                cargarRecursos();
            }
        }
    }


    private void limpiarCampos() {
        vista.campoNombre.setText("");
        vista.campoTipo.setText("");
    }
}
