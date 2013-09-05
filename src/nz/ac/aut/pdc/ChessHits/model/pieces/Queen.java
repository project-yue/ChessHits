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
 * @version 24-07-13 class is built
 * @version 05-08-13 move method is completed
 */
public class Queen extends Piece {

    private final String STRING_REPRESENTATION = "Q";

    public Queen(Board board, int hitPoint, Position position, Color color) {
        super(board, hitPoint, position, color);
    }

    /**
     * move queen on the board
     *
     * @param board the resource board
     * @param position the target position
     * @return true if move is successful, false otherwise
     */
    @Override
    public boolean move(Position targetPosition) {
        boolean isMoveSuccessful = false;
        Position currentPos = super.getCurrentPosition();
        int columnDifference = targetPosition.getColumn() - super.getCurrentPosition().getColumn();
        int rowDifference = targetPosition.getRow() - super.getCurrentPosition().getRow();
        if (Math.abs(rowDifference) == Math.abs(columnDifference)) {//rowDifference == columnDifference || rowDifference + columnDifference == 0) {
            isMoveSuccessful = true;
        } else if (currentPos.getColumn() == targetPosition.getColumn() && currentPos.getRow() != targetPosition.getRow()
                || currentPos.getRow() == targetPosition.getRow() && currentPos.getColumn() != targetPosition.getColumn()) {
            isMoveSuccessful = true;
        }
        return isMoveSuccessful;
    }

    /**
     * get queen's text representation
     *
     * @return string "Q" with color
     */
    @Override
    public String getStringRepresentation() {
        return super.determineColor() + this.STRING_REPRESENTATION;
    }

    @Override
    public Collection<Square> allPossibleMoves(Position end) {
        Collection<Square> squares = new HashSet<>();
        Board board = super.getBoard();
        boolean isNW = false, isSE = false, isNE = false, isSW = false, isVertical = false, isHorizontal = false;;
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
        } else if (getCurrentPosition().getColumn() == end.getColumn()) {
            isVertical = true;
        } else if (getCurrentPosition().getRow() == end.getRow()) {
            isHorizontal = true;
        }
        int curRow = getCurrentPosition().getRow(), curCol = getCurrentPosition().getColumn(), destRow = end.getRow(), destCol = end.getColumn();
        boolean isOccupantNotFound = true;
        boolean shouldAddElement = true;
        boolean shouldLoop = true;
        Position tempPos = null;
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
        } else if (isVertical
                && getCurrentPosition()
                .getRow() <= end.getRow()) {
            int row = getCurrentPosition().getRow() + 1;
            for (int index = row; index <= end.getRow(); index++) {
                tempPos = board.getPositions()[index][getCurrentPosition().getColumn()];
                Square temSquare = board.getSquare(tempPos);
                isOccupantNotFound = temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (!isOccupantNotFound) {
                    shouldAddElement = false;
                }
            }
        } else if (isVertical
                && getCurrentPosition()
                .getRow() >= end.getRow()) {
            int row = getCurrentPosition().getRow() - 1;
            for (int index = row; index >= end.getRow(); index--) {
                tempPos = board.getPositions()[index][getCurrentPosition().getColumn()];
                Square temSquare = board.getSquare(tempPos);
                isOccupantNotFound = !temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (isOccupantNotFound) {
                    shouldAddElement = false;
                }
            }
        } else if (isHorizontal
                && getCurrentPosition()
                .getColumn() >= end.getColumn()) {
            int col = getCurrentPosition().getColumn() - 1;
            for (int index = col; index >= end.getColumn(); index--) {
                tempPos = board.getPositions()[getCurrentPosition().getRow()][col];
                Square temSquare = board.getSquare(tempPos);
                isOccupantNotFound = !temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (isOccupantNotFound) {
                    shouldAddElement = false;
                }
            }
        } else if (isHorizontal
                && getCurrentPosition()
                .getColumn() <= end.getColumn()) {
            int col = getCurrentPosition().getColumn() + 1;
            for (int index = col; index <= end.getColumn(); index++) {
                tempPos = board.getPositions()[getCurrentPosition().getRow()][index];
                Square temSquare = board.getSquare(tempPos);
                isOccupantNotFound = !temSquare.isSquareAvailable();
                if (shouldAddElement) {
                    squares.add(temSquare);
                }
                if (isOccupantNotFound) {
                    shouldAddElement = false;
                }
            }
        }
        return squares;
    }
}
