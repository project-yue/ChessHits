/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * hi yue
 */
package nz.ac.aut.pdc.ChessHits.GUI;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import nz.ac.aut.pdc.ChessHits.model.Board;
import nz.ac.aut.pdc.ChessHits.model.ChessHitsGame;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Player;
import nz.ac.aut.pdc.ChessHits.userDB.UserDatabase;

// Variables declaration - do not modify                     
// End of variables declaration                   
/**
 * frame provides view to players
 *
 * @author gl Modified by Yue
 */
public class MainFrame extends javax.swing.JFrame {

    private ChessHitsGame game;
    private Player playerOne;
    private Player playerTwo;
    private Board board;
    private final String INSTRUCTION = "Move a piece: click on a piece, the selected piece appears with yellow background\n"
            + "then click the position, click the place for piece to move. Any invalid move is considered as cancel the move.\n"
            + "Hits: when a piece moves to the opponent's \"territory\" hits occur between the attacker and defender.\n"
            + "The dead pieces shall be removed from board\nWinning: The player gets opponent's king wins.";

    /**
     * Construct a game frame to hold all the components
     *
     * @param playerOne one player of the game
     * @param playerTwo the other player of the game
     * @param game the ChessHitsGame
     * @param isPlayerOneWhite is playerOne plays as White
     */
    public MainFrame(Player playerOne, Player playerTwo, ChessHitsGame game, boolean isPlayerOneWhite) {
        this.game = game;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = game.getBoard();
        initComponents();
        this.setLocationRelativeTo(null);
        setUpPanels();
        if (isPlayerOneWhite) {
            this.playerOne.setIsTurn(true);
            game.setWhitePlayer(playerOne);
            game.setBlackPlayer(playerTwo);
            game.setWhiteTurn(true);
            this.playerTwo.setIsTurn(false);

        } else {
            this.playerOne.setIsTurn(false);
            game.setWhitePlayer(playerTwo);
            game.setBlackPlayer(playerOne);
            game.setWhiteTurn(true);
            this.playerTwo.setIsTurn(true);
        }
        updateText();
    }

    /**
     * update the player label and the color of player's turn
     */
    public final void updateText() {
        if (playerOne.getIsTurn()) {
            lblPlayerMoveTurn.setText("turn: " + playerOne.getName());
            lblPlayerColor.setText("color: " + playerOne.getSelectedColor().getTextRepresentation());
        } else {
            lblPlayerMoveTurn.setText("turn: " + playerTwo.getName());
            lblPlayerColor.setText("color: " + playerTwo.getSelectedColor().getTextRepresentation());
        }
    }

    /**
     * construct a panel as Chess board
     */
    private void setUpPanels() {
        int row = board.getHeight();
        int col = board.getWidth();
        gamePanel.setLayout(new GridLayout(row, col));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                SquarePanel sQP = new SquarePanel(game, i, j, this);
                gamePanel.add(sQP);
            }
        }
        this.validate();
    }

    public JPanel getGamePanel() {
        return this.gamePanel;
    }

    /**
     * restart game with same players
     */
    public void startNewGame() {
        this.game = new ChessHitsGame();
        UserDatabase userDB = new UserDatabase();
        userDB.establishConnection();
        this.game.setDataBase(userDB);
        this.gamePanel.removeAll();
        setUpPanels();
        if (this.playerOne.getSelectedColor() == Color.WHITE) {
            this.playerOne.setIsTurn(true);
            game.setWhitePlayer(playerOne);
            game.setBlackPlayer(playerTwo);
            game.setWhiteTurn(true);
            this.playerTwo.setIsTurn(false);

        } else {
            this.playerOne.setIsTurn(false);
            game.setWhitePlayer(playerTwo);
            game.setBlackPlayer(playerOne);
            game.setWhiteTurn(true);
            this.playerTwo.setIsTurn(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblPlayerMoveTurn = new javax.swing.JLabel();
        gamePanel = new javax.swing.JPanel();
        lblPlayerColor = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newGame = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(450, 500));
        setMinimumSize(new java.awt.Dimension(450, 500));
        setPreferredSize(new java.awt.Dimension(450, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblPlayerMoveTurn.setText("playerNmlb");
        lblPlayerMoveTurn.setAlignmentY(0.0F);
        lblPlayerMoveTurn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPlayerMoveTurn.setMaximumSize(null);
        lblPlayerMoveTurn.setMinimumSize(null);
        lblPlayerMoveTurn.setPreferredSize(null);
        lblPlayerMoveTurn.setRequestFocusEnabled(false);
        lblPlayerMoveTurn.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblPlayerMoveTurn, gridBagConstraints);
        lblPlayerMoveTurn.getAccessibleContext().setAccessibleDescription("");

        gamePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gamePanel.setMaximumSize(new java.awt.Dimension(100, 100));
        gamePanel.setMinimumSize(new java.awt.Dimension(100, 100));
        gamePanel.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.ipadx = 307;
        gridBagConstraints.ipady = 288;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(gamePanel, gridBagConstraints);

        lblPlayerColor.setText("colorlb");
        lblPlayerColor.setFocusable(false);
        lblPlayerColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPlayerColor.setMaximumSize(null);
        lblPlayerColor.setMinimumSize(null);
        lblPlayerColor.setPreferredSize(null);
        lblPlayerColor.setRequestFocusEnabled(false);
        lblPlayerColor.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(lblPlayerColor, gridBagConstraints);

        jMenu1.setText("File");

        newGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newGame.setText("Restart");
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });
        jMenu1.add(newGame);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Change Players");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("Instrcution");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("Version & Info");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
        startNewGame();
        updateText();
    }//GEN-LAST:event_newGameActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, this.INSTRUCTION, "INSTRUCTION", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.gamePanel.removeAll();
        this.game = new ChessHitsGame();
        this.dispose();
        StartFrame start = new StartFrame(this.game);
        start.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(rootPane, "ChessHits is produced by Guy Langford-lee & Yue Li at AUT in 2013, "
                + "all rights reserved\n Thank you for your participation\n                         ChessHits Team");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gamePanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JLabel lblPlayerColor;
    private javax.swing.JLabel lblPlayerMoveTurn;
    private javax.swing.JMenuItem newGame;
    // End of variables declaration//GEN-END:variables
}
