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

    private final int STROKE_WIDTH = 2;
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

        g.drawOval(10, 10, w - 10, h - 10);
    }
}
