
import java.util.ArrayList;
import java.util.Scanner;
public class Driver {
        private static ArrayList<Room> rooms;
        private static Player player;
        // put something here
        private static final Room startRoom = null;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userName = in.next();
        player = new Player(userName, startRoom, null);
        
    }

    public void makeStory() {

    }
}
