package projet.Vue;

public class JSONBoolean {

    String newBoolean;

    public void Readboolean(String booleen){
        for(char character : booleen.toCharArray()){
            newBoolean= newBoolean+character;
        }
        System.out.print(newBoolean);
    }
}
