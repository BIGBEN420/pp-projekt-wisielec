package game;

import hangman.stats.HangmanGameStats;
import hangman.wordmanager.HangmanWordManager;
import hangman.levels.HangmanDifficultyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;



//Klasa HangmanGUI\\
//Tworzymy klase HangmanGUI która dziedziczy po JFrame
public class HangmanGUI extends JFrame {
    private JPanel displayPanel = new JPanel(new GridBagLayout());
    private JPanel buttonPanel = new JPanel();
    private JTextField inputField = new JTextField(); //Pole do wpisania litery bądz słowa
    private JButton easyButton = new JButton("Łatwy"); //Przycisk easy 10 prób
    private JButton mediumButton = new JButton("Średni"); //Przycisk medium 5 prób
    private JButton hardButton = new JButton("Trudny"); //Przycisk hard 3 próby
    private JButton addButton = new JButton("Dodaj słowo"); //Przycisk dodania słowa
    private JButton showWordsButton = new JButton("Wyświetl słowa z bazy"); //Przycisk wyświetlania bazy słów
    private JLabel statsLabel = new JLabel();

    private HangmanWordManager wordManager = new HangmanWordManager();; //Utworzenie zmiennej wordManager przechowywującej obiekt HangmanWordManager
    private HangmanGameStats gameStats = new HangmanGameStats(); //Utworzenie zmiennej gameStats przechowywującą obiekt HangmanGameStats
    private String secretWord; //Szukane słowo
    private int attempts; //Liczba prób
    private int errors; //Liczba błędów
    private int inputAttempts; //Ilość wpisanych wartości w danej sesji
    private Set<Character> guessedLetters; //Kolekcja szukanych słów
    private int originalAttempts; //Orginalna wejściowa ilość prób po wciśnięciu przycisku

    //Konstruktor HangmanGUI
    public HangmanGUI() {
        wordManager.loadWords(); //wywołanie metody loadWords zaczytanie słów z bazy
        setTitle("GRA W WIŚIELCA"); //tytuł
        setSize(900, 350); //wartość szerokości i wysokości okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Przypisanie x w prawym górnym rogu do wyjścia z programu
        setLayout(new BorderLayout()); //Ustawienie layolautu

        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //ustawienie siatki
        add(displayPanel, BorderLayout.CENTER); //dodanie panelu

        add(inputField, BorderLayout.SOUTH); //dodanie pola do wpisywania

            buttonPanel.add(easyButton); //dodanie przycisku easyButton
            buttonPanel.add(mediumButton); //dodanie przycisku mediumButton
            buttonPanel.add(hardButton); //dodanie przycisku hardButton
            buttonPanel.add(addButton); //Dodanie przycisku addButton
            buttonPanel.add(showWordsButton); //Dodanie przycisku showWordsButton
        add(buttonPanel, BorderLayout.NORTH); //dodanie panelu przycisków

        add(statsLabel, BorderLayout.EAST); //dodanie obiektu statsLabel

        easyButton.addActionListener(new HangmanDifficultyListener(10, this)); //Dodanie akcji do przycisku który uruchamia dany poziom 
        mediumButton.addActionListener(new HangmanDifficultyListener(5, this));
        hardButton.addActionListener(new HangmanDifficultyListener(3, this));
        easyButton.addActionListener(e -> originalAttempts = 10); //Dodanie akcji zadeklarowania zmiennej o danej wartości prób do utrzymania sesji bez resetu statystyk
        mediumButton.addActionListener(e -> originalAttempts = 5);
        hardButton.addActionListener(e -> originalAttempts = 3);

        addButton.addActionListener(new ActionListener() 
        { //Dodanie akcji do przycisku addButton
            @Override
            public void actionPerformed(ActionEvent e) { //metoda umożliwiająca wprowadzenie nowego słowa
                String newWord = JOptionPane.showInputDialog("Wprowadź nowe słowo:"); //wyświetl okno do wpisania nowego słowa
                if (newWord != null && !newWord.isEmpty()) { //sprawdzenie wartości czy nie jest pusta
                    wordManager.addWord(newWord); //dodanie nowego słowa do bazy
                }
            }
        }
        );

        showWordsButton.addActionListener(new ActionListener() 
        { //Dodanie akcji do przycisku showWordsButton
            @Override
            public void actionPerformed(ActionEvent e) {//Przycisk wywołuje metode displayAllWords
                displayAllWords();
            }
        }
        );

        inputField.addActionListener(new ActionListener() {
            // Dodanie akcji do pola wpisywania liter bądź słów
            @Override
            public void actionPerformed(ActionEvent e) {
                // Metoda, która wczytuje słowo do zmiennej input
                String input = inputField.getText().trim();
        
                // Sprawdzenie, czy wprowadzony tekst składa się z jednej litery
                if (input.length() == 1) {
                    // Jeśli tak, pobierz tę literę
                    char letter = input.charAt(0);
                    
                    // Sprawdzenie, czy litera nie została już wcześniej zgadywana
                    if (!guessedLetters.contains(letter)) {
                        // Jeśli nie, sprawdź literę
                        checkLetter(letter);
                        // Dodaj literę do listy już zgadywanych liter
                        guessedLetters.add(letter);
                    }
                }else {
                    // Jeśli wprowadzony tekst nie jest pojedynczą literą, sprawdź całe słowo
                    checkWord(input);
                }
                
                // Zresetowanie pola wprowadzania tekstu
                inputField.setText("");
            }
        });
        
        setVisible(true);
    }        





















