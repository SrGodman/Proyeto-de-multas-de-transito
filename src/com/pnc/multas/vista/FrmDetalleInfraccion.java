package com.pnc.multas.vista;

import com.pnc.multas.dao.DetalleInfraccionDAO;
import com.pnc.multas.dao.MultaDAO;
import com.pnc.multas.modelo.DetalleInfraccion;
import com.pnc.multas.modelo.Multa;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla de Detalle de Infracciones.
 * Diseñada con el editor gráfico de NetBeans (ver FrmDetalleInfraccion.form).
 *
 * Una multa puede tener varias infracciones asociadas. Primero se busca
 * la multa por su número (correlativo-año) y luego se administran las
 * infracciones ligadas a ella.
 */
public class FrmDetalleInfraccion extends javax.swing.JFrame {

    private final MultaDAO multaDAO = new MultaDAO();
    private final DetalleInfraccionDAO detalleDAO = new DetalleInfraccionDAO();

    private Multa multaActual;
    private DefaultTableModel modeloTabla;

    public FrmDetalleInfraccion() {
        this(null);
    }

    /** @param menu ventana del menú principal a la que se regresa al cerrar esta pantalla (puede ser null) */
    public FrmDetalleInfraccion(JFrame menu) {
        initComponents();
        if (menu != null) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    menu.setVisible(true);
                }
            });
        }
        setSize(950, 650);
        setLocationRelativeTo(null);
        modeloTabla = (DefaultTableModel) tablaInfracciones.getModel();
        tablaInfracciones.setAutoCreateRowSorter(true);
        habilitarFormulario(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlBusqueda = new javax.swing.JPanel();
        pnlFilaBusqueda = new javax.swing.JPanel();
        lblNumeroBuscar = new javax.swing.JLabel();
        txtBuscarNumero = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pnlFilaInfo = new javax.swing.JPanel();
        lblInfoMulta = new javax.swing.JLabel();
        pnlCentral = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        lblArticulo = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        scrollDescripcion = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        cboEstadoInfraccion = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        scrollInfracciones = new javax.swing.JScrollPane();
        tablaInfracciones = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnCambiarEstadoInfraccion = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle de Infracciones");

        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar multa"));
        pnlBusqueda.setLayout(new java.awt.GridLayout(2, 1));

        pnlFilaBusqueda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblNumeroBuscar.setText("Número de multa (ej. 1-2026):");
        pnlFilaBusqueda.add(lblNumeroBuscar);

        txtBuscarNumero.setColumns(15);
        pnlFilaBusqueda.add(txtBuscarNumero);

        btnBuscar.setText("Buscar por número de multa");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlFilaBusqueda.add(btnBuscar);

        pnlBusqueda.add(pnlFilaBusqueda);

        pnlFilaInfo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblInfoMulta.setText("Ninguna multa seleccionada.");
        pnlFilaInfo.add(lblInfoMulta);

        pnlBusqueda.add(pnlFilaInfo);

        getContentPane().add(pnlBusqueda, java.awt.BorderLayout.NORTH);

        pnlCentral.setLayout(new java.awt.BorderLayout());

        pnlForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva infracción"));
        pnlForm.setLayout(new java.awt.GridBagLayout());

        lblArticulo.setText("Artículo de ley violado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(lblArticulo, gridBagConstraints);

        txtArticulo.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(txtArticulo, gridBagConstraints);

        lblMonto.setText("Monto (Q):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(lblMonto, gridBagConstraints);

        txtMonto.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(txtMonto, gridBagConstraints);

        lblDescripcion.setText("Descripción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(lblDescripcion, gridBagConstraints);

        txtDescripcion.setColumns(25);
        txtDescripcion.setRows(2);
        txtDescripcion.setLineWrap(true);
        scrollDescripcion.setViewportView(txtDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(scrollDescripcion, gridBagConstraints);

        cboEstadoInfraccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(cboEstadoInfraccion, gridBagConstraints);

        btnAgregar.setText("Agregar infracción");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 4, 6);
        pnlForm.add(btnAgregar, gridBagConstraints);

        pnlCentral.add(pnlForm, java.awt.BorderLayout.NORTH);

        scrollInfracciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Infracciones de la multa"));

        tablaInfracciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Correlativo", "Artículo de ley", "Descripción", "Monto (Q)", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollInfracciones.setViewportView(tablaInfracciones);

        pnlCentral.add(scrollInfracciones, java.awt.BorderLayout.CENTER);

        pnlBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnCambiarEstadoInfraccion.setText("Activar/Inactivar infracción seleccionada");
        btnCambiarEstadoInfraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoInfraccionActionPerformed(evt);
            }
        });
        pnlBotones.add(btnCambiarEstadoInfraccion);

        btnRegresar.setText("Regresar al menú principal");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        pnlBotones.add(btnRegresar);

        pnlCentral.add(pnlBotones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarMulta();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarInfraccion();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCambiarEstadoInfraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoInfraccionActionPerformed
        cambiarEstadoInfraccion();
    }//GEN-LAST:event_btnCambiarEstadoInfraccionActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void buscarMulta() {
        String numero = txtBuscarNumero.getText().trim();
        if (numero.isEmpty()) {
            mostrarError("Ingrese el número de multa a buscar.");
            return;
        }
        Multa m = multaDAO.buscarPorNumero(numero);
        if (m == null) {
            mostrarError("No se encontró ninguna multa con el número: " + numero);
            lblInfoMulta.setText("Ninguna multa seleccionada.");
            habilitarFormulario(false);
            multaActual = null;
            modeloTabla.setRowCount(0);
            return;
        }
        multaActual = m;
        lblInfoMulta.setText("<html>Multa <b>" + m.getNumeroMulta() + "</b> | Agente: " + m.getAgente()
                + " | Estado: " + m.getEstado() + " | Conductor: "
                + (m.getNombreConductor() == null ? "-" : m.getNombreConductor()) + "</html>");
        habilitarFormulario(true);
        refrescarTablaInfracciones();
    }

    private void agregarInfraccion() {
        if (multaActual == null) {
            mostrarError("Primero debe buscar y seleccionar una multa.");
            return;
        }
        String articulo = txtArticulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String montoTexto = txtMonto.getText().trim();

        if (articulo.isEmpty()) {
            mostrarError("Debe ingresar el artículo de ley violado.");
            return;
        }
        if (descripcion.isEmpty()) {
            mostrarError("Debe ingresar la descripción de la infracción.");
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(montoTexto.replace(",", "."));
        } catch (NumberFormatException ex) {
            mostrarError("El monto de la infracción debe ser numérico (ej. 150.00).");
            return;
        }

        DetalleInfraccion d = new DetalleInfraccion();
        d.setIdMulta(multaActual.getId());
        d.setArticuloLey(articulo);
        d.setDescripcion(descripcion);
        d.setMonto(monto);
        d.setEstado((String) cboEstadoInfraccion.getSelectedItem());

        detalleDAO.guardar(d);
        JOptionPane.showMessageDialog(this, "Infracción agregada correctamente (correlativo "
                + d.getCorrelativo() + ").", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        txtArticulo.setText("");
        txtDescripcion.setText("");
        txtMonto.setText("");
        cboEstadoInfraccion.setSelectedItem(DetalleInfraccion.ESTADO_ACTIVO);
        refrescarTablaInfracciones();
    }

    private void cambiarEstadoInfraccion() {
        int fila = tablaInfracciones.getSelectedRow();
        if (fila < 0) {
            mostrarError("Seleccione una infracción de la tabla.");
            return;
        }
        int filaModelo = tablaInfracciones.convertRowIndexToModel(fila);
        int correlativo = (int) modeloTabla.getValueAt(filaModelo, 0);
        String estadoActual = (String) modeloTabla.getValueAt(filaModelo, 4);
        String nuevoEstado = DetalleInfraccion.ESTADO_ACTIVO.equals(estadoActual)
                ? DetalleInfraccion.ESTADO_INACTIVO : DetalleInfraccion.ESTADO_ACTIVO;
        detalleDAO.actualizarEstado(correlativo, nuevoEstado);
        refrescarTablaInfracciones();
    }

    private void refrescarTablaInfracciones() {
        modeloTabla.setRowCount(0);
        if (multaActual == null) {
            return;
        }
        for (DetalleInfraccion d : detalleDAO.listarPorMulta(multaActual.getId())) {
            modeloTabla.addRow(new Object[]{
                    d.getCorrelativo(), d.getArticuloLey(), d.getDescripcion(),
                    String.format("%.2f", d.getMonto()), d.getEstado()
            });
        }
    }

    private void habilitarFormulario(boolean habilitar) {
        txtArticulo.setEnabled(habilitar);
        txtDescripcion.setEnabled(habilitar);
        txtMonto.setEnabled(habilitar);
        cboEstadoInfraccion.setEnabled(habilitar);
        btnAgregar.setEnabled(habilitar);
        btnCambiarEstadoInfraccion.setEnabled(habilitar);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta", JOptionPane.WARNING_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCambiarEstadoInfraccion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboEstadoInfraccion;
    private javax.swing.JLabel lblArticulo;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblInfoMulta;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNumeroBuscar;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlFilaBusqueda;
    private javax.swing.JPanel pnlFilaInfo;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JScrollPane scrollDescripcion;
    private javax.swing.JScrollPane scrollInfracciones;
    private javax.swing.JTable tablaInfracciones;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtBuscarNumero;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
