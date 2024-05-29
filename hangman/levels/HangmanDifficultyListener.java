package hangman.levels; 
//Importujemy potrzebne biblioteki
import game.HangmanGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanDifficultyListener implements ActionListener { //Nowa klasa Określania poziomu trudności przy pierwszym uruchomieniu bądz przy kliknięciu przycisku
    private int maxAttempts; //Ilość prób
    private HangmanGUI gui; //Obiekt HangmanGUI do wywołania metody startGame
    

    public HangmanDifficultyListener(int maxAttempts, HangmanGUI gui) { // Konstruktor przyjmujący maksymalną liczbę prób i referencję do obiektu HangmanGUI
        this.maxAttempts = maxAttempts; // Przypisanie wartości maksymalnej liczby prób do zmiennej maxAttempts
        this.gui = gui; // Przypisanie referencji do obiektu HangmanGUI do zmiennej gui
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {// Metoda obsługująca zdarzenie akcji
        // Wywołanie metody startGame z obiektu gui, przekazując maksymalną liczbę prób
        gui.startGame(maxAttempts);
    }
}    
