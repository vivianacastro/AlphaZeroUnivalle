/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.algorithm;

/**
 *
 * @author Viviana
 */
public class Piece {
    
    private int[] position;
    
    private int items;
    
    public Piece() {        
    }

    public Piece(Piece piece) {
        this.position = piece.getPosition();
        this.items = piece.getItems();
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
    }
    
    public void increaseItems() {
        this.items += 1;
    }
    
    public void decreaseItems() {
        this.items -= 1;
    }
    
    public void addItems(int items) {
        this.items += items;
    }
    
    public void restartItems() {
        this.items = 0;
    }    
    
}
