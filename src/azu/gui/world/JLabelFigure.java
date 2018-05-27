/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui.world;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Viviana
 */
public class JLabelFigure extends JLabel {

    private final String image;
    private Image background;    

    public JLabelFigure(String image) {
        super();
        this.image = image;
        this.setBackgroundImage(image);

    }

    public String getImage() {
        return image;
    }    

    private void setBackgroundImage(String image) {
        this.background = new ImageIcon(image).getImage();
    }
    

    @Override
    public void paint(Graphics g) {
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        super.paint(g);
    }
}
