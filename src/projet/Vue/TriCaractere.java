import projet.api.Token;
import projet.json.JSONToken;
import projet.json.JSONTree;

public class TriCaractere {
    Texte text = new Texte();
    public void Pu(String a){

        
        JSONTree arbre = new JSONTree();
        JSONToken feuille = new JSONToken();
        for(feuille : arbre){
            switch(a.getValue()){
                case String :
                String j = a.toString();
                text.ReadString(j);
                case Number :
                case Boolean :
                case NULL :
                case ARRAY :
                case DICT :
            }

        }

    }
}
