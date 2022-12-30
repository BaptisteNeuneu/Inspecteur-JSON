public class  JSONWhitespace{

    String newespace;

    public String ReadWhiteSpace(String espace){
        for(char character : espace.toCharArray()){
            newespace = newespace+character;
        }
        return newespace;
    }
}