/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

/**
 * Chess pieces have either white or black as their colors
 * @author yue
 */
public enum Color {

    WHITE("White"), BLACK("Black");
    private final String text;

    private Color(String text) {
        this.text = text;
    }
    /**
     * get text representation of color
     * @return "White" or "Black"
     */
    public String getTextRepresentation(){
        return this.text;
    }
}
