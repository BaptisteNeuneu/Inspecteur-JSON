package projet.controller;

import java.awt.event.*;
import java.io.*;

import javax.swing.JOptionPane;
import java.awt.Container;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;
import projet.json.JSONFormatException;
import projet.json.JSONParser;
import projet.php.PHPPrettyPrinter;
import projet.view.AffichageJSON;

public class TestFichier implements ActionListener {

    private JTextComponent zoneURL;
    private JTabbedPane zoneResult;

    public TestFichier(JTextComponent zoneURL, JTabbedPane zoneResult){
        this.zoneURL = zoneURL;
        this.zoneResult = zoneResult;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (BufferedReader fr = new BufferedReader(new InputStreamReader(cl.getResourceAsStream(this.zoneURL.getText())))) {
            String s = "";
            while (fr.ready()) s += fr.readLine().trim();
            Tree<Token> tree = JSONParser.deserialize(s);
            
            // Affichage JSON
            Container resultPaneJSON = (Container) this.zoneResult.getComponentAt(0);
            new AffichageJSON(tree, resultPaneJSON);

            // Affichage PHP
            PrettyPrinter prettyPrinterPHP = new PHPPrettyPrinter(tree);
            JTextPane resultPanePHP = (JTextPane) this.zoneResult.getComponentAt(1);
            resultPanePHP.setText(prettyPrinterPHP.prettyString());
        } catch (JSONFormatException e) {
            System.err.println("Format JSON non-conforme.");
            System.err.println("Pour plus d'informations : https://www.rfc-editor.org/rfc/rfc8259.html");
            JOptionPane.showMessageDialog(null, "Format JSON non-conforme. Pour plus d'informations : https://www.rfc-editor.org/rfc/rfc8259.html", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            JOptionPane.showMessageDialog(null, "Le fichier n'a pas été trouvé.", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.err.println("Reading error");
            JOptionPane.showMessageDialog(null, "Le fichier n'a pas pu être lu", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        } catch (NullPointerException e) {
            System.err.println("No file given");
            JOptionPane.showMessageDialog(null, "Il ne s'agit pas du chemin d'un fichier", "InfoBox" , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
}
