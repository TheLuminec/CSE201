import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents an actionable option within a room.
 * Options may have prerequisites and can trigger certain game flags.
 */
public class Option {
    private final ArrayList<String> flagsNeeded;
    private final ArrayList<String> flagsTriggered;
    private final String description;
    private final String result;
    private boolean isDone = false;
    private int turnCost;
    private Room toRoom;

    /**
     * Constructs an Option with specified parameters.
     *
     * @param flagsNeeded    Flags required for this option to be available.
     * @param flagsTriggered Flags that will be triggered upon choosing this option.
     * @param description    The description of the option.
     * @param result         The result text displayed after choosing the option.
     * @param turnCost       The number of turns this option consumes.
     * @param toRoom         The room to move to after choosing this option (if any).
     */
    public Option(Collection<String> flagsNeeded, Collection<String> flagsTriggered,
                  String description, String result, int turnCost, Room toRoom) {
        if (flagsNeeded != null)
            this.flagsNeeded = new ArrayList<>(flagsNeeded);
        else
            this.flagsNeeded = new ArrayList<>();

        if (flagsTriggered != null)
            this.flagsTriggered = new ArrayList<>(flagsTriggered);
        else
            this.flagsTriggered = new ArrayList<>();

        this.description = description;
        this.result = result;
        this.turnCost = turnCost;
        this.toRoom = toRoom;
    }

    /**
     * Constructs an Option with default turn cost and no room change.
     *
     * @param flagsNeeded    Flags required for this option to be available.
     * @param flagsTriggered Flags that will be triggered upon choosing this option.
     * @param description    The description of the option.
     * @param result         The result text displayed after choosing the option.
     */
    public Option(Collection<String> flagsNeeded, Collection<String> flagsTriggered,
                  String description, String result) {
        this(flagsNeeded, flagsTriggered, description, result, 1, null);
    }

    /**
     * Constructs an Option that leads to another room.
     *
     * @param flagsNeeded    Flags required for this option to be available.
     * @param flagsTriggered Flags that will be triggered upon choosing this option.
     * @param description    The description of the option.
     * @param result         The result text displayed after choosing the option.
     * @param room           The room to move to after choosing this option.
     */
    public Option(Collection<String> flagsNeeded, Collection<String> flagsTriggered,
                  String description, String result, Room room) {
        this(flagsNeeded, flagsTriggered, description, result, 1, room);
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

    @Override
    public String toString() {
        return description;
    }
}
