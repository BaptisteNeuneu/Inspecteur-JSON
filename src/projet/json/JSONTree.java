package projet.json;

import projet.api.Token;
import projet.api.Tree;

/***
 * Implémentation de la classe Tree pour les documents JSON.
 */
public class JSONTree implements Tree<Token> {

    private Token root;

    @Override
    public Token add(Token object, String path) throws IllegalArgumentException, IllegalStateException {
        if (path == "") {
            Token oldRoot = root;
            root = object;
            return oldRoot;
        }

        String[] pathSteps = path.split("\\.");
        Token currToken = root;

        for (int i = 0; i < pathSteps.length; i++) {
            String step = pathSteps[i];

            switch (currToken.getValueType()) {
                case ARRAY:
                    try {
                        int index = Integer.parseInt(step);

                        if (i == pathSteps.length - 1) {
                            return currToken.getValues().set(index, object);
                        }

                        currToken = currToken.getValues().get(index);
                    } catch (NumberFormatException e) {
                        throw new IllegalStateException("Non number index found with an array type token.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new IllegalStateException("Token index out of bounds");
                    }
                    break;
                case DICT:
                    if (i == pathSteps.length - 1) {
                        return currToken.getMembers().put(step, object);
                    }

                    if (!currToken.getMembers().containsKey(step))
                        throw new IllegalStateException("Unknwown dict member '" + step + "'");

                    currToken = currToken.getMembers().get(step);
                    break;
                default:
                    throw new IllegalArgumentException("Path goes through non ARRAY or DICT Tokens.");
            }
        }

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
