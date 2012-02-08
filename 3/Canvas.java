import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JPanel implements MouseListener {
    private Dimension preferredSize = new Dimension(300, 300);

    private Color bgColor = Util.gray(20);

    private int w = 0;
    private int h = 0;

    public Canvas() {
        setPreferredSize(preferredSize);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        w = getWidth();
        h = getHeight();
        draw((Graphics2D) g);
    }

    private void draw(Graphics2D g) {
        drawBG(g);
    }

    private void drawBG(Graphics2D g) {
        g.setBackground(bgColor);
        g.clearRect(0, 0, w, h);
    }

    public void mouseClicked(MouseEvent e) {
        Debug.echo("Clicked");
    }

    public void mousePressed(MouseEvent e) {
        Debug.echo("Pressed");
    }

    public void mouseReleased(MouseEvent e) {
        Debug.echo("Released");
    }

    public void mouseEntered(MouseEvent e) {
        Debug.echo("Entered");
    }

    public void mouseExited(MouseEvent e) {
        Debug.echo("Exited");
    }
}
