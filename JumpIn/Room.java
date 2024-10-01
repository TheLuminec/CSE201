import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private List<Description> descriptions = new ArrayList<>();
    private List<Option> options = new ArrayList<>();

    public Room(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void addDescription(Description description) {
        descriptions.add(description);
    }

    public void addOption(Option option) {
        options.add(option);
    }
}
