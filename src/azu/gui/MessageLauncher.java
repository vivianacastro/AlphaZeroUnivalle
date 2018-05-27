/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Viviana
 */
public class MessageLauncher {
    
    /**
     * Atributos privados de la clase.
     */
    Component parentComponent;

    public MessageLauncher(Component parentComponent) {
        this.parentComponent = parentComponent;
    }
    
    public void displayMessageDialog(String message, String title, int type) {
        JOptionPane.showMessageDialog(this.parentComponent, message, title, type);
    }

    public int displayConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(this.parentComponent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    public String displayInputDialog(String message, String title){
        return JOptionPane.showInputDialog(this.parentComponent, message, title, JOptionPane.QUESTION_MESSAGE);
    }
}
