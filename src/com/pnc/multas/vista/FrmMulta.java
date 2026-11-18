package com.pnc.multas.vista;

import com.pnc.multas.dao.DepartamentoDAO;
import com.pnc.multas.dao.MultaDAO;
import com.pnc.multas.dao.MunicipioDAO;
import com.pnc.multas.modelo.Departamento;
import com.pnc.multas.modelo.Multa;
import com.pnc.multas.modelo.Municipio;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla de registro y consulta de Multas.
 * Diseñada con el editor gráfico de NetBeans (ver FrmMulta.form).
 *
 * Implementa la regla de negocio RN1: el sistema solo debe permitir
 * guardar una multa si se ingresa placa del vehículo, o dpi del
 * conductor, o número de licencia. En caso contrario se muestra una
 * alerta y no se guarda el registro.
 */
public class FrmMulta extends javax.swing.JFrame {

    private final DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private final MunicipioDAO municipioDAO = new MunicipioDAO();
    private final MultaDAO multaDAO = new MultaDAO();

    private DefaultTableModel modeloTabla;
    private Multa multaSeleccionada;

    public FrmMulta() {
        this(null);
    }

    /** @param menu ventana del menú principal a la que se regresa al cerrar esta pantalla (puede ser null) */
    public FrmMulta(JFrame menu) {
        initComponents();
        if (menu != null) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    menu.setVisible(true);
                }
            });
        }
        setSize(1050, 720);
        setLocationRelativeTo(null);

        modeloTabla = (DefaultTableModel) tablaMultas.getModel();
        tablaMultas.setAutoCreateRowSorter(true);
        // La columna ID se mantiene oculta (solo se usa internamente)
        tablaMultas.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaMultas.getColumnModel().getColumn(0).setMinWidth(0);
        tablaMultas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarMultaEnTabla();
            }
        });

        cargarDepartamentos();
        refrescarTabla();
        actualizarFechaHoraPreview();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlDatos = new javax.swing.JPanel();
        lblNumeroMulta = new javax.swing.JLabel();
        txtNumeroMulta = new javax.swing.JTextField();
        lblFechaHora = new javax.swing.JLabel();
        txtFechaHora = new javax.swing.JTextField();
        lblAgente = new javax.swing.JLabel();
        txtAgente = new javax.swing.JTextField();
        lblDepartamento = new javax.swing.JLabel();
        cboDepartamento = new javax.swing.JComboBox<>();
        lblMunicipio = new javax.swing.JLabel();
        cboMunicipio = new javax.swing.JComboBox<>();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblDpi = new javax.swing.JLabel();
        txtDpi = new javax.swing.JTextField();
        lblLicencia = new javax.swing.JLabel();
        txtLicencia = new javax.swing.JTextField();
        lblNombreConductor = new javax.swing.JLabel();
        txtNombreConductor = new javax.swing.JTextField();
        lblPlaca = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        lblLinea = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        lblColor = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lblNota = new javax.swing.JLabel();
        pnlBotonesForm = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        scrollMultas = new javax.swing.JScrollPane();
        tablaMultas = new javax.swing.JTable();
        pnlEstado = new javax.swing.JPanel();
        lblNuevoEstado = new javax.swing.JLabel();
        cboEstadoNuevo = new javax.swing.JComboBox<>();
        lblJustificacion = new javax.swing.JLabel();
        scrollJustificacion = new javax.swing.JScrollPane();
        txtJustificacion = new javax.swing.JTextArea();
        btnActualizarEstado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Multas de Tránsito");

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la multa"));
        pnlDatos.setLayout(new java.awt.GridBagLayout());

        lblNumeroMulta.setText("Número de multa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblNumeroMulta, gridBagConstraints);

        txtNumeroMulta.setText("(se genera automáticamente al guardar)");
        txtNumeroMulta.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtNumeroMulta, gridBagConstraints);

        lblFechaHora.setText("Fecha y hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblFechaHora, gridBagConstraints);

        txtFechaHora.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtFechaHora, gridBagConstraints);

        lblAgente.setText("Agente que impone la multa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblAgente, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtAgente, gridBagConstraints);

        lblDepartamento.setText("Departamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblDepartamento, gridBagConstraints);

        cboDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDepartamentoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(cboDepartamento, gridBagConstraints);

        lblMunicipio.setText("Municipio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblMunicipio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(cboMunicipio, gridBagConstraints);

        lblDireccion.setText("Dirección exacta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblDireccion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtDireccion, gridBagConstraints);

        lblDpi.setText("DPI del conductor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblDpi, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtDpi, gridBagConstraints);

        lblLicencia.setText("Número de licencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblLicencia, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtLicencia, gridBagConstraints);

        lblNombreConductor.setText("Nombre del conductor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblNombreConductor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtNombreConductor, gridBagConstraints);

        lblPlaca.setText("Placa del vehículo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblPlaca, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtPlaca, gridBagConstraints);

        lblMarca.setText("Marca del vehículo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblMarca, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtMarca, gridBagConstraints);

        lblLinea.setText("Línea del vehículo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblLinea, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtLinea, gridBagConstraints);

        lblColor.setText("Color del vehículo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblColor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(txtColor, gridBagConstraints);

        lblNota.setText("<html><i>Nota (RN1): debe ingresar al menos la placa del vehículo, el DPI del conductor o el número de licencia.</i></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(lblNota, gridBagConstraints);

        pnlBotonesForm.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnGuardar.setText("Guardar multa");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlBotonesForm.add(btnGuardar);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlBotonesForm.add(btnLimpiar);

        btnRegresar.setText("Regresar al menú principal");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        pnlBotonesForm.add(btnRegresar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlDatos.add(pnlBotonesForm, gridBagConstraints);

        getContentPane().add(pnlDatos, java.awt.BorderLayout.NORTH);

        scrollMultas.setBorder(javax.swing.BorderFactory.createTitledBorder("Multas registradas"));

        tablaMultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Número", "Fecha/Hora", "Agente", "Departamento", "Municipio", "Placa", "DPI", "Licencia", "Conductor", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollMultas.setViewportView(tablaMultas);

        getContentPane().add(scrollMultas, java.awt.BorderLayout.CENTER);

        pnlEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("Cambiar estado de la multa seleccionada (anular / marcar como pagada)"));
        pnlEstado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblNuevoEstado.setText("Nuevo estado:");
        pnlEstado.add(lblNuevoEstado);

        cboEstadoNuevo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVA", "ANULADA", "PAGADA" }));
        pnlEstado.add(cboEstadoNuevo);

        lblJustificacion.setText("Justificación de anulado:");
        pnlEstado.add(lblJustificacion);

        txtJustificacion.setColumns(30);
        txtJustificacion.setRows(2);
        txtJustificacion.setLineWrap(true);
        scrollJustificacion.setViewportView(txtJustificacion);

        pnlEstado.add(scrollJustificacion);

        btnActualizarEstado.setText("Actualizar estado");
        btnActualizarEstado.setEnabled(false);
        btnActualizarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEstadoActionPerformed(evt);
            }
        });
        pnlEstado.add(btnActualizarEstado);

        getContentPane().add(pnlEstado, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDepartamentoActionPerformed
        cargarMunicipios();
    }//GEN-LAST:event_cboDepartamentoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarMulta();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnActualizarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEstadoActionPerformed
        actualizarEstadoMulta();
    }//GEN-LAST:event_btnActualizarEstadoActionPerformed

    // -----------------------------------------------------------------
    // Lógica
    // -----------------------------------------------------------------

    private void cargarDepartamentos() {
        cboDepartamento.removeAllItems();
        List<Departamento> lista = departamentoDAO.listarTodos();
        for (Departamento d : lista) {
            cboDepartamento.addItem(d);
        }
        cargarMunicipios();
    }

    private void cargarMunicipios() {
        cboMunicipio.removeAllItems();
        Departamento seleccionado = (Departamento) cboDepartamento.getSelectedItem();
        if (seleccionado == null) {
            return;
        }
        List<Municipio> lista = municipioDAO.listarPorDepartamento(seleccionado.getId());
        for (Municipio m : lista) {
            cboMunicipio.addItem(m);
        }
    }

    private void actualizarFechaHoraPreview() {
        txtFechaHora.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " (referencial)");
    }

    private void guardarMulta() {
        String agente = txtAgente.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String dpi = txtDpi.getText().trim();
        String licencia = txtLicencia.getText().trim();
        String nombreConductor = txtNombreConductor.getText().trim();
        String placa = txtPlaca.getText().trim();

        if (agente.isEmpty()) {
            mostrarError("Debe ingresar el agente que impone la multa.");
            return;
        }
        if (cboDepartamento.getSelectedItem() == null) {
            mostrarError("Debe seleccionar un departamento.");
            return;
        }
        if (cboMunicipio.getSelectedItem() == null) {
            mostrarError("Debe seleccionar un municipio.");
            return;
        }
        if (direccion.isEmpty()) {
            mostrarError("Debe ingresar la dirección exacta.");
            return;
        }

        // ----- Regla de negocio RN1 -----
        boolean tienePlaca = !placa.isEmpty();
        boolean tieneDpi = !dpi.isEmpty();
        boolean tieneLicencia = !licencia.isEmpty();
        if (!tienePlaca && !tieneDpi && !tieneLicencia) {
            mostrarError("No se puede guardar la multa: debe ingresar al menos la placa del vehículo,\n"
                    + "el DPI del conductor o el número de licencia (Regla de negocio RN1).");
            return;
        }
        // Si se ingresó dpi o licencia, el nombre del conductor es obligatorio
        if ((tieneDpi || tieneLicencia) && nombreConductor.isEmpty()) {
            mostrarError("Debe ingresar el nombre del conductor ya que ingresó DPI o número de licencia.");
            return;
        }

        Multa m = new Multa();
        m.setAgente(agente);
        m.setIdDepartamento(((Departamento) cboDepartamento.getSelectedItem()).getId());
        m.setIdMunicipio(((Municipio) cboMunicipio.getSelectedItem()).getId());
        m.setDireccionExacta(direccion);
        m.setDpiConductor(dpi);
        m.setLicenciaConductor(licencia);
        m.setNombreConductor(nombreConductor);
        m.setPlacaVehiculo(placa);
        m.setMarcaVehiculo(txtMarca.getText().trim());
        m.setLineaVehiculo(txtLinea.getText().trim());
        m.setColorVehiculo(txtColor.getText().trim());
        m.setEstado(Multa.ESTADO_ACTIVA);

        try {
            multaDAO.guardar(m);
            JOptionPane.showMessageDialog(this, "Multa guardada correctamente con número: " + m.getNumeroMulta(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            refrescarTabla();
        } catch (RuntimeException ex) {
            mostrarError("Ocurrió un error al guardar la multa:\n" + ex.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtAgente.setText("");
        txtDireccion.setText("");
        txtDpi.setText("");
        txtLicencia.setText("");
        txtNombreConductor.setText("");
        txtPlaca.setText("");
        txtMarca.setText("");
        txtLinea.setText("");
        txtColor.setText("");
        txtNumeroMulta.setText("(se genera automáticamente al guardar)");
        actualizarFechaHoraPreview();
    }

    private void refrescarTabla() {
        modeloTabla.setRowCount(0);
        for (Multa m : multaDAO.listarTodas()) {
            modeloTabla.addRow(new Object[]{
                    m.getId(), m.getNumeroMulta(), m.getFechaHora(), m.getAgente(),
                    m.getNombreDepartamento(), m.getNombreMunicipio(),
                    nullToGuion(m.getPlacaVehiculo()), nullToGuion(m.getDpiConductor()),
                    nullToGuion(m.getLicenciaConductor()), nullToGuion(m.getNombreConductor()),
                    m.getEstado()
            });
        }
    }

    private void seleccionarMultaEnTabla() {
        int fila = tablaMultas.getSelectedRow();
        if (fila < 0) {
            btnActualizarEstado.setEnabled(false);
            multaSeleccionada = null;
            return;
        }
        int filaModelo = tablaMultas.convertRowIndexToModel(fila);
        String numero = (String) modeloTabla.getValueAt(filaModelo, 1);
        multaSeleccionada = multaDAO.buscarPorNumero(numero);
        btnActualizarEstado.setEnabled(multaSeleccionada != null);
        if (multaSeleccionada != null) {
            cboEstadoNuevo.setSelectedItem(multaSeleccionada.getEstado());
            txtJustificacion.setText(multaSeleccionada.getJustificacionAnulado() == null
                    ? "" : multaSeleccionada.getJustificacionAnulado());
        }
    }

    private void actualizarEstadoMulta() {
        if (multaSeleccionada == null) {
            return;
        }
        String nuevoEstado = (String) cboEstadoNuevo.getSelectedItem();
        String justificacion = txtJustificacion.getText().trim();
        if (Multa.ESTADO_ANULADA.equals(nuevoEstado) && justificacion.isEmpty()) {
            mostrarError("Debe ingresar la justificación para anular la multa.");
            return;
        }
        multaDAO.actualizarEstado(multaSeleccionada.getId(), nuevoEstado,
                Multa.ESTADO_ANULADA.equals(nuevoEstado) ? justificacion : null);
        JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        refrescarTabla();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta", JOptionPane.WARNING_MESSAGE);
    }

    private String nullToGuion(String s) {
        return (s == null || s.isEmpty()) ? "-" : s;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarEstado;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<Departamento> cboDepartamento;
    private javax.swing.JComboBox<String> cboEstadoNuevo;
    private javax.swing.JComboBox<Municipio> cboMunicipio;
    private javax.swing.JLabel lblAgente;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDpi;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblJustificacion;
    private javax.swing.JLabel lblLicencia;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMunicipio;
    private javax.swing.JLabel lblNombreConductor;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblNuevoEstado;
    private javax.swing.JLabel lblNumeroMulta;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JPanel pnlBotonesForm;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlEstado;
    private javax.swing.JScrollPane scrollJustificacion;
    private javax.swing.JScrollPane scrollMultas;
    private javax.swing.JTable tablaMultas;
    private javax.swing.JTextField txtAgente;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDpi;
    private javax.swing.JTextField txtFechaHora;
    private javax.swing.JTextArea txtJustificacion;
    private javax.swing.JTextField txtLicencia;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombreConductor;
    private javax.swing.JTextField txtNumeroMulta;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
