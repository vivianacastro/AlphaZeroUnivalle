/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.gui.world;

import azu.AppConstants;
import azu.algorithm.MiniMax;
import azu.algorithm.MiniMaxUtils;
import azu.algorithm.Node;
import azu.algorithm.Piece;
import azu.gui.MessageLauncher;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Viviana
 */
public class JPanelWorld extends JPanel {   

    private int items;
    
    private JPanelPosition[][] board;
    
    private JLabelPlayer player;
    private JLabelPlayer machine;
    private final MyMouseLister mouseListener = new MyMouseLister();
    
    private boolean machineTime;

    public JPanelWorld(int items, JLabel jLabelCountPlayer, JLabel jLabelCountMachine) throws Exception {
        this.items = items;
        this.player = new JLabelPlayer(AppConstants.BLACK_HORSE_PATH, jLabelCountPlayer);
        this.machine = new JLabelPlayer(AppConstants.WHITE_HORSE_PATH, jLabelCountMachine);
        this.setupWorldContainer();
        this.setupWord();
        this.setupItems();
        // Habilitamos el primer movimiento de la maquina.
        this.moveMachine();
    } 

    private void setupWorldContainer() {
        this.setLayout(new GridLayout(AppConstants.WORLD_DIM, AppConstants.WORLD_DIM));
    }

