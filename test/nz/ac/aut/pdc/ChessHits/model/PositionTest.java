package nz.ac.aut.pdc.ChessHits.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import nz.ac.aut.pdc.ChessHits.model.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gl
 */
public class PositionTest {
    private Position position;
    private Position positionFail;
    
    public PositionTest() {
    }
    @Before
    public void setUp() {
        position = new Position(1,1);
        
    }
    
    @After
    public void tearDown() {
        position = null;
        positionFail = null;
    }

    /**
     * Test of getRow method, of class Position.
     */
    @Test
    public void testGetRow() {
        assertEquals(1, position.getRow());
        
    }

    /**
     * Test of getColumn method, of class Position.
     */
    @Test
    public void testGetColumn() {
        assertEquals(1,position.getRow());
    }
}
