/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author levenick
 */
public class Board extends JPanel {
    public static int SIZE = 10;

    public static final int COOP   = 1;
    public static final int DEFECT = 2;
    public static final int TIT    = 3;

    private int sqWidth;

    static final Color COOP_COLOR   = Util.colorFromHex("#73d216");
    static final Color DC_COLOR     = Util.colorFromHex("#3465a4");
    static final Color DEFECT_COLOR = Util.colorFromHex("#cc0000");
    static final Color CD_COLOR     = Util.colorFromHex("#eeeeec");
    static final Color TIT_COLOR    = Util.colorFromHex("#75507b");

    Strategy[][] strats;

    public Board(PrisFrame f) {
        SIZE = Params.size;
        int w = 600;
        int h = 600;
        setPreferredSize(new Dimension(w, h));
        sqWidth = w/SIZE;
        strats = new Strategy[SIZE][SIZE];
        init();
    }

    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                g.setColor(appropColor(strats[row][col].id(), strats[row][col].getPrevID()));
                g.fillRect(col * sqWidth, row * sqWidth, sqWidth, sqWidth);
            }
        }
    }

    void init() {
        mix();
        strats[SIZE / 2][SIZE / 2] = new Defect();
    }

    void allCoop() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                strats[row][col] = new Cooperate();
            }
        }
    }

    void allTitForTat() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                strats[row][col] = new TitForTat();
            }
        }
    }

    void mix() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (Math.random() < Params.percentCoop/100.0) {
                    strats[row][col] = new Cooperate();
                } else {
                    strats[row][col] = new TitForTat();
                }
            }
        }
    }

    void update() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                initialize(row, col); // set score=0
            }
        }

        if (Params.BROKEN) {
            spew("after init");
        }

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                interact(row, col); // play a set with every nbr, accumulating score
            }
        }
        if (Params.BROKEN) {
            spew("after interact");
        }

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                setNextStrat(row, col); // clone strat with highest score among the 9 and save it so after this loop we can put it in place
            }
        }

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                strats[row][col] = strats[row][col].getNextStrategy();  // put new strategy in place
            }
        }
    }

    void spew(String s) {
        System.out.println(s + "\n");
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(spew(row, col));
            }
            System.out.println("");
        }
    }

    String spew(int row, int col) {
        Strategy strat = strats[row][col];
        String returnMe = "\t" + appropID(strat.id());

        returnMe += strat.getScore();

        return returnMe;
    }

    void initialize(int row, int col) {
        strats[row][col].setScore(0);
    }

    void interact(int i, int j) {
        // for each nbr {play a round and record scores}
        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if ((col != j || row != i)) {// if not identity (i.e. don't play with yourself!
                    PrisonersDilemma.playARound(strats[i][j], strats[torus(row)][torus(col)]);
                }
            } // for col
        } // for row
    }

    void setNextStrat(int i, int j) {
        // for each nbr {chose best}
        Strategy bestStrat = strats[i][j];
        int max = strats[i][j].getScore();
        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if ((col != j || row != i)) {// if not identity (i.e. don't play with yourself!
                    int score = strats[torus(row)][torus(col)].getScore();
                    if (score > max) {
                        max = score;
                        bestStrat = strats[torus(row)][torus(col)];
                    }
                }
            } // for col
        } // for row

        strats[i][j].setNextStrategy(appropNewStrat(bestStrat.id(), strats[i][j].id())); //save for later!
    }

    int torus(int x) { // works for nbrhoods bigger than 1 away from the center
        if (x >= SIZE) {
            return x % SIZE;
        } else if (x < 0) {
            return SIZE + x;
        }
        return x;
    }

    String appropID(int id) {
        switch (id) {
        case COOP:   return "c";
        case DEFECT: return "d";
        case TIT:    return "t";
        default:
            System.out.println("ieeee! bad id! " + id);
            System.exit(1);
        }
        return "x";
    }

    Strategy appropNewStrat(int id, int prevID) {
        switch (id) {
        case COOP:   return new Cooperate(prevID);
        case DEFECT: return new Defect(prevID);
        case TIT:    return new TitForTat(prevID);
        default:
            System.out.println("ieeee! bad id! " + id);
            System.exit(1);
        }
        return null;
    }

    Color appropColor(int id, int prevID) {
        switch (id) {
        case COOP:   return prevID == DEFECT? DC_COLOR: COOP_COLOR;
        case DEFECT: return prevID == COOP?   CD_COLOR: DEFECT_COLOR;
        case TIT:    return TIT_COLOR;
        default:
            System.out.println("ieeee! bad id! " + id);
            return Color.yellow;
        }
    }
}
