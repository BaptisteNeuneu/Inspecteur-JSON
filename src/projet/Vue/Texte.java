package projet.Vue;

import javax.sql.rowset.CachedRowSet;

public class Texte {

    String newText;
    boolean backslach = false;

    public String ReadString(String a){
        for(char character : a.toCharArray()){
            if(backslach == true){
               switch(character){
                    case '\'':

                    case '"':

                    case '/':

                    case 'b':

                    case 'f':

                    case 'n':

                    case 'r':

                    case 't':

                    case 'u':
               } 
            }
        
            switch (character){
                    case '\'':
                    newText = newText+character;
                    backslach = true;

                    default :
                    newText= newText + character;

                }
                 
            }
            return newText;
        }
    }
