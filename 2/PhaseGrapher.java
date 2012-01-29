import java.awt.*;
import javax.swing.*;

public class PhaseGrapher extends JFrame {
    private final PhaseCanvas canvas;
    private final Color bgColor = new Color( 17,  17,  17);
    private final Color fgColor = new Color(224, 224, 224);

    public PhaseGrapher(DataList dl) {
        canvas = new PhaseCanvas(dl);
        canvas.setPreferredSize(new Dimension(1000, 480));
        setBackground(bgColor);
        setForeground(fgColor);
        add(canvas);
        pack();
        setVisible(true);
        setTitle("Phase Grapher by Brian Mock");
    }
}
