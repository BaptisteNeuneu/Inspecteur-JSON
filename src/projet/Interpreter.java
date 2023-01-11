package projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import projet.api.Token;
import projet.api.Tree;
import projet.json.JSONFormatException;
import projet.json.JSONParser;
import projet.Vue.TriCaractere;

/***
 * Début du programme, choisit entre le mode prettyPrint à la console et le mode graphique.
 */
public class Interpreter {
    public static void main(String[] args) {

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (BufferedReader fr = new BufferedReader(new InputStreamReader(cl.getResourceAsStream("json_examples/json1.json")))) {
            String s = "";
            while (fr.ready()) s += fr.readLine().trim();
            Tree<Token> tree = JSONParser.deserialize(s);
            TriCaractere lecture = new TriCaractere();
            lecture.PrintConsole(tree);
        } catch (JSONFormatException e) {
            System.err.println("Format JSON non-conforme.");
            System.err.println("Pour plus d'informations : https://www.rfc-editor.org/rfc/rfc8259.html");
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("Reading error");
        }
    }
}
