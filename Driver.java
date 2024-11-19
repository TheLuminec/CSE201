import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main driver class for the text adventure game.
 * It initializes the game, handles user input, and runs the game loop.
 */
public class Driver {
    private static ArrayList<Room> rooms;
    private static Player player;
    private static Room startRoom = null;
    private static Scanner input;

     /**
     * The main method to start the game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        input = new Scanner(System.in);
        System.out.println("Enter your Name!");
        String userName = input.nextLine();

        //makeStory();
        testStory();

        player = new Player(userName, 30, startRoom);

        run();

        input.close();
    }

    /**
     * Runs the main game loop, processing player input and game state.
     */
    public static void run() {
        player.getRoom().enterRoom();

        while(true) {
            Room currRoom = player.getRoom();
            if(player.timeIsUp()) {
                System.out.println("Game Over!");
                break;
            }

            currRoom.printOptions(player);
            int choice = 0;
            try{
                choice = input.nextInt();
            } catch(Exception e) {
                System.out.println("Invalid Option! Please enter a number.");
                continue;
            }
            
            if(currRoom.chooseOption(choice, player)) {
                if(currRoom.hasFlag("gameEnd")) {
                    break;
                }
            }
            else {
                System.out.println("Invalid Option!");
                continue;
            }

        }

        if(player.getScore() > 0.1)
            System.out.println("Congratulations " + player.getName() + " your score is: " + Math.round(player.getScore() * 100.0) / 100.0 + "!");
        else
            System.out.println("You lost " + player.getName() + "!");
    }

    private static void testStory() {
        rooms = new ArrayList<>();
        Room testroom = new Room("Testroom", "This is an example test room");
        rooms.add(testroom);
        startRoom = testroom;

        testroom.addOption(new Option("Take item", "you took the item", 5));
        testroom.lastSetItems("item1");
        testroom.lastSetFlags("this is a flag");

        testroom.addOption(new Option("Use item", "you used the item"));
        testroom.lastNeedItems("item1");
        testroom.lastSetFlags("gameEnd");

    }

    /**
     * Sets up the game story by creating rooms and their respective options.
     */
    private static void makeStory() {//example
        rooms = new ArrayList<>();
        Room bedroom = new Room("Bedroom", 
        "You wake up on your spaceship to a blaring alarm repeating
        \“the air purification system is damaged. Proceed to the purification room to do further diagnostics.\”.   
        You look around the room and see [1] a door straight ahead,  [2] a closet to the right, [3] an alarm clock to the left, 
        and [4] an old missile on the wall");
        bedroom.addOption(new Option("You inspect the missile and notice that it is still live and functional, would you like to take it?"
        , "You take the missile somehow carrying it."));
        



        Room closet = new Room("Closet", "closet (hammer)");
        Room hallway = new Room("Hallway", "hallway stuff");
        Room storage = new Room("Storage", "storage stuff");
        Room purifier = new Room("Purifier", "purifier stuff");
        Room controlRoom = new Room("Control Room", "control stuff");

        rooms.add(bedroom);
        rooms.add(closet);
        rooms.add(hallway);
        rooms.add(storage);
        rooms.add(purifier);
        rooms.add(controlRoom);
        startRoom = bedroom;


    }

    static Scanner getScanner() {
        return input;
    }
}