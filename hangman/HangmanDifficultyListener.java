package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanDifficultyListener implements ActionListener {
    private int maxAttempts;
    private HangmanGUI gui;

    public HangmanDifficultyListener(int maxAttempts, HangmanGUI gui) {
        this.maxAttempts = maxAttempts;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.startGame(maxAttempts);
    }
}
