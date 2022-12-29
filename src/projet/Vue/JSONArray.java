public class JSONArray {
    String newtableau;

    public String ReadArray(String tableau){
        newtableau = "[";
        for(char charactere : tableau.toCharArray()){
            if(charactere != ']'){
                if(charactere == ' '){
                    newtableau = newtableau+' ';
                }
                else{
                    newtableau = newtableau+charactere+',';
                }
            }    
            else{
                newtableau = newtableau+']';
                return newtableau;
            }
        }         
    }
}
