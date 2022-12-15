package projet.json;

/***
 * Représente une exception de formattage d'un fichier JSON
 */
public class JSONFormatException extends Exception {
    public JSONFormatException() {
        super("Invalid JSON formatting.");
    }

    public JSONFormatException(String s) {
        super(s);
    }
}
