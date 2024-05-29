package hangman.stats;

public class HangmanGameStats { //Utworzenie klasy HangmanGameStats
    private int totalAttemptsToWin; //Dodanie zmiennych 
    private int totalGames;
    private int wins;

    public void incrementWins() { //Dodaj 1 do zmiennej wins
        wins++;
    }

    public void incrementGames() {//Dodaj 1 do zmiennej incrementsGame
        totalGames++;
    }

    public void addAttemptsToWin(int attempts) { //Dodanie 1 do addAtemptsToWin jeżeli wpisana litera bądz słowo było prawidłowe
        totalAttemptsToWin ++;
    }

    public String getStats(int attempts, int errors) {//Metoda tworząca statystyki
        double averageAttemptsToWin = wins == 0 ? 0 : (double) totalAttemptsToWin / wins; //Deklaracja w zmiennej jeżeli wins jest 0 to średnia prób też 0 jeżeli jest inaczej zastosuj wzór
        return String.format("<html>GRA W WIŚIELCA<br><br>Próby: %d<br>Błędy: %d<br>Wygrane gry: %d<br>Liczba gier: %d<br>Średnia ilość prób: %.2f<br><br><i>ŚredniaPrób =ŚredniaPróbWin/LiczbaWygranych</i></html>",
                attempts, errors, wins, totalGames, averageAttemptsToWin); //Zwróćenie statystyk
    }
}
