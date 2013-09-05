/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

/**
 * A position object holds the position information of a Piece object
 *
 * @author Yue Li
 * @version 23-07-13 class is created
 * @version 02-08-13 handled coordinates fault
 */
public class Position {

    private int row;
    private int column;

    /**
     * constructs a Position object
     *
     * @param row the resource row info
     * @param column the resource column info
     * @throws IllegalArgumentException if coordinates info out of range
     */
    public Position(int row, int column) {
        if (row > 7 || column > 7 || row < 0 || column < 0) {
            throw new IllegalArgumentException("input infomation should be in the range of 0-7 but row:" + row + " column:" + column);
        }
        this.row = row;
        this.column = column;
    }

    /**
     * gets the row
     *
     * @return the row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * gets the column
     *
     * @return the column
     */
    public int getColumn() {
        return this.column;
    }
}
