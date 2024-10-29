import java.util.ArrayList;

public class Room {
    private ArrayList<String> flags;
    private ArrayList<Option> options;
    private String roomName;
    private String roomEnterDescription;

    public Room(String roomName, String roomEnterDescription) {
        this.roomName = roomName;
        this.roomEnterDescription = roomEnterDescription;
        this.flags = new ArrayList<String>();
        this.options = new ArrayList<Option>();
    }

    public boolean chooseOption(int choose) {
        return false;
    }

    public void triggerFlag(String flag) {
        flags.add(flag);
    }

    public void addOption(Option opt) {
        options.add(opt);
    }

    public void removeOption(Option opt) {
        options.remove(opt);
    }

    public void printOptions() {
        int index = 1;
        for(Option opt : options) {
            if(checkFlags(opt)) {
                System.out.println("["+index+"]: " + opt);
                index++;
            }
        }
    }

    public void enterRoom() {
        System.out.println(roomEnterDescription);
        printOptions();
    }

    private boolean checkFlags(Option opt) {
        for(String flag : opt.getNeededFlags()) {
            if(!flags.contains(flag)) {
                return false;
            }
        }
        return true;
    } 
}