import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        makeStory();

        player = new Player(userName, 30, startRoom, null);

        run();

        input.close();
    }

    /**
     * Runs the main game loop, processing player input and game state.
     */
    public static void run() {
        player.getRoom().enterRoom();

        while (true) {
            Room currRoom = player.getRoom();
            if (player.timeIsUp()) {
                System.out.println("Game Over!");
                break;
            }

            currRoom.printOptions();
            int choice = 0;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Option! Please enter a number.");
                input.nextLine(); // Clear invalid input
                continue;
            }

            if (currRoom.chooseOption(choice, player)) {
                // Check for game end condition
                if (currRoom.hasFlag("gameEnd")) {
                    break;
                }
            } else {
                System.out.println("Invalid Option!");
                continue;
            }
        }
    }

    /**
     * Sets up the game story by creating rooms and their respective options.
     */
    private static void makeStory() {
        rooms = new ArrayList<>();
        Room bedroom = new Room("Bedroom", "There are things in here, bedroom.");
        Room closet = new Room("Closet", "Closet (hammer)");
        Room hallway = new Room("Hallway", "Hallway stuff");
        Room storage = new Room("Storage", "Storage stuff");
        Room purifier = new Room("Purifier", "Purifier stuff");
        Room controlRoom = new Room("Control Room", "Control stuff");

        rooms.add(bedroom);
        rooms.add(closet);
        rooms.add(hallway);
        rooms.add(storage);
        rooms.add(purifier);
        rooms.add(controlRoom);

        bedroomStory(bedroom);
        closetStory(closet);
        hallwayStory(hallway);
        purifierStory(purifier);

        startRoom = bedroom;
    }

    /**
     * Defines the options and flags for the bedroom.
     *
     * @param bedroom The bedroom room object.
     */
    private static void bedroomStory(Room bedroom) {
        bedroom.addOption(new Option(null, flags("hasMissile"), "missile: option 1", "can pick up missile"));
        bedroom.addOption(new Option(null, null, "closet: option 2", "go to closet"));
        bedroom.addOption(new Option(null, flags("alarmVisit"), "clock: option 3", "go to clock"));
        bedroom.addOption(new Option(null, null, "leave: option 4", "leave to hallway"));
        bedroom.addOption(new Option(
                flags("alarmVisit", "hasHammer"),
                flags("alarmbreak"),
                "clock: option 2",
                "go to clock",
                rooms.get(1)
        ));
    }

    /**
     * Defines the options and flags for the closet.
     *
     * @param closet The closet room object.
     */
    private static void closetStory(Room closet) {
        closet.addOption(new Option(null, null, "leave: option 1", "leave closet"));
        closet.addOption(new Option(null, null, "clothes: option 2", "clothes"));
    }

    /**
     * Defines the options and flags for the hallway.
     *
     * @param hallway The hallway room object.
     */
    private static void hallwayStory(Room hallway) {
        hallway.addOption(new Option(null, null, "bedroom leave: option 1", "go to bedroom", rooms.get(0)));
        hallway.addOption(new Option(null, null, "broken storage: option 2", "see Storage", rooms.get(3)));
        hallway.addOption(new Option(null, null, "purifier leave: option 3", "go to purifier", rooms.get(4)));
        hallway.addOption(new Option(null, null, "control room leave: option 4", "go to control room", rooms.get(5)));
    }

    /**
     * Defines the options and flags for the purifier room.
     *
     * @param purifier The purifier room object.
     */
    private static void purifierStory(Room purifier) {
        purifier.addOption(new Option(null, null, "control panel: option 1", "control info"));
        purifier.addOption(new Option(null, flags("hasFilter"), "filter panel: option 2", "filter gain"));
        purifier.addOption(new Option(null, null, "leave: option 3", "leave purifier", rooms.get(2)));
    }

    /**
     * Helper method to create a list of flags.
     *
     * @param flag One or more flag strings.
     * @return A list of flags.
     */
    private static List<String> flags(String... flag) {
        return Arrays.asList(flag);
    }
}
