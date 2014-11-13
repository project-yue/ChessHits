/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import nz.ac.aut.pdc.ChessHits.model.pieces.*;
import static org.junit.Assert.*;

/**
 *
 * @author Yue
 */
public class ChessHitsGameTest {

    private ChessHitsGame game;

    public ChessHitsGameTest() {
    }

    @Before
    public void setUp() {
        this.game = new ChessHitsGame();
    }

    @After
    public void tearDown() {
        this.game = null;
    }

    /**
     * Test of getPiece method, of class ChessHitsGame.
     */
    @Test
    public void testGetPieceSuccessful() {
        assertTrue(this.game.getPiece(new Position(0, 0)) instanceof Rook);
        assertTrue(this.game.getPiece(new Position(7, 0)) instanceof Rook);
        assertTrue(this.game.getPiece(new Position(0, 1)) instanceof Knight);
        assertTrue(this.game.getPiece(new Position(7, 1)) instanceof Knight);
        assertTrue(this.game.getPiece(new Position(0, 2)) instanceof Bishop);
        assertTrue(this.game.getPiece(new Position(7, 2)) instanceof Bishop);
        assertTrue(this.game.getPiece(new Position(0, 3)) instanceof Queen);
        assertTrue(this.game.getPiece(new Position(7, 3)) instanceof Queen);
        assertTrue(this.game.getPiece(new Position(0, 4)) instanceof King);
        assertTrue(this.game.getPiece(new Position(7, 4)) instanceof King);
    }

