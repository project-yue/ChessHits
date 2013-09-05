/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

/**
 * present the game welcome message.
 *
 * @author yl
 */
public class Welcome {

    private String welcomeMessage = "\u001B[36m" + " WW      WW EEEEE LL     CCCCC  OOOO  MM    MM EEEEE\n"
            +"\u001B[36m" + " WW  WW  WW EE    LL     CCC   OO  OO MMM  MMM EEE\n"
            +"\u001B[36m" + " WW  WW  WW EEEEE LL     CCC   OO  OO MMMMMMMM EEEEE\n"
            +"\u001B[36m" + "  WW WW WW  EE    LL     CCC   OO  OO MM MM MM EEE\n"
            +"\u001B[36m" + "    W  W    EEEEE LLLLLL CCCCC  OOOO  MM    MM EEEEE\n\n"
            +"\u001B[36m" + "\t\t\t\t\tTTTTTT  OOOO\n"
            +"\u001B[36m" + "\t\t\t\t\t  TT   OO  OO\n"
            +"\u001B[36m" + "\t\t\t\t\t  TT   OO  OO\n"
            +"\u001B[36m" + "\t\t\t\t\t  TT   OO  OO\n"
            +"\u001B[36m" + "\t\t\t\t\t  TT    OOOO\n\n"
            +"\u001B[36m" + "\t\t\tCCCCC  HH  HH  EEEEE  SSSSS   SSSSS\n"
            +"\u001B[36m" + "\t\t\tCCC    HH  HH  EE     SS   S  SS   S\n"
            +"\u001B[36m" + "\t\t\tCCC    HHHHHH  EEEEE   SS      SS\n"
            +"\u001B[36m" + "\t\t\tCCC    HH  HH  EE    S   SS  S   SS\n"
            +"\u001B[36m" + "\t\t\tCCCCC  HH  HH  EEEEE  SSSSS   SSSSS\n\n"
            +"\u001B[36m" + "\t\t\t\t\t\tHH  HH  IIIIII  TTTTTT  SSSSS\n"
            +"\u001B[36m" + "\t\t\t\t\t\tHH  HH    II      TT    SS   S\n"
            +"\u001B[36m" + "\t\t\t\t\t\tHHHHHH    II      TT     SS\n"
            +"\u001B[36m" + "\t\t\t\t\t\tHH  HH    II      TT   S   SS\n"
            +"\u001B[36m" + "\t\t\t\t\t\tHH  HH  IIIIII    TT    SSSSS\n\n\n";

    public Welcome() {
    }

    public void printWelcome() {
        System.out.println(this.welcomeMessage);
    }
}
