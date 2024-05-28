package hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class HangmanWordManager {
    private ArrayList<String> words;

    public HangmanWordManager() {
        words = new ArrayList<>();
    }

    public void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("baza.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("baza.txt"))) {
            for (String word : words) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord(String word) {
        words.add(word);
        saveWords();
    }

    public String getRandomWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public String getAllWords() {
        StringBuilder wordList = new StringBuilder("Słowa w bazie:\n");
        for (String word : words) {
            wordList.append(word).append("\n");
        }
        return wordList.toString();
    }
}
