/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import nz.ac.aut.pdc.ChessHits.model.pieces.Piece;

/**
 * the place where pieces exist
 *
 * @author Yue Li
 * @version 23-07-13 class is built
 * @version 2-08-13 refined the board representation with coordinates
 */
public class Board {

    private final String ROWCOODINATES = " | a| b| c| d| e| f| g| h|";
    private final int WIDTH = 8, HEIGHT = 8;
    private Square[][] playBoard;
    private Position[][] positions;
    private Piece defensePiece;

    public Board() {
        initializeSquares();
    }

    /**
     * present the board in CLI environment
     */
    public void draw() {
        String forDraw = "";
        int howManyRow = 8;
        for (Square squareRow[] : playBoard) {
            forDraw += howManyRow + "|";
            for (Square SquareCol : squareRow) {

                forDraw += SquareCol.getStringRepresentation();
            }
            if (howManyRow != 1) {
                forDraw += "\n";
            }
            howManyRow--;
        }
        System.out.println(forDraw);
        System.out.println(this.ROWCOODINATES);
    }

    /**
     * get width
     *
     * @return the width
     */
    public int getWidth() {
        return this.WIDTH;
    }

    /**
     * get height
     *
     * @return the height
     */
    public int getHeight() {
        return this.HEIGHT;
    }

    /**
     * returns a square object in the board
     *
     * @param position the coordinates of the square
     * @return the target Square object
     */
    public Square getSquare(Position position) {
        return this.playBoard[position.getRow()][position.getColumn()];
    }

    /**
     * returns all the coordinates objects of the board, aim to locate various
     * square
     *
     * @return all the positions
     */
    public Position[][] getPositions() {
        return this.positions;
    }

    /**
     * initialize the board for holding different pieces
     */
    private void initializeSquares() {
        this.playBoard = new Square[8][8];
        this.positions = new Position[8][8];
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Position position = new Position(row, column);
                this.playBoard[row][column] = new Square(position);
                this.positions[row][column] = position;
            }
        }
    }
}
