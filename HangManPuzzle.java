import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.random.RandomGenerator;

/**
 * The class representing a standard Hangman-style Puzzle.
 * Players will attempt to guess letters from a given phrase,
 * and are allows up to 6 failed attempts.
 */
public final class HangManPuzzle extends Puzzle {

    // The default words for users to guess.
    String words = "Temporary Puzzles Are Cool";

    // The concealed version of the words that the user sees.  
    String concealWords = "";

    // The amount of guesses the player has, starts at 6.
    int guessCount = 6;

    // An ArrayList of already guessed characters by the user.
    ArrayList<Character> guessedChars = new ArrayList<>();

    /**
     * Constructor for a HangManPuzzle object.
     * @param descString The description of the puzzle.
     */
    public HangManPuzzle(String descString) {
        generateWords();
        concealWords();
        description = descString;
    }
    

    @Override
    protected void generateWords() {
        try {
            try (Scanner fileScanner = new Scanner(new File("hangman.txt"))) {
                for (int i = 0; i < RandomGenerator.getDefault().nextInt(98); i++) {
                    fileScanner.nextLine();
                }
                words = fileScanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Word file not found!\nUsing default word.");
        }
    }

    /**
     * Helper method to make a concealed version of the puzzle's phrase.
     */
    private void concealWords() {
        for (char ch : words.toCharArray()) {
            if (ch != ' ') {
                concealWords += "_";
            } else {
                concealWords += " ";
            }
        }
    }



    // Triggeres the Hangman Puzzle.
    @Override
    public boolean triggerPuzzle() {
        System.out.println(description);
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

    /**
     * Helper method for the user guessing characters  
     * @return True if and only if the user correctly guesses a character.
     */
    private boolean guessChar() {
        while(true) {
            System.out.println("Guess a letter: ");
            String input = Driver.getScanner().nextLine();
            if (isValidInput(input)) {
                return (processChar(input.charAt(0)));
            }
        }
    }

    /**
     * Helper method for filtering user inputs.
     * @param input The user input.
     * @return True if and only if the input is a character and not guessed yet.
     */
    private boolean isValidInput(String input) {
        if (!(input.length() == 1 && input.charAt(0) != ' ')) {
            System.out.println("Please enter a valid character. (Ex: 'b')");
            return false;
        } else if (guessedChars.contains(Character.toLowerCase(input.charAt(0))) 
            || guessedChars.contains(Character.toUpperCase(input.charAt(0)))) {
            System.out.println("You've already guessed this character!");
            return false;
        }
        return true;
    }

    /**
     * Helper method to process the guessed character.
     * This method will search for the character in the phrase,
     * and then it will reveal all instances of it in the concealedWords.
     * @param ch The character to process.
     * @return True if the character is found in the phrase, false otherwise.
     */
    private boolean processChar(char ch) {
        char[] letters = concealWords.toCharArray();
        boolean letterFound = false;
        guessedChars.add(ch);
        for (int i = 0; i < concealWords.length(); i++) {
            if (Character.toUpperCase(words.charAt(i)) == Character.toUpperCase(ch)) {
                letters[i] = words.charAt(i);
                letterFound = true;
            }
        }
        concealWords = new String(letters);
        return letterFound;
    }

}
