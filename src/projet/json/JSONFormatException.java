package projet.json;

public class JSONFormatException extends Exception {
    public JSONFormatException() {
        super("Invalid JSON formatting.");
    }

    public JSONFormatException(String s) {
        super(s);
    }
}
