package projet.json;

import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;

/**
 * La classe <code>JSONPrettyPrinter</code> hérite de la classe PrettyPrinter et définit ses élements de syntaxe pour correspondre à du JSON
 * 
 * @version 1.1
 */
public class JSONPrettyPrinter extends PrettyPrinter{
    public JSONPrettyPrinter(Tree<Token> tree) {
        super(tree);
        setDictFormatter("{\n{values}\n{indents}}");
        setTableFormatter("[\n{values}\n{indents}]");
        setDictEntryFormatter("\"{key}\": {value}");
        setTableEntryFormatter("{value}");
        setDictValueDelimiter(",\n");
        setTableValueDelimiter(",\n");
    }
}
