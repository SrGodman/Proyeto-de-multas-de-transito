package com.pnc.multas.vista;

/**
 * Ventana principal / menú de navegación del sistema.
 * Diseñada con el editor gráfico de NetBeans (ver MenuPrincipal.form).
 */
public class MenuPrincipal extends javax.swing.JFrame {

    public MenuPrincipal() {
        initComponents();
        setSize(520, 380);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblTitulo = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        btnMultas = new javax.swing.JButton();
        btnDetalle = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        lblPie = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Control de Multas de Tránsito - Policía Nacional Civil");
        lblTitulo.setText("Sistema de Control de Multas de Tránsito");
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 18));
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 10, 10));

        getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);
        pnlBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 60, 20, 60));
        pnlBotones.setLayout(new java.awt.GridBagLayout());
        btnMultas.setText("Registrar / Consultar Multas");
        btnMultas.setFont(new java.awt.Font("SansSerif", 0, 14));
        btnMultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultasActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlBotones.add(btnMultas, gridBagConstraints);
        btnDetalle.setText("Detalle de Infracciones");
        btnDetalle.setFont(new java.awt.Font("SansSerif", 0, 14));
        btnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlBotones.add(btnDetalle, gridBagConstraints);
        btnReportes.setText("Reportes");
        btnReportes.setFont(new java.awt.Font("SansSerif", 0, 14));
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlBotones.add(btnReportes, gridBagConstraints);

        getContentPane().add(pnlBotones, java.awt.BorderLayout.CENTER);
        lblPie.setText("Proyecto de práctica - Módulo de Multas");
        lblPie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));

        getContentPane().add(lblPie, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultasActionPerformed
        abrir(new FrmMulta(this));
    }//GEN-LAST:event_btnMultasActionPerformed

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        abrir(new FrmDetalleInfraccion(this));
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        abrir(new FrmReportes(this));
    }//GEN-LAST:event_btnReportesActionPerformed

    /** Oculta el menú y muestra el módulo; el módulo vuelve a mostrar el menú al cerrarse. */
    private void abrir(javax.swing.JFrame modulo) {
        setVisible(false);
        modulo.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalle;
    private javax.swing.JButton btnMultas;
    private javax.swing.JButton btnReportes;
    private javax.swing.JLabel lblPie;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlBotones;
    // End of variables declaration//GEN-END:variables
}
