import javax.swing.UIManager.*;
import java.awt.*;
import javax.swing.*;

public class GrapherMaker {
    private double alpha;
    private double beta;
    private double a;
    private double b;
    private int h;
    private int p;

    private DataList dl;

    private static final int steps = 1000;

    public GrapherMaker(double alpha_, double beta_, double a_, double b_, int h_, int p_) {
        alpha = alpha_;
        beta  = beta_;
        a = a_;
        b = b_;
        h = h_;
        p = p_;

        dl = new DataList();
        dl.add(new DataPoint((int) h, (int) p));
        for (int n = 0; n < steps; n++) {
            final double dh =    a*h   - alpha*h*p;
            final double dp = beta*h*p -       b*p;

            //Debug.printf("dh=%9.7f, dp=%9.7f\n", dh, dp);

            h += dh;
            p += dp;
            if (h <= 0) break;
            if (p <= 0) break;
            dl.add(new DataPoint((int) h, (int) p));
        }

        new Grapher(dl);
    }
}
