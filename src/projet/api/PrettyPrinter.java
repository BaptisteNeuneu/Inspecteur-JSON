package projet.api;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public abstract class PrettyPrinter {
    
    private Tree<Token> tree;

    //Spécifie comment formatter un tableau / dictionnaire dans sa généralité
    private String dictFormatter;
    private String tableFormatter;

    //Spécifie comment formatter un couple clé/valeur ou une valeur dans les tableaux / dictionnaires
    private String dictEntryFormatter;
    private String tableEntryFormatter;

    //Spécifie comment séparer les valeurs entres elles.
    private String dictValueDelimiter;
    private String tableValueDelimiter;

    //Liste de couples valeur couleur
    private ColoredNode treeRoot;

    // Couleurs pour la coloration syntaxique
    private Color separateurs = new Color(212, 212, 212);
    private Color cles = new Color(156, 220, 254);
    private Color nombres = new Color(182, 206, 168);
    private Color strings = new Color(206, 145, 120);
    private Color otherValues = new Color(86, 156, 214);

    public PrettyPrinter(Tree<Token> tree) {
        this.tree = tree;
    }
    
    protected String getDictFormatter() {
        return dictFormatter;
    }

    //Getters et setters.
    protected void setDictFormatter(String dictFormatter) {
        this.dictFormatter = dictFormatter;
    }

    protected String getTableFormatter() {
        return tableFormatter;
    }

    protected void setTableFormatter(String tableFormatter) {
        this.tableFormatter = tableFormatter;
    }

    protected String getDictEntryFormatter() {
        return dictEntryFormatter;
    }

    protected void setDictEntryFormatter(String dictEntryFormatter) {
        this.dictEntryFormatter = dictEntryFormatter;
    }

    protected String getTableEntryFormatter() {
        return tableEntryFormatter;
    }
    
    protected void setTableEntryFormatter(String tableEntryFormatter) {
        this.tableEntryFormatter = tableEntryFormatter;
    }

    protected String getDictValueDelimiter() {
        return dictValueDelimiter;
    }

    protected void setDictValueDelimiter(String dictValueDelimiter) {
        this.dictValueDelimiter = dictValueDelimiter;
    }

    protected String getTableValueDelimiter() {
        return tableValueDelimiter;
    }

    protected void setTableValueDelimiter(String tableValueDelimiter) {
        this.tableValueDelimiter = tableValueDelimiter;
    }

    /**
     * Retourne l'arbre traité par la classe
     * @return l'arbre traité par la classe
     */
    public Tree<Token> getTree() {
        return tree;
    }

    /**
     * Définit l'arbre à traiter
     * @param tree l'arbre à traiter
     */
    public void setTree(Tree<Token> tree) {
        this.tree = tree;
    }


    /**
     * Retourne une chaine de caractère représentant un token et les données qu'il contient
     * @param indent le nombre d'indentations précédant le token en question
     * @param token le token à traiter
     * @return les données du token formattées et indentées
     */
    private String printToken(Token token, int indent) {
        String formattedString = "";
        String indentString = "";
        for (int i = 0; i < indent; i++) indentString += "\t";
        formattedString += indentString;

        switch (token.getValueType()) {
            case DICT: {
                formattedString = dictFormatter.replaceAll("\\{indents\\}", indentString);

                String formattedValues = "";
                Map<String, Token> dict = token.getMembers();
                
                boolean first = true;
                for (Map.Entry<String, Token> entry : dict.entrySet()) {
                    String formattedValue = (first ? "" : dictValueDelimiter) + indentString + dictEntryFormatter;
                    formattedValue = formattedValue.replaceAll("\\{key\\}", entry.getKey());
                    formattedValue = formattedValue.replaceAll("\\{value\\}", printToken(entry.getValue(), indent + 1));
                    formattedValues += formattedValue;
                    if (first) first = false;
                }
                
                formattedString = formattedString.replaceAll("\\{values\\}", formattedValues);
                break;
            }

            case ARRAY: {
                formattedString = tableFormatter.replaceAll("\\{indents\\}", indentString);

                String formattedValues = "";
                List<Token> list = token.getValues();

                for (Integer i = 0; i < list.size(); i++) {
                    String formattedValue = indentString + tableEntryFormatter;
                    formattedValue = formattedValue.replaceAll("\\{index\\}", i.toString());
                    formattedValue = formattedValue.replaceAll("\\{value\\}", printToken(list.get(i), indent + 1));
                    formattedValues += formattedValue;
                    if (i < list.size() - 1) formattedValues += tableValueDelimiter;
                }

                formattedString = formattedString.replaceAll("\\{values\\}", formattedValues);
                break;
            }

            default:
                formattedString = token.getValue().toString();
                break;
        }

        return formattedString;
    }

    /**
     * Retourne une chaine de caractère de l'arbre dans la syntaxe donnée.
     * @return les données arbre dans la syntaxe donnée.
     */
    public String prettyString(){
        if (tree.getRoot() == null) return "";
        return printToken(tree.getRoot(), 0);
    }

    /**
     * Créer un arbre de ColoredNodes à partir de l'arbre de valeurs et rempli la liste de ColoredString
     * @param token la racine de l'arbre des valeurs
     * @param indent l'indentation 
     */
    public void createHighlightTree(Token token, ColoredNode parent, int indent, boolean isIndentified ){
        String indentString = "";
        for (int i = 0; i < indent; i++) indentString += "    ";

        switch (token.getValueType()) {
            case DICT: {
                if (parent == null) {
                    parent = new ColoredNode("{\n", separateurs);
                    parent.setFolded(false);
                    treeRoot = parent;
                } else {
                    ColoredNode newParent = new ColoredNode("{\n", separateurs);
                    parent.addFils(newParent);
                    parent = newParent;
                }

                Map<String, Token> dict = token.getMembers();
                boolean first = true;

                for (Map.Entry<String, Token> entry : dict.entrySet()) {
                    if (!first){
                        parent.addFils(new ColoredNode(",\n", separateurs));
                    }
                    
                    parent.addFils(new ColoredNode(indentString+"\""+entry.getKey()+"\"",cles));
                    parent.addFils(new ColoredNode(": ",separateurs));

                    createHighlightTree(entry.getValue(), parent, indent+1, false);
                    if (first) first = false;
                }

                parent.addFils(new ColoredNode(indentString + "}", separateurs));
                break;
            }

            case ARRAY: {
                if (parent == null) {
                    parent = new ColoredNode("[\n", separateurs);
                    treeRoot = parent;
                } else {
                    ColoredNode newParent = new ColoredNode("[\n", separateurs);
                    parent.addFils(newParent);
                    parent = newParent;
                }

                List<Token> list = token.getValues();

                boolean first = true;
                for (Integer i = 0; i < list.size(); i++) {
                    if (!first){
                        parent.addFils(new ColoredNode(",\n", separateurs));
                    }
                    createHighlightTree(list.get(i), parent, indent+2, true);
                    if (first) first = false;
                }
                parent.addFils(new ColoredNode(indentString + "]", separateurs));
                break;
            }

            default: {
                String formattedString = token.getValue().toString();
                ColoredNode node;
                switch (token.getValueType()) {
                    case NUMBER:
                        node = new ColoredNode(isIndentified ? indentString+formattedString : formattedString, nombres);
                        if (parent == null) {
                            treeRoot = node;
                        } else {
                            parent.addFils(node);
                        }
                        break;
                    case STRING:
                        node = new ColoredNode(isIndentified ? indentString+formattedString : formattedString, strings);
                        if (parent == null) {
                            treeRoot = node;
                        } else {
                            parent.addFils(node);
                        }
                        break;
                    default:
                        node = new ColoredNode(isIndentified ? indentString+formattedString : formattedString, otherValues);
                        if (parent == null) {
                            treeRoot = node;
                        } else {
                            parent.addFils(node);
                        }
                        break;
                }

                break;
            } 
        }
    }

    /**
     * Retourne une ensemble de chaines de caractère colorées 
     * représentant l'arbre et ses données avec coloration syntaxique.
     * @return l'ensemble des données de l'arbre avec coloration syntaxique
     */
    public ColoredNode getHighlightedText() {
        if (tree.getRoot() == null) return new ColoredNode("", Color.BLACK);
        createHighlightTree(tree.getRoot(), null, 0, true);

        return treeRoot;
    }

}
