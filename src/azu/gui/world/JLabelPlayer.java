/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui.world;

import javax.swing.JLabel;

/**
 *
 * @author Viviana
 */
public class JLabelPlayer extends JLabelFigure{    
    private int[] position;
    private int items;
    private JLabel jLabelCount;

    public JLabelPlayer(String name, int[] position, JLabel jLabelCount) {
        super(name);
        this.position = position;
        this.jLabelCount = jLabelCount;
    }
    
    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
        this.jLabelCount.setText(String.valueOf(this.items));
    }
    
    public void increaseItems() {
        this.items++;
        this.jLabelCount.setText(String.valueOf(this.items));
    }
    
    public void decreaseItems() {
        this.items--;
        this.jLabelCount.setText(String.valueOf(this.items));
    }
    
    public void addItems(int items) {
        this.items += items;
        this.jLabelCount.setText(String.valueOf(this.items));
    }
    
    public void restartItems() {
        this.items = 0;
        this.jLabelCount.setText(String.valueOf(this.items));
    }
    
}
