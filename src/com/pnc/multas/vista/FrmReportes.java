package com.pnc.multas.vista;

import com.pnc.multas.dao.DepartamentoDAO;
import com.pnc.multas.dao.MultaDAO;
import com.pnc.multas.dao.MunicipioDAO;
import com.pnc.multas.modelo.Departamento;
import com.pnc.multas.modelo.Multa;
import com.pnc.multas.modelo.Municipio;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla de Reportes. Diseñada con el editor gráfico de NetBeans
 * (ver FrmReportes.form). Contiene las cuatro consultas solicitadas:
 *  1) Multas por día / mes / año / rango de fechas (con filtro de estado)
 *  2) Multas por municipio o departamento (con filtro de estado)
 *  3) Historial de multas por agente
 *  4) Búsqueda de una multa por número, placa, dpi o licencia
 */
public class FrmReportes extends javax.swing.JFrame {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final MultaDAO multaDAO = new MultaDAO();
    private final DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private final MunicipioDAO municipioDAO = new MunicipioDAO();

    private DefaultTableModel modeloResultados;

    public FrmReportes() {
        this(null);
    }

    /** @param menu ventana del menú principal a la que se regresa al cerrar esta pantalla (puede ser null) */
    public FrmReportes(JFrame menu) {
        initComponents();
        if (menu != null) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    menu.setVisible(true);
                }
            });
        }
        setSize(1100, 700);
        setLocationRelativeTo(null);

        modeloResultados = (DefaultTableModel) tablaResultados.getModel();
        tablaResultados.setAutoCreateRowSorter(true);

        configurarSpinners();
        actualizarPeriodoVisible();

        cboDeptoReporte.addItem("Todos los departamentos");
        for (Departamento d : departamentoDAO.listarTodos()) {
            cboDeptoReporte.addItem(d);
        }
        cargarMunicipiosReporte();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        pnlFecha = new javax.swing.JPanel();
        pnlFechaFila1 = new javax.swing.JPanel();
        lblConsultarPor = new javax.swing.JLabel();
        cboTipoPeriodo = new javax.swing.JComboBox<>();
        lblEstadoFecha = new javax.swing.JLabel();
        cboEstadoFecha = new javax.swing.JComboBox<>();
        pnlFechaFila2 = new javax.swing.JPanel();
        pnlDia = new javax.swing.JPanel();
        lblDia = new javax.swing.JLabel();
        spDia = new javax.swing.JSpinner();
        pnlMes = new javax.swing.JPanel();
        lblMes = new javax.swing.JLabel();
        cboMes = new javax.swing.JComboBox<>();
        lblAnioMes = new javax.swing.JLabel();
        spAnioMes = new javax.swing.JSpinner();
        pnlAnio = new javax.swing.JPanel();
        lblAnio = new javax.swing.JLabel();
        spAnio = new javax.swing.JSpinner();
        pnlRango = new javax.swing.JPanel();
        lblDesde = new javax.swing.JLabel();
        spRangoDesde = new javax.swing.JSpinner();
        lblHasta = new javax.swing.JLabel();
        spRangoHasta = new javax.swing.JSpinner();
        pnlFechaFila3 = new javax.swing.JPanel();
        btnGenerarFecha = new javax.swing.JButton();
        pnlUbicacion = new javax.swing.JPanel();
        lblDepartamento = new javax.swing.JLabel();
        cboDeptoReporte = new javax.swing.JComboBox<>();
        lblMunicipio = new javax.swing.JLabel();
        cboMuniReporte = new javax.swing.JComboBox<>();
        lblEstadoUbicacion = new javax.swing.JLabel();
        cboEstadoUbicacion = new javax.swing.JComboBox<>();
        btnGenerarUbicacion = new javax.swing.JButton();
        pnlAgente = new javax.swing.JPanel();
        lblAgenteReporte = new javax.swing.JLabel();
        txtAgenteReporte = new javax.swing.JTextField();
        btnVerHistorial = new javax.swing.JButton();
        pnlBuscar = new javax.swing.JPanel();
        lblCriterio = new javax.swing.JLabel();
        txtCriterioBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        scrollResultados = new javax.swing.JScrollPane();
        tablaResultados = new javax.swing.JTable();
        pnlInferior = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes de Multas");

        pnlFecha.setLayout(new java.awt.GridLayout(3, 1));

        pnlFechaFila1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblConsultarPor.setText("Consultar por:");
        pnlFechaFila1.add(lblConsultarPor);

        cboTipoPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Día", "Mes", "Año", "Rango de fechas" }));
        cboTipoPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoPeriodoActionPerformed(evt);
            }
        });
        pnlFechaFila1.add(cboTipoPeriodo);

        lblEstadoFecha.setText("Estado:");
        pnlFechaFila1.add(lblEstadoFecha);

        cboEstadoFecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODAS", "ACTIVA", "ANULADA", "PAGADA" }));
        pnlFechaFila1.add(cboEstadoFecha);

        pnlFecha.add(pnlFechaFila1);

        pnlFechaFila2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnlDia.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblDia.setText("Fecha:");
        pnlDia.add(lblDia);
        pnlDia.add(spDia);

        pnlFechaFila2.add(pnlDia);

        pnlMes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblMes.setText("Mes:");
        pnlMes.add(lblMes);

        cboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        pnlMes.add(cboMes);

        lblAnioMes.setText("Año:");
        pnlMes.add(lblAnioMes);
        pnlMes.add(spAnioMes);

        pnlFechaFila2.add(pnlMes);

        pnlAnio.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblAnio.setText("Año:");
        pnlAnio.add(lblAnio);
        pnlAnio.add(spAnio);

        pnlFechaFila2.add(pnlAnio);

        pnlRango.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblDesde.setText("Desde:");
        pnlRango.add(lblDesde);
        pnlRango.add(spRangoDesde);

        lblHasta.setText("Hasta:");
        pnlRango.add(lblHasta);
        pnlRango.add(spRangoHasta);

        pnlFechaFila2.add(pnlRango);

        pnlFecha.add(pnlFechaFila2);

        pnlFechaFila3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnGenerarFecha.setText("Generar reporte");
        btnGenerarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarFechaActionPerformed(evt);
            }
        });
        pnlFechaFila3.add(btnGenerarFecha);

        pnlFecha.add(pnlFechaFila3);

        tabs.addTab("Por fecha", pnlFecha);

        pnlUbicacion.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblDepartamento.setText("Departamento:");
        pnlUbicacion.add(lblDepartamento);

        cboDeptoReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDeptoReporteActionPerformed(evt);
            }
        });
        pnlUbicacion.add(cboDeptoReporte);

        lblMunicipio.setText("Municipio:");
        pnlUbicacion.add(lblMunicipio);
        pnlUbicacion.add(cboMuniReporte);

        lblEstadoUbicacion.setText("Estado:");
        pnlUbicacion.add(lblEstadoUbicacion);

        cboEstadoUbicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODAS", "ACTIVA", "ANULADA", "PAGADA" }));
        pnlUbicacion.add(cboEstadoUbicacion);

        btnGenerarUbicacion.setText("Generar reporte");
        btnGenerarUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarUbicacionActionPerformed(evt);
            }
        });
        pnlUbicacion.add(btnGenerarUbicacion);

        tabs.addTab("Por departamento / municipio", pnlUbicacion);

        pnlAgente.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblAgenteReporte.setText("Nombre del agente:");
        pnlAgente.add(lblAgenteReporte);

        txtAgenteReporte.setColumns(20);
        pnlAgente.add(txtAgenteReporte);

        btnVerHistorial.setText("Ver historial");
        btnVerHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerHistorialActionPerformed(evt);
            }
        });
        pnlAgente.add(btnVerHistorial);

        tabs.addTab("Historial por agente", pnlAgente);

        pnlBuscar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblCriterio.setText("Número de multa, placa, DPI o licencia:");
        pnlBuscar.add(lblCriterio);

        txtCriterioBusqueda.setColumns(20);
        pnlBuscar.add(txtCriterioBusqueda);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBuscar.add(btnBuscar);

        tabs.addTab("Buscar multa", pnlBuscar);

        getContentPane().add(tabs, java.awt.BorderLayout.NORTH);

        scrollResultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));

        tablaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Fecha/Hora", "Agente", "Departamento", "Municipio", "Dirección", "Placa", "DPI", "Licencia", "Conductor", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollResultados.setViewportView(tablaResultados);

        getContentPane().add(scrollResultados, java.awt.BorderLayout.CENTER);

        pnlInferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnRegresar.setText("Regresar al menú principal");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        pnlInferior.add(btnRegresar);

        getContentPane().add(pnlInferior, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTipoPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoPeriodoActionPerformed
        actualizarPeriodoVisible();
    }//GEN-LAST:event_cboTipoPeriodoActionPerformed

    private void btnGenerarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarFechaActionPerformed
        generarReportePorFecha();
    }//GEN-LAST:event_btnGenerarFechaActionPerformed

    private void cboDeptoReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDeptoReporteActionPerformed
        cargarMunicipiosReporte();
    }//GEN-LAST:event_cboDeptoReporteActionPerformed

    private void btnGenerarUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarUbicacionActionPerformed
        generarReportePorUbicacion();
    }//GEN-LAST:event_btnGenerarUbicacionActionPerformed

    private void btnVerHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHistorialActionPerformed
        verHistorialAgente();
    }//GEN-LAST:event_btnVerHistorialActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarMultaReporte();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    // -----------------------------------------------------------------
    // Reporte por fecha (día, mes, año o rango) con filtro de estado
    // -----------------------------------------------------------------

    private void configurarSpinners() {
        int anio = LocalDate.now().getYear();
        spDia.setModel(new SpinnerDateModel());
        spDia.setEditor(new JSpinner.DateEditor(spDia, "yyyy-MM-dd"));
        spRangoDesde.setModel(new SpinnerDateModel());
        spRangoDesde.setEditor(new JSpinner.DateEditor(spRangoDesde, "yyyy-MM-dd"));
        spRangoHasta.setModel(new SpinnerDateModel());
        spRangoHasta.setEditor(new JSpinner.DateEditor(spRangoHasta, "yyyy-MM-dd"));
        spAnioMes.setModel(new SpinnerNumberModel(anio, 2000, 2100, 1));
        spAnioMes.setEditor(new JSpinner.NumberEditor(spAnioMes, "#"));
        spAnio.setModel(new SpinnerNumberModel(anio, 2000, 2100, 1));
        spAnio.setEditor(new JSpinner.NumberEditor(spAnio, "#"));
    }

    /** Muestra solo los controles del período elegido (día, mes, año o rango). */
    private void actualizarPeriodoVisible() {
        String tipo = (String) cboTipoPeriodo.getSelectedItem();
        pnlDia.setVisible("Día".equals(tipo));
        pnlMes.setVisible("Mes".equals(tipo));
        pnlAnio.setVisible("Año".equals(tipo));
        pnlRango.setVisible("Rango de fechas".equals(tipo));
    }

    private void generarReportePorFecha() {
        String tipo = (String) cboTipoPeriodo.getSelectedItem();
        String estado = (String) cboEstadoFecha.getSelectedItem();
        LocalDate desde;
        LocalDate hasta;

        switch (tipo) {
            case "Día":
                desde = toLocalDate((Date) spDia.getValue());
                hasta = desde;
                break;
            case "Mes":
                int mes = cboMes.getSelectedIndex() + 1;
                int anioMes = (int) spAnioMes.getValue();
                YearMonth ym = YearMonth.of(anioMes, mes);
                desde = ym.atDay(1);
                hasta = ym.atEndOfMonth();
                break;
            case "Año":
                int anio = (int) spAnio.getValue();
                desde = LocalDate.of(anio, 1, 1);
                hasta = LocalDate.of(anio, 12, 31);
                break;
            default: // Rango de fechas
                desde = toLocalDate((Date) spRangoDesde.getValue());
                hasta = toLocalDate((Date) spRangoHasta.getValue());
        }

        if (desde.isAfter(hasta)) {
            JOptionPane.showMessageDialog(this, "La fecha 'desde' no puede ser posterior a la fecha 'hasta'.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Multa> resultado = multaDAO.reportePorFecha(desde.format(FMT), hasta.format(FMT), estado);
        mostrarResultados(resultado);
    }

    private LocalDate toLocalDate(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // -----------------------------------------------------------------
    // Reporte por departamento / municipio con filtro de estado
    // -----------------------------------------------------------------

    private void cargarMunicipiosReporte() {
        cboMuniReporte.removeAllItems();
        cboMuniReporte.addItem("Todos los municipios");
        Object seleccionado = cboDeptoReporte.getSelectedItem();
        if (seleccionado instanceof Departamento) {
            List<Municipio> lista = municipioDAO.listarPorDepartamento(((Departamento) seleccionado).getId());
            for (Municipio m : lista) {
                cboMuniReporte.addItem(m);
            }
        }
    }

    private void generarReportePorUbicacion() {
        Object depto = cboDeptoReporte.getSelectedItem();
        Object muni = cboMuniReporte.getSelectedItem();
        Integer idDepto = (depto instanceof Departamento) ? ((Departamento) depto).getId() : null;
        Integer idMuni = (muni instanceof Municipio) ? ((Municipio) muni).getId() : null;
        String estado = (String) cboEstadoUbicacion.getSelectedItem();

        List<Multa> resultado = multaDAO.reportePorUbicacion(idDepto, idMuni, estado);
        mostrarResultados(resultado);
    }

    // -----------------------------------------------------------------
    // Historial de multas por agente
    // -----------------------------------------------------------------

    private void verHistorialAgente() {
        String agente = txtAgenteReporte.getText().trim();
        if (agente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre (o parte del nombre) del agente.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mostrarResultados(multaDAO.historialPorAgente(agente));
    }

    // -----------------------------------------------------------------
    // Búsqueda general (número de multa, placa, dpi o licencia)
    // -----------------------------------------------------------------

    private void buscarMultaReporte() {
        String criterio = txtCriterioBusqueda.getText().trim();
        if (criterio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de multa, placa, dpi o licencia.",
                    "Alerta", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mostrarResultados(multaDAO.buscar(criterio));
    }

    // -----------------------------------------------------------------
    // Tabla de resultados compartida
    // -----------------------------------------------------------------

    private void mostrarResultados(List<Multa> lista) {
        modeloResultados.setRowCount(0);
        for (Multa m : lista) {
            modeloResultados.addRow(new Object[]{
                    m.getNumeroMulta(), m.getFechaHora(), m.getAgente(),
                    m.getNombreDepartamento(), m.getNombreMunicipio(), m.getDireccionExacta(),
                    nullToGuion(m.getPlacaVehiculo()), nullToGuion(m.getDpiConductor()),
                    nullToGuion(m.getLicenciaConductor()), nullToGuion(m.getNombreConductor()),
                    m.getEstado()
            });
        }
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron multas con los criterios indicados.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String nullToGuion(String s) {
        return (s == null || s.isEmpty()) ? "-" : s;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarFecha;
    private javax.swing.JButton btnGenerarUbicacion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVerHistorial;
    private javax.swing.JComboBox<Object> cboDeptoReporte;
    private javax.swing.JComboBox<String> cboEstadoFecha;
    private javax.swing.JComboBox<String> cboEstadoUbicacion;
    private javax.swing.JComboBox<String> cboMes;
    private javax.swing.JComboBox<Object> cboMuniReporte;
    private javax.swing.JComboBox<String> cboTipoPeriodo;
    private javax.swing.JLabel lblAgenteReporte;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblAnioMes;
    private javax.swing.JLabel lblConsultarPor;
    private javax.swing.JLabel lblCriterio;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblEstadoFecha;
    private javax.swing.JLabel lblEstadoUbicacion;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JPanel pnlAgente;
    private javax.swing.JPanel pnlAnio;
    private javax.swing.JPanel pnlBuscar;
    private javax.swing.JPanel pnlDia;
    private javax.swing.JPanel pnlFecha;
    private javax.swing.JPanel pnlFechaFila1;
    private javax.swing.JPanel pnlFechaFila2;
    private javax.swing.JPanel pnlFechaFila3;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlMes;
    private javax.swing.JPanel pnlRango;
    private javax.swing.JPanel pnlUbicacion;
    private javax.swing.JScrollPane scrollResultados;
    private javax.swing.JSpinner spAnio;
    private javax.swing.JSpinner spAnioMes;
    private javax.swing.JSpinner spDia;
    private javax.swing.JSpinner spRangoDesde;
    private javax.swing.JSpinner spRangoHasta;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField txtAgenteReporte;
    private javax.swing.JTextField txtCriterioBusqueda;
    // End of variables declaration//GEN-END:variables
}
