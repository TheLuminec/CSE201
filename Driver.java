
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
        rooms.add(bedroom);
        rooms.add(closet);
        rooms.add(hallway);
        bedroomStory(bedroom);
        closetStory(closet);
    

        startRoom = bedroom;
    }

    private static void bedroomStory(Room bedroom) {
        List<String> flags = Arrays.asList("flag1");
        bedroom.addOption(new Option(null, flags("takeMissile"), "missile: option 1", "can pick up missile"));
        bedroom.addOption(new Option(null, null, "closet: option 2", "go to closet"));
        bedroom.addOption(new Option(null, flags("alarmVisit"), "clock: option 3", "go to clock"));
        bedroom.addOption(new Option(null, null, "leave: option 4", "leave hallway"));
        bedroom.addOption(new Option(flags(new String[]{"alarmVisit", "hasHammer"}), flags("alarmbreak"), "clock: option 2", "go to clock", rooms.get(1)));
    }

    private static void closetStory(Room closet) {
        closet.addOption(new Option(null, null, "leave: option 1", "leave hallway"));
    }
    
    private static void hallwayStory(Room hallway) {
        hallway.addOption(new Option(null, null, "bedroom leave: option 1 ", "go bedroom"));
        hallway.addOption(new Option(null, null, "bedroom leave: option 1 ", "go bedroom"));
        hallway.addOption(new Option(null, null, "bedroom leave: option 1 ", "go bedroom"));
        hallway.addOption(new Option(null, null, "bedroom leave: option 1 ", "go bedroom"));

    }

    private static List<String> flags(String... flag) {
        return Arrays.asList(flag);
    }

}
