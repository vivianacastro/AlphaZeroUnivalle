/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Viviana
 */
public class MessageLauncher {
    
    public static void displayMessageDialog(Component parentComponent, String message, String title, int type) {
        JOptionPane.showMessageDialog(parentComponent, message, title, type);
    }

    public static int displayConfirmDialog(Component parentComponent, String message, String title) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    public static String displayInputDialog(Component parentComponent, String message, String title){
        return JOptionPane.showInputDialog(parentComponent, message, title, JOptionPane.QUESTION_MESSAGE);
    }
}
