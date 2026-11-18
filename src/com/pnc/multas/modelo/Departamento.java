package com.pnc.multas.modelo;

/**
 * Representa un departamento de la República (catálogo).
 */
public class Departamento {

    private int id;
    private String nombre;

    public Departamento() {
    }

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Se sobre-escribe toString para que el objeto se muestre
     * correctamente dentro de un JComboBox.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
