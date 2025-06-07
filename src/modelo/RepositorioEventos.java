package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RepositorioEventos extends RepositorioBase<Evento> {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RepositorioEventos() {
        super("eventos.txt");
    }

    @Override
    protected Evento parsearLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length == 3) {
            return new Evento(partes[0], LocalDate.parse(partes[1], FORMATO), partes[2]);
        }
        return null;
    }

    @Override
    protected String formatearLinea(Evento evento) {
        return evento.getNombre() + ";" + evento.getFechaComoTexto() + ";" + evento.getLugar();
    }

    // Métodos utilitarios si querés exponerlos directamente
    public static List<Evento> cargarEventos() {
        return new RepositorioEventos().cargar();
    }

    public static void guardarEventos(List<Evento> eventos) {
        new RepositorioEventos().guardar(eventos);
    }
}
