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

    private static final int steps = 20;

    public GrapherMaker(double alpha, double beta, double a, double b, int h, int p) {
        this.alpha = alpha;
        this.beta  = beta;
        this.a = a;
        this.b = b;
        this.h = h;
        this.p = p;

        dl = new DataList();
        for (int n = 0; n < steps; n++) {
            step();
            dl.add(new DataPoint(h, p));
        }

        new Grapher(dl);
    }

    private void step() {
        final int dh = (int) (a*h - alpha*h*p);
        final int dp = (int) (beta*h*p - b*p);

        h += dh;
        p += dp;
    }
}
