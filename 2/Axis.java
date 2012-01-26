import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class Axis extends JPanel {
    private final Color bgColor = new Color( 32,  32,  32);
    private final Color fgColor = new Color(224, 224, 224);
    private DataList dl;
    protected List<Integer> hVals;
    protected List<Integer> pVals;
    protected int xMax;
    protected int yMax;
    protected final int offset = 32;

    public Axis(DataList dl) {
        this.dl = dl;

        hVals = new ArrayList<Integer>();
        pVals = new ArrayList<Integer>();

        for (DataPoint dp: dl) {
            hVals.add(dp.h);
            pVals.add(dp.p);
        }

        setPreferredSize(new Dimension(offset, offset));

        xMax = dl.size();
        yMax = Math.max(Collections.max(hVals), Collections.max(pVals));

        Debug.printf("xmax = %d, ymax = %d\n", xMax, yMax);
    }

    public void paintComponent(Graphics gq) {
        if (! (gq instanceof Graphics2D)) return;

        final int w = getWidth();
        final int h = getHeight();

        Graphics2D g = (Graphics2D) gq;
        g.setBackground(bgColor);
        g.clearRect(0, 0, w, h);
        g.setColor(fgColor);
        drawMe(g);
    }

    protected void drawMe(Graphics2D g) {
        final int w = getWidth();
        final int h = getHeight();

        g.drawOval(0, 0, w, h);
    }
}
