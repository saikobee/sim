package pris;

/**
 * CellStrategy.java   --  created on Jan 24, 2012, 8:50:04 AM
 * @author levenick
 */

public abstract class CellStrategy implements Strategy {
    boolean previousOpponentMove = true; // inited so TitForTat works!
    int score;
    protected Strategy nextStrategy;
    int prevID;
    int prePre;

    public CellStrategy() {}
    public CellStrategy(int prevID, int prePre) {
        this.prevID = prevID;
        this.prePre = prePre;
    }
    public int getPrevID() {return prevID;}
    public int getPrePre() {return prePre;}

    @Override
    public abstract boolean cooperate();
    public void setPreviousOpponentMove(boolean cooperate) {
        previousOpponentMove = cooperate;
    }

    public abstract int id();
    public void setScore(int score) {this.score = score;}
    public int getScore() {return score;}

    /**
     * @return the nextStrategy
     */
    public Strategy getNextStrategy() {
        return nextStrategy;
    }

    /**
     * @param nextStrategy the nextStrategy to set
     */
    public void setNextStrategy(Strategy nextStrategy) {
        this.nextStrategy = nextStrategy;
    }
}
