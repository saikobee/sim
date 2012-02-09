import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;

public class Util {
    public static Color gray(int brightness) {
        return new Color(brightness, brightness, brightness);
    }

    public static void circle(Graphics2D g, int x, int y, int r) {
        g.drawOval(x-r, y-r, r+r, r+r);
    }

    public static Border makeBorder(int n) {
        return BorderFactory.createEmptyBorder(n, n, n, n);
    }
}
