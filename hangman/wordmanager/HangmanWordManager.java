package hangman.wordmanager;

// Importujemy potrzebne biblioteki

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

// Klasa HangmanWordManager zarządza listą słów w grze

public class HangmanWordManager {//Utworzenie klasy HangmanWordManager

    private ArrayList<String> words = new ArrayList<>(); // Lista przechowująca słowa

    // Zmienna przechowująca ścieżkę do folderu src
    public String currentDirectory = System.getProperty("user.dir");

    // Zmienna przechowująca ścieżkę do pliku Baza.txt
    private String fileName = currentDirectory + File.separator + "\Baza.txt"; //Zmienna definiująca scieżkę do Baza.txt

    // Metoda wczytująca słowa z pliku
    public void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Baza wczytana "+fileName);
            while ((line = reader.readLine()) != null) {
                words.add(line.trim()); // Dodawanie słów do listy
            }
        } catch (IOException e) {
            e.printStackTrace(); // Obsługa wyjątków
            System.out.println("Baza nie wczytana");
        }
    }

    // Metoda zapisująca słowa do pliku
    public void saveWords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String word : words) {
                writer.write(word);
                writer.newLine(); // Nowa linia po każdym słowie
            }
        } catch (IOException e) {
            e.printStackTrace(); // Obsługa wyjątków
        }
    }

    // Metoda dodająca nowe słowo do listy i zapisująca listę do pliku
    public void addWord(String word) {
        words.add(word);
        saveWords();
    }

    // Metoda zwracająca losowe słowo z listy
    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    // Metoda zwracająca wszystkie słowa z listy w postaci jednego ciągu znaków
    public String getAllWords() {
        StringBuilder wordList = new StringBuilder("Słowa w bazie:\n");
        for (String word : words) {
            wordList.append(word).append("\n");
        }
        return wordList.toString();
    }
}