    /**
     * test move king
     */
    @Test
    public void testMovePlayerPieceKingCantMoveToAllianceSquare() {
        assertFalse(this.game.movePlayerPiece(new Position(0, 4), new Position(0, 3)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 4), new Position(0, 5)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 4), new Position(1, 5)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 4), new Position(1, 4)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 4), new Position(1, 3)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 4), new Position(7, 3)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 4), new Position(7, 5)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 4), new Position(6, 5)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 4), new Position(6, 4)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 4), new Position(6, 3)));
    }

    @Test
    public void testMovePlayerPieceKingCanMoveToEmptySquare() {
        this.game.movePlayerPiece(new Position(1, 4), new Position(3, 4));
        assertTrue(this.game.movePlayerPiece(new Position(0, 4), new Position(1, 4)));
    }

    @Test
    public void testMovePlayerPieceKingAttackPiece() {
        this.game.movePlayerPiece(new Position(6, 4), new Position(5, 4));
        this.game.movePlayerPiece(new Position(7, 4), new Position(6, 4));
        this.game.movePlayerPiece(new Position(6, 4), new Position(5, 5));
        this.game.movePlayerPiece(new Position(5, 5), new Position(4, 5));
        this.game.movePlayerPiece(new Position(4, 5), new Position(3, 5));
        this.game.movePlayerPiece(new Position(3, 5), new Position(2, 5));
        assertTrue(this.game.getPiece(new Position(1, 5)) instanceof Pawn);
        this.game.movePlayerPiece(new Position(2, 5), new Position(1, 5));
        assertTrue(this.game.getPiece(new Position(1, 5)) instanceof King
                && this.game.getPiece(new Position(1, 5)).getColor() == Color.BLACK);
    }

    /**
     * move queen
     */
    @Test
    public void testMovePlayerPieceQueenCantMoveToAllianceSquare() {
        assertFalse(this.game.movePlayerPiece(new Position(7, 5), new Position(7, 4)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 5), new Position(7, 6)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 5), new Position(6, 6)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 5), new Position(6, 5)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 5), new Position(6, 4)));
    }

    @Test
    public void testMovePlayerPieceQueenAttackSuccesssful() {
        this.game.movePlayerPiece(new Position(1, 4), new Position(2, 4));
        this.game.movePlayerPiece(new Position(0, 3), new Position(2, 5));
        assertTrue(this.game.getPiece(new Position(6, 5)).getHP() == 3);
        this.game.movePlayerPiece(new Position(2, 5), new Position(6, 5));
        assertTrue(this.game.getPiece(new Position(6, 5)).getHP() == 2);
    }

    @Test
    public void testMovePlayerPieceQueenKillPieceSuccessful() {
        this.game.movePlayerPiece(new Position(1, 4), new Position(2, 4));
        this.game.movePlayerPiece(new Position(0, 3), new Position(2, 5));
        this.game.getPiece(new Position(6, 5)).reduceHP();
        this.game.getPiece(new Position(6, 5)).reduceHP();
        assertTrue(this.game.getPiece(new Position(6, 5)).getHP() == 1);
        this.game.movePlayerPiece(new Position(2, 5), new Position(6, 5));
        assertTrue(this.game.getPiece(new Position(6, 5)) instanceof Queen
                && this.game.getPiece(new Position(6, 5)).getColor() == Color.WHITE);
    }

    //bishop
    @Test
    public void testMovePlayerPieceBishopCantMoveToAllianceSquare() {
        assertFalse(this.game.movePlayerPiece(new Position(0, 2), new Position(1, 3)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 2), new Position(1, 1)));
    }

    @Test
    public void testMovePlayerPieceBishopCantMoveOverOtherPieces() {
        assertFalse(this.game.movePlayerPiece(new Position(0, 2), new Position(2, 4)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 2), new Position(2, 0)));
        assertFalse(this.game.movePlayerPiece(new Position(6, 2), new Position(5, 0)));
        assertFalse(this.game.movePlayerPiece(new Position(6, 2), new Position(5, 4)));
    }

    @Test
    public void testMovePlayerPieceBishopSuccessfulMove() {
        this.game.movePlayerPiece(new Position(6, 1), new Position(5, 1));
        assertTrue(this.game.movePlayerPiece(new Position(7, 2), new Position(5, 0)));
        assertTrue(this.game.movePlayerPiece(new Position(5, 0), new Position(2, 3)));
    }

    @Test
    public void testMovePlayerPieceBishopAttackSuccessful() {
        this.game.movePlayerPiece(new Position(6, 1), new Position(5, 1));
        assertTrue(this.game.movePlayerPiece(new Position(7, 2), new Position(5, 0)));
        assertTrue(this.game.getPiece(new Position(1, 4)).getHP() == 3);
        assertTrue(this.game.movePlayerPiece(new Position(5, 0), new Position(1, 4)));
        assertTrue(this.game.getPiece(new Position(1, 4)).getHP() == 2);
    }

    @Test
    public void testMovePlayerPieceBishopKillPieceSuccessful() {
        this.game.movePlayerPiece(new Position(6, 1), new Position(5, 1));
        assertTrue(this.game.movePlayerPiece(new Position(7, 2), new Position(5, 0)));
        this.game.getPiece(new Position(1, 4)).reduceHP();
        this.game.getPiece(new Position(1, 4)).reduceHP();
        assertTrue(this.game.getPiece(new Position(1, 4)).getHP() == 1);
        assertTrue(this.game.movePlayerPiece(new Position(5, 0), new Position(1, 4)));
        assertTrue(this.game.getPiece(new Position(1, 4)) instanceof Bishop);
    }

    //knight
    @Test
    public void testMovePlayerPieceKnightCantMoveToAllianceSquare() {
        assertFalse(this.game.movePlayerPiece(new Position(7, 1), new Position(6, 2)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 6), new Position(6, 5)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 1), new Position(1, 3)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 6), new Position(1, 4)));
    }

    @Test
    public void testMovePlayerPieceKnightSuccessfulMove() {
        assertTrue(this.game.movePlayerPiece(new Position(0, 1), new Position(2, 0)));
        assertTrue(this.game.movePlayerPiece(new Position(2, 0), new Position(0, 1)));
        assertTrue(this.game.movePlayerPiece(new Position(0, 1), new Position(2, 2)));
        assertTrue(this.game.movePlayerPiece(new Position(2, 2), new Position(0, 1)));
        assertTrue(this.game.movePlayerPiece(new Position(7, 1), new Position(5, 0)));
        assertTrue(this.game.movePlayerPiece(new Position(5, 0), new Position(7, 1)));
        assertTrue(this.game.movePlayerPiece(new Position(7, 1), new Position(5, 2)));
        assertTrue(this.game.movePlayerPiece(new Position(5, 2), new Position(7, 1)));
    }

    @Test
    public void testMovePlayerPieceKnightAttackSuccessful() {
        this.game.movePlayerPiece(new Position(0, 1), new Position(2, 2));
        this.game.movePlayerPiece(new Position(2, 2), new Position(4, 3));
        assertTrue(this.game.getPiece(new Position(6, 2)).getHP() == 3);
        assertTrue(this.game.movePlayerPiece(new Position(4, 3), new Position(6, 2)));
        assertTrue(this.game.getPiece(new Position(6, 2)).getHP() == 2);
    }

    //rook
    @Test
    public void testMovePlayerPieceRookCantMoveToAllianceSquare() {
        assertFalse(this.game.movePlayerPiece(new Position(0, 0), new Position(0, 1)));
        assertFalse(this.game.movePlayerPiece(new Position(0, 0), new Position(1, 0)));
    }

    @Test
    public void testMovePlayerPieceRookCantMoveOverOtherPieces() {
        assertFalse(this.game.movePlayerPiece(new Position(0, 0), new Position(5, 0)));
        assertFalse(this.game.movePlayerPiece(new Position(7, 0), new Position(3, 0)));
    }

    @Test
    public void testMovePlayerPieceRookSuccessfulMove() {
        this.game.movePlayerPiece(new Position(6, 7), new Position(4, 7));
        assertTrue(this.game.movePlayerPiece(new Position(7, 7), new Position(5, 7)));
        assertTrue(this.game.movePlayerPiece(new Position(5, 7), new Position(5, 4)));
        assertTrue(this.game.movePlayerPiece(new Position(5, 4), new Position(5, 7)));
        assertTrue(this.game.movePlayerPiece(new Position(5, 7), new Position(7, 7)));
    }

    @Test
    public void testMovePlayerPieceRookAttackSuccessful() {
        this.game.movePlayerPiece(new Position(1, 7), new Position(3, 7));
        this.game.movePlayerPiece(new Position(0, 7), new Position(2, 7));
        this.game.movePlayerPiece(new Position(2, 7), new Position(2, 5));
        assertTrue(this.game.getPiece(new Position(6, 5)).getHP() == 3);
        this.game.movePlayerPiece(new Position(2, 5), new Position(6, 5));
        assertTrue(this.game.getPiece(new Position(6, 5)).getHP() == 2);

    }

    @Test
    public void testMovePlayerPiecePawnUnableToMoveTwoSquaresOnceMoved() {
        this.game.movePlayerPiece(new Position(1, 3), new Position(2, 3));
        assertFalse(this.game.movePlayerPiece(new Position(2, 3), new Position(4, 3)));
    }

    @Test
    public void testMovePlayerPiecePawnUnableToForkPieceWhenSquareIsEmpty() {
        assertFalse(this.game.movePlayerPiece(new Position(6, 6), new Position(5, 5)));
    }

    @Test
    public void testMovePlayerPiecePawnSuccessfulFork() {
        int row = 1;
        for (int i = 0; i < 4; i++) {
            this.game.movePlayerPiece(new Position(row, 2), new Position(row + 1, 2));
            row++;
        }
        assertTrue(this.game.getPiece(new Position(row + 1, 3)).getHP() == 3);
        assertFalse(this.game.movePlayerPiece(new Position(row, 2), new Position(row + 1, 2)));
        assertTrue(this.game.movePlayerPiece(new Position(row, 2), new Position(row + 1, 3)));
        assertTrue(this.game.getPiece(new Position(row + 1, 3)).getHP() == 2);
        assertTrue(this.game.getPiece(new Position(row, 2)).getHP() == 2);


    }
}