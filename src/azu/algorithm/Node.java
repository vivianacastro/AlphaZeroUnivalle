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
public class Node {
    /** Parent node. */
    private Node parent;
    /**
     * Tablero del juego. Contiene posición de los items y de los caballos.
     */
    private int[][] matrix;
    /**
     * Informacion del dueño del turno.
     */
    private Piece owner;
    /**
     * Información del contrincante.
     */
    private Piece opponent;
    
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Piece getOwner() {
        return owner;
    }

    public void setOwner(Piece owner) {
        this.owner = owner;
    }

    public Piece getOpponent() {
        return opponent;
    }

    public void setOpponent(Piece opponent) {
        this.opponent = opponent;
    }
    
    
}
