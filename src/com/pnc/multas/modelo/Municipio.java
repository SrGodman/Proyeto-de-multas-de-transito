package com.pnc.multas.modelo;

/**
 * Representa un municipio, asociado a un departamento (catálogo).
 */
public class Municipio {

    private int id;
    private int idDepartamento;
    private String nombre;

    public Municipio() {
    }

    public Municipio(int id, int idDepartamento, String nombre) {
        this.id = id;
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
