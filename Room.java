import java.util.ArrayList;
import java.util.Collection;

public class Room {
    private ArrayList<String> flags;
    private ArrayList<Option> options;
    private String roomName;
    private String roomEnterDescription;
    private ArrayList<Option> currentOptions;

    public Room(String roomName, String roomEnterDescription) {
        this.roomName = roomName;
        this.roomEnterDescription = roomEnterDescription;
        this.flags = new ArrayList<String>();
        this.options = new ArrayList<Option>();
        this.currentOptions = new ArrayList<Option>();
    }

    public boolean chooseOption(int choice, Player player) {
        if(choice < 1)
            return false;
        if(choice > options.size())
            return false;

        Option opt = currentOptions.get(choice-1);

        triggerFlag(opt.triggerFlags());
        System.out.println(opt.getResult());
        
        currentOptions.clear();
        player.incrementTurnCounter(opt.getTurnCost());
        if(opt.getToRoom() != null)
            player.moveRoom(opt.getToRoom());
            
        return true;
    }

    public void triggerFlag(String flag) {
        flags.add(flag);
    }

    public void triggerFlag(Collection<String> newFlags) {
        flags.addAll(newFlags);
    }

    public void addOption(Option opt) {
        options.add(opt);
    }

    public void removeOption(Option opt) {
        options.remove(opt);
    }

    public void printOptions() {
        int index = 1;
        currentOptions.clear();

        for(Option opt : options) {
            if(checkFlags(opt)) {
                currentOptions.add(index-1, opt);
                System.out.println("["+index+"]: " + opt);
                index++;
            }
        }
    }

    public String getName() {
        return roomName;
    }

    public void enterRoom() {
        System.out.println(roomEnterDescription);
    }

    private boolean checkFlags(Option opt) {
        if(opt.getIsDone())
            return false;
        for(String flag : opt.getNeededFlags()) {
            if(!flags.contains(flag)) {
                return false;
            }
        }
        return true;
    } 
}