package newsrc;

public class NoPossibleWords extends Exception {
    public NoPossibleWords() {
        super("No possible words exist.");
    }
}
