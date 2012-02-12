import java.awt.*;

public class Pendulum {
    private double theta  = 0;
    private double vtheta = 0;

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
        Util.drawCircle(g, x, y, 10);
    }
}
