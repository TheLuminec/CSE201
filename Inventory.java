import java.util.ArrayList;

public abstract class Inventory {
    protected ArrayList<String> items;

    public boolean contains(String item) {
        return items.contains(item);
    }

    public boolean containsAll(ArrayList<String> items) {
        return items.containsAll(items);
    }

    public void clearItems() {
        items.clear();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void addItems(ArrayList<String> items) {
        this.items.addAll(items);
    }

    public void removeItems(ArrayList<String> items) {
        this.items.removeAll(items);
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

}
