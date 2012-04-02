package pris;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Util {
    public static Color colorFromHex(String hex) {
        try {
            if (hex.charAt(0) == '#')
                hex = hex.substring(1);

            assert hex.length() == 6;

            final int r = Integer.parseInt(hex.substring(0, 2), 16);
            final int g = Integer.parseInt(hex.substring(2, 4), 16);
            final int b = Integer.parseInt(hex.substring(4, 6), 16);

            return new Color(r, g, b);
        }
        catch (NumberFormatException e) {
            return new Color(255, 0, 255);
        }
    }

    public static Image getImage(String filename) {
        try {
            return ImageIO.read(Util.class.getResource("/" + filename));
        }
        catch (IOException e) {
            try {
                return ImageIO.read(Util.class.getResource(filename));
            }
            catch (IOException e2) {
                System.err.println("Problem reading " + filename + ": " + e);
                return null;
            }
        }
    }
}
