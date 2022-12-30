public class JSONBoolean {

    String newBoolean;

    public String Readboolean(String booleen){
        for(char character : booleen.toCharArray()){
            newBoolean= newBoolean+character;
        }
        return newBoolean;
    }
}
