import java.awt.*;

public class YAxis extends Axis {
    public YAxis(DataList dl) {
        super(dl);
    }

    protected void drawMe(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        g.drawLine(w - 1, 0, w - 1, h);

        final int numTicks    = 6;
        final int tickOffset  = 10;
        final int tickSpacing = (h - tickOffset) / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        int n = numTicks;
        int y = tickSpacing;
        while (y < (h - tickOffset) && n > 0) {
            int num = n * (yMax / numTicks);

            g.drawLine(w - tickSize, y, w, y);
            g.drawString("" + num, textOffset, y);

            n--;
            y += tickSpacing;
        }
    }
}
