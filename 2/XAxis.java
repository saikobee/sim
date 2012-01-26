import java.awt.*;

public class XAxis extends Axis {
    public XAxis(DataList dl) {
        super(dl);
    }

    protected void drawMe(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        g.drawLine(offset - 1, 0, w, 0);
    }
}
