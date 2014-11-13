/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import nz.ac.aut.pdc.ChessHits.model.pieces.Piece;

/**
 * Square represents 1 cell of the board
 *
 * @author Yue Li
 * @version 23-07-13 class is built, implemented basic functions for getting
 * info from the square and methods for adding and removing piece methods
 * @version 02-08-13 handled invalid input for class constructor
 */
public class Square {

    private boolean isAvailable;
    private Piece occupiedPiece;
    private Position position;

    /**
     * construct a Square object
     *
     * @param position The coordinates of the Square
     * @throws NullPointerException if position is null
     */
    public Square(Position position) {
        if (position == null) {
            throw new NullPointerException("the position should exsit but was " + position);
        }
        this.isAvailable = true;
        this.occupiedPiece = null;
        this.position = position;
    }

    /**
     * add a piece to the square
     *
     * @param piece the piece intend to be in the square
     * @return true if piece is added, false otherwise
     */
    public boolean addPiece(Piece piece) {
        boolean isSuccessful = false;
        if (piece != null) {
            this.occupiedPiece = piece;
            piece.setCurrentPosition(this.position);
            isSuccessful = true;
            this.isAvailable = false;
        }
        return isSuccessful;
    }

    /**
     * remove a piece from the square
     *
     * @param piece the piece to be removed
     * @return true if piece is removed, false otherwise
     */
    public boolean removePiece(Piece piece) {
        boolean isSuccessful = false;
        if (piece != null) {
            this.occupiedPiece = null;
            piece.setCurrentPosition(null);
            this.isAvailable = true;
            isSuccessful = true;
        }
        return isSuccessful;
    }

    /**
     * gets the occupied piece
     *
     * @return the piece on the square
     */
    public Piece getOccupiedPiece() {
        return this.occupiedPiece;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * gets whether this square is available to add a piece
     *
     * @return true if it is available, false otherwise
     */
    public boolean isSquareAvailable() {
        return this.isAvailable;
    }

    /**
     * get the string representation of the square
     *
     * @return the text form
     */
    public String getStringRepresentation() {
        String outText = "";
        if (this.isAvailable) {
            outText += "  |";
        } else {
            outText += this.occupiedPiece.getStringRepresentation() + "|";
        }
        return outText;
    }
}
