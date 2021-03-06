/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui;

import azu.AppConstants;
import azu.gui.world.JPanelWorld;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Viviana
 */
public class AzuMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form AzuMainFrame
     */
    public AzuMainFrame() {
        initComponents();
        
        // Full screen.
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // New game by default.
        newGame();       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBottonNewGame = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanelGameContent = new javax.swing.JPanel();
        jPanelGameHeader = new javax.swing.JPanel();
        jLabelTittle = new javax.swing.JLabel();
        jPanelGameBoard = new javax.swing.JPanel();
        jPanelDetails = new javax.swing.JPanel();
        jPanelPlayer = new javax.swing.JPanel();
        jLabelItemsPlayer = new javax.swing.JLabel();
        jLabelCountPlayer = new javax.swing.JLabel();
        jPanelMachine = new javax.swing.JPanel();
        jLabelItemsMachine = new javax.swing.JLabel();
        jLabelCountMachine = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBottonNewGame.setMinimumSize(new java.awt.Dimension(150, 33));
        jBottonNewGame.setPreferredSize(new java.awt.Dimension(150, 33));

        jButton1.setText("New Game");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jBottonNewGame.add(jButton1);

        getContentPane().add(jBottonNewGame, java.awt.BorderLayout.WEST);

        jPanelGameContent.setLayout(new java.awt.BorderLayout());

        jPanelGameHeader.setLayout(new java.awt.GridLayout(2, 1));

        jLabelTittle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelTittle.setText("AlphaZero Univalle");
        jPanelGameHeader.add(jLabelTittle);

        jPanelGameContent.add(jPanelGameHeader, java.awt.BorderLayout.NORTH);

        jPanelGameBoard.setMaximumSize(new java.awt.Dimension(500, 500));
        jPanelGameBoard.setMinimumSize(new java.awt.Dimension(500, 500));
        jPanelGameBoard.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanelGameBoard.setLayout(new java.awt.GridLayout(1, 1));
        jPanelGameContent.add(jPanelGameBoard, java.awt.BorderLayout.CENTER);

        jPanelDetails.setLayout(new java.awt.GridLayout(1, 0));

        jPanelPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder("Jugador"));
        jPanelPlayer.setToolTipText("");

        jLabelItemsPlayer.setText("Items:");
        jPanelPlayer.add(jLabelItemsPlayer);

        jLabelCountPlayer.setText("0");
        jPanelPlayer.add(jLabelCountPlayer);

        jPanelDetails.add(jPanelPlayer);

        jPanelMachine.setBorder(javax.swing.BorderFactory.createTitledBorder("Maquina"));

        jLabelItemsMachine.setText("Items:");
        jPanelMachine.add(jLabelItemsMachine);

        jLabelCountMachine.setText("0");
        jPanelMachine.add(jLabelCountMachine);

        jPanelDetails.add(jPanelMachine);

        jPanelGameContent.add(jPanelDetails, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelGameContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            newGame();
        } catch (Exception ex) {
            Logger.getLogger(AzuMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBottonNewGame;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelCountMachine;
    private javax.swing.JLabel jLabelCountPlayer;
    private javax.swing.JLabel jLabelItemsMachine;
    private javax.swing.JLabel jLabelItemsPlayer;
    private javax.swing.JLabel jLabelTittle;
    private javax.swing.JPanel jPanelDetails;
    private javax.swing.JPanel jPanelGameBoard;
    private javax.swing.JPanel jPanelGameContent;
    private javax.swing.JPanel jPanelGameHeader;
    private javax.swing.JPanel jPanelMachine;
    private javax.swing.JPanel jPanelPlayer;
    // End of variables declaration//GEN-END:variables

    private MessageLauncher messageLauncher;
    private JPanelWorld jPanelWorld;
    
    private void newGame() {
        // Inicializamos el lanzador de mensajes.
        try {
            int items = Integer.valueOf(this.messageLauncher.displayInputDialog(this, "Ingresa el numero de items.", ""));
            // Configuramos el panel del mundo.
            this.setupWorldContainer(items);

            // Update GUI.
            this.jPanelWorld.updateUI();

            // Update labels.
            this.jLabelCountPlayer.setText(String.valueOf(0));
            this.jLabelCountMachine.setText(String.valueOf(0));
        } catch (Exception ex) {
            Logger.getLogger(AzuMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupWorldContainer(int items) {
        items = items != 0 ? items : AppConstants.WORLD_ITEMS;
        try {
            this.jPanelWorld = new JPanelWorld(items, this.jLabelCountPlayer, this.jLabelCountMachine);
        } catch (Exception ex) {
            Logger.getLogger(AzuMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Update board.
        this.jPanelGameBoard.removeAll();
        this.jPanelGameBoard.add(this.jPanelWorld, java.awt.BorderLayout.CENTER);
    }

}
