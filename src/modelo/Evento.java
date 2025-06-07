package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nombre;
    private LocalDate fecha;
    private String lugar;
    private List<Asistente> asistentes;
    private List<Recurso> recursosAsignados;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Evento(String nombre, String fechaTexto, String lugar) {
        this.nombre = nombre;
        this.fecha = LocalDate.parse(fechaTexto, FORMATO_FECHA);
        this.lugar = lugar;
        this.asistentes = new ArrayList<>();
        this.recursosAsignados = new ArrayList<>();
    }

    public Evento(String nombre, LocalDate fecha, String lugar) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.asistentes = new ArrayList<>();
        this.recursosAsignados = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }
    public String getLugar() { return lugar; }
    public String getFechaComoTexto() { return fecha.format(FORMATO_FECHA); }

    public List<Asistente> getAsistentes() { return asistentes; }
    public List<Recurso> getRecursosAsignados() { return recursosAsignados; }

    public void agregarAsistente(Asistente a) {
        asistentes.add(a);
    }

    public void asignarRecurso(Recurso r) {
        if (!recursosAsignados.contains(r)) {
            recursosAsignados.add(r);
        }
    }

    public String toArchivo() {
        return nombre + ";" + fecha.format(FORMATO_FECHA) + ";" + lugar;
    }
}
