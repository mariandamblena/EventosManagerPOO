package modelo;

public class Recurso {
    private String nombre;
    private String tipo;
    private boolean disponible;

    public Recurso(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponible = true;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public boolean estaDisponible() { return disponible; }

    public void asignar() { this.disponible = false; }
    public void liberar() { this.disponible = true; }

    public String toArchivo() {
        return nombre + ";" + tipo + ";" + (disponible ? "si" : "no");
    }
}
