package projet.json;

import projet.api.Tree;
import projet.api.TreePath;

/***
 * Implémentation de la classe Tree pour les documents JSON.
 */
public class JSONTree implements Tree<JSONToken> {

    private JSONToken root;

    @Override
    public JSONToken add(JSONToken object, TreePath path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONToken remove(TreePath path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JSONToken get(TreePath path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean pathExists(TreePath path) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(JSONToken value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public JSONToken getRoot() {
        return root;
    }
    
}
