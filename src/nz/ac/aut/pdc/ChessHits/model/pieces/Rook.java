/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.*;
import nz.ac.aut.pdc.ChessHits.model.*;

/**
 * Rook can move vertically or horizontally on chessboard
 *
 * @author Yue Li
 * @version 31-07-13 class is built. move method implemented
 */
public class Rook extends Piece {

    private final String STRING_REPRESENTATION = "R";
    private boolean isAbleToCastle;

    /**
     * construct a rook object
     *
     * @param board the board that rook will be on
     * @param hitPoint the hit points of rook
     * @param position the initial position of rook
     * @param color the color of rook
     */
    public Rook(Board board, int hitPoint, Position position, Color color) {
        super(board, hitPoint, position, color);
        this.isAbleToCastle = true;
    }

    /**
     * move rook to a position
     *
     * @param targetPosition the target position
     * @return true if rook is moved, false otherwise
     */
    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSuccessful = false;
        Position currentPos = super.getCurrentPosition();
        if (currentPos.getColumn() == targetPosition.getColumn() && currentPos.getRow() != targetPosition.getRow()
                || currentPos.getRow() == targetPosition.getRow() && currentPos.getColumn() != targetPosition.getColumn()) {
            isMoveSuccessful = true;
        }
        return isMoveSuccessful;
    }

    /**
     * get rook's representation
     *
     * @return string representation
     */
    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    /**
     * get all possible move square of rook
     *
     * @param end the source position
     * @return all possible squares to move
     */
    @Override
    public Collection<Square> allPossibleMoves() {
        Collection<Square> squares = new HashSet<>();
        Board board = super.getBoard();
        boolean isOccupiedNotFound;
        boolean shouldAddElement = true;
        Position tempPos;
        int row = super.getCurrentPosition().getRow() + 1;
        while (row <= 7) {
            tempPos = board.getPositions()[row][super.getCurrentPosition().getColumn()];
            Square temSquare = board.getSquare(tempPos);
            isOccupiedNotFound = temSquare.isSquareAvailable();
            if (shouldAddElement) {
                squares.add(temSquare);
            }
            if (!isOccupiedNotFound) {
                shouldAddElement = false;
            }
            row++;
        }
        shouldAddElement = true;
        row = super.getCurrentPosition().getRow() - 1;
        while (row >= 0) {
            tempPos = board.getPositions()[row][super.getCurrentPosition().getColumn()];
            Square temSquare = board.getSquare(tempPos);
            isOccupiedNotFound = !temSquare.isSquareAvailable();
            if (shouldAddElement) {
                squares.add(temSquare);
            }
            if (isOccupiedNotFound) {
                shouldAddElement = false;
            }
            row--;
        }
        shouldAddElement = true;
        int col = super.getCurrentPosition().getColumn() - 1;
        while (col >= 0) {
            tempPos = board.getPositions()[super.getCurrentPosition().getRow()][col];
            Square temSquare = board.getSquare(tempPos);
            isOccupiedNotFound = !temSquare.isSquareAvailable();
            if (shouldAddElement) {
                squares.add(temSquare);
            }
            if (isOccupiedNotFound) {
                shouldAddElement = false;
            }
            col--;
        }
        shouldAddElement = true;
        col = super.getCurrentPosition().getColumn() + 1;
        while (col <= 7) {
            tempPos = board.getPositions()[getCurrentPosition().getRow()][col];
            Square temSquare = board.getSquare(tempPos);
            isOccupiedNotFound = !temSquare.isSquareAvailable();
            if (shouldAddElement) {
                squares.add(temSquare);
            }
            if (isOccupiedNotFound) {
                shouldAddElement = false;
            }
            col++;
        }
        return squares;
    }

    /**
     * disable castle status
     *
     * @deprecated as canceled at Sprint 2
     */
    public void disableCastle() {
        this.isAbleToCastle = false;
    }

    /**
     * get castle status
     *
     * @return true if rook is able to castle, false otherwise
     * @deprecated as canceled at Sprint 2
     */
    public boolean getCastleStatus() {
        return this.isAbleToCastle;
    }
}
