import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents an actionable option within a room.
 * Options may have prerequisites and can trigger certain game flags.
 */
public class Option {
    private ArrayList<String> flagsNeeded;
    private ArrayList<String> flagsTriggered;
    private ArrayList<String> itemsNeeded;
    private ArrayList<String> items;
    private final String description;
    private final String result;
    private boolean isDone = false;
    private int turnCost;
    private Room toRoom;
    private Puzzle puzzle;

    /**
     * Default constructor for a basic option
     * @param description the description of the option
     * @param result    the result of the option
     */
    public Option(String description, String result) {
        this.description = description;
        this.result = result;
        this.flagsNeeded = new ArrayList<String>();
        this.flagsTriggered = new ArrayList<String>();
        this.itemsNeeded = new ArrayList<String>();
        this.items = new ArrayList<String>();
        this.turnCost = 1;
        this.toRoom = null;
        this.isDone = false;
    }

    /**
     * Constructor for turn cost requirements
     * @param description the description of the option
     * @param result the result of the option
     * @param turnCost the turn cost of the option
     */
    public Option(String description, String result, int turnCost) {
        this(description, result);
        this.turnCost = turnCost;
    }

    /**
     * Constructor for room transition requirements
     * @param description the description of the option
     * @param result the result of the option
     * @param turnCost the turn cost of the option
     * @param room the room to move to after choosing this option
     */
    public Option(String description, String result, int turnCost, Room room) {
        this(description, result, turnCost);
        this.toRoom = room;
    }

    /**
     * Constructor for Puzzle requirements
     * @param description the description of the option
     * @param result the result of the option
     * @param turnCost the turn cost of the option
     * @param room the room to move to after choosing this option
     */
    public Option(String description, String result, int turnCost, Puzzle puzzle) {
        this(description, result, turnCost);
        this.puzzle = puzzle;
    }
    
    /**
     * Triggers the puzzle associated with this option.
     * 
     * @return True if solved, false if no puzzle or failed puzzle.
     */
    public boolean triggerPuzzle() {
        if (puzzle == null) {
            return true; 
        } else {
            return puzzle.triggerPuzzle();
        }
    }
    /**
     * Triggers the flags associated with this option.
     *
     * @return The list of flags that were triggered.
     */
    public ArrayList<String> triggerFlags() {
        isDone = true;
        return flagsTriggered;
    }

    /**
     * Checks if this option has already been completed.
     *
     * @return True if the option is done, false otherwise.
     */
    public boolean getIsDone() {
        if (toRoom != null)
            return false;
        return isDone;
    }

    /**
     * Gets the flags required to make this option available.
     *
     * @return A list of required flags.
     */
    public ArrayList<String> getNeededFlags() {
        return flagsNeeded;
    }

    /**
     * Gets the items required to make this option available.
     *
     * @return A list of required items.
     */
    public ArrayList<String> getNeededItems() {
        return itemsNeeded;
    }

    /**
     * Gets the result text to display after choosing this option.
     *
     * @return The result string.
     */
    public String getResult() {
        return result;
    }

    /**
     * Gets the turn cost of this option.
     *
     * @return The number of turns this option consumes.
     */
    public int getTurnCost() {
        return turnCost;
    }

    /**
     * Gets the room to move to after choosing this option.
     *
     * @return The room object, or null if there is no room change.
     */
    public Room getToRoom() {
        return toRoom;
    }

    /**
     * Gets the items the option gives the player
     * @return The items the option gives the player
     */
    public ArrayList<String> getItems() {
        return items;
    }

    /**
     * Adds new flags to the triggered flags
     * @param flags The flags to add
     */
    public void addTriggeredFlags(Collection<String> flags) {
        flagsTriggered.addAll(flags);
    }
    
    /**
     * Adds new items to the triggered items
     * @param items The items to add
     */
    public void addItems(Collection<String> items) {
        this.items.addAll(items);
    }

    /**
     * Adds the new items to the needed items
     * @param items The items to add
     */
    public void addNeededItems(Collection<String> items) {
        itemsNeeded.addAll(items);
    }

    /**
     * Adds the new flags to the needed flags
     * @param flags The flags to add
     */
    public void addNeededFlags(Collection<String> flags) {
        flagsNeeded.addAll(flags);
    }

    @Override
    public String toString() {
        return description;
    }
}
