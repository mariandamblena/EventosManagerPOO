package modelo;

import java.io.*;
import java.util.*;

public class RepositorioRecursosEvento {
    private static final String ARCHIVO = "recursos_eventos.txt";

    public static void guardar(List<Evento> eventos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Evento e : eventos) {
                for (Recurso r : e.getRecursosAsignados()) {
                    pw.println(e.getNombre() + ";" + r.getNombre() + ";" + r.getTipo());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargar(List<Evento> eventos, List<Recurso> recursosDisponibles) {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().split(";");
                if (partes.length == 3) {
                    String eventoNombre = partes[0];
                    String recursoNombre = partes[1];
                    String recursoTipo = partes[2];
                    for (Evento e : eventos) {
                        if (e.getNombre().equals(eventoNombre)) {
                            for (Recurso r : recursosDisponibles) {
                                if (r.getNombre().equals(recursoNombre) && r.getTipo().equals(recursoTipo)) {
                                    e.asignarRecurso(r);
                                    r.asignar();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
