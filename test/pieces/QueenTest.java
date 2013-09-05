/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.pieces.Queen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class QueenTest {

    private Queen queenWhite;
    private Queen queenBlack;
    private Position position;
    private Board board;

    public QueenTest() {
    }

    @Before
    public void setUp() {
        position = new Position(1, 1);
        queenBlack = new Queen(board, 1, position, Color.BLACK);
        queenWhite = new Queen(board, 1, position, Color.WHITE);
        this.board = new Board();
    }

    @After
    public void tearDown() {
        position = null;
        queenBlack = null;
        queenWhite = null;
    }

    /**
     * Test of move method, of class Queen.
     */
    @Test
    public void testMoveVerticallySuccess() {
        Position positionNew = new Position(1, 5);
        assertTrue(queenWhite.move(positionNew));
    }

    @Test
    public void testMoveHorizontallySuccess() {
        Position positionNew = new Position(5, 1);
        assertTrue(queenWhite.move(positionNew));
    }

    @Test
    public void testMoveDiagonallySuccess() {
        Position positionNew = new Position(6, 6);
        assertTrue(queenWhite.move(positionNew));
    }

    /**
     * Test of getStringRepresentation method, of class Queen.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("BQ", queenBlack.getStringRepresentation());
        assertEquals("WQ", queenWhite.getStringRepresentation());
    }
}
