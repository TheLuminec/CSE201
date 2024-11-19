
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.random.RandomGenerator;



public final class GuessWordPuzzle extends Puzzle {
    // Default word
    String word = "spaghetti";

    public GuessWordPuzzle() {
        generateWords();
    }

    private void generateDescription() {
        
    }

    @Override
    protected void generateWords() {
        try {
            try (Scanner fileScanner = new Scanner(new File("words.txt"))) {
                for (int i = -1; i < RandomGenerator.getDefault().nextInt(187404); i++) {
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
        System.out.println(getDescription());
        System.out.print("Input: ");
        if(Driver.getScanner().nextLine().equals(word)) {
            System.out.println("Correct!\nYou guessed " + word + " correctly!");
            return true;
        }
        System.out.println("Incorrect!\nYou guessed " + word + " incorrectly!");

        return false;
    }



}