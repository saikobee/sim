import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

public class Editor extends JFrame {
    private JPanel      toolbar;
    private JTextArea   text;
    private JTextField  name;
    private JScrollPane scroll;

    private JButton saveButton;
    private JButton loadButton;
    private JButton nukeButton;
    private JButton quitButton;
    private JButton rmButton;

    public Editor() {
        makePretty();

        setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        text   = new JTextArea();
        name   = new JTextField();
        scroll = new JScrollPane(text);

        toolbar = new JPanel();
        saveButton = new SaveButton();
        loadButton = new LoadButton();
        nukeButton = new NukeButton();
        quitButton = new QuitButton();
        rmButton   = new RmButton();

        toolbar.add(saveButton);
        toolbar.add(loadButton);
        toolbar.add(rmButton);
        toolbar.add(nukeButton);
        toolbar.add(quitButton);

        add(toolbar, BorderLayout.NORTH);
        add(scroll,  BorderLayout.CENTER);
        add(name,    BorderLayout.SOUTH);

        setSize(400, 400);

        setVisible(true);
    }

    private void makePretty() {
        try {
            for (LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals("Nimbus")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
        }
    }

    private void showFs() {
        System.out.println(Globals.fs);
    }

    private void setEditorText(String str) {
        text.setText(str);
    }

    private String getEditorText() {
        return text.getText();
    }

    private String getFilename() {
        return name.getText();
    }

    private class SaveButton
    extends JButton implements ActionListener {
        public SaveButton() {
            setText("Save");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("Save");
            Globals.fs.save(getFilename(), getEditorText());
            showFs();
        }
    }

    private class LoadButton
    extends JButton implements ActionListener {
        public LoadButton() {
            setText("Load");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("Load");
            setEditorText(Globals.fs.load(getFilename()));
            showFs();
        }
    }

    private class NukeButton
    extends JButton implements ActionListener {
        public NukeButton() {
            setText("Nuke");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("Nuke");
            Globals.fs.nuke();
            showFs();
        }
    }

    private class RmButton
    extends JButton implements ActionListener {
        public RmButton() {
            setText("Delete");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("Delete");
            Globals.fs.delete(getFilename());
            showFs();
        }
    }

    private class QuitButton
    extends JButton implements ActionListener {
        public QuitButton() {
            setText("Quit");
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
