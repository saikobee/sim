import java.awt.*;
import javax.swing.*;

public class Canvas extends JPanel {
    private final Dimension size = new Dimension(1000, 1000);

    public Canvas() {
        super();

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        final int w = getWidth();
        final int h = getHeight();

        g.clearRect(0, 0, w, h);
        g.fillOval(0, 0, w, h);
    }
}
