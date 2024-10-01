public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.loadGame("script.txt");
            game.play();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
