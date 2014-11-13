/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.pieces.Pawn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class PawnTest {

    private Pawn pawnBlack;
    private Pawn pawnWhite;
    private Board board;
    private Position whitePosition;
    private Position blackPosition;

    public PawnTest() {
    }

    @Before
    public void setUp() {
        board = new Board();
        whitePosition = new Position(4, 4);
        blackPosition = new Position(5, 5);
        pawnBlack = new Pawn(board, 1, blackPosition, Color.BLACK);
        pawnWhite = new Pawn(board, 1, whitePosition, Color.WHITE);
    }

    @After
    public void tearDown() {
        pawnBlack = null;
        pawnWhite = null;
        board = null;
        whitePosition = null;
    }

    /**
     * Test of getInitMoveStatus method, of class Pawn.
     */
    @Test
    public void testGetInitMoveStatusWhenFirstMove() {
        assertTrue(pawnBlack.getInitMoveStatus());
    }

    @Test
    public void testGetInitMoveStatusWhenHasMoved() {
        Position position = board.getPositions()[5][4];
        pawnWhite.move(position);
        assertFalse(pawnWhite.getInitMoveStatus());
    }

    /**
     * Test of move method, of class Pawn.
     */
    @Test
    public void testMoveAbleToMoveOneSquareForward() {
        Position positionNew = new Position(4, 5);
        assertTrue(pawnBlack.move(positionNew));
    }

    @Test
    public void testMoveAbleToMoveTwoSquaresForward() {
        Position positionNew = new Position(6, 4);
        assertTrue(pawnWhite.move(positionNew));
    }

    @Test
    public void testMovePawnsUnableToMoveBackward() {
        Position whitePos = new Position(3, 4);
        Position blackPos = new Position(6, 4);
        assertFalse(pawnWhite.move(whitePos));
        assertFalse(pawnBlack.move(blackPos));
    }

    /**
     * Test of getStringRepresentation method, of class Pawn.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("WP", pawnWhite.getStringRepresentation());
        assertEquals("BP", pawnBlack.getStringRepresentation());
    }
}
