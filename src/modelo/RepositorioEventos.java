package modelo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RepositorioEventos {
    private static final String ARCHIVO = "eventos.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void guardarEventos(List<Evento> eventos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Evento e : eventos) {
                pw.println(e.getNombre() + ";" + e.getFecha().format(FORMATO) + ";" + e.getLugar());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Evento> cargarEventos() {
        List<Evento> eventos = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return eventos;

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().split(";");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    LocalDate fecha = LocalDate.parse(partes[1], FORMATO);
                    String lugar = partes[2];
                    eventos.add(new Evento(nombre, fecha, lugar));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventos;
    }
}