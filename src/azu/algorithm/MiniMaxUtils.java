/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu.algorithm;

import azu.AppConstants;
import java.util.ArrayList;

/**
 *
 * @author Viviana
 */
public class MiniMaxUtils {

    /**
     * Retorna true si es un posible movimiento, false sino.
     * @param previous
     * @param next
     * @return 
     */
    public static boolean isPosibleMove(int[] previous, int[] next) {
        boolean validnextx = 0 <= next[0] && next[0] <  AppConstants.WORLD_DIM;
        boolean validnexty = 0 <= next[1] && next[1] <  AppConstants.WORLD_DIM;
        boolean validx = Math.abs(previous[0] - next[0]) == 1 || Math.abs(previous[0] - next[0]) == 2;
        boolean validy = Math.abs(previous[1] - next[1]) == 1 || Math.abs(previous[1] - next[1]) == 2;
        int manhatan = Math.abs(previous[0] - next[0]) + Math.abs(previous[1] - next[1]);

        return validnextx & validnexty & validx & validy && manhatan == AppConstants.MANHATAN;
    }

    /**
     * Metodo encargado de generar los posibles movimientos de una ficha.
     * @param pos
     * @return 
     */
    public static ArrayList<int[]> posibleMoves(int[] pos) {
        ArrayList<int[]> moves = new ArrayList<>();

        // Movimientos a la derecha.
        moves.add(new int[]{pos[0] + 2, pos[1] + 1});
        moves.add(new int[]{pos[0] + 2, pos[1] - 1});

        // Movimientos a la izquierda.
        moves.add(new int[]{pos[0] - 2, pos[1] + 1});
        moves.add(new int[]{pos[0] - 2, pos[1] - 1});

        // Movimientos abajo.
        moves.add(new int[]{pos[0] + 1, pos[1] + 2});
        moves.add(new int[]{pos[0] - 1, pos[1] + 2});

        // Movimientos arriba.
        moves.add(new int[]{pos[0] + 1, pos[1] - 2});
        moves.add(new int[]{pos[0] - 1, pos[1] - 2});

        return moves;
    }
    
    public static boolean equals(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }
    
    public static boolean end(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == AppConstants.Agent.ITEM.value()) {
                    return false;
                }
            }
        }
        return true;
    }
}
