package projet.controller;

import java.awt.Container;
import java.util.List;

import projet.json.JSONPrettyPrinter;
import projet.api.ColoredString;
import projet.api.Token;
import projet.api.Tree;

public class AffichageJSON extends JSONPrettyPrinter {
    
    private Container paneToFill;

    public AffichageJSON(Tree<Token> tree, Container paneJSON){
        super(tree);
        this.paneToFill = paneJSON;
        List<ColoredString> coloredValues = getHighlightedText(tree.getRoot(), 0);
        prettyColoredPrint();
    }
    
    public void prettyColoredPrint(){

    }
}
