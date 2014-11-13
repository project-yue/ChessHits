/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits;

import nz.ac.aut.pdc.ChessHits.model.ChessHitsGame;
import nz.ac.aut.pdc.ChessHits.GUI.StartFrame;

/**
 *
 *
 * @author Guy Langford-lee & Yue Li
 * @version 23-07-2013 project file is created
 * @version 20-10-2013 project finished
 */
public class ChessHits {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ChessHitsGame game = new ChessHitsGame();
        final StartFrame gui = new StartFrame(game);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
            }
        });
    }
}