import javax.swing.UIManager.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Maker extends JFrame {
    /*
     *
     * dH = aH - αHP
     * dP = βHP - bP
     */
    private JTextField alphaNum;
    private JTextField betaNum;
    private JTextField aNum;
    private JTextField bNum;
    private JTextField hNum;
    private JTextField pNum;
    private JLabel     alphaLabel;
    private JLabel     betaLabel;
    private JLabel     aLabel;
    private JLabel     bLabel;
    private JLabel     hLabel;
    private JLabel     pLabel;

    private JButton submit;
    private JButton phase;

    private static final int pad = 10;

    public Maker() {
        beautify();
        JPanel grid = new JPanel();
        setTitle("Graph Maker by Brian Mock");

        grid.setBorder(BorderFactory.createEmptyBorder(pad, pad, pad, pad));
        grid.setLayout(new GridLayout(0, 2));
        grid.setBackground(getBackground());

        alphaLabel = new JLabel("α", JLabel.RIGHT);
        betaLabel  = new JLabel("β", JLabel.RIGHT);
        aLabel     = new JLabel("a", JLabel.RIGHT);
        bLabel     = new JLabel("b", JLabel.RIGHT);
        hLabel     = new JLabel("H", JLabel.RIGHT);
        pLabel     = new JLabel("P", JLabel.RIGHT);

        alphaNum   = new JTextField("0.001");
        betaNum    = new JTextField("0.0001");
        aNum       = new JTextField("0.1");
        bNum       = new JTextField("0.1");
        hNum       = new JTextField("1000");
        pNum       = new JTextField("99");

        grid.add(alphaLabel);
        grid.add(alphaNum);

        grid.add(betaLabel);
        grid.add(betaNum);

        grid.add(aLabel);
        grid.add(aNum);

        grid.add(bLabel);
        grid.add(bNum);

        grid.add(hLabel);
        grid.add(hNum);

        grid.add(pLabel);
        grid.add(pNum);

        setLayout(new BorderLayout());

        submit = new JButton("Graph");
        submit.addActionListener(new DoIt());
        phase  = new JButton("Phase");
        phase .addActionListener(new PhaseIt());
        JPanel toolbar = new JPanel();
        toolbar.add(submit);
        toolbar.add(phase);

        add(grid,    BorderLayout.CENTER);
        add(toolbar, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setTitle("Grapher by Brian Mock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class DoIt extends Thread implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new DoIt().start();
        }

        public void run() {
            new GrapherMaker(
                new Double(alphaNum.getText()),
                new Double(betaNum.getText()),
                new Double(aNum.getText()),
                new Double(bNum.getText()),
                new Integer(hNum.getText()),
                new Integer(pNum.getText())
            ).grapher();
        }
    }

    private class PhaseIt extends Thread implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new PhaseIt().start();
        }

        public void run() {
            new GrapherMaker(
                new Double(alphaNum.getText()),
                new Double(betaNum.getText()),
                new Double(aNum.getText()),
                new Double(bNum.getText()),
                new Integer(hNum.getText()),
                new Integer(pNum.getText())
            ).phaser();
        }
    }

    private void beautify() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
        }
    }
}
