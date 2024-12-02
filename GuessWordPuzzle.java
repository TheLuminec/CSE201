
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.random.RandomGenerator;


/**
 * The class representing a word guessing puzzle.
 * Players will attempt to guess a random word chosen from a file.
 */
public final class GuessWordPuzzle extends Puzzle {

    // Default word
    String word = "spaghetti";

    /**
     * The constructor for a GuessWordPuzzle object.
     * @param descString The description of the puzzle.
     */
    public GuessWordPuzzle(String descString) {
        generateWords();
        description = descString;
    }

    @Override
    protected void generateWords() {
        try {
            try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
                for (int i = 0; i < RandomGenerator.getDefault().nextInt(187403); i++) {
                    fileScanner.nextLine();
                }
                word = fileScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Word file not found!\nUsing default word.");
        }
    }

    // Triggers the Word Guessing Puzzle.
    @Override
    public boolean triggerPuzzle() {
        Driver.getScanner().nextLine();
        System.out.println(getDescription());
        System.out.print("Guess the word I'm thinking of: ");
        if(Driver.getScanner().nextLine().equalsIgnoreCase(word)) {
            System.out.println("Correct!\nYou guessed " + word + " correctly!");
            return true;
        }
        System.out.println("Incorrect!\nYou guessed " + word + " incorrectly!");

        return false;
    }

}
