package projet.json;

import projet.api.PrettyPrinter;
import projet.api.Token;
import projet.api.Tree;

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