    //Metody klasy HangmanGUI logika gry\\
    public void startGame(int maxAttempts) { //Metoda uruchamiająca gre podając ilość Prób
        secretWord = wordManager.getRandomWord();  //losowanie słowa z bazy
        attempts = maxAttempts;
        errors = 0;
        inputAttempts = 0; 
        guessedLetters = new HashSet<>();
        displayWord();
        updateStats(); //Aktualizacja statystyk 
    }

    private void displayWord() {
        // Metoda wyświetlająca słowo do odgadnięcia
    
        // Usunięcie wszystkich komponentów z displayPanel
        displayPanel.removeAll();
    
        // Konfiguracja obiektu GridBagConstraints do rozmieszczenia komponentów w GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Ustawienie początkowej pozycji x na 0
        gbc.insets = new Insets(5, 5, 5, 5); // Ustawienie odstępów (padding) wokół komponentów
    
        // Iteracja przez każdy znak w słowie szukanym
        for (char c : secretWord.toCharArray()) {
            // Utworzenie JLabel z odpowiednim tekstem (" " dla spacji lub "_" dla liter)
            JLabel letterLabel = new JLabel(c == ' ' ? " " : "_");
            // Ustawienie czcionki dla JLabel
            letterLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
            // Dodanie JLabel do displayPanel z odpowiednimi ustawieniami GridBagConstraints
            displayPanel.add(letterLabel, gbc);
            // Przesunięcie pozycji x dla następnego komponentu
            gbc.gridx++;
        }
    
        // Odświeżenie displayPanel po dodaniu wszystkich komponentów
        displayPanel.revalidate();
        displayPanel.repaint();
    }
    

    private void updateStats() { //Metoda aktualizacja statsLabel wywołując metode getStats
        statsLabel.setText(gameStats.getStats(attempts, errors));
    }

    private void checkLetter(char letter) {
        // Metoda sprawdzająca literę w słowie szukanym
    
        // Inicjalizacja zmiennej logicznej oznaczającej, czy litera została znaleziona
        boolean found = false;
        
        // Pobranie wszystkich komponentów z displayPanel
        Component[] components = displayPanel.getComponents();
        
        // Iteracja przez każdą literę w słowie szukanym
        for (int i = 0; i < secretWord.length(); i++) {
            // Sprawdzanie, czy bieżąca litera w słowie szukanym jest równa wprowadzonej literze
            if (secretWord.charAt(i) == letter) {
                found = true; // Ustawienie flagi na true, jeśli litera została znaleziona
                inputAttempts++; // Zwiększenie liczby prób
                // Ustawienie tekstu w odpowiednim JLabel na znalezioną literę
                ((JLabel) components[i]).setText(String.valueOf(letter));
            }
        }
        
        // Jeśli litera nie została znaleziona
        if (!found) {
            errors++; // Zwiększenie liczby błędów
            attempts--; // Zmniejszenie liczby pozostałych prób
        }
        
        // Aktualizacja statystyk gry
        updateStats();
        
        // Sprawdzenie, czy gra została wygrana lub przegrana
        checkWinOrLoss();
    }
    

