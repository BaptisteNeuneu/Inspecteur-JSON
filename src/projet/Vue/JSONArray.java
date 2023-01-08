package  projet.Vue;

public class JSONArray {
    String newtableau;

    public void ReadArray(String tableau){
        for(char charactere : tableau.toCharArray()){
            switch(charactere){
                case '[':
                System.out.println(charactere);

                case ',':
                newtableau = newtableau + charactere;
                System.out.println(newtableau);
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

