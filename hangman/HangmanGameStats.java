package hangman;

public class HangmanGameStats {
    private int totalAttempts;
    private int totalGames;
    private int wins;

    public void incrementWins() {
        wins++;
    }

    public void incrementGames() {
        totalGames++;
    }

    public void addAttempts(int attempts) {
        totalAttempts += attempts;
    }

    public String getStats(int attempts, int errors) {
        double averageAttemptsToWin = totalGames == 0 ? 0 : (double) totalAttempts / totalGames;
        return String.format("<html>GRA W WIŚIELCA<br><br>Próby: %d<br>Błędy: %d<br>Wygrane gry: %d<br>Liczba gier: %d<br>Średnia ilość prób: %.2f<br><br><i>ŚredniaPrób = SumaPrób/LiczbaGier</i></html>",
                attempts, errors, wins, totalGames, averageAttemptsToWin);
    }
}
