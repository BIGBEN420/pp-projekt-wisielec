# Gra w wisielca
## Spis Treści

1. [Opis Projektu](#opis-projektu)
2. [Funkcjonalności](#funkcjonalności)
3. [Instrukcje Obsługi](#instrukcje-obsługi)
4. [Struktura Projektu](#struktura-projektu)
5. [Opis Klas i Funkcji](#opis-klas-i-funkcji)

## Opis Projektu

**HangmanGUI** to aplikacja desktopowa napisana w języku Java, która implementuje klasyczną grę w Wisielca. Użytkownik może wybierać między różnymi poziomami trudności, zarządzać bazą słów oraz śledzić statystyki gry.

## Funkcjonalności

1. **Graficzny Interfejs Użytkownika**: Intuicyjny interfejs użytkownika zapewnia łatwą nawigację i interakcję.
2. **Wybór Poziomu Trudności**: Możliwość wyboru między trzema poziomami trudności: Łatwy, Średni i Trudny.
3. **Zarządzanie Słowami**: Dodawanie nowych słów do bazy oraz wyświetlanie wszystkich słów z bazy.
4. **Statystyki Gry**: Śledzenie liczby prób, błędów, wygranych oraz średniej liczby prób na grę.

## Instrukcje Obsługi

### Wymagania

- Java Development Kit (JDK) 8 lub nowszy
- Środowisko programistyczne lub edytor tekstu (np. IntelliJ IDEA, Eclipse)

### Uruchomienie

1. Sklonuj repozytorium na swoje urządzenie.
2. Upewnij się, że plik `baza.txt` z listą słów znajduje się w katologu src.
3. Otwórz projekt w wybranym środowisku programistycznym.
4. Uruchom plik `HangmanGUI.java` jako aplikację Java plik znajduje się folderze game.

### Dodawanie Nowych Słów

1. Kliknij przycisk "Dodaj słowo".
2. Wprowadź nowe słowo do pola dialogowego i potwierdź.

### Wybór Poziomu Trudności

1. Kliknij jeden z przycisków: "Łatwy", "Średni" lub "Trudny".

### Wprowadzanie Liter/Słów

1. Wpisz literę lub całe słowo w pole tekstowe na dole okna.
2. Naciśnij klawisz Enter, aby potwierdzić.

### Wyświetlanie Słów z Bazy

1. Kliknij przycisk "Pokaż słowa".

### Zakończenie Gry

- Gra kończy się, gdy odgadniesz wszystkie litery w słowie lub wykorzystasz wszystkie próby.
- Po zakończeniu gry możesz rozpocząć nową grę, wybierając poziom trudności.

## Struktura Projektu

Projekt składa się z następujących plików:
- `HangmanGUI.java`: Główna klasa odpowiedzialna za interfejs użytkownika i logikę gry.
- `HangmanWordManager.java`: Klasa zarządzająca bazą słów.
- `HangmanGameStats.java`: Klasa odpowiedzialna za przechowywanie i wyświetlanie statystyk gry.
- `HangmanDifficultyListener.java`: Klasa obsługująca wybór poziomu trudności.

## Opis Klas i Funkcji

### HangmanGUI.java

#### Klasa

- **HangmanGUI**: Rozszerza `JFrame`. Implementuje graficzny interfejs użytkownika i logikę gry.

#### Pola

- `JPanel displayPanel`: Panel do wyświetlania słowa.
- `JTextField inputField`: Pole tekstowe do wprowadzania liter lub całych słów.
- `JButton easyButton`, `mediumButton`, `hardButton`: Przyciski do wyboru poziomu trudności.
- `JButton addButton`, `showWordsButton`: Przyciski do dodawania nowych słów i wyświetlania wszystkich słów.
- `JLabel statsLabel`: Etykieta do wyświetlania statystyk gry.
- `JLabel wordLengthLabel`: Etykieta do wyświetlania długości słowa.
- `HangmanWordManager wordManager`: Obiekt zarządzający słowami.
- `HangmanGameStats gameStats`: Obiekt zarządzający statystykami gry.
- `String secretWord`: Przechowuje aktualne tajne słowo.
- `int attempts`: Liczba dostępnych prób.
- `int errors`: Liczba błędów.
- `Set<Character> guessedLetters`: Zbiór odgadniętych liter.

#### Konstruktor

- `HangmanGUI()`: Inicjalizuje interfejs użytkownika, ładuje słowa z bazy, ustawia słuchaczy zdarzeń i rozpoczyna grę.

#### Metody

- `startGame(int maxAttempts)`: Rozpoczyna nową grę z określoną liczbą maksymalnych prób.
- `displayWord()`: Wyświetla aktualny stan tajnego słowa.
- `updateStats()`: Aktualizuje etykietę statystyk.
- `checkLetter(char letter)`: Sprawdza, czy wprowadzona litera znajduje się w tajnym słowie.
- `checkWord(String word)`: Sprawdza, czy wprowadzone słowo jest poprawne.
- `checkWinOrLoss()`: Sprawdza, czy gra została wygrana lub przegrana.
- `showLossImage()`: Wyświetla obrazek przegranej.
- `startNewGame()`: Rozpoczyna nową grę.
- `displayAllWords()`: Wyświetla wszystkie słowa z bazy w oknie dialogowym.

### HangmanWordManager.java

#### Klasa

- **HangmanWordManager**: Zarządza bazą słów używanych w grze.

#### Pola

- `ArrayList<String> words: Lista przechowująca słowa.
- 'String currentDirectory: informacje o lokalizacji folderu.
- 'String fileName: przechowuje informacje o lokalizacji baza.txt
#### Konstruktor

- `HangmanWordManager()`: Inicjalizuje pustą listę słów.

#### Metody

- `loadWords()`: Wczytuje słowa z pliku `baza.txt`.
- `saveWords()`: Zapisuje słowa do pliku `baza.txt`.
- `addWord(String word)`: Dodaje nowe słowo do listy i zapisuje je w pliku.
- `getRandomWord()`: Zwraca losowe słowo z listy.
- `getAllWords()`: Zwraca wszystkie słowa jako sformatowany string.

### HangmanGameStats.java

#### Klasa

- **HangmanGameStats**: Przechowuje i zarządza statystykami gry.

#### Pola

- `int totalAttempts`: Całkowita liczba prób.
- `int totalGames`: Całkowita liczba gier.
- `int wins`: Liczba wygranych gier.

#### Metody

- `incrementWins()`: Zwiększa liczbę wygranych gier.
- `incrementGames()`: Zwiększa liczbę rozegranych gier.
- `addAttempts(int attempts)`: Dodaje próby do całkowitej liczby prób.
- `getStats(int attempts, int errors)`: Zwraca statystyki gry w formacie HTML.

### HangmanDifficultyListener.java

#### Klasa

- **HangmanDifficultyListener**: Implementuje ActionListener do obsługi wyboru poziomu trudności.

#### Pola

- `int maxAttempts`: Maksymalna liczba prób dla danego poziomu trudności.
- `HangmanGUI gui`: Odniesienie do głównego interfejsu użytkownika.

#### Konstruktor

- `HangmanDifficultyListener(int maxAttempts, HangmanGUI gui)`: Inicjalizuje słuchacza z określoną liczbą maksymalnych prób i odniesieniem do GUI.

#### Metody

- `actionPerformed(ActionEvent e)`: Rozpoczyna nową grę z określoną liczbą prób po wybraniu poziomu trudności.

## Autorzy

Projekt został stworzony przez Bartłomieja Krawczyka nr albumu 58847. Wszelkie uwagi i sugestie są mile widziane!
