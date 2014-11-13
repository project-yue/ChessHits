package pieces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.pieces.Knight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class KnightTest {

    private Board board;
    private Position position;
    private Knight knightWhite;
    private Knight knightBlack;

    public KnightTest() {
    }

    @Before
    public void setUp() {
        board = new Board();
        position = new Position(1, 1);
        knightWhite = new Knight(board, 1, position, Color.WHITE);
        knightBlack = new Knight(board, 1, position, Color.BLACK);
    }

    @After
    public void tearDown() {
        board = null;
        position = null;
        knightBlack = null;
        knightWhite = null;
    }

    /**
     * Test of move method, of class Knight.
     */
    @Test
    public void testMoveSuccessful() {
        Position positionSEUpper = new Position(2, 3);
        assertTrue(knightBlack.move(positionSEUpper));
        Position positionSELower = new Position(3, 5);
    }

    /**
     * Test of getStringRepresentation method, of class Knight.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("BN", knightBlack.getStringRepresentation());
        assertEquals("WN", knightWhite.getStringRepresentation());
    }
}
