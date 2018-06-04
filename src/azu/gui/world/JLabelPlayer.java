/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui.world;

import azu.algorithm.Piece;
import javax.swing.JLabel;

/**
 *
 * @author Viviana
 */
public class JLabelPlayer extends JLabelFigure{    
    private Piece piece;
    private JLabel label;

    public JLabelPlayer(String name, JLabel label) {
        super(name);
        this.label = label;
    }    

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Piece getPiece() {
        if (piece == null) {
            piece = new Piece();
        }
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
