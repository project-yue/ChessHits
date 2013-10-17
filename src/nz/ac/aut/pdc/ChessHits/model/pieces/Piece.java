/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.Collection;
import nz.ac.aut.pdc.ChessHits.model.*;

/**
 *
 * @author Yue Li
 * @version 23-07-2013 class is built
 */
public abstract class Piece {

    private int hitPoint;
    private Position position;
    private Color color;
    private Board board;

    /**
     * construct a Piece object
     *
     * @param hitPoint the hit point of the piece
     * @param position the position of the piece
     */
    public Piece(Board board, int hitPoint, Position position, Color color) {
        //invariants
        this.hitPoint = hitPoint;
        this.position = position;
        this.color = color;
        this.board = board;
    }

    /**
     * reduce a piece's hit points by 1
     */
    public void reduceHP() {
        this.hitPoint--;
    }

    /**
     * get hit points remaining of a piece
     *
     * @return the hit points remaining
     */
    public int getHP() {
        return this.hitPoint;
    }

    /**
     * get whether a piece is alive
     *
     * @return true if hp > 0, false otherwise
     */
    public boolean isAlive() {
        return this.hitPoint > 0;
    }

    /**
     * attack a piece by reducing hp by 1
     *
     * @param piece the hp of piece to be reduced
     */
    public void attack(Piece piece) {
        piece.reduceHP();
    }

    /**
     * find out piece's color
     *
     * @return the color representation
     */
    public String determineColor() {
        String textRep = "";
        if (this.getColor() == Color.BLACK) {
            textRep += "B";
        } else {
            textRep += "W";
        }
        return textRep;
    }

    /**
     * defines how pieces move
     */
    public abstract boolean move(Position position);

    /**
     * get piece string representation
     *
     * @return the representation
     */
    public abstract String getStringRepresentation();

    /**
     * get all possible moves of the piece
     *
     * @param end the source position
     * @return all possible squares
     */
    public abstract Collection<Square> allPossibleMoves();


    /**
     * get current position of the piece
     *
     * @return current position
     */
    public Position getCurrentPosition() {
        return this.position;
    }

    /**
     * get the color of the piece
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * get game's board
     *
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * set a position to the piece as a reference
     *
     * @param position the position to be updated
     */
    public void setCurrentPosition(Position position) {
        this.position = position;
    }
}
