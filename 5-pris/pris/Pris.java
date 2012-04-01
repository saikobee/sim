/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pris;
import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager.*;

/**
 *
 * @author levenick
 */
public class Pris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setNiceTheme();
        new ControlFrame(new PrisFrame());
    }

    private static void setNiceTheme() {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
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
