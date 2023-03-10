package projet.view;

import java.awt.*;
import javax.swing.JLabel;
import projet.json.JSONPrettyPrinter;
import projet.api.ColoredNode;
import projet.api.ColoredString;
import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;

/**
 * La classe <code>AffichageJSON</code> gère l'affichage d'un fichier JSON dans l'interface graphique.
 * 
 * @version 1.1
 */
public class AffichageJSON {
    
    private JLabel paneToFill;
    ColoredNode highlightTreeRoot;

    /**
    * Constructeur qui indique quel est l'arbre syntaxique à afficher, et dans quel container
    *
    * @param tree l'arbre syntaxique
    * @param paneJSON le container dans lequel réaliser l'affichage
    */
    public AffichageJSON(Tree<Token> tree, Container paneJSON){
        this.paneToFill = new JLabel();
        paneJSON.add(this.paneToFill);
        this.paneToFill.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        PrettyPrinter prettyPrinter = new JSONPrettyPrinter(tree);
        highlightTreeRoot = prettyPrinter.getHighlightedText();
        prettyColoredPrint();
    }

    /**
    * Méthode qui affiche les couples chaine de caractères / couleur d'un arbre syntaxique
    *
    * @param node la racine de l'arbre syntaxique coloré
    */
    private void addToText(ColoredNode node) {
        ColoredString couple = node.toColoredString();

        String string = couple.getString();
        if (string.length()<=50){
            // Si l'élément est un crochet ou une accolade fermante il faut passer une ligne avant de l'afficher
            if(couple.getString().matches(" *[\\}\\]]$")){
                JLabel emptySpace = new JLabel("");
                emptySpace.setPreferredSize(new Dimension(3000,0));
                this.paneToFill.add(emptySpace);
            }
            JLabel value = new JLabel(string);
            value.setForeground(couple.getColor());
            //value.setBackground(couple.getColor());
            if (node.getFils().size() > 0) {
                value.addMouseListener(new FoldingListener(this, node));
            }
            this.paneToFill.add(value);
            //System.out.println(value.toString());
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
                        value.setBackground(couple.getColor());
                        value.setForeground(couple.getColor());
                        this.paneToFill.add(value);
                        newString = newString.substring(j+1);
                    }
                }
                sizeOfWord = word.length();
            }
            JLabel value = new JLabel(newString);
            value.setBackground(couple.getColor());
            value.setForeground(couple.getColor());
            this.paneToFill.add(value);
            if(newString.contains("\n")){
                JLabel emptySpace = new JLabel("");  
                emptySpace.setPreferredSize(new Dimension(3000,0));
                this.paneToFill.add(emptySpace);
            }
        }
        
        if (!node.isFolded()) {
            for (ColoredNode fils : node.getFils()) {
                addToText(fils);
            }
        }
    }

    /**
    * Getter pour obtenir la racine d'un arbre syntaxique coloré 
    */
    public ColoredNode getHighlightTreeRoot() {
        return highlightTreeRoot;
    }
    
    /**
    * Méthode qui refresh l'affichage 
    */
    public void prettyColoredPrint() {
        this.paneToFill.removeAll();
        addToText(highlightTreeRoot);
        this.paneToFill.getParent().repaint();
        this.paneToFill.getParent().revalidate();
    }
}
