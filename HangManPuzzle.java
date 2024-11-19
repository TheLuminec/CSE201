import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public final class HangManPuzzle extends Puzzle {
    String words = "Temporary Puzzles Are Cool";
    String concealWords = "";
    int guessCount = 6;

    public HangManPuzzle() {
        generateWords();
        concealWords();
    }
    

    @Override
    protected void generateWords() {
        try {
            try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
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
            if (input.length() == 1 && input.charAt(0) != ' ') {
                return (processChar(input.charAt(0)));
                // isValidInput = true;
            }
            System.out.println("Please enter a valid character. (Ex: 'b')");
        }
    }

    private boolean processChar(char ch) {
        char[] letters = concealWords.toCharArray();
        boolean letterFound = false;
        for (int i = 0; i < concealWords.length(); i++) {
            if (words.charAt(i) == ch) {
                letters[i] = ch;
                letterFound = true;
            }
            if (words.charAt(i) == Character.toUpperCase(ch)) {
                letters[i] = Character.toUpperCase(ch);
                letterFound = true;
            }
        }
        concealWords = new String(letters);
        return letterFound;
    }

}
