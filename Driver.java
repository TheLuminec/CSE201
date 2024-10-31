
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

        player = new Player(userName, 20, startRoom, null);

        run();
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
            int choice = input.nextInt();//need error handling
            
            if(currRoom.chooseOption(choice, player)) {
                //score and things
            }
            else {
                System.out.println("Invalid Option!");
                continue;
            }

        }

        input.close();
    }

    private static void makeStory() {//example
        Room bedroom = new Room("Bedroom", "there are things in here, bedroom");
        bedroomStory(bedroom);

        startRoom = bedroom;
    }

    private static void bedroomStory(Room bedroom) {
        List<String> flags = Arrays.asList("flag1");
        bedroom.addOption(new Option(null, Arrays.asList("missileSeen"), "missile: option 1", "can pick up missile"));
        bedroom.addOption(new Option(null, flags, "this is the bedroom option 2", "what happened also"));
    }

    private static List<String> flag(String[] flag) {
        return Arrays.asList(flag);
    }
}
