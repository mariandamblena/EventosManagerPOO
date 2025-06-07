package modelo;

import java.io.*;
import java.util.*;

public class RepositorioAsistentes {
    private static final String ARCHIVO = "asistentes.txt";

    public static void guardar(List<Evento> eventos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Evento e : eventos) {
                for (Asistente a : e.getAsistentes()) {
                    pw.println(e.getNombre() + ";" + a.getNombre());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargar(List<Evento> eventos) {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().split(";");
                if (partes.length == 2) {
                    String eventoNombre = partes[0];
                    String asistenteNombre = partes[1];
                    for (Evento e : eventos) {
                        if (e.getNombre().equals(eventoNombre)) {
                            e.agregarAsistente(new Asistente(asistenteNombre, ""));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}