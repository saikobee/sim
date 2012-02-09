import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JPanel implements MouseListener {
    private Dimension preferredSize = new Dimension(300, 300);

    private Color bgColor = Util.gray(20);
    private Color fgColor = Util.gray(80);

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

    public Canvas() {
        isPlaying = false;
        setPreferredSize(preferredSize);
        addMouseListener(this);
    }

    public void setTimeStep (int n) { Debug.printf("time step = %d\n", n); }
    public void setGravity  (int n) { Debug.printf("gravity   = %d\n", n); }
    public void setMagnetism(int n) { Debug.printf("magnetism = %d\n", n); }
    public void setDelay    (int n) { Debug.printf("delay     = %d\n", n); }

    public void play() {
        isPlaying = !isPlaying;
    }

    public void reset() {
        isPlaying = false;
    }

    public void paintComponent(Graphics g) {
        w = getWidth();
        h = getHeight();

        draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
        g.setStroke(stroke);
        enableAA(g);
        drawBG(g);
        drawPendulums(g);
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
        Util.circle(g, cx, cy, cr);
    }

    public void mouseReleased(MouseEvent e) {
        final int button = e.getButton();
        final int x = e.getX();
        final int y = e.getY();

        cx = x;
        cy = y;

        repaint();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
