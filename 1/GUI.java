import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class GUI extends JFrame {
    private JPanel      toolbar;
    private JPanel      padding;
    private JButton     stayButton;
    private JButton     swapButton;
    private JButton     quitButton;
    private JScrollPane scrollPane;
    private JTextArea   text;

    private final Dimension size = new Dimension(500, 500);

    private final int padPx = 4;

    private final Color borderColor = new Color(128, 128, 128);

    public GUI() {
        setLayout(new BorderLayout());

        pretty();

        toolbar    = new JPanel();
        stayButton = new JButton("Stay");
        swapButton = new JButton("Switch");
        quitButton = new JButton("Quit");
        text       = new JTextArea();
        scrollPane = new JScrollPane(text);

        // scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(padPx, padPx, padPx, padPx),
            scrollPane.getBorder()
        ));
        toolbar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
        text.setEditable(false);

        toolbar.add(stayButton);
        toolbar.add(swapButton);
        toolbar.add(quitButton);

        add(toolbar,    BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();

        setVisible(true);
    }

    private void pretty() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
