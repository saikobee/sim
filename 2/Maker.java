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
    private JLabel   alphaLabel;
    private JSpinner alphaNum;
    private JLabel   betaLabel;
    private JSpinner betaNum;
    private JLabel   aLabel;
    private JSpinner aNum;
    private JLabel   bLabel;
    private JSpinner bNum;
    private JLabel   hLabel;
    private JSpinner hNum;
    private JLabel   pLabel;
    private JSpinner pNum;

    private JButton submit;

    private static final int pad = 10;

    public Maker() {
        beautify();
        JPanel grid = new JPanel();
        setTitle("Graph Maker by Brian Mock");

        grid.setBorder(BorderFactory.createEmptyBorder(pad, pad, pad, pad));
        grid.setLayout(new GridLayout(0, 4));
        grid.setBackground(getBackground());

        alphaLabel = new JLabel("α", JLabel.RIGHT);
        betaLabel  = new JLabel("β", JLabel.RIGHT);
        aLabel     = new JLabel("a", JLabel.RIGHT);
        bLabel     = new JLabel("b", JLabel.RIGHT);
        hLabel     = new JLabel("H", JLabel.RIGHT);
        pLabel     = new JLabel("P", JLabel.RIGHT);

        alphaNum   = new JSpinner(new SpinnerNumberModel(0.1, 0.0, 10.0, 0.1));
        betaNum    = new JSpinner(new SpinnerNumberModel(0.1, 0.0, 10.0, 0.1));
        aNum       = new JSpinner(new SpinnerNumberModel(0.1, 0.0, 10.0, 0.1));
        bNum       = new JSpinner(new SpinnerNumberModel(0.1, 0.0, 10.0, 0.1));
        hNum       = new JSpinner(new SpinnerNumberModel(1000, 0, 10000, 1));
        pNum       = new JSpinner(new SpinnerNumberModel( 100, 0, 10000, 1));

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
        JPanel toolbar = new JPanel();
        toolbar.add(submit);

        add(grid,    BorderLayout.CENTER);
        add(toolbar, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setTitle("Grapher by Brian Mock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class DoIt implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new GrapherMaker(
                (Double) alphaNum.getValue(),
                (Double) betaNum.getValue(),
                (Double) aNum.getValue(),
                (Double) bNum.getValue(),
                (Integer) hNum.getValue(),
                (Integer) pNum.getValue()
            );
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
