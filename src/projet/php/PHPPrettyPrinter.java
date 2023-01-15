package projet.php;

import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;

/**
 * La classe <code>PHPPrettyPrinter</code> hérite de la classe PrettyPrinter et définit ses élements de syntaxe pour correspondre à du PHP
 * 
 * @version 1.1
 */
public class PHPPrettyPrinter extends PrettyPrinter{
    public PHPPrettyPrinter(Tree<Token> tree) {
        super(tree);
        setDictFormatter("Array\n{indents}(\n{values}\n{indents})");
        setTableFormatter("Array\n{indents}(\n{values}\n{indents})");
        setDictEntryFormatter("[{key}] => {value}");
        setTableEntryFormatter("[{index}] => {value}");
        setDictValueDelimiter("\n");
        setTableValueDelimiter("\n");
    }
}
