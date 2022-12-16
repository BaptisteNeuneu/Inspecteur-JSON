package projet.json;

import projet.api.Token;
import projet.api.Tree;

/***
 * Implémentation de la classe Tree pour les documents JSON.
 */
public class JSONTree implements Tree<Token> {

    private Token root;

    @Override
    public JSONToken add(Token object, String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Token remove(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Token get(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean pathExists(String path) {
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
    
    @Override
    public void setRoot(Token root) {
        this.root = root;
    }
}
