import java.awt.*;

public class YAxis extends Axis {
    public YAxis(DataList dl) {
        super(dl);
    }

    protected void drawMe(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        g.drawLine(w - 1, 0, w - 1, h);

        int tickSpacing = h / yMax;

        for (int y = tickSpacing; y < yMax; y += tickSpacing) {
            g.drawLine(0, y, w, y);
        }
    }
}
