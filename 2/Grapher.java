import java.awt.*;
import javax.swing.*;

public class Grapher extends JFrame {
    private final Canvas canvas;
    private final Color bgColor = new Color( 17,  17,  17);
    private final Color fgColor = new Color(224, 224, 224);

    public Grapher(DataList dl) {
        canvas = new Canvas(dl);
        canvas.setPreferredSize(new Dimension(1000, 480));
        setBackground(bgColor);
        setForeground(fgColor);
        add(canvas);
        pack();
        setVisible(true);
        setTitle("Grapher by Brian Mock");
    }
}
