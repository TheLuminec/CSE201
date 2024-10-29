public class Player{
    private String username;
    private Room location;
    private Inventory inventory;
    
    public Player(String username, Room location, Inventory inventory) {
        this.username = username;
        this.location = location;
        this.inventory = inventory; 
    }
    
    public String getName() {
        return this.username;
    }
    public Room getRoom() {
        return this.location;
    }
    public void moveRoom(Room room) {
        this.location = room;
    }
    public Inventory getInventory() {
        return this.inventory;
    }
    public String getUserName() {
        return this.username;
    }
}
