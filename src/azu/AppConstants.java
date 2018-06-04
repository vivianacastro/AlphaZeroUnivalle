/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azu;

import java.awt.Color;

/**
 *
 * @author Viviana
 */
public class AppConstants {

    public static final String APPLE_PATH = "src/resources/images/apple.png";
    public static final String WHITE_HORSE_PATH = "src/resources/images/horse_white.png";
    public static final String BLACK_HORSE_PATH = "src/resources/images/horse_black.png";

    public static final int MANHATAN = 3;

    public static final Color CYAM = new Color(154, 174, 240);
    public static final Color GRAY = new Color(243, 244, 247);
    
    public static final int DEPTH = 50;
    
    public static final int WORLD_DIM = 6;
    public static final int WORLD_ITEMS = 4;

    public static enum Agent {
        EMPTY(0), ITEM(1), MACHINE(2), PLAYER(3), MACHINE_PLAYER(4);

        private final int value;

        Agent(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    public enum Operator {
        MIN(">") {
            @Override
            public boolean apply(int a, int b) {
                return a > b;
            }
        },
        MAX("<") {
            @Override
            public boolean apply(int a, int b) {
                return a < b;
            }
        };
        private final String text;

        private Operator(String text) {
            this.text = text;
        }
        
        // Yes, enums *can* have abstract methods. This code compiles...
        public abstract boolean apply(int a, int b);

        @Override
        public String toString() {
            return text;
        }
    }
}
