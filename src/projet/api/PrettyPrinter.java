package projet.api;

import java.util.List;

public abstract class PrettyPrinter {
    
    private Tree<Token> tree;

    protected String dictFormatter = "";
    protected String tableFormatter = "";

    protected String dictKeyFormatter = "";
    protected String arrayIndexFormatter = "";

    protected String dictValueDelimiter = "";
    protected String arrayValueDelimiter = "";

    public PrettyPrinter(Tree<Token> tree) {
        this.tree = tree;
    }

    public Tree<Token> getTree() {
        return tree;
    }

    public void setTree(Tree<Token> tree) {
        this.tree = tree;
    }
    
    public List<ColoredString> getHighlightedText() {
        return null;
    }
}
