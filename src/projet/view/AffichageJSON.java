package projet.view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import projet.json.JSONPrettyPrinter;
import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;
import projet.controller.ColoredString;

public class AffichageJSON {
    
    private Container paneToFill;

    public AffichageJSON(Tree<Token> tree, Container paneJSON){
        this.paneToFill = paneJSON;
        PrettyPrinter prettyPrinter = new JSONPrettyPrinter(tree);
        List<ColoredString> coloredValues = prettyPrinter.getHighlightedText();
        prettyColoredPrint(coloredValues);
    }
    
    public void prettyColoredPrint(List<ColoredString> coloredCouples){
        this.paneToFill.removeAll();
        for (ColoredString couple : coloredCouples){
            String string = couple.getString();
            if (string.length()<=50){
                // Si l'élément est un crochet ou une accolade fermante il faut passer une ligne avant de l'afficher
                if(couple.getString().matches(" *[\\}\\]]$")){
                    JLabel emptySpace = new JLabel("");  
                    emptySpace.setPreferredSize(new Dimension(3000,0));
                    this.paneToFill.add(emptySpace);
                }
                JLabel value = new JLabel(string);
                System.out.println(couple.getString());
                value.setForeground(couple.getColor());
                this.paneToFill.add(value);
                // Si il y a un retour à la ligne, ce dernier est effectué en insérant un JLabel vide
                if(couple.getString().contains("\n")){
                    JLabel emptySpace = new JLabel("");
                    emptySpace.setPreferredSize(new Dimension(3000,0));
                    this.paneToFill.add(emptySpace);
                }
            } else {
                // Lorsqu'un string est trop long il est découpé en plusieurs lignes en ajoutant les mots un à un
                String newString = string;
                int sizeOfWord = 0;
                for (int i = 0; i<string.length();i+=sizeOfWord){
                    String word = newString;
                    for (int j = 0; (j+1)<word.length(); j++){
                        if (newString.substring(j,j+1).equals(" ")){
                            word = newString.substring(0, j+1);
                            JLabel value = new JLabel(word);
                            value.setForeground(couple.getColor());
                            this.paneToFill.add(value);
                            if(word.contains("\n")){
                                JLabel emptySpace = new JLabel("");  
                                emptySpace.setPreferredSize(new Dimension(3000,0));
                                this.paneToFill.add(emptySpace);
                            }
                            newString = newString.substring(j+1);
                        }
                    }
                    sizeOfWord = word.length();
                }
            }
            this.paneToFill.repaint();
        }
    }
}
