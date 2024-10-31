import java.util.ArrayList;
import java.util.Collection;

public class Option {
    final private ArrayList<String> flagsNeeded;
    final private ArrayList<String> flagsTriggered;
    private final String description;
    private final String result;
    private boolean isDone = false;
    private int turnCost;
    private Room toRoom;

    public Option(Collection<String> flagsNeeded, Collection<String> flagsTriggered, String description, String result, int turnCost, Room toRoom) {
        if(flagsNeeded != null)
            this.flagsNeeded = new ArrayList<>(flagsNeeded);
        else
            this.flagsNeeded = new ArrayList<>();

        if(flagsTriggered != null)
            this.flagsTriggered = new ArrayList<>(flagsTriggered);
        else
            this.flagsTriggered = new ArrayList<>();

        this.description = description;
        this.result = result;
        this.turnCost = turnCost;
        this.toRoom = toRoom;
    }

    public Option(Collection<String> flagsNeeded, Collection<String> flagsTriggered, String description, String result) {
        this(flagsNeeded, flagsTriggered, description, result, 1, null);
    }

    public ArrayList<String> triggerFlags() {
        isDone = true;
        return flagsTriggered;
    }

    public boolean getIsDone() {
        if(toRoom != null)
            return false;
        return isDone;
    }

    public ArrayList<String> getNeededFlags() {
        return flagsNeeded;
    }

    public String getResult() {
        return result;
    }

    public int getTurnCost() {
        return turnCost;
    }

    public Room getToRoom() {
        return toRoom;
    }

    @Override
    public String toString() {
        return description;
    }

}
