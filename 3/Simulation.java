import java.awt.*;
import java.util.*;
import java.util.List;

public class Simulation {
    private List<Pendulum> pendulums;

    private static final int radius = 10;

    private static final Color color = Util.gray(80);

    public Simulation() {
        pendulums = new ArrayList<Pendulum>();
    }

    public synchronized void step() {
        List<Pendulum> theFuture = new ArrayList<Pendulum>(pendulums.size());

        for (Pendulum pendulum: pendulums) {
            theFuture.add(pendulum.stepped(pendulums));
        }

        pendulums = theFuture;
    }

    public void reset() {
        pendulums.clear();
    }

    public synchronized void addPendulumAtAngle(double theta) {
        pendulums.add(new Pendulum(theta));
    }

    public void draw(Graphics2D g) {
        for (Pendulum pendulum: pendulums) {
            pendulum.draw(g);
        }
        g.setColor(color);
        Util.fillCircle(g, Params.x, Params.y, radius);
    }
}
