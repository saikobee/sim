import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class GUI extends JFrame {
    private JPanel canvas;
    private JPanel bottomBar;
    private JPanel topBar;

    public GUI() {
        setNiceTheme();

        setLayout(new BorderLayout());

        canvas    = new Canvas();
        bottomBar = new BottomBar();
        topBar    = new TopBar();

        add(canvas,    BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
        add(topBar,    BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setVisible(true);
    }

    private void setNiceTheme() {
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
