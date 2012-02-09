import javax.swing.*;
import java.awt.*;

public class Slydr extends JPanel {
    public JLabel     label;
    public JSlider    slider;
    public JTextField field;

    private static final int borderWidth = 8;

    public Slydr(String name, int min, int max, int val) {
        label  = new JLabel(name);
        slider = new JSlider(SwingConstants.HORIZONTAL, min, max, val);
        field  = new JTextField("" + max);

        setLayout(new BorderLayout());
        this  .setBorder(Util.makeBorder(borderWidth));

        add(label,  BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);
        add(field,  BorderLayout.EAST);
    }
}
