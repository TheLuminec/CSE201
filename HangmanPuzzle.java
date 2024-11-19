import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.random.RandomGenerator;

public final class HangManPuzzle extends Puzzle {
    String words = "Temporary Puzzles Are Cool";
    String concealWords = "";

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

        return false;
    }

    // Helper method for filling in characters
    private void guessChar() {
        boolean isValidInput = false;
        while(!isValidInput) {
            String input = Driver.getScanner().nextLine();
            if (input.length() == 1 && input.charAt(0) != ' ') {
                processChar(input.charAt(0));
            }
        }
    }

    private void processChar(char ch) {

    }

}
