import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    public TopBar() {
        add(new JButton("Play"));
        add(new JButton("Reset"));
        add(new JButton("Quit"));
    }
}
