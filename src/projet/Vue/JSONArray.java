package  projet.Vue;

import java.util.List;

import javax.swing.*;

import projet.api.Token;
 

public class JSONArray {
    String newtableau;
    Texte text = new Texte();
    Nombre nombre = new Nombre();
    JSONBoolean bool = new JSONBoolean();
    JSONWhitespace escape = new JSONWhitespace();

    public void ReadArrayConsole(List<Token> list){
        for(Token token : list){
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

    public void ReadArrayFrame(String tableau,JFrame window){
        for(char charactere : tableau.toCharArray()){
            switch(charactere){
                case '[':
                System.out.println(charactere);

                case ',':
                newtableau = newtableau + charactere;
                JLabel value = new JLabel();
                window.add(value);
                newtableau = "";

                case ']':
                System.out.println(newtableau);
                System.out.println(charactere);

                default:
                newtableau = newtableau+charactere;
            }
            } 

    }
     
    }

