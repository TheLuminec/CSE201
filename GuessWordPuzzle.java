
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.random.RandomGenerator;



public final class GuessWordPuzzle extends Puzzle {
    // Default word
    String word = "spaghetti";

    public GuessWordPuzzle(String deString) {
        generateWords();
        // Filler description
        description = deString;
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