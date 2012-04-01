/*
 * PrisFrame.java
 *
 * Created on Jan 23, 2012, 3:14:53 PM
 */
package pris;

import java.awt.Graphics;

/**
 *
 * @author levenick
 */
public class PrisFrame extends javax.swing.JFrame {

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
        theBoard = new Board(this);
        setSize(700, 700);
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
        catch (Exception e) { }
    }

    public void paint(Graphics g) {
        theBoard.paint(g);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

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
