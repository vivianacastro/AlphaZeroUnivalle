/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui.world;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Viviana
 */
public class JPanelWorld extends JPanel {

    private static final String APPLE_PATH = "src/resources/images/apple.png";
    private static final String WHITE_HORSE_PATH = "src/resources/images/horse_white.png";
    private static final String BLACK_HORSE_PATH = "src/resources/images/horse_black.png";

    private static final int MANHATAN = 3;

    private static final Color CYAM = new Color(154, 174, 240);
    private static final Color GRAY = new Color(243, 244, 247);

    private int items;
    private final int dim;

    private JPanelPosition[][] matrix;
    private JLabelPlayer player;
    private final JLabel jLabelCountPlayer;
    private JLabelPlayer machine;
    private final JLabel jLabelCountMachine;
    private final MyMouseLister mouseListener = new MyMouseLister();

    public JPanelWorld(int dim, int items, JLabel jLabelCountPlayer, JLabel jLabelCountMachine) throws Exception {
        this.dim = dim;
        this.items = items;
        this.jLabelCountPlayer = jLabelCountPlayer;
        this.jLabelCountMachine = jLabelCountMachine;
        this.setupWorldContainer();
        this.setupWord();
        this.setupItems();
    }

    private void setupWorldContainer() {
        this.setLayout(new GridLayout(this.dim, this.dim));
    }

    private void setupWord() {
        this.matrix = new JPanelPosition[this.dim][this.dim];
        JPanelPosition jPanelTmp;

        for (int i = 0; i < matrix.length; i++) {
            Color color = CYAM;
            if (i % 2 == 0) {
                color = GRAY;
            }
            for (int j = 0; j < matrix.length; j++) {
                jPanelTmp = new JPanelPosition();
                jPanelTmp.setBackground(color);
                color = GRAY.equals(color) ? CYAM : GRAY;
                this.matrix[i][j] = jPanelTmp;
                jPanelTmp.setLayout(new GridLayout(1, 1));

                // Setup game controls.
                jPanelTmp.addMouseListener(mouseListener);

                // Setup position.
                jPanelTmp.setPosition(new int[]{i, j});

                this.add(jPanelTmp);
            }
        }
    }

    private void setupItems() throws Exception {
        ArrayList<int[]> positions = this.getRndPositions(this.items + 2);
        if (this.items + 2 != positions.size()) {
            String message = String.format("La cantidad de posiciones generadas no es correcta. Actual: %d, Esperada %d", positions.size(), this.items + 2);
            throw new Exception(message);
        }

        // Setup items.
        for (int i = 0; i < this.items; i++) {
            int[] position = positions.get(i);
            this.matrix[position[0]][position[1]].addFigure(APPLE_PATH);
        }

        // Setup horses.
        // Setup player.
        this.player = new JLabelPlayer(BLACK_HORSE_PATH, positions.get(this.items), this.jLabelCountPlayer);
        this.matrix[this.player.getPosition()[0]][this.player.getPosition()[1]].addFigure(this.player);

        // Setup machine.
        this.machine = new JLabelPlayer(WHITE_HORSE_PATH, positions.get(this.items + 1), this.jLabelCountMachine);
        this.matrix[this.machine.getPosition()[0]][this.machine.getPosition()[1]].addFigure(this.machine);
    }

    private ArrayList<int[]> getRndPositions(int n) {
        ArrayList<int[]> positions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] position = this.getRndPosition();
            while (this.contains(positions, position)) {
                position = this.getRndPosition();
            }
            positions.add(position);
        }

        // this.showPositions(positions);
        return positions;
    }

    private int[] getRndPosition() {
        Random rnd = new Random();
        int x = rnd.nextInt(this.dim);
        int y = rnd.nextInt(this.dim);
        // System.out.println(String.format("[%d,%d]", x, y));
        return new int[]{x, y};
    }

    private boolean contains(ArrayList<int[]> positions, int[] position) {
        // this.showPositions(positions);
        // System.out.println(String.format("[%d, %d]", position[0], position[1]));        
        return positions.stream().anyMatch((position_) -> (position_[0] == position[0] && position_[1] == position[1]));
    }

    private void showPositions(ArrayList<int[]> positions) {
        String p = new String();
        for (int[] position : positions) {
            p = p.concat(String.format("[%d, %d] ", position[0], position[1]));
        }
        System.out.println(p);
    }

    public JLabelPlayer getPlayer() {
        return player;
    }

    public void setPlayer(JLabelPlayer player) {
        this.player = player;
    }

    public JLabelPlayer getMachine() {
        return machine;
    }

    public void setMachine(JLabelPlayer machine) {
        this.machine = machine;
    }

    public void restartWordl(int items) throws Exception {
        this.items = items;
        // Remove all items.
        this.removeAll();
        // Setup world and items.
        this.setupWord();
        this.setupItems();
        // Restart world.
        this.updateUI();
    }

    public void movePiece(JLabelPlayer p, int[] next) {
        int[] previous = p.getPosition();
        // Borramos la imagen de la casilla actual.
        matrix[previous[0]][previous[1]].removeFigure();
        // Actualizamos la nueva posición del caballo.
        p.setPosition(next);

        // Si en dicha posición esta otro caballo le quita los items.
        if (matrix[next[0]][next[1]].getFigure() instanceof JLabelPlayer) {
            JLabelPlayer opponent = (JLabelPlayer) matrix[next[0]][next[1]].getFigure();
            p.addItems(opponent.getItems());
            opponent.restartItems();
        } // Si en dicha posicion había una manzana.
        else if (matrix[next[0]][next[1]].getFigure() instanceof JLabelFigure) {
            matrix[next[0]][next[1]].removeFigure();
            p.increaseItems();
        }

        // Actualizamos la imagen de la casilla actual.
        matrix[next[0]][next[1]].addFigure(p.getImage());

        // Actualizamos la interfaz.
        this.updateUI();
    }

    public boolean isPlayerPostion(int[] position) {
        return this.player.getPosition()[0] == position[0] && this.player.getPosition()[1] == position[1];
    }

    public boolean isMachinePosition(int[] position) {
        return this.machine.getPosition()[0] == position[0] && this.machine.getPosition()[1] == position[1];
    }

    public boolean isPosibleMove(int[] previous, int[] next) {
        boolean validx = Math.abs(previous[0] - next[0]) == 1 || Math.abs(previous[0] - next[0]) == 2;
        boolean validy = Math.abs(previous[1] - next[1]) == 1 || Math.abs(previous[1] - next[1]) == 2;
        int manhatan = Math.abs(previous[0] - next[0]) + Math.abs(previous[1] - next[1]);

        return validx & validy && manhatan == MANHATAN;
    }

    private void cleanBox() {
    }

    protected class MyMouseLister implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            JPanelPosition jp = (JPanelPosition) me.getSource();
            // System.out.println("Mouse clicked.");
            // Si es la posición de jugador.
            if (isPlayerPostion(jp.getPosition())) {
                // System.out.println("Player position");
                // Si es la posición del jugador mostramos los posibles movimientos.

            } else if (isPosibleMove(player.getPosition(), jp.getPosition())) {
                // System.out.println("Posible move.");
                // Si es un posible movimiento lo ejecutamos.
                movePiece(player, jp.getPosition());
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

    }
}
