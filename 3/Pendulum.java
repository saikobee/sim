import java.awt.*;
import java.util.List;

public class Pendulum {
    private final double theta;
    private final double vtheta;

    private static final int boxWidth  = 20;
    private static final int boxHeight = 30;

    private Color color;

    public Pendulum() {
        this.color  = Util.randomColor();
        this.theta  = 0;
        this.vtheta = 0;
    }

    public Pendulum(double theta) {
        this.color  = Util.randomColor();
        this.theta  = theta % (2*Math.PI);
        this.vtheta = 0;
    }

    private Pendulum(double theta, double vtheta, Color color) {
        this.color  = color;
        this.theta  = theta % (2*Math.PI);
        this.vtheta = vtheta;
    }

    public Pendulum stepped(List<Pendulum> pendulums) {
        double vth = 0;

        for (Pendulum that: pendulums) {
            if (this == that)
                continue;

            final boolean attract   = that.isClockwiseOf(this);
            final double  direction = attract? +1: -1;
            final double  distance  = Util.distance(
                Params.r * Math.cos(this.theta),
                Params.r * Math.sin(this.theta),
                Params.r * Math.cos(that.theta),
                Params.r * Math.sin(that.theta)
            );
            final double angle = this.minimumAngleBetween(that);
            final double dvth  = direction * Params.magnetism * (1/(distance * distance)) * Math.sin(angle);
            Debug.echo();
            Debug.echo("direction  =", direction);
            Debug.echo("magnetism  =", Params.magnetism);
            Debug.echo("1/d^2      =", 1/(distance * distance));
            Debug.echo("sin(angle) =", Math.sin(angle));
            Debug.echo("dvtheta (magnetism) =", dvth);
            vth += dvth;
        }

        vth += Params.gravity * Math.cos(-theta);
        final double th  = vtheta / Params.timestep;
        return new Pendulum(theta + th, vtheta - vth, color);
    }

    private boolean isClockwiseOf(Pendulum that) {
        return this.theta - that.theta < that.theta - this.theta;
    }

    private double minimumAngleBetween(Pendulum that) {
        return Math.min(
            this.theta - that.theta,
            that.theta - this.theta
        );
    }

    public void draw(Graphics2D g) {
        final int x = Params.x + (int) (Params.r * Math.cos(theta));
        final int y = Params.y - (int) (Params.r * Math.sin(theta));

        g.setColor(color);
        g.drawLine(Params.x, Params.y, x, y);
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

    public String toString() {
        return String.format("#<Pendulum theta=%07.3f vtheta=%07.3f>", theta, vtheta);
    }
}