    private void checkWord(String word) {//Metoda sprawdzająca całe słowo
        if (word.equalsIgnoreCase(secretWord)) { //Jeżeli słowo jest prawidłowe wyświetla prompt oraz zaczytuje dane do statystyk
            JOptionPane.showMessageDialog(this, "Wygrałeś!");
            gameStats.incrementWins();
            gameStats.incrementWins();
            gameStats.addAttemptsToWin(inputAttempts); 
            startNewGame();//Uruchamia nową gre
        } else {
            errors++;
            attempts--;
        }
        updateStats();
        checkWinOrLoss();
    }

    private void checkWinOrLoss() {
        // Inicjalizacja flagi oznaczającej wygraną
        boolean won = true;
        
        // Iteracja przez wszystkie komponenty w displayPanel
        for (Component component : displayPanel.getComponents()) {
            // Sprawdzanie, czy tekst w JLabel to "_"
            if (((JLabel) component).getText().equals("_")) {
                won = false; // Jeśli tak, ustawia flagę na false i przerywa pętlę
                break;
            }
        }
        
        // Jeśli flaga oznaczająca wygraną jest true
        if (won) {
            // Wyświetlenie komunikatu o wygranej
            JOptionPane.showMessageDialog(this, "Wygrałeś!");
            
            // Aktualizacja statystyk gry
            gameStats.incrementWins(); // Zwiększenie liczby wygranych
            gameStats.incrementGames(); // Zwiększenie liczby gier
            gameStats.addAttemptsToWin(inputAttempts); // Dodanie liczby prób do wygranych
            gameStats.getStats(attempts, errors);
            // Rozpoczęcie nowej gry
            startNewGame();
        } 
        // Jeśli liczba pozostałych prób wynosi 0
        else if (attempts == 0) {
            // Wyświetlenie komunikatu o przegranej z ujawnieniem szukanego słowa
            JOptionPane.showMessageDialog(this, "Przegrałeś. Szukane słowo to: " + secretWord);
            
            // Aktualizacja statystyk gry
            gameStats.incrementGames(); // Zwiększenie liczby gier
            
            // Wyświetlenie obrazu przegranej (jeśli jest taka funkcja)
            showLossImage();
            
            // Rozpoczęcie nowej gry
            startNewGame();
        }
    }

    private void showLossImage() { //Metoda wyświetlająca wiśielca po przegranej
        String fileName = wordManager.currentDirectory + File.separator + "HangMan.png";
        ImageIcon lossIcon = new ImageIcon(fileName);
        JOptionPane.showMessageDialog(this, "", "Przegrałeś", JOptionPane.INFORMATION_MESSAGE, lossIcon);
    }

    private void startNewGame() { //Inicjalizacja uruchomienia gry
        guessedLetters.clear();
        errors = 0;
        startGame(originalAttempts);
    }

    private void displayAllWords() {
        // Tworzenie JTextArea o wymiarach 20 wierszy i 40 kolumn
        JTextArea textArea = new JTextArea(20, 40); 
        
        // Tworzenie JScrollPane, który umożliwia przewijanie zawartości JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea); 
    
        // Ustawienie JTextArea jako nieedytowalny
        textArea.setEditable(false); 
    
        // Pobieranie wszystkich słów z wordManager i ustawienie ich w JTextArea
        String allWords = wordManager.getAllWords();
        textArea.setText(allWords);
    
        // Wyświetlenie JScrollPane zawierającego JTextArea w oknie dialogowym
        JOptionPane.showMessageDialog(this, scrollPane, "Lista słów", JOptionPane.INFORMATION_MESSAGE);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanGUI::new);
    }
}
