package projet.Vue;

import java.util.List;
import java.util.Map;

import projet.api.Token;
import projet.json.JSONToken;
import projet.json.JSONTree;
import projet.api.Tree;
import projet.api.ValueType;


public class TriCaractere {
    
    String avancer;
    private Map<String, Token> dict;
    List<Token> list;
    JSONArray tableau = new JSONArray();
    JSONObject object = new JSONObject();
    Token feuille = new JSONToken();
    String root = "";
    JSONToken jetontableau = new JSONToken();
    JSONToken jetondictionnaire = new JSONToken();


    public void PrintConsole(Tree<Token> arbre){

        

        while(arbre.pathExists(root) == true){
            feuille = arbre.get(root);
            System.out.println("le path est :" + root);
            System.out.println("le type de valeur est :" + feuille.getValueType());
            switch(feuille.getValueType()){
                case ARRAY :
                list =jetontableau.getValues();
                tableau.ReadArrayConsole(list);
                root = root + list;
                
                case DICT :
                dict = jetondictionnaire.getMembers();
                object.ReadObjectConsole(dict);
                root = root + object;
                
                default :

            }            
        }
    }

    public void PrintWindow(Tree<Token> arbre){


        while(arbre.pathExists(root) == true){
            feuille = arbre.get(root);
            System.out.println("le path est :" + root);
            System.out.println("le type de valeur est :" + feuille.getValueType());
            switch (feuille.getValueType()) {
                case ARRAY:
                    String tab = (String) feuille.getValue();
                    tableau.ReadArrayConsole(list);
                    root = root + tab;

                case DICT:
                dict = jetondictionnaire.getMembers();
                object.ReadObjectConsole(dict);
                root = root + object;

                default:

            }

        }
    }
}
