/**
 * Abstract class representing a generic puzzle.
 * This class provides the structure for creating different types of puzzles.
 * This class allows for all puzzles to be triggerable and
 * have a gettable description.
 */
public abstract class Puzzle {
    protected String description;
    
    /**
     * This method is used to get the description of a puzzle.
     * @return The description of the puzzle.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used by a puzzle to generate a word for
     * its related puzzle.
     */
    protected abstract void generateWords();
    
    /**
     * This method is used to trigger the user interaction
     * portion of the puzzle.
     * @return True if the puzzle is successfully completed, false otherwise.
     */
    public abstract boolean triggerPuzzle();
}