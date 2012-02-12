import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class Helper extends JPanel {
    private JPanel bottomBar;
    private JPanel topBar;

    private Canvas canvas;

    public Helper(Canvas canvas) {
        this.canvas = canvas;

        setLayout(new BorderLayout());

        bottomBar = new BottomBar(canvas);
        topBar    = new TopBar(canvas);

        add(bottomBar, BorderLayout.SOUTH);
        add(topBar,    BorderLayout.NORTH);

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
