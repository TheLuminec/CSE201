

public class GuessWordPuzzle extends Puzzle {
    String word;

    public GuessWordPuzzle() {
        generateWord();
    }

    private void generateWord() {
        word = "TEMP";
    }

    @Override
    public void triggerPuzzle() {

    }

}