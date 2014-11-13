/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import java.util.*;
import static nz.ac.aut.pdc.ChessHits.model.Color.*;
import nz.ac.aut.pdc.ChessHits.model.pieces.*;
import nz.ac.aut.pdc.ChessHits.userDB.UserDatabase;

/**
 * the wrapper class of ChessHitsGame.
 *
 * @author Yue Li
 * @version 31-07-13 class is built, implemented Chess board initialization
 * @version 01-08-13 the initialization function is done. loading data should be
 * at the root directory of the project. the game class is able to read from txt
 * file.
 * @version 20-08-13 implemented draft move control.
 */
public class ChessHitsGame {

    private Board board;
    private Player blackPlayer;
    private Player whitePlayer;
    private Player winner;
    private boolean isGameRunning;
    private boolean firstSelected = false;
    private Square currentSelectedSquare;
    private boolean whiteTurn;
    private UserDatabase userDB;

    /**
     * create a new game.
     */
    public ChessHitsGame() {
        this.winner = null;
        this.isGameRunning = true;
        this.board = new Board();
        generateRankPieces(BLACK);
        generateRankPieces(WHITE);
        generatePawns(BLACK);
        generatePawns(WHITE);
    }

    /**
     * test if pawn is able to fork a piece
     *
     * @param piece the pawn
     * @param toPos the place to be forked
     * @return true if pawn is able to fork, false otherwise
     */
    private boolean isPawnAbleFork(Piece piece, Position toPos) {
        boolean isSuccessful = false;
        Pawn forkPawn = (Pawn) piece;
        Piece anotherPiece = null;
        if (this.board.getSquare(toPos).getOccupiedPiece() != null) {
            anotherPiece = this.board.getSquare(toPos).getOccupiedPiece();
        }
        //black
        boolean isOkayToForkPiece = forkPawn.canForkPosition(toPos) && anotherPiece != null && !this.board.getSquare(toPos).isSquareAvailable();
        if (isOkayToForkPiece) {
            isSuccessful = true;
        }
        return isSuccessful;
    }

    /**
     * generate pawn pieces for selected color.
     *
     * @param color the color for Pawn to be
     */
    private void generatePawns(Color color) {
        int row;
        if (color == Color.BLACK) {
            row = 6;
        } else {
            row = 1;
        }
        for (int col = 0; col < 8; col++) {
            Position temPosition = this.board.getPositions()[row][col];
            Square tempSquare = this.board.getSquare(temPosition);
            Piece pawn = new Pawn(this.board, 3, temPosition, color);
            tempSquare.addPiece(pawn);
        }
    }

    /**
     * get a square from board
     *
     * @param row row of the square
     * @param col column of the square
     * @return the square to be selected
     */
    public Square getSquare(int row, int col) {
        return this.board.getSquare(board.getPositions()[row][col]);

    }

    /**
     * get board
     *
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * get piece from a position
     *
     * @param position the position of the piece
     * @return the piece of the position
     */
    protected Piece getPiece(Position position) {
        return board.getSquare(position).getOccupiedPiece();

    }

    /**
     * set database
     *
     * @param database the database to be selected
     */
    public void setDataBase(UserDatabase database) {
        this.userDB = database;
    }

    /**
     * moves the players Piece to the new place and attacks a piece if it is in
     * its way
     *
     * @param curPos where the players piece is currently
     * @param tarPos where you want to move the piece
     */
    protected boolean movePlayerPiece(Position curPos, Position tarPos) {
        Piece piece = getPiece(curPos);
        boolean isMoved = false;
        if (positionOnBoard(tarPos) && piece != null) {
            isMoved = determineMoveOrAttack(piece, tarPos);
        }
        return isMoved;
    }

    /**
     * a piece moves to a square if the square is available, or it attacks the
     * piece of the square
     *
     * @param piece piece to move
     * @param toPos move to position
     * @return true if piece performed a move or attack, false otherwise
     */
    private boolean determineMoveOrAttack(Piece piece, Position toPos) {
        boolean isMoved = false;
        Square destSquare = this.board.getSquare(toPos);
        Piece dPiece = this.board.getSquare(toPos).getOccupiedPiece();
        boolean shouldAttack = false;
        //moves
        Collection<Square> pieceSquares = piece.allPossibleMoves();
        if (pieceSquares.contains(destSquare)) {
            System.out.println("on the move");
            isMoved = movePieceTo(piece, destSquare);
            if (!isMoved) {
                shouldAttack = true;
            }
            if (destSquare.getOccupiedPiece() != null && shouldAttack && piece.getColor() != destSquare.getOccupiedPiece().getColor()) {//when game think
                isMoved = attackPiece(piece, dPiece);
            }
        }
        return isMoved;

    }

