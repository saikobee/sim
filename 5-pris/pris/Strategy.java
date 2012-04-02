/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;

/**
 *
 * @author levenick
 */
public interface Strategy {

    public boolean cooperate();

    public void setPreviousOpponentMove(boolean cooperate);

    public int id();

    public void setScore(int score);

    public int getScore();

    public Strategy getNextStrategy();

    public void setNextStrategy(Strategy nextStrategy);

    public int getPrevID();
    public int getPrePre();
}
