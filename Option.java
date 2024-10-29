import java.util.ArrayList;

public class Option {
    final private ArrayList<String> flagsNeeded;
    final private ArrayList<String> flagsTriggered;
    private final String description;
    private boolean isDone = false;

    public Option(ArrayList<String> flagsNeeded, ArrayList<String> flagsTriggered, String description) {
        this.flagsNeeded = flagsNeeded;
        this.flagsTriggered = flagsTriggered;
        this.description = description;
    }

    public ArrayList<String> triggerFlags() {
        isDone = true;
        return flagsTriggered;
    }

    public boolean getIsDone(ArrayList<String> flags) {
        return isDone;
    }

    public ArrayList<String> getNeededFlags() {
        return flagsNeeded;
    }

    @Override
    public String toString() {
        return description;
    }

}
