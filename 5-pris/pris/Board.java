/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 *
 * @author levenick
 */
public class Board extends JPanel {
    private int SIZE;

    public static final int COOP   = 0;
    public static final int DEFECT = 1;
    public static final int TIT    = 2;

    public static Image imgB;
    public static Image imgW;

    private int sqWidth;

    public static Color CCC_COLOR = Util.colorFromHex("#4b55a3");
    public static Color CCD_COLOR = Util.colorFromHex("#4ba36d");
    public static Color CDC_COLOR = Util.colorFromHex("#a34b81");
    public static Color CDD_COLOR = Util.colorFromHex("#a3994b");

    public static Color DCC_COLOR = Util.colorFromHex("#ff0000");
    public static Color DCD_COLOR = Util.colorFromHex("#00ff00");
    public static Color DDC_COLOR = Util.colorFromHex("#0000ff");
    public static Color DDD_COLOR = Util.colorFromHex("#ff00ff");

    public static Color TIT_COLOR = Util.colorFromHex("#ffffff");

    Strategy[][] strats;

    public Board(PrisFrame f, int w, int h, int sqWidth, int size) {
        setPreferredSize(new Dimension(w, h));
        this.sqWidth = sqWidth;
        SIZE = size;
        strats = new Strategy[SIZE][SIZE];
        init();

        imgB = Util.getImage("img/b.png");
        imgW = Util.getImage("img/w.png");

    }

    public void paint(Graphics g) {
        // No idea why this can be null,
        // but it's getting passed in from elsewhere...
        if (g == null)
            return;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                g.setColor(appropColor(
                    strats[row][col].id(),
                    strats[row][col].getPrevID(),
                    strats[row][col].getPrePre()
                ));
                Image img = strats[row][col].cooperate()? imgB: imgW;
                final int w = sqWidth;
                final int h = sqWidth;
                final int x = col * w;
                final int y = row * h;
                g.fillRect(x, y, w, h);
                g.drawImage(img, x, y, w, h, null);
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
                // clone strat with highest score among the 9
                // and save it so after this loop we can put it in place
                setNextStrat(row, col);
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

        strats[i][j].setNextStrategy(appropNewStrat(
            bestStrat.id(),
            strats[i][j].id(),
            strats[i][j].getPrevID()
        )); //save for later!
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

    Strategy appropNewStrat(int id, int prevID, int prePre) {
        switch (id) {
        case COOP:   return new Cooperate(prevID, prePre);
        case DEFECT: return new Defect   (prevID, prePre);
        case TIT:    return new TitForTat(prevID, prePre);
        default:
            System.out.println("ieeee! bad id! " + id);
            System.exit(1);
        }
        return null;
    }

    Color appropColor(int id, int prevID, int prePre) {
        int combined = id | (prevID << 1) | (prePre << 2);
        if (id == TIT) return TIT_COLOR;
        switch (combined) {
        case 0: /* 0b000 */ return CCC_COLOR;
        case 1: /* 0b001 */ return CCD_COLOR;
        case 2: /* 0b010 */ return CDC_COLOR;
        case 3: /* 0b011 */ return CDD_COLOR;

        case 4: /* 0b100 */ return DCC_COLOR;
        case 5: /* 0b101 */ return DCD_COLOR;
        case 6: /* 0b110 */ return DDC_COLOR;
        case 7: /* 0b111 */ return DDD_COLOR;
        default:
            System.out.println("ieeee! bad id! " + id);
            return Color.yellow;
        }
    }
}
