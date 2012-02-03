import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class Canvas extends JPanel {
    private DataList dl;

    private List<Point> hPoints;
    private List<Point> pPoints;

    private int hMax;
    private int pMax;
    private int size;

    private Font font = new Font(Font.MONOSPACED, Font.BOLD, 16);

    private final Color hColor  = new Color( 64, 192, 255);
    private final Color pColor  = new Color(224,  64,  64);
    private final Color bgColor = new Color( 17,  17,  17);
    private final Color fgColor = new Color(255, 255, 255);

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
        size = dl.size() - 1;
        pMax = 0;
        hMax = 0;

        int x = 0;
        for (DataPoint dp: dl) {
            if (dp.h > hMax) hMax = dp.h;
            if (dp.p > pMax) pMax = dp.p;

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

        g.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g.setFont(font);
        g.setStroke(strokeBig);
        g.setBackground(bgColor);
        g.clearRect(0, 0, w, h);

        //Debug.printf("\n");
        //Debug.printf("HHHH\n");
        //Debug.printf("====\n");
        drawLines(g, hMax, hPoints, hColor);
        // drawPoints(g, hPoints, hColor);

        //Debug.printf("\n");
        //Debug.printf("PPPP\n");
        //Debug.printf("====\n");
        drawLines(g, pMax, pPoints, pColor);
        // drawPoints(g, pPoints, pColor);

        g.setColor(fgColor);
        g.setStroke(strokeSmall);
        drawTicksY1(g);
        drawTicksY2(g);
        drawTicksX(g);
    }

    private void drawLines(Graphics2D g, int max, List<Point> points, Color color) {
        final int w = getWidth();
        final int h = getHeight();

        final int r = 6;

        g.setColor(color);

        //Debug.printf("size=%d, max=%d\n", size, max);

        Iterator<Point> it = points.iterator();

        Point p1 = it.hasNext()? it.next(): null;
        Point p2 = it.hasNext()? it.next(): null;
        while (p1 != null && p2 != null) {
            final int x1 = scaledX(p1.x, size);
            final int y1 = scaledY(p1.y, max);
            final int x2 = scaledX(p2.x, size);
            final int y2 = scaledY(p2.y, max);

            //Debug.printf("p1=(%d, %d)\n", p1.x, p1.y);
            //Debug.printf("p2=(%d, %d)\n", p2.x, p2.y);
            circle(g, x1, y1, r);
            circle(g, x2, y2, r);
            g.drawLine(x1, y1, x2, y2);

            p1 = p2;
            p2 = it.hasNext()? it.next(): null;
        }

        if (it.hasNext()) {
            throw new RuntimeException("List should have been exhausted");
        }
    }

    private int scaledX(int x, int max) { return               (int) (x * (getWidth()  / (double) max)); }
    private int scaledY(int y, int max) { return getHeight() - (int) (y * (getHeight() / (double) max)); }

    private void drawTicksY1(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        // g.drawLine(0, 0, 0, h);

        final int numTicks    = Math.min(pMax, 6);
        final int tickSpacing = pMax / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        g.setColor(pColor);

        int n = numTicks;
        while (n >= 1) {
            int num = n * tickSpacing;
            int y   = scaledY(num, pMax);

            g.drawLine(
                tickSize, y,
                0,        y
            );
            final String text = String.format("%02d", num);
            final int    txtX = 2*textOffset;
            final int    txtY = y + textOffset + 8;
            g.setColor(bgColor); g.drawString(text, txtX,     txtY);
            g.setColor( pColor); g.drawString(text, txtX + 1, txtY - 1);

            n--;
        }
    }

    private void drawTicksY2(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        // g.drawLine(0, 0, 0, h);

        final int numTicks    = Math.min(hMax, 6);
        final int tickSpacing = hMax / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        g.setColor(hColor);

        int n = numTicks;
        while (n >= 1) {
            int num = n * tickSpacing;
            int y   = scaledY(num, hMax);

            g.drawLine(
                tickSize, y,
                0,        y
            );
            final String text = String.format("%02d", num);
            final int    txtX = 2*textOffset;
            final int    txtY = y + textOffset - 8;
            g.setColor(bgColor); g.drawString(text, txtX,     txtY);
            g.setColor( hColor); g.drawString(text, txtX + 1, txtY - 1);

            n--;
        }
    }

    protected void drawTicksX(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        // g.drawLine(0, h - 1, w, h - 1);

        final int numTicks    = Math.max(1, Math.min(size, 12));
        final int tickSpacing = w / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        g.setColor(fgColor);

        int n = 1;
        while (n <= numTicks) {
            int num = n * (size / numTicks);
            int x   = scaledX(num, size);

            g.drawLine(
                x, h - tickSize,
                x, h
            );
            final String text = String.format("%02d", num);
            final int    txtX = x - 3*textOffset;
            final int    txtY = h - 2*textOffset;
            g.setColor(bgColor); g.drawString(text, txtX,     txtY);
            g.setColor(fgColor); g.drawString(text, txtX + 1, txtY - 1);

            n++;
            x += tickSpacing;
        }
    }

    private void circle(Graphics g, int x, int y, int r) {
        g.fillOval(x - r, y - r, 2*r, 2*r);
    }
}
