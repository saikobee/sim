/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;

/**
 *
 * @author levenick
 */
public class Defect extends CellStrategy {
    
    public Defect() {}

    public Defect(int prevID) {
        super(prevID);
    }

    public boolean cooperate() {
        return false;
    }
    
    public int id() {
        return Board.DEFECT;
    }

    public String toString() {
        return "\nDefecttor: \n\t score=" + score;
    }
}

