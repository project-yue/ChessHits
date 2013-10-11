/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import java.io.FileWriter;
import java.util.Scanner;

/**
 * grouping all necessary information of the player together
 *
 * @author 03-08-13 Yue Li created class. setSelectPiece and movePiece are done.
 */
public class Player {

    //private final int TIMELEFT = 10;
    private String name;
    private int wining;
//    private int losing;
//    private Scanner userInput;
//    private FileWriter fWriter;
    private Color color;
    private boolean isTurn;

    /**
     * constructs a Player object to represent a user.
     *
     */
    public Player(String name, Color color) {
        this.color = color;
        this.name = name;
    }

    /**
     * set whether player is in turn
     *
     * @param isTurn
     */
    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public int getNumberOfWins() {
        return this.wining;
    }

    public void setNumberOfWins(int wins) {
        this.wining = wins;
    }

    public Color getSelectedColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsTurn() {
        return this.isTurn;
    }
}
