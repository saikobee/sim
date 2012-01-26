import java.awt.*;
import javax.swing.*;

public class Grapher extends JFrame {
    private final Canvas canvas;

    public Grapher(DataList dl) {
        canvas = new Canvas(dl);
        canvas.setPreferredSize(new Dimension(800, 480));
        add(canvas);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
