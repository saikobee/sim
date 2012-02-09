import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Slydr extends JPanel {
    private JLabel     label;
    private JSlider    slider;
    private JTextField field;

    private ChangeListener cl;

    private JPanel split;

    private static final int borderWidth = 8;

    public Slydr(String name, int min, int max, int val) {
        label  = new JLabel(name);
        slider = new JSlider(JSlider.HORIZONTAL, min, max, val);
        field  = new JTextField("" + max);

        final int size = Math.max(1, Math.abs(max - min));

        slider.setMajorTickSpacing(size/4);
        slider.setMinorTickSpacing(size/10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        field.addActionListener(new FieldChanged());
        slider.addChangeListener(new SliderChanged());

        setLayout(new BorderLayout());
        setBorder(Util.makeBorder(borderWidth));

        split = new JPanel();
        split.setLayout(new GridLayout(0, 2));
        split.add(label);
        split.add(field);

        add(split,  BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);
    }

    public void setValue(int n) {
        if (slider.getValue() != n) {
            change();
        }

        slider.setValue(n);
        field.setText("" + n);
    }

    public int getValue() { return slider.getValue(); }

    private void change() {
        if (cl != null) {
            cl.stateChanged(new ChangeEvent(this));
        }
    }

    public void setChangeListener(ChangeListener cl) {
        this.cl = cl;
    }

    private class FieldChanged implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                setValue(new Integer(field.getText()));
            }
            catch (NumberFormatException ex) {
            }
        }
    }

    private class SliderChanged implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            setValue(slider.getValue());
        }
    }
}
