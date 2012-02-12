import java.awt.*;
import java.util.*;
import java.util.List;

public class Simulation {
    private List<Pendulum> pendulums;

    private static final int radius = 10;

    public Simulation() {
        pendulums = new ArrayList<Pendulum>();
        pendulums.add(new Pendulum());
    }

    public void step() {
        Debug.echo("Simulation is stepping");
        for (Pendulum pendulum: pendulums) {
            pendulum.step();
        }
    }

    public void draw(Graphics2D g) {
        Util.fillCircle(g, Params.x, Params.y, radius);
        for (Pendulum pendulum: pendulums) {
            pendulum.draw(g);
        }
    }
}
