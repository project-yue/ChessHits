/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model.pieces;

import java.util.Collection;
import java.util.HashSet;
import nz.ac.aut.pdc.ChessHits.model.*;

/**
 *
 * @author Yue Li
 * @version 02-08-13 the implementation for move is done
 */
public class Bishop extends Piece {

    private final String STRING_REPRESENTATION = "B";

    /**
     * construct a Bishop object
     *
     * @param hitPoint the times of attack a bishop can take
     * @param position the initial position
     * @param color the resource color
     */
    public Bishop(Board board, int hitPoint, Position position, Color color) {
        super(board, hitPoint, position, color);
    }

    /**
     * move bishop to a target position on the board
     *
     * @param board the resource board
     * @param targetPosition the position bishop is moving to
     * @return true if bishop successfully moved, false otherwise
     */
    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSuccessful = false;
        int colTarget = targetPosition.getColumn() - getCurrentPosition().getColumn();
        int rowTarget = targetPosition.getRow() - getCurrentPosition().getRow();
        //checking if it can move
        if (Math.abs(colTarget) - Math.abs(rowTarget) == 0) {
            isMoveSuccessful = true;
        }

        return isMoveSuccessful;
    }

    @Override
    public Collection<Square> allPossibleMoves() {
        Collection<Square> squares = new HashSet<>();
        Board board = super.getBoard();
        int curRow = getCurrentPosition().getRow(), curCol = getCurrentPosition().getColumn();
        boolean isOccupantNotFound;
        boolean shouldAddElement = true;
        boolean shouldLoop = true;
        int rowIndex = curRow + 1, colIndex = curCol + 1;
        while (shouldLoop && rowIndex <= 7 && colIndex <= 7) {
            if (shouldLoop) {
                Square tempSquare = board.getSquare(board.getPositions()[rowIndex][colIndex]);
                isOccupantNotFound = tempSquare.isSquareAvailable();
                if (!isOccupantNotFound) {
                    shouldAddElement = false;
                    shouldLoop = false;
                    squares.add(tempSquare);
                } else if (shouldAddElement) {
                    squares.add(tempSquare);
                }
                rowIndex++;
                colIndex++;
            }
        }
        shouldLoop = true;
        shouldAddElement = true;
        rowIndex = curRow - 1;
        colIndex = curCol + 1;
        while (shouldLoop && rowIndex >= 0 && colIndex <= 7) {
            Square tempSquare = board.getSquare(board.getPositions()[rowIndex][colIndex]);
            isOccupantNotFound = tempSquare.isSquareAvailable();
            if (!isOccupantNotFound) {
                shouldAddElement = false;
                squares.add(tempSquare);
                shouldLoop = false;
            } else if (shouldAddElement) {
                squares.add(tempSquare);
            }
            rowIndex--;
            colIndex++;
        }
        shouldLoop = true;
        shouldAddElement = true;
        rowIndex = curRow - 1;
        colIndex = curCol - 1;
        while (shouldLoop && rowIndex >= 0 && colIndex >= 0) {
            Square tempSquare = board.getSquare(board.getPositions()[rowIndex][colIndex]);
            isOccupantNotFound = tempSquare.isSquareAvailable();
            if (!isOccupantNotFound) {
                shouldAddElement = false;
                squares.add(tempSquare);
                shouldLoop = false;
            } else if (shouldAddElement) {
                squares.add(tempSquare);
            }
            rowIndex--;
            colIndex--;
        }
        shouldLoop = true;
        shouldAddElement = true;
        rowIndex = curRow + 1;
        colIndex = curCol - 1;
        while (shouldLoop && rowIndex <= 7 && colIndex >= 0) {
            Square tempSquare = board.getSquare(board.getPositions()[rowIndex][colIndex]);
            isOccupantNotFound = tempSquare.isSquareAvailable();
            if (!isOccupantNotFound) {
                shouldAddElement = false;
                squares.add(tempSquare);
                shouldLoop = false;
            } else if (shouldAddElement) {
                squares.add(tempSquare);
            }
            rowIndex++;
            colIndex--;
        }
        return squares;
    }

    /**
     * gets the string representation of bishop
     *
     * @return specified color with character B
     */
    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }
}
