/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;

/**
 *
 * @author levenick
 */
public class Cooperate extends CellStrategy {
    
    public Cooperate() {}

    public Cooperate(int prevID) {
        super(prevID);
    }

    public boolean cooperate() {
        return true;
    }
    
    public int id() {
        return Board.COOP;
    }

    public String toString() {
        return "\nCooperator: \n\t score=" + score;
    }
}
