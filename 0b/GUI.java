import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
    private JPanel      canvas;
    private JScrollPane scrollPane;

    private final Dimension size = new Dimension(500, 500);

    public GUI() {
        setLayout(new BorderLayout());

        canvas     = new Canvas();
        scrollPane = new JScrollPane(canvas);

        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMinimumSize(size);

        pack();

        setVisible(true);
    }
}
