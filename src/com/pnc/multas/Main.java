package com.pnc.multas;

import com.pnc.multas.util.ConexionBD;
import com.pnc.multas.vista.MenuPrincipal;

import javax.swing.*;

/**
 * Punto de entrada de la aplicación.
 *
 * En NetBeans: click derecho sobre este archivo -> "Run File" (Shift+F6),
 * o defínalo como clase principal del proyecto en las propiedades del
 * proyecto (Project Properties -> Run -> Main Class).
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // si falla, se usa el look and feel por defecto
        }

        SwingUtilities.invokeLater(() -> {
            // Fuerza la creación/apertura de la base de datos y sus tablas
            // desde el inicio de la aplicación.
            ConexionBD.getConexion();

            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}
