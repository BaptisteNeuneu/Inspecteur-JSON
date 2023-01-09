package projet.Vue;

import java.util.Map;
import projet.api.Token;

public class JSONObject {

    String path;
    JSONBoolean bool = new JSONBoolean();
    Texte text = new Texte();
    Nombre nombre = new Nombre();
    JSONWhitespace escape= new JSONWhitespace();  
    String newlist;
    public void ReadObjectConsole(Map<String, Token> dict){
        for(Token token : dict.values()){
            switch(token.getValueType()){
                case DICT:
                

                case ARRAY:


                case STRING :
                String texte = (String)token.getValue();
                text.ReadString(texte);
                case NUMBER :
                String num = (String)token.getValue();
                nombre.ReadNombre(num);

                case BOOLEAN :
                String vraifaux = (String)token.getValue();
                bool.Readboolean(vraifaux);

                case NULL :
                String espace = (String)token.getValue();
                escape.ReadWhiteSpace(espace);


            } 
        }
    }
}
