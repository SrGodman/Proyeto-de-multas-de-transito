package com.pnc.multas.modelo;

/**
 * Detalle de infracción asociado a una multa. Una multa puede tener
 * varias infracciones (relación 1 a muchos).
 */
public class DetalleInfraccion {

    public static final String ESTADO_ACTIVO = "ACTIVO";
    public static final String ESTADO_INACTIVO = "INACTIVO";

    private int correlativo;         // se genera automáticamente (PK autoincrement)
    private int idMulta;
    private String numeroMulta;      // solo para mostrar en tablas/reportes
    private String articuloLey;
    private String descripcion;
    private double monto;
    private String estado;

    public DetalleInfraccion() {
        this.estado = ESTADO_ACTIVO;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public String getNumeroMulta() {
        return numeroMulta;
    }

    public void setNumeroMulta(String numeroMulta) {
        this.numeroMulta = numeroMulta;
    }

    public String getArticuloLey() {
        return articuloLey;
    }

    public void setArticuloLey(String articuloLey) {
        this.articuloLey = articuloLey;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
