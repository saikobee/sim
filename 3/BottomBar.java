import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class BottomBar extends JPanel {
    private Slydr t;
    private Slydr g;
    private Slydr m;
    private Slydr d;

    private Canvas sim;

    public BottomBar(Canvas sim) {
        this.sim = sim;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Slydr[] slydrs = new Slydr[] {
            t = new Slydr("Time step", 1,  100, Params.timestep),
            g = new Slydr("Gravity",   1,  100, Params.gravity),
            m = new Slydr("Magnetism", 1,  100, Params.magnetism),
            d = new Slydr("Delay",     1, 1000, Params.delay),
        };

        for (Slydr slydr: slydrs) {
            add(slydr);
            slydr.setChangeListener(new Updater());
        }
    }

    private void updateConstants() {
        sim.setTimeStep(t.getValue());
        sim.setGravity(g.getValue());
        sim.setMagnetism(m.getValue());
        sim.setDelay(d.getValue());
    }

    private class Updater implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            updateConstants();
        }
    }
}