    /**
     * chess piece battle
     *
     * @param fromPiece the attacker
     * @param toPiece the defender
     * @return true if battle occurred, false otherwise
     */
    private boolean attackPiece(Piece fromPiece, Piece toPiece) {
        boolean isSuccessful = false;
        if (fromPiece instanceof Pawn && toPiece != null && toPiece.isAlive()) {
            Pawn pawn = (Pawn) fromPiece;
            if (isPawnAbleFork(pawn, toPiece.getCurrentPosition())) {
                pawn.attack(toPiece);
                isSuccessful = true;
                if (toPiece instanceof Pawn && toPiece.isAlive()) {
                    Pawn anotherPawn = (Pawn) toPiece;
                    if (isPawnAbleFork(anotherPawn, fromPiece.getCurrentPosition())) {
                        anotherPawn.attack(fromPiece);
                    }
                }
            }
        } else if (toPiece != null) {
            System.out.println(fromPiece.getStringRepresentation() + " attacks " + toPiece.getStringRepresentation());
            fromPiece.attack(toPiece);
            if (toPiece.isAlive() && toPiece instanceof Pawn && isPawnAbleFork(toPiece, fromPiece.getCurrentPosition())) {
                toPiece.attack(fromPiece);
            } else if (toPiece instanceof Pawn) {
                // do nothing
            } else if (toPiece.isAlive() && toPiece.move(fromPiece.getCurrentPosition())) {
                System.out.println(toPiece.getStringRepresentation() + " attacks " + fromPiece.getStringRepresentation());
                toPiece.attack(fromPiece);
            }
            isSuccessful = true;
        }
        if (!fromPiece.isAlive()) {
            Position temPos = fromPiece.getCurrentPosition();
            this.board.getSquare(temPos).removePiece(fromPiece);
        }
        if (toPiece != null && !toPiece.isAlive()) {
            Position temPos = toPiece.getCurrentPosition();
            Square toSquare = this.board.getSquare(temPos);
            Position temFromPos = fromPiece.getCurrentPosition();
            Square fromSquare = this.board.getSquare(temFromPos);
            fromSquare.removePiece(fromPiece);
            toSquare.removePiece(toPiece);
            toSquare.addPiece(fromPiece);
        }
        if (toPiece instanceof King && !toPiece.isAlive()) {
            switch (toPiece.getColor()) {
                case BLACK:
                    this.winner = this.whitePlayer;
                    break;
                case WHITE:
                    this.winner = this.blackPlayer;
                    break;
                default:
                    break;
            }
            this.userDB.increaseWins(this.winner.getName());
            System.out.println(this.winner.getName() + " won the game");
            this.isGameRunning = false;
            this.userDB.closeConnections();
        } else {
            if (isSuccessful && fromPiece.isAlive()) {
                System.out.println(fromPiece.getStringRepresentation() + " NOW at row: " + fromPiece.getCurrentPosition().getRow()
                        + " column: " + fromPiece.getCurrentPosition().getColumn() + "; " + fromPiece.getStringRepresentation()
                        + " has " + fromPiece.getHP() + " left");
            }
            if (isSuccessful && toPiece != null && toPiece.isAlive()) {
                System.out.println(toPiece.getStringRepresentation() + " NOW at row: " + toPiece.getCurrentPosition().getRow()
                        + " column: " + toPiece.getCurrentPosition().getColumn() + "; " + toPiece.getStringRepresentation()
                        + " has " + toPiece.getHP() + " left.");
            }
        }
        return isSuccessful;
    }

