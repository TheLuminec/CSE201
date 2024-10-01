import java.util.List;

public class Option {
    private String text;
    private List<String> conditions;
    private List<String> actions;
    private String nextRoomId;
    private int timeCost = 1;

    public String getText() {
        return text;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public List<String> getActions() {
        return actions;
    }

    public String getNextRoomId() {
        return nextRoomId;
    }

    public int getTimeCost() {
        return timeCost;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public void setNextRoomId(String nextRoomId) {
        this.nextRoomId = nextRoomId;
    }

    public void setTimeCost(int timeCost) {
        this.timeCost = timeCost;
    }
}
