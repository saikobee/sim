import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {
    public Sidebar() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JButton("alpha"));
        add(new JButton("beta"));
        add(new JButton("gamma"));
    }
}
