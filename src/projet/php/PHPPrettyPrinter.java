package projet.php;

import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;

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
