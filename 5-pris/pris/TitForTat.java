/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;

/**
 *
 * @author levenick
 */
public class TitForTat extends CellStrategy  {
    public TitForTat() {}

    public TitForTat(int p, int pp) {
        super(p, pp);
    }

    public boolean cooperate() {
        return previousOpponentMove;
    }

    public int id() {
        return Board.TIT;
    }
}
