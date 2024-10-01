import java.util.HashSet;
import java.util.Set;

public class Player {
    private Set<String> inventory = new HashSet<>();

    public void addItem(String item) {
        inventory.add(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }
}
