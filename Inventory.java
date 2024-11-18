import java.util.ArrayList;

public abstract class Inventory {
    protected ArrayList<String> items;

    public Inventory() {
        items = new ArrayList<String>();
    }

    //Checks if the inventory contains an given item
    public boolean contains(String item) {
        return items.contains(item);
    }

    //Checks if the inventory contains all the given items
    public boolean containsAll(ArrayList<String> items) {
        return items.containsAll(items);
    }

    //Clears all items from the inventory
    public void clearItems() {
        items.clear();
    }

    //Returns the current items in the inventory
    public ArrayList<String> getItems() {
        return items;
    }

    //Adds the given items to the inventory
    public void addItems(ArrayList<String> items) {
        for(String item : items) {
            addItem(item);
        }
    }

    //Removes the given items from the inventory
    public void removeItems(ArrayList<String> items) {
        for(String item : items) {
            removeItem(item);
        }
    }

    //Adds the given item to the inventory
    public void addItem(String item) {
        items.add(item);
    }

    //Removes the given item from the inventory
    public void removeItem(String item) {
        items.remove(item);
    }

}
