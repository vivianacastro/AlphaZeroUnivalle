/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.algorithm;

import azu.AppConstants;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Viviana
 */
public class MiniMax {
    
    private static ArrayList<Node> expand(Node node) {
        // Lista de nodos expandidos.
        ArrayList<Node> nodes = new ArrayList<>();
        // Variable temporal.
        Node tmp;
        
        // Realizamos el intercambio de turno para expandir.
        Piece owner = node.getOpponent();
        Piece opponent = node.getOwner();

        // Obtenemos los posibles movimientos del dueño del turno.
        ArrayList<int[]> moves = MiniMaxUtils.posibleMoves(owner.getPosition());
        
        // Iteramos sobre los posibles movimientos del dueño del turno.
        for (int[] move : moves) {            
            // Lo añadimos solamente si es un movimiento posible para el dueño del turno.
            if (!MiniMaxUtils.isPosibleMove(owner.getPosition(), move)) {                
                continue;
            }
            
            // System.out.println(String.format("[%d, %d] ", move[0], move[1]));
            // Creamos el nuevo nodo.
            tmp = new Node();
            // Seup parent.
            tmp.setParent(node);
            // Configuramos las piezas.
            tmp.setOwner(new Piece(owner));
            tmp.setOpponent(new Piece(opponent));
            // Le configuramos el tablero del juego.
            tmp.setMatrix(node.getMatrix());
            
            // Actualizamos la nueva posicion del dueño del turno.
            tmp.getOwner().setPosition(move);
            
            // Si en la nueva posicion había un item actualizamos el contador del dueño de turno.
            if (tmp.getMatrix()[move[0]][move[1]] == AppConstants.Agent.ITEM.value()) {
                tmp.getOwner().increaseItems();
                // Limpiamos el item en el mundo.
                tmp.getMatrix()[move[0]][move[1]] = AppConstants.Agent.EMPTY.value();
            }
            // Si se encontraba el caballo del contrincante, le quitamos sus items.
            else if (MiniMaxUtils.equals(move, tmp.getOpponent().getPosition())) {
                tmp.getOwner().addItems(tmp.getOpponent().getItems());
                // Limpiamos los items del caballo del jugador.
                tmp.getOpponent().restartItems();               
            }        

            // Añadimos el nodo a la lista.
            nodes.add(tmp);
        }

        return nodes;
    }
    
    private static Node miniMax(Node node, int depth, AppConstants.Operator op) {
        // Validamos la profundidad.
        if (MiniMaxUtils.end(node.getMatrix()) || depth == 1) {
            return node;
        }
        // Expandimos el nodo y hacemos el llamdo recursivo.        
        ArrayList<Node> nodes = expand(node);
        // Obtenemos el minimax del primer nodo.
        Node miniMax = miniMax(
                nodes.get(0), 
                depth - 1, 
                op == AppConstants.Operator.MAX? AppConstants.Operator.MIN : AppConstants.Operator.MAX);
        for (int i = 1; i < nodes.size(); i++) {            
            // Obtenemos el minimax de los siguientes nodos.
            Node tmp = miniMax(
                nodes.get(i), 
                depth - 1, 
                op == AppConstants.Operator.MAX? AppConstants.Operator.MIN : AppConstants.Operator.MAX);
            if (op.apply(miniMax.getOwner().getItems(), tmp.getOwner().getItems())) {
                miniMax = tmp;
            }            
        }
        
        // Retornamos el minimax.
        return miniMax;
    }
    
    private static int[] getPosition(Node node) {
        ArrayList<int[]> path = new ArrayList<>();
        
        Node tmp = node;
        do {
            path.add(tmp.getOwner().getPosition());
            tmp = tmp.getParent();         
        } while(tmp != null);
        
        Collections.reverse(path);
        
        return path.get(1);
    }
    
    public static int[] miniMax(Node node, int depth) {
        Node minimax =  miniMax(node, depth, AppConstants.Operator.MAX);
        int[] position = getPosition(minimax);        
        return position;
    }    
}
