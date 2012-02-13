import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Slydr extends JPanel {
    private JLabel     label;
    private JSlider    slider;
    private JSpinner   spinner;

    private ChangeListener cl;

    private int value;

    private JPanel split;

    private static final int borderWidth = 8;

    private static final int step = 1;

    public Slydr(String name, int min, int max, int val) {
        label   = new JLabel(name);
        slider  = new JSlider(JSlider.HORIZONTAL, min, max, val);
        spinner = new JSpinner(new SpinnerNumberModel(val, min, max, step));

        value = val;

        final int size = Math.max(1, Math.abs(max - min));

        //slider.setMajorTickSpacing(size/4);
        slider.setMinorTickSpacing(Math.max(1, size/16));
        slider.setPaintTicks(true);
        //slider.setPaintLabels(true);

        spinner.addChangeListener(new FieldChanged());
        slider .addChangeListener(new SliderChanged());

        setLayout(new BorderLayout());
        setBorder(Util.makeBorder(borderWidth));

        split = new JPanel();
        split.setLayout(new GridLayout(0, 2));
        split.add(label);
        split.add(spinner);

        add(split,  BorderLayout.NORTH);
        add(slider, BorderLayout.CENTER);
    }

    public void setValue(int n) {
        change();

        slider.setValue(n);
        value = slider.getValue();
        spinner.setValue(value);
    }

    public int getValue() { return value; }

    private void change() {
        if (cl != null) {
            cl.stateChanged(new ChangeEvent(this));
        }
    }

    public void setChangeListener(ChangeListener cl) {
        this.cl = cl;
    }

    private class FieldChanged implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            setValue((Integer) spinner.getValue());
        }
    }

    private class SliderChanged implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            setValue(slider.getValue());
        }
    }
}
