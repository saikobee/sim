import java.awt.*;

public class XAxis extends Axis {
    public XAxis(DataList dl) {
        super(dl);
    }

    protected void drawMe(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        final int Q = offset - 1;

        g.drawLine(offset - 1, 0, w, 0);

        final int numTicks    = 12;
        final int tickSpacing = (w - offset) / numTicks;
        final int tickSize    = 4;
        final int textOffset  = 4;

        // int n = 1;
        // int x = tickSpacing;
        int n = 0;
        int x = 0;
        while (x < w && n < numTicks) {
            int num = (int) (n * ((double) xMax / numTicks));

            g.drawLine(
                x + Q, tickSize,
                x + Q, 0
            );
            g.drawString(
                "" + num,
                (x - 2*textOffset) + offset,
                (h - 2*textOffset)
            );

            n++;
            x += tickSpacing;
        }
    }
}
