package projet.api;

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
     * Retourne une ensemble de chaines de caractère colorées 
     * représentant l'arbre et ses données avec coloration syntaxique.
     * @return l'ensemble des données de l'arbre avec coloration syntaxique
     */
    public List<ColoredString> getHighlightedText() {
        return null;
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
        for (int i = 0; i < indent; i++) indentString += "  ";
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

}
