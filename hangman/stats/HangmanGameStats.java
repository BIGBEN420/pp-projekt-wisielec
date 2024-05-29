package hangman.stats;

public class HangmanGameStats { //Utworzenie klasy HangmanGameStats
    private int totalAttemptsToWin; //Dodanie zmiennych 
    private int totalGames;
    private int wins;
    public HangmanGameStats(){
        totalAttemptsToWin=0;
    }

    public void incrementWins() { //Dodaj 1 do zmiennej wins
        wins++;
    }

    public void incrementGames() {//Dodaj 1 do zmiennej incrementsGame
        totalGames++;
    }

    public void addAttemptsToWin(int attempts) { //Dodanie 1 do addAtemptsToWin jeżeli wpisana litera bądz słowo było prawidłowe
        totalAttemptsToWin += attempts;
    }

    public String getStats(int attempts, int errors) {//Metoda tworząca statystyki
        return String.format("<html>GRA W WIŚIELCA<br><br>Próby: %d<br>Błędy: %d<br>Wygrane gry: %d<br>Liczba gier: %d<br>Średnia ilość prób: %.2f<br><br><i>ŚredniaPrób =ŚredniaPróbWin/LiczbaWygranych</i></html>",
                attempts, errors, wins, totalGames, averageAttemptsToWin()); //Zwróćenie statystyk
    
            }
            public double averageAttemptsToWin() {
                // Sprawdzenie, czy rozegrano jakiekolwiek gry
                if (wins == 0) {
                    return 0;
                } else {
                    // Obliczenie średniej ilości prób na wygraną
                    return (double) totalAttemptsToWin / wins;
                }
            }

}
