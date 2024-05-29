##Gra w Wisielca

## Spis treści
1. Opis projektu
2. Instrukcja obsługi
3. Klasa `HangmanGUI`
4. Klasa `HangmanDifficultyListener`
5. Klasa `HangmanGameStats`
6. Klasa `HangmanWordManager`


## Opis projektu
Projekt zaliczeniowy polega na stworzeniu gry w wisielca w formie interfejsu graficznego. Gracz ma za zadanie odgadnąć ukryte słowo, wpisując litery lub całe słowo. Projekt wykorzystuje biblioteki Swing do budowy interfejsu graficznego.

## Instrukcja obsługi
1. Uruchomienie aplikacji:
   - Pobierz repozytorium z kodem źródłowym aplikacji z platformy Github.
   - Skompiluj i uruchom projekt w środowisku Java, np. za pomocą IDE.
2. Dodawanie nowego słowa:
   - Kliknij przycisk "Dodaj słowo".
   - Wprowadź nowe słowo w wyświetlonym oknie dialogowym.
3. Wyświetlanie bazy słów:
   - Kliknij przycisk "Wyświetl słowa z bazy".
   - Zostanie wyświetlone okno dialogowe z listą wszystkich słów w bazie.
4. Rozpoczęcie gry:
   - Wybierz poziom trudności (łatwy, średni, trudny).
   - Gra rozpocznie się po wybraniu poziomu trudności.
5. Odgadywanie słowa:
   - Wpisz literę lub całe słowo w pole tekstowe i naciśnij Enter.
   - W przypadku wprowadzenia litery, gra sprawdzi, czy litera występuje w słowie.
   - W przypadku wprowadzenia całego słowa, gra sprawdzi, czy jest ono zgodne ze szukanym.
6. Zakończenie gry:
   - Gra zakończy się po wygranej (odgadnięciu słowa) lub przegranej (przekroczeniu limitu prób).
   - Wyświetlone zostaną statystyki gry oraz inform

## Klasa `HangmanGUI`
#### Konstruktor:
- `HangmanGUI()`: Konstruktor bezargumentowy inicjuje interfejs graficzny gry.

#### Zmienne:
- `displayPanel`: JPanel wyświetlający planszę gry.
- `buttonPanel`: JPanel zawierający przyciski sterujące grą.
- `inputField`: JTextField do wprowadzania liter lub słów.
- `easyButton`, `mediumButton`, `hardButton`: Przyciski do wyboru poziomu trudności.
- `addButton`: Przycisk do dodawania nowego słowa do bazy.
- `showWordsButton`: Przycisk do wyświetlania bazy słów.
- `statsLabel`: JLabel wyświetlający statystyki gry.
- `wordManager`: Obiekt zarządzający słowami w grze.
- `gameStats`: Obiekt przechowujący statystyki gry.
- `secretWord`: String przechowujący ukryte słowo.
- `attempts`: Int przechowujący liczbę prób.
- `errors`: Int przechowujący liczbę błędów.
- `inputAttempts`: Int przechowujący liczbę prób w danej sesji.
- `guessedLetters`: Zbiór przechowujący już zgadywane litery.
- `originalAttempts`: Int przechowujący pierwotną liczbę prób.

#### Metody:
- `startGame(int maxAttempts)`: Rozpoczyna grę z określoną ilością prób.
- `displayWord()`: Wyświetla słowo do odgadnięcia.
- `updateStats()`: Aktualizuje statystyki gry.
- `checkLetter(char letter)`: Sprawdza wprowadzoną literę.
- `checkWord(String word)`: Sprawdza wprowadzone słowo.
- `checkWinOrLoss()`: Sprawdza, czy gra została wygrana lub przegrana.
- `showLossImage()`: Wyświetla obrazek w przypadku przegranej.
- `startNewGame()`: Rozpoczyna nową grę.
- `displayAllWords()`: Wyświetla bazę danych słów.

## Klasa `HangmanDifficultyListener`
#### Konstruktor:
- `HangmanDifficultyListener(int maxAttempts, HangmanGUI gui)`: Inicjuje obiekt określający poziom trudności.

#### Metody:
- `actionPerformed(ActionEvent e)`: Obsługuje zdarzenie akcji, rozpoczynając grę.

## Klasa `HangmanGameStats`
#### Zmienne:
- `totalAttemptsToWin`: Int przechowujący łączną liczbę prób do wygranej.
- `totalGames`: Int przechowujący łączną liczbę gier.
- `wins`: Int przechowujący liczbę wygranych.

#### Metody:
- `incrementWins()`: Inkrementuje liczbę wygranych.
- `incrementGames()`: Inkrementuje liczbę gier.
- `addAttemptsToWin(int attempts)`: Dodaje liczbę prób do wygranych.
- `getStats(int attempts, int errors)`: Zwraca statystyki gry w formacie HTML.

## Klasa `HangmanWordManager`
#### Zmienne:
- `words`: ArrayList przechowujący słowa.
- `currentDirectory`: String przechowujący ścieżkę do folderu źródłowego.
- `fileName`: String przechowujący ścieżkę do pliku z bazą słów.

#### Metody:
- `loadWords()`: Wczytuje słowa z pliku.
- `saveWords()`: Zapisuje słowa do pliku.
- `addWord(String word)`: Dodaje nowe słowo do listy.
- `getRandomWord()`: Zwraca losowe słowo z listy.
- `getAllWords()`: Zwraca wszystkie słowa z listy.

Autor Bartłomiej Krawczyk nr albumu 58847
