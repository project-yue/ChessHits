/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

/**
 * grouping all necessary information of the player together
 *
 * @author 03-08-13 Yue Li created class. setSelectPiece and movePiece are done.
 */
public class Player {

    private String name;
    private int wining;
    private Color color;
    private boolean isTurn;

    /**
     * constructs a Player object to represent a user.
     *
     * @param name player name
     * @param color player color
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

    /**
     * get how many times the player wins
     *
     * @return the number of wins
     */
    public int getNumberOfWins() {
        return this.wining;
    }

    /**
     * set the number of wins for the player
     *
     * @param wins the number of wins
     */
    public void setNumberOfWins(int wins) {
        this.wining = wins;
    }

    /**
     * get player's color
     *
     * @return WHITE or BLACK
     */
    public Color getSelectedColor() {
        return this.color;
    }

    /**
     * get player's name
     *
     * @return player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get player's turn
     *
     * @return true if it is player's turn, false otherwise
     */
    public boolean getIsTurn() {
        return this.isTurn;
    }
}
