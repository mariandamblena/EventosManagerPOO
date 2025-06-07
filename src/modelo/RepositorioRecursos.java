package modelo;

import java.util.List;

public class RepositorioRecursos extends RepositorioBase<Recurso> {

    public RepositorioRecursos() {
        super("recursos.txt");
    }

    @Override
    protected Recurso parsearLinea(String linea) {
        String[] datos = linea.split(";");
        if (datos.length == 3) {
            Recurso r = new Recurso(datos[0], datos[1]);
            if ("no".equalsIgnoreCase(datos[2])) r.asignar();
            return r;
        }
        return null;
    }

    @Override
    protected String formatearLinea(Recurso r) {
        return r.getNombre() + ";" + r.getTipo() + ";" + (r.estaDisponible() ? "si" : "no");
    }

    public static List<Recurso> cargarRecursos() {
        return new RepositorioRecursos().cargar();
    }

    public static void guardarRecursos(List<Recurso> recursos) {
        new RepositorioRecursos().guardar(recursos);
    }
}
