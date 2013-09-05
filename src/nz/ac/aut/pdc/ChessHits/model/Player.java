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
    private int losing;
    private Scanner userInput;
    private FileWriter fWriter;
    private Color color;
    private boolean isTurn;

    /**
     * constructs a Player object to represent a user.
     *
     */
    public Player() {
        this.userInput = new Scanner(System.in);

    }

    /**
     * select user's piece color
     *
     * @return The color of Piece
     */
    public Color chooseColor() {
        Color result = null;
        String chosenColor;
        do {
            System.out.print("Please choose color to play(white/black):");
            chosenColor = userInput.next();
        } while (!chosenColor.equals("white") && !chosenColor.equals("black"));
        switch (chosenColor) {
            case "white":
                result = Color.WHITE;
                break;
            case "black":
                result = Color.BLACK;
                break;
            default:
                System.err.println("input is invalid");
        }
        return result;
    }

    /**
     * set whether player is in turn
     *
     * @param isTurn
     */
    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
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
