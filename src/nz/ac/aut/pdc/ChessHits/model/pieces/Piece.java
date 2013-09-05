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

    public void reduceHP() {
        this.hitPoint--;
    }

    public int getHP() {
        return this.hitPoint;
    }

    public boolean isAlive() {
        return this.hitPoint > 0;
    }

    public void attack(Piece piece) {
        piece.reduceHP();
    }

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

    public abstract String getStringRepresentation();

    public abstract Collection<Square> allPossibleMoves(Position end);

    public Position getCurrentPosition() {
        return this.position;
    }

    public Color getColor() {
        return this.color;
    }

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
