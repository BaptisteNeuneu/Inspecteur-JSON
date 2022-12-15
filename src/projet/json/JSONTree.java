package projet.json;

import projet.api.Token;
import projet.api.Tree;
import projet.api.TreePath;

/***
 * Implémentation de la classe Tree pour les documents JSON.
 */
public class JSONTree implements Tree<Token> {

    private Token root;

    @Override
    public JSONToken add(Token object, TreePath path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Token remove(TreePath path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Token get(TreePath path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean pathExists(TreePath path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Token value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Token getRoot() {
        return root;
    }
    
}
