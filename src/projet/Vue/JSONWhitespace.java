package projet.Vue;

public class  JSONWhitespace{

    String newespace;

    public void ReadWhiteSpace(String espace){
        for(char character : espace.toCharArray()){
            newespace = newespace+character;
        }
        System.out.print(newespace);
    }
}