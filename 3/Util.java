import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import java.util.*;

public class Util {
    private static final Random random = new Random();

    public static Color gray(int brightness) {
        return new Color(brightness, brightness, brightness);
    }

    public static void fillCircle(Graphics2D g, int x, int y, int r) {
        g.fillOval(x-r, y-r, r+r, r+r);
    }

    public static void drawCircle(Graphics2D g, int x, int y, int r) {
        g.drawOval(x-r, y-r, r+r, r+r);
    }

    public static Border makeBorder(int n) {
        return BorderFactory.createEmptyBorder(n, n, n, n);
    }

    public static void sleep(long time) {
        try { Thread.sleep(time); }
        catch (InterruptedException e) {}
    }

    public static Color randomColor() {
        return Color.getHSBColor(
            randomHue(),
            0.50f,
            0.75f
        );
    }

    public static Color colorFromHue(float hue) {
        return Color.getHSBColor(
            hue,
            0.50f,
            0.75f
        );
    }

    private static int   index = 0;
    private static float step  = 1.0f / 8f;
    private static Color[] rainbow = {
        colorFromHue(0*step),
        colorFromHue(1*step),
        colorFromHue(2*step),
        colorFromHue(3*step),
        colorFromHue(4*step),
        colorFromHue(5*step),
        colorFromHue(6*step),
        colorFromHue(7*step),
    };

    public static Color nextRainbowColor() {
        Color color = rainbow[index];

        index++;
        index %= rainbow.length;

        return color;
    }

    private static int randomComponent() {
        return random.nextInt(64) + 128;
    }

    private static float randomHue() {
        final int n = Integer.MAX_VALUE;
        return random.nextInt(n) / (float)n;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
