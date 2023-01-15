package projet.json;

/***
 * La classe <code>JSONFormatException</code> représente une exception de formattage d'un fichier JSON
 */
public class JSONFormatException extends Exception {
    public JSONFormatException() {
        super("Invalid JSON formatting.");
    }

    public JSONFormatException(String s) {
        super(s);
    }
}
