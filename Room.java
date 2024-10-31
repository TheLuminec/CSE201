import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a room in the game.
 * Contains options, flags, and methods to interact with the room.
 */
public class Room {
    private ArrayList<String> flags;
    private ArrayList<Option> options;
    private String roomName;
    private String roomEnterDescription;
    private ArrayList<Option> currentOptions;

    /**
     * Constructs a new Room object.
     *
     * @param roomName             The name of the room.
     * @param roomEnterDescription The description displayed when entering the room.
     */
    public Room(String roomName, String roomEnterDescription) {
        this.roomName = roomName;
        this.roomEnterDescription = roomEnterDescription;
        this.flags = new ArrayList<>();
        this.options = new ArrayList<>();
        this.currentOptions = new ArrayList<>();
    }

    /**
     * Processes the player's choice and updates the game state.
     *
     * @param choice The player's choice index.
     * @param player The player object.
     * @return True if the choice is valid and processed, false otherwise.
     */
    public boolean chooseOption(int choice, Player player) {
        if (choice < 1 || choice > currentOptions.size())
            return false;

        Option opt = currentOptions.get(choice - 1);

        triggerFlag(opt.triggerFlags());
        System.out.println(opt.getResult());

        currentOptions.clear();
        player.incrementTurnCounter(opt.getTurnCost());
        if (opt.getToRoom() != null)
            player.moveRoom(opt.getToRoom());

        return true;
    }

    /**
     * Adds a flag to the room's flag list.
     *
     * @param flag The flag to add.
     */
    public void triggerFlag(String flag) {
        flags.add(flag);
    }

    /**
     * Adds multiple flags to the room's flag list.
     *
     * @param newFlags A collection of flags to add.
     */
    public void triggerFlag(Collection<String> newFlags) {
        flags.addAll(newFlags);
    }

    /**
     * Adds an option to the room.
     *
     * @param opt The option to add.
     */
    public void addOption(Option opt) {
        options.add(opt);
    }

    /**
     * Removes an option from the room.
     *
     * @param opt The option to remove.
     */
    public void removeOption(Option opt) {
        options.remove(opt);
    }

    /**
     * Prints the available options to the player.
     */
    public void printOptions() {
        int index = 1;
        currentOptions.clear();

        for (Option opt : options) {
            if (checkFlags(opt)) {
                currentOptions.add(opt);
                System.out.println("[" + index + "]: " + opt);
                index++;
            }
        }
    }

    /**
     * Gets the name of the room.
     *
     * @return The room's name.
     */
    public String getName() {
        return roomName;
    }

    /**
     * Displays the room's enter description.
     */
    public void enterRoom() {
        System.out.println(getName() + ": " + roomEnterDescription);
    }

    /**
     * Checks if the room has a specific flag.
     *
     * @param flag The flag to check for.
     * @return True if the flag exists, false otherwise.
     */
    public boolean hasFlag(String flag) {
        return flags.contains(flag);
    }

    /**
     * Checks if an option is available based on its required flags.
     *
     * @param opt The option to check.
     * @return True if the option can be shown, false otherwise.
     */
    private boolean checkFlags(Option opt) {
        if (opt.getIsDone())
            return false;
        for (String flag : opt.getNeededFlags()) {
            if (!flags.contains(flag)) {
                return false;
            }
        }
        return true;
    }
}
