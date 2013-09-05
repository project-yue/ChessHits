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
            isMoveSuccessful = true;
        }

        return isMoveSuccessful;
    }

    @Override
    public Collection<Square> allPossibleMoves(Position end) {
        //idea should be finding available positions for piece to move, 
        //it returns a set of empty squares for normal moves
        //or notify board to perform an attack
        Collection<Square> squares = new HashSet<>();
        Board board = super.getBoard();
        boolean isNW = false, isSE = false, isNE = false, isSW = false;
        if (Math.abs(end.getColumn() - getCurrentPosition().getColumn()) == Math.abs(end.getRow() - getCurrentPosition().getRow())) {
            //difine bishop move direction
            if (getCurrentPosition().getColumn() < end.getColumn() && getCurrentPosition().getRow() < end.getRow()) {
                isSE = true;
            } else if (getCurrentPosition().getColumn() < end.getColumn() && getCurrentPosition().getRow() > end.getRow()) {
                isNE = true;
            } else if (getCurrentPosition().getColumn() > end.getColumn() && getCurrentPosition().getRow() < end.getRow()) {
                isSW = true;
            } else if (getCurrentPosition().getColumn() > end.getColumn() && getCurrentPosition().getRow() > end.getRow()) {
                isNW = true;
            }
        }
        int curRow = getCurrentPosition().getRow(), curCol = getCurrentPosition().getColumn(), destRow = end.getRow(), destCol = end.getColumn();
        boolean isOccupantNotFound = true;
        boolean shouldAddElement = true;
        boolean shouldLoop = true;

        if (isSE) {//searches south east direction: values of row and col are the same
            int rowIndex = curRow + 1, colIndex = curCol + 1;
            while (shouldLoop && rowIndex <= destRow && colIndex <= destCol) {
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
        } else if (isNE) {//searches north east: row- col+
            int rowIndex = curRow - 1, colIndex = curCol + 1;
            while (shouldLoop && rowIndex >= destRow && colIndex <= destCol) {
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
        } else if (isNW) {//searches north west:  row- col- 
            int rowIndex = curRow - 1, colIndex = curCol - 1;
            while (shouldLoop && rowIndex >= destRow && colIndex >= destCol) {
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
        } else if (isSW) {//searches south west row+ col-
            int rowIndex = curRow + 1, colIndex = curCol - 1;
            while (shouldLoop && rowIndex <= destRow && colIndex >= destCol) {
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
