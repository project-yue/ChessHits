/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits.model;

/**
 *
 * @author makingitbettergo
 */
public enum Color {

    WHITE("White"), BLACK("Black");
    private final String text;

    private Color(String text) {
        this.text = text;
    }
    
    public String getTextRepresentation(){
        return this.text;
    }
}
