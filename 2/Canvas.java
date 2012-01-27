import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class Canvas extends JPanel {
    private DataList dl;

    private List<Point> hPoints;
    private List<Point> pPoints;

    private int max;
    private int size;

    private final Color hColor  = new Color(  0, 128, 255);
    private final Color pColor  = new Color(255, 128,   0);
    private final Color bgColor = new Color( 17,  17,  17);
    private final Color fgColor = new Color(224, 224, 224);

    private final int STROKE_WIDTH = 3;
    private final Stroke strokeBig = new BasicStroke(
        STROKE_WIDTH,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND
    );
    private final Stroke strokeSmall = new BasicStroke(1);

    public Canvas(DataList dl) {
        this.dl = dl;

        hPoints = new ArrayList<Point>();
        pPoints = new ArrayList<Point>();

        calcPoints();
    }

    private void calcPoints() {
        size = dl.size();
        max  = 0;

        int x = 0;
        for (DataPoint dp: dl) {
            if (dp.h > max) max = dp.h;
            if (dp.p > max) max = dp.p;

            hPoints.add(new Point(x, dp.h));
            pPoints.add(new Point(x, dp.p));

            x++;
        }
    }

    public void paintComponent(Graphics gUnit) {
        if (! (gUnit instanceof Graphics2D)) return;

        final int w = getWidth();
        final int h = getHeight();

        Graphics2D g = (Graphics2D) gUnit;

        g.setStroke(strokeBig);
        g.setBackground(bgColor);
        g.clearRect(0, 0, w, h);

        Debug.printf("\n");
        Debug.printf("HHHH\n");
        Debug.printf("====\n");
        drawLines(g, hPoints, hColor);

        Debug.printf("\n");
        Debug.printf("PPPP\n");
        Debug.printf("====\n");
        drawLines(g, pPoints, pColor);

        drawTicksY(g);
        drawTicksX(g);
    }

    private void drawLines(Graphics2D g, List<Point> points, Color color) {
        final int w = getWidth();
        final int h = getHeight();

        final int r = 6;

        g.setColor(color);

        Iterator<Point> it = points.iterator();

        Point p1 = it.hasNext()? it.next(): null;
        Point p2 = it.hasNext()? it.next(): null;
        while (p1 != null && p2 != null) {
            final int x1 = (int) (p1.x * (w / (double) size));
            final int y1 = (int) (p1.y * (h / (double) max ));
            final int x2 = (int) (p2.x * (w / (double) size));
            final int y2 = (int) (p2.y * (h / (double) max ));

            // Debug.printf("p1=(%d, %d)\n", p1.x, p1.y);
            Debug.printf("p2=(%d, %d)\n", p2.x, p2.y);
            circle(g, x1, y1, r);
            circle(g, x2, y2, r);
            g.drawLine(x1, y1, x2, y2);

            p1 = p2;
            p2 = it.hasNext()? it.next(): null;
        }
    }

    private void drawTicksY(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        g.setColor(fgColor);
        g.setStroke(strokeSmall);
        g.drawLine(0, 0, 0, h);

        final int numTicks    = 6;
        final int tickSpacing = h / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        int n = 0;
        int y = h - 1;
        while (y > 0 && n < numTicks) {
            int num = (int) (n * ((double) max / numTicks));

            g.drawLine(
                tickSize, y,
                0,        y
            );
            g.drawString("" + num, textOffset, y);

            n++;
            y -= tickSpacing;
        }
    }

    protected void drawTicksX(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        g.drawLine(0, h - 1, w, h - 1);

        final int numTicks    = 12;
        final int tickSpacing = w / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        int n = 0;
        int x = 0;
        while (x < w && n < numTicks) {
            int num = (int) (n * ((double) size / numTicks));

            g.drawLine(
                x, h - tickSize,
                x, h
            );
            g.drawString(
                "" + num,
                (x - 2*textOffset),
                (h - 2*textOffset)
            );

            n++;
            x += tickSpacing;
        }
    }

    private void circle(Graphics g, int x, int y, int r) {
        g.fillOval(x - r, y - r, 2*r, 2*r);
    }
}
