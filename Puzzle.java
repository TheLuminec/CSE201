public abstract class Puzzle {
    protected String description;
    
    public String getDescription() {
        return description;
    }

    protected abstract void generateWords();
    public abstract boolean triggerPuzzle();
}