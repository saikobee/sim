import javax.swing.*;
import java.awt.*;

public class BottomBar extends JPanel {
    public BottomBar() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new Slydr("Time step", 0, 100, 50));
        add(new Slydr("Gravity",   0, 100, 50));
        add(new Slydr("Magnetism", 0, 100, 50));
        add(new Slydr("Delay",     0, 100, 50));
    }
}
