package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class HangmanGUI extends JFrame {
    private JPanel displayPanel;
    private JTextField inputField;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton addButton;
    private JButton showWordsButton;
    private JLabel statsLabel;
    private JLabel wordLengthLabel;

    private HangmanWordManager wordManager;
    private HangmanGameStats gameStats;
    private String secretWord;
    private int attempts;
    private int errors;
    private Set<Character> guessedLetters;

    public HangmanGUI() {
        setTitle("GRA W WIŚIELCA");
        setSize(700, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        wordManager = new HangmanWordManager();
        gameStats = new HangmanGameStats();

        displayPanel = new JPanel(new GridBagLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(displayPanel, BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        easyButton = new JButton("Łatwy");
        mediumButton = new JButton("Średni");
        hardButton = new JButton("Trudny");
        addButton = new JButton("Dodaj słowo");
        showWordsButton = new JButton("Pokaż słowa");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        buttonPanel.add(addButton);
        buttonPanel.add(showWordsButton);
        add(buttonPanel, BorderLayout.NORTH);

        statsLabel = new JLabel();
        add(statsLabel, BorderLayout.EAST);

        wordLengthLabel = new JLabel();
        add(wordLengthLabel, BorderLayout.WEST);

        wordManager.loadWords();

        easyButton.addActionListener(new HangmanDifficultyListener(10, this));
        mediumButton.addActionListener(new HangmanDifficultyListener(5, this));
        hardButton.addActionListener(new HangmanDifficultyListener(3, this));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newWord = JOptionPane.showInputDialog("Wprowadź nowe słowo:");
                if (newWord != null && !newWord.isEmpty()) {
                    wordManager.addWord(newWord);
                }
            }
        });

        showWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllWords();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                if (!input.isEmpty()) {
                    if (input.length() == 1) {
                        char letter = input.charAt(0);
                        if (!guessedLetters.contains(letter)) {
                            checkLetter(letter);
                            guessedLetters.add(letter);
                        }
                    } else {
                        checkWord(input);
                    }
                    inputField.setText("");
                }
            }
        });

        setVisible(true);
    }

    public void startGame(int maxAttempts) {
        secretWord = wordManager.getRandomWord();
        attempts = maxAttempts;
        errors = 0;
        guessedLetters = new HashSet<>();
        displayWord();
        updateStats();
    }

    private void displayWord() {
        displayPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (char c : secretWord.toCharArray()) {
            JLabel letterLabel = new JLabel(c == ' ' ? " " : "_");
            letterLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
            displayPanel.add(letterLabel, gbc);
            gbc.gridx++;
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private void updateStats() {
        statsLabel.setText(gameStats.getStats(attempts, errors));
    }

    private void checkLetter(char letter) {
        boolean found = false;
        Component[] components = displayPanel.getComponents();
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                found = true;
                ((JLabel) components[i]).setText(String.valueOf(letter));
            }
        }
        if (!found) {
            errors++;
            attempts--;
        }
        updateStats();
        checkWinOrLoss();
    }

    private void checkWord(String word) {
        if (word.equalsIgnoreCase(secretWord)) {
            JOptionPane.showMessageDialog(this, "Wygrałeś!");
            gameStats.incrementWins();
            startNewGame();
        } else {
            errors++;
            attempts--;
        }
        updateStats();
        checkWinOrLoss();
    }

    private void checkWinOrLoss() {
        boolean won = true;
        for (Component component : displayPanel.getComponents()) {
            if (((JLabel) component).getText().equals("_")) {
                won = false;
                break;
            }
        }
        if (won) {
            JOptionPane.showMessageDialog(this, "Wygrałeś!");
            gameStats.incrementWins();
            startNewGame();
        } else if (attempts == 0) {
            JOptionPane.showMessageDialog(this, "Przegrałeś. Szukane słowo to: " + secretWord);
            showLossImage();
            startNewGame();
        }
    }

    private void showLossImage() {
        ImageIcon lossIcon = new ImageIcon("HangMan.png");
        JOptionPane.showMessageDialog(this, "", "Przegrałeś", JOptionPane.INFORMATION_MESSAGE, lossIcon);
    }

    private void startNewGame() {
        guessedLetters.clear();
        startGame(attempts);
    }

    private void displayAllWords() {
        JOptionPane.showMessageDialog(this, wordManager.getAllWords(), "Lista słów", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanGUI::new);
    }
}
