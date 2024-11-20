import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.random.RandomGenerator;


public final class HangManPuzzle extends Puzzle {
    String words = "Temporary Puzzles Are Cool";
    String concealWords = "";
    int guessCount = 6;
    ArrayList<Character> guessedChars = new ArrayList<>();

    public HangManPuzzle() {
        generateWords();
        concealWords();
    }
    

    @Override
    protected void generateWords() {
        try {
            try (Scanner fileScanner = new Scanner(new File("wordss.txt"))) {
                for (int i = -1; i < RandomGenerator.getDefault().nextInt(187404); i++) {
                    fileScanner.nextLine();
                }
                words = fileScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Word file not found!\nUsing default word.");
        }
    }

    private void concealWords() {
        for (char ch : words.toCharArray()) {
            if (ch != ' ') {
                concealWords += "_";
            } else {
                concealWords += " ";
            }
        }
    }



    // Have user do the puzzle
    @Override
    public boolean triggerPuzzle() {
        System.out.println(Character.toUpperCase('s') == 'S');
        while(guessCount > 0) {
            if (concealWords.equals(words)) {
                System.out.println("You solved the puzzle.");
                return true;
            }
            System.out.printf("%d guesses remaining!\n%s\n", guessCount, concealWords);
            // Incorrect guess
            if (!guessChar()) {
                guessCount--;
            }
        }
        System.out.println("You failed the puzzle");
        return false;
    }

    // Helper method for filling in characters
    private boolean guessChar() {
        while(true) {
            System.out.println("Guess a letter: ");
            String input = Driver.getScanner().nextLine();
            if (isValidInput(input)) {
                return (processChar(input.charAt(0)));
                // isValidInput = true;
            }
        }
    }

    private boolean isValidInput(String input) {
        if (!(input.length() == 1 && input.charAt(0) != ' ')) {
            System.out.println("Please enter a valid character. (Ex: 'b')");
            return false;
        } else if (guessedChars.contains(input.charAt(0))) {
            System.out.println("You've already guessed this character!");
            return false;
        }
        return true;
    }

    private boolean processChar(char ch) {
        char[] letters = concealWords.toCharArray();
        boolean letterFound = false;
        guessedChars.add(ch);
        for (int i = 0; i < concealWords.length(); i++) {
            if (words.charAt(i) == ch || Character.toUpperCase(words.charAt(i)) == ch || words.charAt(i) == Character.toUpperCase(ch)) {
                letters[i] = words.charAt(i);
                letterFound = true;
            }
        }
        concealWords = new String(letters);
        return letterFound;
    }

}
