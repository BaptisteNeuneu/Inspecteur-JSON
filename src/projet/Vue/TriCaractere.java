import projet.api.Token;
import projet.json.JSONToken;
import projet.json.JSONTree;

public class TriCaractere {
    Texte text = new Texte();
    Nombre nombre = new Nombre();
    JSONArray tableau = new JSONArray();


    public void Pu(String a){

        
        JSONTree arbre = new JSONTree();
        JSONToken feuille = new JSONToken();
        for(feuille : arbre){
            switch(a.getValue()){
                case String :
                String texte = a.toString();
                text.ReadString(texte);
                case Number :
                String num = a.toString();


                case Boolean :
                case NULL :
                case ARRAY :
                String tab = a.toString();
                tableau.ReadArray(tab);
                case DICT :
            }

        }

    }
}
