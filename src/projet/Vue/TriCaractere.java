import projet.api.Token;
import projet.json.JSONToken;
import projet.json.JSONTree;
import projet.api.Tree;
import projet.api.Token;

public class TriCaractere {
    
    JSONArray tableau = new JSONArray();
    Token feuille = new JSONToken();
    String root = "";

    public void PrintConsole(Tree<Token> arbre){

        

        while(arbre.pathExists(root) == true){
            feuille = arbre.get(root);
            System.out.println("le path est :" + root);
            switch(feuille.getValueType()){
                case ARRAY :
                String tab = (String)feuille.getValue();
                tableau.ReadArray(tab);
                root = root + tab;
                
                case DICT :
                String object = (String)feuille.getValue();

                
                default :

            }
             
        }

    }

    public void PrintWindow(Tree<Token> arbre){

        while (arbre.pathExists(root) == true) {
            feuille = arbre.get(root);
            switch (feuille.getValueType()) {
                case ARRAY:
                    String tab = (String) feuille.getValue();
                    tableau.ReadArray(tab);
                    root = root + tab;

                case DICT:
                    String object = (String) feuille.getValue();

                default:

            }

        }
    }
}
