package com.pnc.multas.modelo;

/**
 * Representa una multa de tránsito impuesta por la Policía Nacional Civil.
 *
 * Reglas de negocio relevantes (ver enunciado):
 *  - numeroMulta: correlativo-año (ej. 1-2022, 2-2022 ... 1-2023, 2-2023)
 *  - fechaHora: se genera automáticamente al guardar
 *  - dpiConductor, licenciaConductor y placaVehiculo son opcionales,
 *    EXCEPTO que la regla de negocio RN1 obliga a que al menos uno de
 *    los tres (placa, dpi o licencia) esté presente.
 *  - nombreConductor es obligatorio si se ingresa dpi o licencia.
 *  - justificacionAnulado es obligatorio únicamente si estado = ANULADA.
 */
public class Multa {

    public static final String ESTADO_ACTIVA = "ACTIVA";
    public static final String ESTADO_ANULADA = "ANULADA";
    public static final String ESTADO_PAGADA = "PAGADA";

    private int id;
    private String numeroMulta;      // correlativo-año, ej. 1-2022
    private String fechaHora;        // yyyy-MM-dd HH:mm:ss
    private String agente;
    private int idDepartamento;
    private String nombreDepartamento; // usado solo para mostrar en reportes/tablas
    private int idMunicipio;
    private String nombreMunicipio;    // usado solo para mostrar en reportes/tablas
    private String direccionExacta;
    private String dpiConductor;
    private String licenciaConductor;
    private String nombreConductor;
    private String placaVehiculo;
    private String marcaVehiculo;
    private String lineaVehiculo;
    private String colorVehiculo;
    private String estado;             // ACTIVA, ANULADA, PAGADA
    private String justificacionAnulado;

    public Multa() {
        this.estado = ESTADO_ACTIVA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroMulta() {
        return numeroMulta;
    }

    public void setNumeroMulta(String numeroMulta) {
        this.numeroMulta = numeroMulta;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getDireccionExacta() {
        return direccionExacta;
    }

    public void setDireccionExacta(String direccionExacta) {
        this.direccionExacta = direccionExacta;
    }

    public String getDpiConductor() {
        return dpiConductor;
    }

    public void setDpiConductor(String dpiConductor) {
        this.dpiConductor = dpiConductor;
    }

    public String getLicenciaConductor() {
        return licenciaConductor;
    }

    public void setLicenciaConductor(String licenciaConductor) {
        this.licenciaConductor = licenciaConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getLineaVehiculo() {
        return lineaVehiculo;
    }

    public void setLineaVehiculo(String lineaVehiculo) {
        this.lineaVehiculo = lineaVehiculo;
    }

    public String getColorVehiculo() {
        return colorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getJustificacionAnulado() {
        return justificacionAnulado;
    }

    public void setJustificacionAnulado(String justificacionAnulado) {
        this.justificacionAnulado = justificacionAnulado;
    }
}
