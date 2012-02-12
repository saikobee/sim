import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class TopBar extends JPanel {
    private AbstractButton play;
    private AbstractButton step;
    private AbstractButton reset;
    private AbstractButton quit;

    private Canvas sim;

    public TopBar(Canvas sim) {
        this.sim = sim;

        play  = new JToggleButton("Play");
        step  = new       JButton("Step");
        reset = new       JButton("Reset");
        quit  = new       JButton("Quit");

        add(play);
        add(step);
        add(reset);
        add(quit);

        play .addActionListener(new DoPlay());
        step .addActionListener(new DoStep());
        reset.addActionListener(new DoReset());
        quit .addActionListener(new DoQuit());
    }

    private class DoPlay implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            sim.play();
        }
    }

    private class DoStep implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            sim.step();
        }
    }

    private class DoReset implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            play.setSelected(false);
            sim.reset();
        }
    }

    private class DoQuit implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }
}
