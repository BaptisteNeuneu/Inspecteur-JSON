package projet.Vue;

import javax.sql.rowset.CachedRowSet;

public class Texte {

    String newText;

    public String ReadString(String a){
        for(char character : a.toCharArray()){
            switch (character){
                    case '\"':
                    break;

                    case '\\':

                    case '\b':

                    case '\f':

                    case '\n':

                    case '\r':

                    case '\t':

                    default :
                    newText= newText + character;
                }
                 
            }
            return newText;
        }
    }
