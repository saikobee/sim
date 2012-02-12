import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class GUI extends JFrame {
    private Canvas canvas;
    private Helper helper;

    public GUI() {
        setNiceTheme();

        setLayout(new BorderLayout());
        canvas = new Canvas(this);
        helper = new Helper(canvas);
        add(canvas, BorderLayout.CENTER);
        add(helper, BorderLayout.WEST);

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
