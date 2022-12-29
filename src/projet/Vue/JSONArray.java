package  projet.Vue;

public class JSONArray {
    String newtableau;

    public String ReadArray(String tableau){
        for(char charactere : tableau.toCharArray()){
            if(charactere != ']'){
                if(charactere == ' '){
                    newtableau = newtableau+charactere;
                }
                else if(charactere == '['){
                    newtableau = newtableau+charactere;
                }
                else{
                    newtableau = newtableau+charactere+',';
                }
            }    
            else{
                newtableau = newtableau+charactere;
                return newtableau;
            }
        }
        return newtableau;         
    }
}
