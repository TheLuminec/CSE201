public class Player{
    private String username;
    private Room location;
    private Inventory inventory;
    private int turnCounter = 0;
    private final int MAX_TURNS;
    
    public Player(String username, int turnCount, Room location, Inventory inventory) {
        this.username = username;
        this.location = location;
        this.inventory = inventory; 
        this.MAX_TURNS = turnCount;
    }
    
    public String getName() {
        return this.username;
    }
    public Room getRoom() {
        return this.location;
    }
    public void moveRoom(Room room) {
        if(room != null) {
            this.location = room;
            room.enterRoom();
        }
    }
    public Inventory getInventory() {
        return this.inventory;
    }
    public String getUserName() {
        return this.username;
    }

    public boolean timeIsUp() {
        return turnCounter >= MAX_TURNS;
    }

    public void incrementTurnCounter(int cost) {
        turnCounter += cost;
    }
}
