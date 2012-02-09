import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class GUI extends JFrame {
    private Canvas canvas;

    public GUI() {
        setNiceTheme();

        canvas = new Canvas(this);
        add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();

        setVisible(true);

        new Helper(canvas);
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
