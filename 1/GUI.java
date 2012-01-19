import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import java.util.*;
import java.util.List;

public class GUI extends JFrame {
    private JPanel      toolbar;
    private JPanel      padding;
    private JButton     stayButton;
    private JButton     swapButton;
    private JButton     quitButton;
    private JScrollPane scrollPane;
    private JTextArea   text;

    private static final int tournamentLength = (int) 1e6;

    private final Dimension size = new Dimension(300, 200);

    private final int padPx = 4;

    private final Color borderColor = new Color(128, 128, 128);

    private final boolean chatty = false;

    private int prizeDoor;

    private int wins  = 0;
    private int games = 0;

    public GUI() {
        setLayout(new BorderLayout());

        pretty();

        toolbar    = new JPanel();
        stayButton = new JButton("Stay");
        swapButton = new JButton("Switch");
        quitButton = new JButton("Quit");
        text       = new JTextArea();
        scrollPane = new JScrollPane(text);

        // scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(padPx, padPx, padPx, padPx),
            scrollPane.getBorder()
        ));
        toolbar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
        text.setEditable(false);
        scrollPane.setPreferredSize(size);

        toolbar.add(stayButton);
        toolbar.add(swapButton);
        toolbar.add(quitButton);

        add(toolbar,    BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        stayButton.addActionListener(new Stay());
        swapButton.addActionListener(new Swap());
        quitButton.addActionListener(new Quit());

        pack();

        setVisible(true);
    }

    private void playGameWith(Player player) {
        clear();
        pickDoor();
        chat("Hello everyone and welcome to Let's Make a Deal!");
        chat("I am your host Monty Hall, and this our groovy contestant!");
        chat("So, groovy contestant, which door would you like to pick?");
        int guess = player.guess();
        chat("So you chose door number ", guess, ".");
        chat("Alright, now let's make a deal.");
        final int badDoor = pickBadDoor(guess);
        chat("I know that door number ", badDoor, " doesn't have the prize.");
        chat("Now that you know this, would you like to change your guess?");
        final boolean change = player.change();
        if (change) {
            chat("So you want to gamble on the other door?");
            for (int door: Arrays.asList(0, 1, 2)) {
                if (door != guess && door != badDoor) {
                    guess = door;
                }
            }
        }
        else {
            chat("Staying safe with your current door?");
        }
        chat("Let's see if that was the right move!");
        chat("It looks like the corret door was number ", prizeDoor, "!");
        if (guess == prizeDoor) {
            chat("Congratulations! You won!");
            wins++;
        }
        else {
            chat("ZONK! Better luck next time! Enjoy your gravy-flavored mouthwash!");
        }
        games++;
    }

    private void playTournamentWith(Player player) {
        wins  = 0;
        games = 0;
        for (int n = 0; n < tournamentLength; n++) {
            playGameWith(player);
        }
        clear();
        report("Number of games: ", tournamentLength);
        report("Player type: ", player.type());
        final double ratio = ((double) wins)/((double) games);
        report("Win percentage: ", String.format("%.2f%%", 100 * ratio));
    }

    private int pickBadDoor(int guess) {
        int index;
        List<Integer> doors = new ArrayList(Arrays.asList(0, 1, 2));

        index = doors.indexOf(guess);
        if (index >= 0) doors.remove(index);

        index = doors.indexOf(prizeDoor);
        if (index >= 0) doors.remove(index);

        int num = Util.random.nextInt(doors.size());
        return doors.get(num);
    }

    private void pickDoor() {
        prizeDoor = Util.random.nextInt(3);
    }

    private void report(Object... words) {
        for (Object word: words) {
            text.append("" + word);
        }

        text.append("\n");
    }

    private void chat(Object... words) {
        if (chatty) {
            report(words);
        }
    }

    private void clear() {
        text.setText("");
    }

    private class Stay implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playTournamentWith(new StayPlayer());
        }
    }

    private class Swap implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playTournamentWith(new SwapPlayer());
        }
    }

    private class Quit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private void pretty() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
