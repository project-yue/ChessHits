/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.*;
import nz.ac.aut.pdc.ChessHits.model.*;

/**
 * King is the most important piece in the game
 *
 * @author Yue Li
 * @version 24-07-13 class is built, abstract implementation done, probably not
 * the best time to do the move method, basic function is done.
 * @version 25-07-13 the move method implemented. yet to pass proper tests.
 * contains commented lines.
 * @version 2-08-13 add function for castling with a rook
 * @version 5-08-13 refined move method
 */
public class King extends Piece {

    private final String STRING_REPRESENTATION = "K";
    private boolean isAbleToCastle;

    /**
     * construct a king object
     *
     * @param hitPoint the times of attacks king can take
     * @param position the initial position king is
     * @param color the color of king
     * @param isAbleToCastle resource castle ability of king
     */
    public King(Board board, int hitPoint, Position position, Color color, boolean isAbleToCastle) {
        super(board, hitPoint, position, color);
        this.isAbleToCastle = isAbleToCastle;
    }

    /**
     * king hits every piece down with only 1 hit
     *
     * @param piece the target
     */
    @Override
    public void attack(Piece piece) {
        while (piece.isAlive()) {
            piece.reduceHP();
        }
    }

    /**
     * move King to a new position on the board
     *
     * @param board The resource board
     * @param targetPosition The target position
     * @return true if move is legal, false otherwise
     */
    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSucessful = false;
        int horizontalDifference = targetPosition.getColumn() - super.getCurrentPosition().getColumn();
        int verticalDifference = targetPosition.getRow() - super.getCurrentPosition().getRow();

        if (horizontalDifference >= -1 && horizontalDifference <= 1 && verticalDifference >= -1 && verticalDifference <= 1) {

            isMoveSucessful = true;
        }

        return isMoveSucessful;
    }

    /**
     * string representation
     *
     * @return piece's color+"K"
     */
    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    /**
     * get all possible moves of piece
     *
     * @param end source position
     * @return all the possible positions
     */
    @Override
    public Collection<Square> allPossibleMoves() {
        Collection<Square> squares = new HashSet<>();
        ArrayList<Position> positions = new ArrayList<>();
        Board board = super.getBoard();
        int startRow = getCurrentPosition().getRow(), startCol = getCurrentPosition().getColumn();

        if (startRow > 0 && startCol > 0) {
            Position northWest = board.getPositions()[startRow - 1][startCol - 1];
            positions.add(northWest);
        }
        if (startRow > 0) {
            Position north = board.getPositions()[startRow - 1][startCol];
            positions.add(north);
        }
        if (startRow > 0 && startCol < 7) {
            Position northEast = board.getPositions()[startRow - 1][startCol + 1];
            positions.add(northEast);
        }
        if (startCol > 0) {
            Position west = board.getPositions()[startRow][startCol - 1];
            positions.add(west);
        }
        if (startCol < 7) {
            Position east = board.getPositions()[startRow][startCol + 1];
            positions.add(east);
        }
        if (startRow < 7 && startCol > 0) {
            Position sthWst = board.getPositions()[startRow + 1][startCol - 1];
            positions.add(sthWst);
        }
        if (startRow < 7) {
            Position sth = board.getPositions()[startRow + 1][startCol];
            positions.add(sth);
        }
        if (startRow < 7 && startCol < 7) {
            Position sthEst = board.getPositions()[startRow + 1][startCol + 1];
            positions.add(sthEst);
        }
        for (Position temPos : positions) {
            Square temSquare = board.getSquare(temPos);
            if (temSquare.isSquareAvailable() || temSquare.getOccupiedPiece().getColor() != this.getColor()) {
                squares.add(temSquare);
            }
        }
        return squares;
    }

    /**
     * get the castle status of king
     *
     * @return true if king is able to perform a castle, false otherwise
     * @deprecated
     */
    public boolean getCastleStatus() {
        return this.isAbleToCastle;
    }

    /**
     * disable the ability of castling
     *
     * @deprecated
     */
    public void disableCastle() {
        this.isAbleToCastle = false;
    }
}