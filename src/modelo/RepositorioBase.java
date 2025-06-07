package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositorioBase<T> {
    protected String archivo;

    public RepositorioBase(String archivo) {
        this.archivo = archivo;
    }

    public List<T> cargar() {
        List<T> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                T objeto = parsearLinea(linea);
                if (objeto != null) lista.add(objeto);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + archivo);
        }
        return lista;
    }

    public void guardar(List<T> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (T item : lista) {
                bw.write(formatearLinea(item));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + archivo);
        }
    }

    protected abstract T parsearLinea(String linea);
    protected abstract String formatearLinea(T item);
}