    private void setupWord() {
        this.board = new JPanelPosition[AppConstants.WORLD_DIM][AppConstants.WORLD_DIM];
        JPanelPosition jPanelTmp;

        for (int i = 0; i < AppConstants.WORLD_DIM; i++) {
            Color color = AppConstants.CYAM;
            if (i % 2 == 0) {
                color = AppConstants.GRAY;
            }
            for (int j = 0; j < AppConstants.WORLD_DIM; j++) {
                jPanelTmp = new JPanelPosition();
                jPanelTmp.setBackground(color);
                color = AppConstants.GRAY.equals(color) ? AppConstants.CYAM : AppConstants.GRAY;
                this.board[i][j] = jPanelTmp;
                jPanelTmp.setLayout(new GridLayout(1, 1));

                // Setup game controls.
                jPanelTmp.addMouseListener(this.mouseListener);

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
            this.board[position[0]][position[1]].addFigure(AppConstants.APPLE_PATH);
        }

        // Setup horses.
        // Setup player.
        this.player.getPiece().setPosition(positions.get(this.items));
        this.board[this.player.getPiece().getPosition()[0]][this.player.getPiece().getPosition()[1]].addFigure(this.player);

        // Setup machine.
        this.machine.getPiece().setPosition(positions.get(this.items + 1));
        this.board[this.machine.getPiece().getPosition()[0]][this.machine.getPiece().getPosition()[1]].addFigure(this.machine);
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
        int x = rnd.nextInt(AppConstants.WORLD_DIM);
        int y = rnd.nextInt(AppConstants.WORLD_DIM);
        // System.out.println(String.format("[%d,%d]", x, y));
        return new int[]{x, y};
    }

    private void moveMachine() {
        this.machineTime = true;
        // Construimos el estado actual del tablero.
        Node node = this.buildNode();
        // Obtenemos la primer posición de la maquina.
        int[] position = MiniMax.miniMax(node, AppConstants.DEPTH);
        // Movemos la ficha.
        this.movePiece(this.machine, position);
        this.machineTime = false;
    }
    
    private Node buildNode() {
        Node node = new Node();
        // Configuramos las fichas.
        node.setOwner(new Piece(this.player.getPiece()));
        node.setOpponent(new Piece(this.machine.getPiece()));
        // Configuramos el tablero.
        node.setMatrix(this.buildBoardState());
        
        return node;
    }
    
    private int[][] buildBoardState() {
        int[][] matrix = new int[AppConstants.WORLD_DIM][AppConstants.WORLD_DIM];
        for (int i = 0; i < AppConstants.WORLD_DIM; i++) {
            for (int j = 0; j < AppConstants.WORLD_DIM; j++) {
                matrix[i][j] = AppConstants.Agent.EMPTY.value();
                if (this.board[i][j].getFigures() != null 
                        && !this.board[i][j].getFigures().isEmpty() 
                        && this.board[i][j].getFigures().get(0) instanceof JLabelFigure) {
                    matrix[i][j] = AppConstants.Agent.ITEM.value();
                }
            }
        }
        return matrix;
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
        int[] previous = p.getPiece().getPosition();
        // Borramos la imagen de la casilla actual.
        this.board[previous[0]][previous[1]].removeFigure(p);
        // Actualizamos la nueva posición del caballo.
        p.getPiece().setPosition(next);
        
        // Si en dicha posición esta otro caballo le quita los items.
        if (this.board[next[0]][next[1]].getFigures() != null &&
                !this.board[next[0]][next[1]].getFigures().isEmpty() &&
                this.board[next[0]][next[1]].getFigures().get(0) instanceof JLabelPlayer) {
            JLabelPlayer opponent = (JLabelPlayer) this.board[next[0]][next[1]].getFigures().get(0);
            p.getPiece().addItems(opponent.getPiece().getItems());
            opponent.getPiece().restartItems();
        } // Si en dicha posicion había una manzana.
        else if (this.board[next[0]][next[1]].getFigures() != null &&
                !this.board[next[0]][next[1]].getFigures().isEmpty() && 
                this.board[next[0]][next[1]].getFigures().get(0) instanceof JLabelFigure) {
            this.board[next[0]][next[1]].removeFigures();
            p.getPiece().increaseItems();
            // Decrementamos el total de items.
            this.items -= 1;
            
            // Si la cantidad de items es 0, el juego termino.
            if (this.items == 0) {
                String message;
                if (this.player.getPiece().getItems() == this.machine.getPiece().getItems()) {
                    message = String.format("Empate");
                } else {
                    String winner = this.player.getPiece().getItems() < this.machine.getPiece().getItems()? "la maquina" : "el jugador";
                    message = String.format("Ha ganado %s.", winner);
                }
                MessageLauncher.displayMessageDialog(this, message, "", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        // Actualizamos la imagen de la casilla actual.
        this.board[next[0]][next[1]].addFigure(p);        
    }

    public boolean isPlayerPostion(int[] position) {
        return this.player.getPiece().getPosition()[0] == position[0] && this.player.getPiece().getPosition()[1] == position[1];
    }

    public boolean isMachinePosition(int[] position) {
        return this.machine.getPiece().getPosition()[0] == position[0] && this.machine.getPiece().getPosition()[1] == position[1];
    }

    protected class MyMouseLister implements MouseListener {        

        @Override
        public void mouseClicked(MouseEvent me) {
            if (items == 0) {
                return;
            }
            if (machineTime) {
                return;
            }
            JPanelPosition jp = (JPanelPosition) me.getSource();
            // System.out.println("Mouse clicked.");
            // Si es la posición de jugador.
            if (isPlayerPostion(jp.getPosition())) {
                // System.out.println("Player position");
                // Si es la posición del jugador mostramos los posibles movimientos.

            } else if (MiniMaxUtils.isPosibleMove(player.getPiece().getPosition(), jp.getPosition())) {
                // System.out.println("Posible move.");
                // Si es un posible movimiento lo ejecutamos.
                movePiece(player, jp.getPosition());
                
                // Actualizamos los contadores.
                player.getLabel().setText(String.valueOf(player.getPiece().getItems())); 
                machine.getLabel().setText(String.valueOf(machine.getPiece().getItems()));
                // Actualizamos la interfaz.
                updateUI();
                
                // Movemos la maquina.
                moveMachine();
                
                // Actualizamos los contadores.
                player.getLabel().setText(String.valueOf(player.getPiece().getItems())); 
                machine.getLabel().setText(String.valueOf(machine.getPiece().getItems()));
                // Actualizamos la interfaz.
                updateUI();
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
