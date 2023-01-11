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
        if (path == "") {  
            Token oldRoot = root;  
            root = null;   
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
                            return currToken.getValues().remove(index);    
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
                        return currToken.getMembers().remove(step);    
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
    public Token get(String path) {
        if (path == "") {
            return root;
        }

        String[] pathSteps = path.split("\\.");
        Token currToken = root;

        for (int i = 0; i < pathSteps.length; i++) {
            String step = pathSteps[i];

            switch (currToken.getValueType()) {
                case ARRAY:
                    try {
                        int index = Integer.parseInt(step);
                        currToken = currToken.getValues().get(index);
                    } catch (NumberFormatException e) {
                        throw new IllegalStateException("Non number index found with an array type token.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new IllegalStateException("Token index out of bounds");
                    }
                    break;
                case DICT:
                    if (!currToken.getMembers().containsKey(step))
                        throw new IllegalStateException("Unknwown dict member '" + step + "'");

                    currToken = currToken.getMembers().get(step);
                    break;
                default:
                    throw new IllegalArgumentException("Path goes through non ARRAY or DICT Tokens.");


            }
        }

        return currToken;
    }

    @Override
    public boolean pathExists(String path) {   
        if (path == "") {  
            return true;   
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
                            return true;  
                        }  

                        currToken = currToken.getValues().get(index);    
                    } catch (NumberFormatException e) {  
                        throw new IllegalStateException("Non number index found with an array type token.");    
                    } catch (IndexOutOfBoundsException e) {  
                        return false;   
                    }  
                    break;  
                case DICT:   
                    if (i == pathSteps.length - 1) {    
                        return currToken.getMembers().containsKey(step);  
                    }  

                    if (!currToken.getMembers().containsKey(step))    
                        return false;  

                    currToken = currToken.getMembers().get(step);    
                    break;  
                default:    
                    throw new IllegalArgumentException("Path goes through non ARRAY or DICT Tokens.");    


            }
        }

        return false;   
    }

    @Override
    public boolean contains(Token value) {
        if (root == null) {
            return false;
        }

        return contains(root, value);
    }

    private boolean contains(Token node, Token value) {
        if (node.equals(value)) {
            return true;
        }

        switch (node.getValueType()) {
            case ARRAY:
                for (Token t : node.getValues()) {
                    if (contains(t, value)) {
                        return true;
                    }
                }
                break;
            case DICT:
                for (Token t : node.getMembers().values()) {
                    if (contains(t, value)) {
                        return true;
                    }
                }
                break;
            default: 
                throw new IllegalArgumentException("Path goes through non ARRAY or DICT Tokens."); 
                                                                                                   

        }

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

    @Override
    public String toString() {
        return root.toString();
    }
}
