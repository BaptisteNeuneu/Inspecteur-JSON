package projet;

import projet.json.JSONFormatException;
import projet.json.JSONParser;

/***
 * Début du programme, choisit entre le mode prettyPrint à la console et le mode graphique.
 */
public class Interpreter {
    public static void main(String[] args) {
        String s = "{\n   \"firstName\": \"Joe\",\n   \"lastName\": \"Jackson\",\n   \"gender\": \"male\",\n   \"age\": 28,\n   \"address\": {\n       \"streetAddress\": \"101\",\n       \"city\": \"San Diego\",\n       \"state\": \"CA\"\n   },\n   \"phoneNumbers\": [\n       { \"type\": \"home\", \"number\": \"7349282382\" }\n   ]\n}";

        try {
            JSONParser.deserialize(s);
        } catch (JSONFormatException e) {
            System.err.println("Format JSON non-conforme.");
            System.err.println("Pour plus d'informations : https://www.rfc-editor.org/rfc/rfc8259.html");
        }
    }
}
