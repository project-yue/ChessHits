/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.GUI;

import nz.ac.aut.pdc.ChessHits.model.ChessHitsGame;
import nz.ac.aut.pdc.ChessHits.model.Color;
import nz.ac.aut.pdc.ChessHits.model.Player;
import nz.ac.aut.pdc.ChessHits.userDB.UserDatabase;

/**
 *
 * @author gl
 */
public class StartFrame extends javax.swing.JFrame {

    ChessHitsGame game;
    private UserDatabase userDB;
    private MainFrame mainFrame;

    /**
     * Creates new form Frame
     */
    public StartFrame(ChessHitsGame game) {
        this.game = game;
        initComponents();
        javax.swing.ButtonGroup bg = new javax.swing.ButtonGroup();
        bg.add(blackJradioB);
        bg.add(whiteJradioB);
        whiteJradioB.setSelected(true);
        this.setLocationRelativeTo(null);
        this.userDB = new UserDatabase();
        this.userDB.establishConnection();
        if (!this.userDB.doesUserTableExist()) {
            this.userDB.createTable();
        }
    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        submit = new javax.swing.JButton();
        player1Lbl = new javax.swing.JTextField();
        blackJradioB = new javax.swing.JRadioButton();
        whiteJradioB = new javax.swing.JRadioButton();
        player2Lbl = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        submit.setText("submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        blackJradioB.setText("black");
        blackJradioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blackJradioBActionPerformed(evt);
            }
        });

        whiteJradioB.setText("white");

        player2Lbl.setName(""); // NOI18N
        player2Lbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2LblActionPerformed(evt);
            }
        });

        jLabel1.setText("Player One name:");

        jLabel2.setText("Player Two name:");

        jLabel3.setText("Player One color:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player1Lbl)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blackJradioB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(whiteJradioB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(player2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(player2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blackJradioB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(whiteJradioB)
                .addGap(45, 45, 45)
                .addComponent(submit)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void blackJradioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackJradioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blackJradioBActionPerformed

    private void player2LblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2LblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player2LblActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        Player playerOne = null;
        Player playerTwo = null;
        boolean isPlayerOneWhite = false;
        if (blackJradioB.isSelected()) {

            playerOne = new Player(player1Lbl.getText(), Color.BLACK);
            playerTwo = new Player(player2Lbl.getText(), Color.WHITE);

        } else {
            playerOne = new Player(player1Lbl.getText(), Color.WHITE);
            playerTwo = new Player(player2Lbl.getText(), Color.BLACK);
            isPlayerOneWhite = true;
        }
        this.setVisible(false);
        final MainFrame gameMainFrame = new MainFrame(playerOne, playerTwo, game, isPlayerOneWhite);
        this.mainFrame = gameMainFrame;
        if (!this.userDB.doesAccountExist(player1Lbl.getText())) {
            this.userDB.addNewUser(player1Lbl.getText());
        }
        if (!this.userDB.doesAccountExist(player2Lbl.getText())) {
            this.userDB.addNewUser(player2Lbl.getText());
        }
        this.userDB.increaseWins(player1Lbl.getText());
        System.out.println(player1Lbl.getText() + " has won " + userDB.getWins(player1Lbl.getText()) + " game(s)");
        System.out.println(player2Lbl.getText() + " has won " + userDB.getWins(player2Lbl.getText()) + " game(s)");
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    gameMainFrame.setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_submitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blackJradioB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private static javax.swing.JTextField player1Lbl;
    private static javax.swing.JTextField player2Lbl;
    private javax.swing.JButton submit;
    private javax.swing.JRadioButton whiteJradioB;
    // End of variables declaration//GEN-END:variables
//    private void setListener() {
//        player1Lbl.addCaretListener(new CaretListener() {
//
//            @Override
//            public void caretUpdate(CaretEvent ce) {
//                String player1Text = player1Lbl.getText();
//                if(player1Text.length() > 15){
//                 
//                    player1Lbl.setText( player1Text.substring(0, 14));
//                }
//                
//            }
//        });
//    }
}