    /**
     * generate higher rank pieces for selected color
     *
     * @param color the color for a set of pieces to be
     */
    private void generateRankPieces(Color color) {
        int row;
        if (color == Color.WHITE) {
            row = 0;
        } else {
            row = 7;
        }
        //rook init
        Position temPosition = board.getPositions()[row][0];
        Square temSquare = board.getSquare(temPosition);
        Rook rook1 = new Rook(this.board, 2, temPosition, color);
        temSquare.addPiece(rook1);
        temPosition = board.getPositions()[row][7];
        temSquare = board.getSquare(temPosition);
        Rook rook2 = new Rook(this.board, 2, temPosition, color);
        temSquare.addPiece(rook2);
        //knight initnir
        temPosition = board.getPositions()[row][1];
        Knight knight1 = new Knight(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(knight1);
        temPosition = board.getPositions()[row][6];
        Knight knight2 = new Knight(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(knight2);
        //bishop init
        temPosition = board.getPositions()[row][2];
        Bishop bishop1 = new Bishop(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(bishop1);
        temPosition = board.getPositions()[row][5];
        Bishop bishop2 = new Bishop(this.board, 2, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(bishop2);
        //queen init
        temPosition = board.getPositions()[row][3];
        Queen queen = new Queen(this.board, 1, temPosition, color);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(queen);
        //king init
        temPosition = board.getPositions()[row][4];
        King king = new King(this.board, 1, temPosition, color, true);
        temSquare = board.getSquare(temPosition);
        temSquare.addPiece(king);
    }

    /**
     * check if piece is on board
     *
     * @param position the position to be checked
     * @return true if it is, false otherwise
     */
    private boolean positionOnBoard(Position position) {
        boolean isOnBoard = false;
        if (position.getRow() >= 0 && position.getColumn() >= 0
                && position.getColumn() <= 7 && position.getRow() <= 7) {

            isOnBoard = true;
        }
        return isOnBoard;
    }

    /**
     * get whether a game is running at the moment
     *
     * @return true if a game is running, false otherwise
     */
    public boolean getGameStatus() {
        return this.isGameRunning;
    }

    /**
     * move a piece
     *
     * @param piece piece to move
     * @param square square that piece moves onto
     * @return true if piece moved, false otherwise
     */
    private boolean movePieceTo(Piece piece, Square square) {
        boolean isSuccessful = false;
        if (square.isSquareAvailable() && piece.move(square.getPosition())) {
            board.getSquare(piece.getCurrentPosition()).removePiece(piece);
            square.addPiece(piece);
            isSuccessful = true;
            System.out.println(piece.getStringRepresentation() + " moved to row: "
                    + square.getPosition().getRow() + " column: " + square.getPosition().getColumn());
        } else {
            System.out.println(piece.getStringRepresentation() + " cannot move to the target position");
        }
        return isSuccessful;
    }

    /**
     * determine whether a square is selected
     *
     * @param square the square to be selected
     * @return true if is selected, false otherwise
     */
    public boolean getSelectedSquare(Square square) {
        boolean turn = false;
        if (!firstSelected && !square.isSquareAvailable()) {
            Piece piece = square.getOccupiedPiece();
            if (whiteTurn) {
                if (piece.getColor() == WHITE) {
                    currentSelectedSquare = square;
                    firstSelected = true;
                    turn = true;
                }
            } else if (piece.getColor() == BLACK) {
                currentSelectedSquare = square;
                firstSelected = true;
                turn = true;
            }
        } else if (this.firstSelected && currentSelectedSquare != null) {
            firstSelected = false;
            if (movePlayerPiece(currentSelectedSquare.getPosition(), square.getPosition())) {
                whiteTurn = !whiteTurn;
                whitePlayer.setIsTurn(!whitePlayer.getIsTurn());
                blackPlayer.setIsTurn(!blackPlayer.getIsTurn());
            }
        } else {
            this.currentSelectedSquare = null;
        }
        return turn;
    }

    /**
     * get player
     *
     * @param color the color to be selected
     * @return player with the specified color
     */
    public Player getPlayer(Color color) {
        Player player = null;
        switch (color) {
            case WHITE:
                player = this.whitePlayer;
                break;
            case BLACK:
                player = this.blackPlayer;
                break;
            default:
                break;
        }

        return player;
    }

    /**
     * get the winner of a game
     *
     * @return the winner player
     */
    public Player getWinner() {
        return this.winner;
    }

    public Piece promotePawn(Pawn promotePawn, String promotionPieceName) {
        return promotePawn.promote(this.board, promotionPieceName);
    }

    /**
     * set a player for black pieces
     *
     * @param player the player to be chosen
     */
    public void setBlackPlayer(Player player) {
        this.blackPlayer = player;
    }

    /**
     * set a player for white pieces
     *
     * @param player the player to be chosen
     */
    public void setWhitePlayer(Player player) {
        this.whitePlayer = player;
    }

    /**
     * set white's turn
     *
     * @param whiteTurn
     */
    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }
}
