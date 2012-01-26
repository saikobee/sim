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

    private final int STROKE_WIDTH = 3;
    private final Stroke stroke = new BasicStroke(
        STROKE_WIDTH,
        BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND
    );

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

        g.setStroke(stroke);
        g.setBackground(bgColor);
        g.clearRect(0, 0, w, h);

        drawLines(g, hPoints, hColor);
        drawLines(g, pPoints, pColor);
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

            circle(g, x1, y1, r);
            circle(g, x2, y2, r);
            g.drawLine(x1, y1, x2, y2);

            p1 = p2;
            p2 = it.hasNext()? it.next(): null;
        }
    }

    private void circle(Graphics g, int x, int y, int r) {
        g.fillOval(x - r, y - r, 2*r, 2*r);
    }
}
