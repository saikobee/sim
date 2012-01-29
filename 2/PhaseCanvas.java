import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class PhaseCanvas extends JPanel {
    private DataList dl;

    private List<Point> hPoints;
    private List<Point> pPoints;

    private int hMax;
    private int pMax;
    private int size;

    private Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);

    private final Color bgColor = new Color( 17,  17,  17);
    private final Color fgColor = new Color(255, 255, 255);

    private final int STROKE_WIDTH = 3;
    private final Stroke strokeBig = new BasicStroke(
        STROKE_WIDTH,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND
    );
    private final Stroke strokeSmall = new BasicStroke(1);

    public PhaseCanvas(DataList dl) {
        this.dl = dl;

        calcMaxes();
    }

    private void calcMaxes() {
        pMax = 0;
        hMax = 0;

        for (DataPoint dp: dl) {
            if (dp.h > hMax) hMax = dp.h;
            if (dp.p > pMax) pMax = dp.p;
        }
    }

    public void paintComponent(Graphics gUnit) {
        if (! (gUnit instanceof Graphics2D)) return;

        final int w = getWidth();
        final int h = getHeight();

        Graphics2D g = (Graphics2D) gUnit;

        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        //g.setFont(font);
        g.setStroke(strokeBig);
        g.setBackground(bgColor);
        g.setColor(fgColor);
        g.clearRect(0, 0, w, h);

        drawIt(g);

        //g.setColor(fgColor);
        //g.setStroke(strokeSmall);
        //drawTicksY(g);
        //drawTicksX(g);
    }

    private void drawIt(Graphics2D g) {
        drawLines(g);
        drawDots(g);
    }

    private void drawLines(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        int oldX = -1;
        int oldY = -1;

        float hue = 0.0f;
        for (DataPoint dp: dl) {
            final int x =     scaledX(dp.h);
            final int y = h - scaledY(dp.p);

            g.setColor(Color.getHSBColor(hue, 0.50f, 0.50f));

            if (oldX >= 0 && oldY >= 0) {
                g.drawLine(oldX, oldY, x, y);
            }

            //circle(g, x, y);

            hue += 0.1f;
            hue %= 1.0f;

            oldX = x;
            oldY = y;
        }
    }

    private void drawDots(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        int oldX = -1;
        int oldY = -1;

        float hue = 0.0f;
        for (DataPoint dp: dl) {
            final int x =     scaledX(dp.h);
            final int y = h - scaledY(dp.p);

            g.setColor(Color.getHSBColor(hue, 0.50f, 0.50f));
            circle(g, x, y);

            hue += 0.1f;
            hue %= 1.0f;

            oldX = x;
            oldY = y;
        }
    }

    private int scaledX(int x) { return (int) (x * (getWidth()  / (double) hMax)); }
    private int scaledY(int y) { return (int) (y * (getHeight() / (double) pMax)); }

    private void circle(Graphics g, int x, int y) {
        final int r = 6;
        g.fillOval(x - r, y - r, 2*r, 2*r);
    }
}
