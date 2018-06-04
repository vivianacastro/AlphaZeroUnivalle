/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui.world;

import java.util.ArrayList;

/**
 *
 * @author Viviana
 */
public class JPanelPosition extends javax.swing.JPanel {

    /**
     * Creates new form JPanelPosition
     */
    public JPanelPosition() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private int[] position;
    private ArrayList<JLabelFigure> figures = new ArrayList<>();

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public ArrayList<JLabelFigure> getFigures() {
        return figures;
    }

    public void setFigures(ArrayList<JLabelFigure> figures) {
        this.figures = figures;
    }        

    public void addFigure(JLabelFigure figure) {
        this.figures.add(figure);
        this.add(figure);
    }   
    
    public void addFigure(String path) {
        JLabelFigure figure = new JLabelFigure(path);
        this.figures.add(figure);
        this.add(figure);
    }
    
    public void removeFigures() {
        this.removeAll();
        this.figures.clear();
    }

    public void removeFigure(JLabelFigure figure) {
        if (figure == null) {
            return;
        }
        this.remove(figure);
        this.figures.remove(figure);
    }


    
}
