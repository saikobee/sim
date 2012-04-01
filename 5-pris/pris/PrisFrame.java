/*
 * PrisFrame.java
 *
 * Created on Jan 23, 2012, 3:14:53 PM
 */
package pris;

import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author levenick
 */
public class PrisFrame extends JFrame {

    boolean running, step;
    int count;
    Board theBoard;
    ControlFrame controlFrame = new ControlFrame(this);

    public void toggleRunning() {
        running = !running;
    }

    /** Creates new form PrisFrame */
    public PrisFrame() {
        initComponents();
        setResizable(false);
        setVisible(true);
        spin();
    }

    public void step() {
        running = false;
        step = true;
    }

    void spin() {
        while (true) {
            if (running || step) {
                if (step) {
                    step = false;
                }
                if (Board.SIZE != Params.size) { // user changed size
                    theBoard = new Board(this);
                }
                count++;
                theBoard.update();
                repaint();
            }
            delay();
        }
    }

    void delay() {
        try { Thread.sleep(Params.delay); }
        catch (Exception e) {}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        theBoard = new Board(this);
        add(theBoard);

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PrisFrame().setVisible(true);
            }
        });
    }
}
