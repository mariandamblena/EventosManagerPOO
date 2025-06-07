package modelo;

import java.io.*;
import java.util.*;

public class RepositorioRecursos {
    private static final String ARCHIVO = "recursos.txt";

    public static void guardarRecursos(List<Recurso> recursos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Recurso r : recursos) {
                pw.println(r.getNombre() + ";" + r.getTipo() + ";" + r.estaDisponible());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Recurso> cargarRecursos() {
        List<Recurso> recursos = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return recursos;

        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine()) {
                String[] partes = sc.nextLine().split(";");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    String tipo = partes[1];
                    boolean disponible = Boolean.parseBoolean(partes[2]);
                    Recurso r = new Recurso(nombre, tipo);
                    if (!disponible) r.asignar(); // si estaba ocupado
                    recursos.add(r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recursos;
    }
}
