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

        makeStory();

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
                System.out.println("Game Over! \n You took too long to fix the purifier. Your vision slowly fades to black as you suffocate. You wake up in New Jersey five hours later. You should probably lay off the benadryl for a while.");
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
            System.out.println();
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

    /**
     * Sets up the game story by creating rooms and their respective options.
     */
    private static void makeStory() {//example
        System.out.println("You wake up on your spaceship to a blaring alarm repeating \"the air purification system is damaged. Proceed to the purification room to do further diagnostics.\"");
        rooms = new ArrayList<>();
        Room bedroom = new Room("Bedroom", "You look around your bedroom and see,");
        Room closet = new Room("Closet", "You look in the closet and see,");
        Room hallway = new Room("Hallway", "You walk into the hallway and see red lights flashing,");
        Room storage = new Room("Storage", "You are in the storage room,");
        Room purifier = new Room("Purifier", "You see the air purifier straight ahead,");
        Room controlRoom = new Room("Control Room", "You enter the main control room and see,");
        rooms.add(bedroom);
        rooms.add(closet);
        rooms.add(hallway);
        rooms.add(storage);
        rooms.add(purifier);
        rooms.add(controlRoom);
        startRoom = bedroom;
        player.addItem("brokenDoor");

        //Bedroom choices, and closet
        bedroom.addOption(new Option("Go back to sleep. You're sure the problem will sort itself out.", 
        "You took too long to fix the purifier. Your vision slowly fades to black as you suffocate. You wake up in New Jersey five hours later. You should probably lay off the benadryl for a while.", 100));
        bedroom.lastSetFlags("gameEnd");

        bedroom.addOption(new Option("You inspect the missile and notice that it is still live and functional.",
        "You take the missile somehow carrying it."));
        bedroom.lastSetItems("missle");

        bedroom.addOption(new Option("You see a door leading to the closet.", 
        "You enter the closet.", 2, closet));

        closet.addOption(new Option("You see a sledgehammer you could take it.", 
        "You pick up the sledgehammer.", 2));
        closet.lastSetItems("sledgehammer");
        closet.addOption(new Option("Get dressed.", "You look extraordinarily dapper.", -8));
        closet.addOption(new Option("Leave closet.", "You leave the closet.", 1, bedroom));

        bedroom.addOption(new Option("You see an alarm clock.", "It looks like you could break this."));
        bedroom.lastSetFlags("alarmLook");
        bedroom.addOption(new Option("You see an alarm clock that looks very breakable.", 
        "You smash the alarm clock with your sledgehammer and some batteries fall out.", 3));
        bedroom.lastNeedFlags("alarmLook");
        bedroom.lastNeedItems("sledgehammer");
        bedroom.lastSetItems("batteries");

        bedroom.addOption(new Option("You see a door leading to the hallway", 
        "You leave the bedroom.", 2, hallway));



        //Hallway choices
        hallway.addOption(new Option("You see a broken door leading to the storage room.", 
        "The door does not seem to work.", 1, storage));

        hallway.addOption(new Option("You see a door leading to the air purifier room.", 
        "You enter the air purifier room.", 2, purifier));

        hallway.addOption(new Option("You see a door leading to the main control room", 
        "You enter the main control room.", 2, controlRoom));


        //Control Room choices
        controlRoom.addOption(new Option("You see a console with a warning message on it.", 
        "The warning message on the console reads \"Proceed to the air purifier room and remove the front panel. Internal rewiring is required\"."));

        controlRoom.addOption(new Option("You see a keycard hanging on the wall.", 
        "You take the keycard."));
        controlRoom.lastSetItems("keycard");

        controlRoom.addOption(new Option("You see a new air purifier, its heavy and would take a while to install.", 
        "You pick it up but its very heavy.", 5));
        controlRoom.lastSetItems("airpurifier");

        controlRoom.addOption(new Option("Leave the control room.", 
        "You go back to the hallway.", 1, hallway));



        //Storage
        storage.addOption(new Option("You could blow up the door with the missle.", 
        "You blow up the door but also put a hole in the side of your spaceship. You suffocate and die.", 100));
        storage.lastNeedItems("missle", "brokenDoor");
        storage.lastSetFlags("gameEnd");

        storage.addOption(new Option("You could replace the keycard readers batteries.", 
        "You replace the batteries in the keycard reader and it turns on."));
        storage.lastNeedItems("batteries", "brokenDoor");
        storage.lastSetFlags("keycardReaderOn");

        storage.addOption(new Option("You could use the keycard.", 
        "You use the keycard and the storage room door opens."));
        storage.lastNeedFlags("keycardReaderOn");
        storage.lastNeedItems("keycard", "brokenDoor");
        storage.lastSetFlags("doorOpen");

        storage.addOption(new Option("You see a toolbox in the storage room.", 
        "You take the toolbox."));
        storage.lastNeedFlags("doorOpen");
        storage.lastSetItems("toolbox");

        //puzzle
        storage.addOption(new Option("You could guess the password.", 
        "You somehow guess the password to the door.", 
        2, new GuessWordPuzzle("Guess the password! It could be any word you probbably can't guess it.")));
        storage.lastNeedFlags("keycardReaderOn");
        storage.lastNeedItems("brokenDoor");
        storage.lastSetFlags("doorOpen");

        storage.addOption(new Option("Leave storage room.", 
        "You go back to the hallway.", 1, hallway));


        //Air Purifier
        purifier.addOption(new Option("Leave air purifier room.", 
        "You go back to the hallway.", 3, hallway));

        purifier.addOption(new Option("Look at the purifier.", 
        "You take a look and you can't fix it without a toolbox or new purifer."));

        purifier.addOption(new Option("You use the screwdriver in your toolbox to take the front panel from the purifier.",
         "You fix the air purifier! \n Congratulations! You successfully fixed the air purifier in time and didn\'t die!", 
         5, new HangManPuzzle("The wiring is difficult, solve the puzzle to fix the air purifier!")));
        purifier.lastNeedItems("toolbox");
        purifier.lastSetFlags("gameEnd");

        purifier.addOption(new Option("You could try replacing the whole air purifier but it would be hard.", 
        "You somehow install the new air purifier! \n Congratulations! You successfully fixed the air purifier in time and didn\'t die!", 
        10, new GuessWordPuzzle("The air purifier looks hard to install, you'd have to get lucky.")));
        purifier.lastNeedItems("airpurifier");
        purifier.lastSetFlags("gameEnd");

    }

    static Scanner getScanner() {
        return input;
    }
}