import projet.api.Token;
import projet.json.JSONToken;
import projet.json.JSONTree;

public class TriCaractere {
    Texte text = new Texte();
    Nombre nombre = new Nombre();
    JSONArray tableau = new JSONArray();
    JSONBoolean bool = new JSONBoolean();
    JSONWhitespace escape = new JSONWhitespace();

    public void Pu(String a){

        
        JSONTree arbre = new JSONTree();
        JSONToken feuille = new JSONToken();
        for(feuille : arbre){
            a = feuille.getValueType();
            switch(a){
                case String :
                String texte = feuille.getValue();
                text.ReadString(texte);
                case Number :
                String num = feuille.getValue();
                nombre.ReadNombre(num);

                case Boolean :
                String vraifaux = feuille.getValue();
                bool.Readboolean(vraifaux);

                case NULL :
                String espace = feuille.getValue();
                escape.ReadWhiteSpace(espace);

                case ARRAY :
                String tab = feuille.getValue();
                tableau.ReadArray(tab);
                case DICT :
            }

        }

    }
}
