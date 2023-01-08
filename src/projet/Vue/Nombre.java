public class Nombre {

    String newNombre;
    boolean exponentielle = false;
    boolean nombre = false;
    boolean presenceoperateur = false;
    boolean fraction = false;
    public String ReadNombre(String num){
        for(char character : num.toCharArray()){
            if(fraction == true){
                switch(character){
                    case '0':
                        newNombre = newNombre+character;
                    
                    case '1':
                        newNombre = newNombre + character;
                    case '2':
                        newNombre = newNombre + character;
                    case '3':
                        newNombre = newNombre + character;
                    case '4':
                        newNombre = newNombre + character;
                    case '5':
                        newNombre = newNombre + character;
                    case '6':
                        newNombre = newNombre + character;
                    case '7':
                        newNombre = newNombre + character;
                    case '8':
                        newNombre = newNombre + character;
                    case '9':
                        newNombre = newNombre + character;

                    default :
                        newNombre = newNombre+character;                    
                        fraction = false;

            }
        }

            if (exponentielle == true) {
                switch(character){

                    case '-':
                    if(presenceoperateur == false){
                        newNombre = newNombre+character;
                        presenceoperateur = true;
                    }

                    case '+':
                        if (presenceoperateur == false) {
                            newNombre = newNombre + character;
                            presenceoperateur = true;
                        }

                    case '0':
                    newNombre = newNombre+character;
                    

                    case '1':
                        newNombre = newNombre + character;
                    case '2':
                        newNombre = newNombre + character;
                    case '3':
                        newNombre = newNombre + character;
                    case '4':
                        newNombre = newNombre + character;
                    case '5':
                        newNombre = newNombre + character;
                    case '6':
                        newNombre = newNombre + character;
                    case '7':
                        newNombre = newNombre + character;
                    case '8':
                        newNombre = newNombre + character;
                    case '9':
                        newNombre = newNombre + character;
                    default:
                    newNombre = newNombre + character;
                    exponentielle = false;
                    presenceoperateur = false;
                }
            }

            if (nombre == true) {
            switch(character){
                case '0':
                    newNombre = newNombre + character;
                case '1':
                    newNombre = newNombre + character;
                case '2':
                    newNombre = newNombre + character;
                case '3':
                    newNombre = newNombre + character;
                case '4':
                    newNombre = newNombre + character;
                case '5':
                    newNombre = newNombre + character;
                case '6':
                    newNombre = newNombre + character;
                case '7':
                    newNombre = newNombre + character;
                case '8':
                    newNombre = newNombre + character;
                case '9':
                    newNombre = newNombre + character;
                default :
                newNombre = newNombre+character;
                nombre = false;
            }
            }
            switch(character){



                case '-':
                newNombre = newNombre+character;

                case '0':

                case '1':
                    newNombre = newNombre + character;
                    nombre = true;
                case '2':
                    newNombre = newNombre + character;
                    nombre = true;
                case '3':
                    newNombre = newNombre + character;
                    nombre = true;
                case '4':
                    newNombre = newNombre + character;
                    nombre = true;
                case '5':
                    newNombre = newNombre + character;
                    nombre = true;
                case '6':
                    newNombre = newNombre + character;
                    nombre = true;
                case '7':
                    newNombre = newNombre + character;
                    nombre = true;
                case '8':
                    newNombre = newNombre + character;
                    nombre = true;
                case '9':
                    newNombre = newNombre + character;
                    nombre = true;
                case '.':
                    newNombre = newNombre + character;
                    fraction = true;
                case 'E':
                  exponentielle = true;
                  newNombre = newNombre+character;                    

                case 'e':
                    exponentielle = true;
                    newNombre = newNombre + character;

            }
        }


        System.out.print(newNombre);
    }
}
