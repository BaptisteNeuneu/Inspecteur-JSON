package projet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;
import projet.json.JSONFormatException;
import projet.json.JSONParser;
import projet.json.JSONPrettyPrinter;
import projet.view.Accueil;

/**
 * La classe <code>Interpreter</code> est le début du programme, choisit entre le mode prettyPrint à la console et le mode graphique.
 * 
 * @version 1.1
 */
public class Interpreter {
    public static void main(String[] args) {
        if (args.length == 0) {
            new Accueil();
        } else {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            try (BufferedReader fr = new BufferedReader(new InputStreamReader(cl.getResourceAsStream(args[0])))) {
                String s = "";
                while (fr.ready()) s += fr.readLine().trim();
                Tree<Token> tree = JSONParser.deserialize(s);
                PrettyPrinter prettyPrinter = new JSONPrettyPrinter(tree);
                System.out.println(prettyPrinter.prettyString());
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
}

