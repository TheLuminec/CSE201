/**
 * Represents the player in the game.
 * Manages player's name, location, inventory, and turn count.
 */
public class Player extends Inventory {
    private String username;
    private Room location;
    private int turnCounter = 0;
    private final int MAX_TURNS;

    /**
     * Constructs a new Player object.
     *
     * @param username   The player's name.
     * @param turnCount  The maximum number of turns allowed.
     * @param location   The starting room.
     */
    public Player(String username, int turnCount, Room location) {
        super();
        this.username = username;
        this.location = location;
        this.MAX_TURNS = turnCount;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's username.
     */
    public String getName() {
        return this.username;
    }

    /**
     * Gets the player's current room.
     *
     * @return The room the player is currently in.
     */
    public Room getRoom() {
        return this.location;
    }

    /**
     * Moves the player to a new room.
     *
     * @param room The room to move to.
     */
    public void moveRoom(Room room) {
        if (room != null) {
            this.location = room;
            room.enterRoom();
        }
    }

    /**
     * Checks if the player has run out of turns.
     *
     * @return True if the turn counter has reached the maximum turns, false otherwise.
     */
    public boolean timeIsUp() {
        return turnCounter >= MAX_TURNS;
    }

    /**
     * Increments the player's turn counter.
     *
     * @param cost The number of turns to add.
     */
    public void incrementTurnCounter(int cost) {
        turnCounter += cost;
    }
}
