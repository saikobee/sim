import java.awt.*;

public class Pendulum {
    private double theta  = 0;
    private double vtheta = 0;

    private static final int boxWidth  = 20;
    private static final int boxHeight = 30;

    public Pendulum() {
    }

    public void step() {
        vtheta -= Params.gravity * Math.cos(theta);
        theta  += vtheta / Params.timestep;
    }

    public void draw(Graphics2D g) {
        final int x = Params.x + (int) (Params.r * Math.cos(theta));
        final int y = Params.y - (int) (Params.r * Math.sin(theta));

        g.drawLine(Params.x, Params.y, x, y);
        Util.fillCircle(g, x, y, 4);
        drawBob(g);
    }

    public void drawBob(Graphics2D g) {
        double alpha;
        double thetaPrime;
        int a;
        int b;
        int c;

        a = boxWidth / 2;

        b = Params.r - boxHeight/2;
        alpha = Math.atan2(a, b);
        thetaPrime = theta + alpha;
        c = (int) Math.sqrt(a*a + b*b);
        final int ax = (int) (c * Math.cos(-thetaPrime));
        final int ay = (int) (c * Math.sin(-thetaPrime));

        b = Params.r - boxHeight/2;
        alpha = Math.atan2(a, b);
        thetaPrime = theta - alpha;
        c = (int) Math.sqrt(a*a + b*b);
        final int bx = (int) (c * Math.cos(-thetaPrime));
        final int by = (int) (c * Math.sin(-thetaPrime));

        b = Params.r + boxHeight/2;
        alpha = Math.atan2(a, b);
        thetaPrime = theta + alpha;
        c = (int) Math.sqrt(a*a + b*b);
        final int cx = (int) (c * Math.cos(-thetaPrime));
        final int cy = (int) (c * Math.sin(-thetaPrime));

        b = Params.r + boxHeight/2;
        alpha = Math.atan2(a, b);
        thetaPrime = theta - alpha;
        c = (int) Math.sqrt(a*a + b*b);
        final int dx = (int) (c * Math.cos(-thetaPrime));
        final int dy = (int) (c * Math.sin(-thetaPrime));

        final int x = Params.x;
        final int y = Params.y;

        final int[] xs = {x + ax, x + bx, x + dx, x + cx};
        final int[] ys = {y + ay, y + by, y + dy, y + cy};

        g.fillPolygon(xs, ys, xs.length);
    }
}
