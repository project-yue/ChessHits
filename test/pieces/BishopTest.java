package pieces;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Position;
import nz.ac.aut.pdc.ChessHits.model.pieces.Bishop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 *
 * Modified by Yue
 */
public class BishopTest {

    private Board board;
    private Bishop bishopWhite;
    private Bishop bishopBlack;
    private Position position22;
    private Position position55;

    public BishopTest() {
    }

    @Before
    public void setUp() {
        board = new Board();
        position22 = board.getPositions()[2][2];
        position55 = board.getPositions()[5][5];
        Position positionWhiteInit = board.getPositions()[0][0];
        Position positionBlackInit = board.getPositions()[7][7];
        bishopBlack = new Bishop(board, 2, positionBlackInit, Color.BLACK);
        bishopWhite = new Bishop(board, 2, positionWhiteInit, Color.WHITE);
        board.getSquare(positionWhiteInit).addPiece(bishopWhite);
        board.getSquare(positionBlackInit).addPiece(bishopBlack);
    }

    @After
    public void tearDown() {
        board = null;
        position22 = null;
        position55 = null;
        bishopBlack = null;
        bishopWhite = null;
    }

    @Test
    public void testMoveBishopSuccessfulMove() {
        assertTrue(bishopBlack.move(position22));
        assertTrue(bishopWhite.move(position55));
    }

    @Test
    public void testMoveBishopUnableMoveHorizontally() {
        Position temPos = board.getPositions()[7][2];
        assertFalse(bishopBlack.move(temPos));
    }

    @Test
    public void testMoveBishopUnableMoveVertically() {
        Position temPos = board.getPositions()[1][0];
        assertFalse(bishopWhite.move(temPos));
    }

    /**
     * Test of getStringRepresentation method, of class Bishop.
     */
    @Test
    public void testGetStringRepresentation() {
        assertEquals("BB", bishopBlack.getStringRepresentation());
        assertEquals("WB", bishopWhite.getStringRepresentation());
    }
}
