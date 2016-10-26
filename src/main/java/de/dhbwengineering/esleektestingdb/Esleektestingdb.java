/*
 * DHBW Engineering Stuttgart e.V.
 * All rights reserved
 * 2016
 */
package de.dhbwengineering.esleektestingdb;

import de.dhbwengineering.esleektestingdb.gui.MainMenu;

/**
 * Main class of the project
 *
 * @author Leon
 */
public class Esleektestingdb {

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        //Show MainMenu
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setTitle("eSleek Testing DB");
                mainMenu.setLocationRelativeTo(null);
                mainMenu.setVisible(true);
            }
        });
    }
}
