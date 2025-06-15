package com.mycompany.lista_circular;

import GUI.BancoGUI;
import javax.swing.SwingUtilities;

public class Lista_circular {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BancoGUI gui = new BancoGUI();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}
