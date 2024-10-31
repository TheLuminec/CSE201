
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Driver {
    private static ArrayList<Room> rooms;
    private static Player player;
    // put something here
    private static Room startRoom = null;
    private static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        System.out.println("Enter your Name!");
        String userName = input.nextLine();

        makeStory();

        player = new Player(userName, 30, startRoom, null);

        run();

        input.close();
    }

    public static void run() {
        player.getRoom().enterRoom();

        while(true) {
            Room currRoom = player.getRoom();
            if(player.timeIsUp()) {
                System.out.println("Game Over!");
                break;
            }

            currRoom.printOptions();
            int choice = 0;
            try{
                choice = input.nextInt();
            } catch(Exception e) {
                System.out.println("Invalid Option! Please enter a number.");
                continue;
            }
            
            if(currRoom.chooseOption(choice, player)) {
                //score and things
                if(currRoom.hasFlag("gameEnd")) {
                    break;
                }
            }
            else {
                System.out.println("Invalid Option!");
                continue;
            }

        }

        
    }

    private static void makeStory() {//example
        rooms = new ArrayList<>();
        Room bedroom = new Room("Bedroom", "there are things in here, bedroom");
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


        bedroomStory(bedroom);
        closetStory(closet);
    

        startRoom = bedroom;
    }

    private static void bedroomStory(Room bedroom) {
        bedroom.addOption(new Option(null, flags("takeMissile"), "missile: option 1", "can pick up missile"));
        bedroom.addOption(new Option(null, null, "closet: option 2", "go to closet"));
        bedroom.addOption(new Option(null, flags("alarmVisit"), "clock: option 3", "go to clock"));
        bedroom.addOption(new Option(null, null, "leave: option 4", "leave hallway"));
        bedroom.addOption(new Option(flags("alarmVisit", "hasHammer"),
         flags("alarmbreak"), "clock: option 2", "go to clock", rooms.get(1)));
    }

    private static void closetStory(Room closet) {
        closet.addOption(new Option(null, null, "leave: option 1", "leave closet"));
        closet.addOption(new Option(null, null, "clothes: option 2", "clothes"));
    }
    
    private static void hallwayStory(Room hallway) {
        hallway.addOption(new Option(null, null, "bedroom leave: option 1 ", "go bedroom", rooms.get(0)));
        hallway.addOption(new Option(null, null, "broken storage: option 2 ", "see Storage", rooms.get(3)));
        hallway.addOption(new Option(null, null, "purifier leave: option 3 ", "go purifier", rooms.get(4)));
        hallway.addOption(new Option(null, null, "control room leave: option 4 ", "go control room", rooms.get(5)));
    }

    private static void purifierStory(Room purifier) {
        purifier.addOption(new Option(null, null, "control panel: option 1", "control info"));
        purifier.addOption(new Option(null, flags("hasFilter"), "filter panel: option 2", "filter gain"));
        purifier.addOption(new Option(null, null, "leave: option 3", "leave control", rooms.get(2)));

    }

    private static List<String> flags(String... flag) {
        return Arrays.asList(flag);
    }

}
