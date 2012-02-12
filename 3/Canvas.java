import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas
extends JPanel
implements MouseListener, ComponentListener {
    private Dimension preferredSize = new Dimension(500, 500);

    private Color bgColor = Util.gray(20);
    private Color fgColor = Util.gray(80);

    private Simulation sim;

    private JFrame parent;

    private boolean isPlaying;

    private final int STROKE_WIDTH = 2;
    private final Stroke stroke = new BasicStroke(
        STROKE_WIDTH,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND
    );

    private int w = 0;
    private int h = 0;

    private int cx =  0;
    private int cy =  0;
    private int cr = 10;

    public Canvas(JFrame parent) {
        this.parent = parent;
        parent.setBackground(bgColor);
        isPlaying = false;
        sim = new Simulation();
        setPreferredSize(preferredSize);
        addMouseListener(this);
        addComponentListener(this);
        new Runner().start();
    }

    public void setTimeStep (int n) { Debug.printf("time step = %d\n", n); Params.timestep  = n;}
    public void setGravity  (int n) { Debug.printf("gravity   = %d\n", n); Params.gravity   = n;}
    public void setMagnetism(int n) { Debug.printf("magnetism = %d\n", n); Params.magnetism = n;}
    public void setDelay    (int n) { Debug.printf("delay     = %d\n", n); Params.delay     = n;}

    public void paintComponent(Graphics g) {
        w = getWidth();
        h = getHeight();

        draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
        g.setStroke(stroke);
        enableAA(g);
        drawBG(g);
        g.setColor(fgColor);
        sim.draw(g);
    }

    private void enableAA(Graphics2D g) {
        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
    }

    private void drawBG(Graphics2D g) {
        g.setBackground(bgColor);
        g.clearRect(0, 0, w, h);
    }

    private void drawPendulums(Graphics2D g) {
        g.setColor(fgColor);
        Util.fillCircle(g, cx, cy, cr);
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        final int button = e.getButton();
        final int x = e.getX();
        final int y = e.getY();

        cx = x;
        cy = y;

        repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    private class Repainter extends Thread {
        public void run() {
            while (true) {
                repaint();
                Util.sleep(Params.delay);
            }
        }
    }

    public void play() {
        isPlaying = !isPlaying;
    }

    public void reset() {
        isPlaying = false;
    }

    public void step() {
        sim.step();
        repaint();
    }

    private class Runner extends Thread {
        public void run() {
            while (true) {
                if (isPlaying) {
                    step();
                }

                Util.sleep(Params.delay);
            }
        }
    }

    public void componentResized(ComponentEvent e) {
        Component c = (Component) e.getSource();
        final int w = c.getWidth();
        final int h = c.getHeight();
        Params.x = w/2;
        Params.y = h/2;

        repaint();
    }

    public void componentMoved (ComponentEvent e) {}
    public void componentHidden(ComponentEvent e) {}
    public void componentShown (ComponentEvent e) {}
}
