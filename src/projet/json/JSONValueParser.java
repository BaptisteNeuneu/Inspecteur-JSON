package projet.json;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import projet.api.Token;

/***
 * Classe séparée traitant les valeurs JSON.
 */
public class JSONValueParser {

    private static int getFirstValueLength(String json) {

        int count = 1;
        int length = 1;
        boolean escaped = false;
        boolean inString = false;
        boolean justEscaped = false;

        switch (json.charAt(0)) {
            case '{': {
                while (count > 0) {
                    justEscaped = false;
                    switch (json.charAt(length)) {
                        case '{':
                            if (escaped || inString) break;
                            count++;
                            break;

                        case '}':
                            if (escaped || inString) break;
                            count--;
                            break;

                        case '"':
                            inString = !inString;
                            break;

                        case '\\':
                            justEscaped = true;
                            escaped = true;
                            break;
                    }

                    length++;
                    if (escaped && !justEscaped) escaped = false;
                }

                return length;
            }
            
            case '[': {
                while (count > 0) {
                    justEscaped = false;
                    switch (json.charAt(length)) {
                        case '[':
                            if (escaped || inString) break;
                            count++;
                            break;

                        case ']':
                            if (escaped || inString) break;
                            count--;
                            break;

                        case '"':
                            inString = !inString;
                            break;

                        case '\\':
                            justEscaped = true;
                            escaped = true;
                            break;
                    }

                    length++;
                    if (escaped && !justEscaped) escaped = false;
                }

                return length;
            }

            case '"': {
                while (true) {
                    justEscaped = false;
                    switch (json.charAt(length)) {
                        case '"':
                            if (escaped) break;
                            length++;
                            return length;

                        case '\\':
                            escaped = true;
                            justEscaped = true;
                            break;
                    }

                    if (escaped && !justEscaped) escaped = false;
                    length++;
                }
            }

            default:
                return json.split("[\\s,\\}\\]]")[0].length();
        }
    }

    public static JSONToken treatObject(String object) throws JSONFormatException {
        object = object.substring(1, object.length() - 1).trim();

        Map<String, Token> map = new HashMap<String, Token>();

        while (object.length() > 0) {
            String[] keyValue = object.substring(1).split("\"\\s*:\\s*", 2);
            String key = keyValue[0];
            String value = keyValue[1].substring(0, getFirstValueLength(keyValue[1]));

            JSONToken token;

            switch (value.charAt(0)) {
                case '{':
                    token = treatObject(value);
                    break;

                case '[':
                    token = treatTable(value);
                    break;

                case 't':
                    token = treatBoolean(value);
                    break;

                case 'f':
                    token = treatBoolean(value);
                    break;

                case 'n':
                    token = treatNull(value);
                    break;

                case '"':
                    token = treatString(value);
                    break;

                default:
                    token = treatNumber(value);
                    break;
            }

            map.put(key, token);
            if (keyValue[1].length() > value.length()) {
                object = keyValue[1].substring(value.length() + 1);
            } else {
                object = "";
            }
        }

        return new JSONToken(map);
    }

    public static JSONToken treatTable(String table) throws JSONFormatException {
        table = table.substring(1, table.length() - 1).trim();
        List<Token> list = new LinkedList<Token>();
        
        while (table.length() > 0) {
            table = table.trim();
            int valueLength = getFirstValueLength(table);
            String value = table.substring(0, valueLength);
            
            JSONToken token;
            switch (value.charAt(0)) {
                case '{':
                    token = treatObject(value);
                    break;

                case '[':
                    token = treatTable(value);
                    break;

                case 't':
                    token = treatBoolean(value);
                    break;

                case 'f':
                    token = treatBoolean(value);
                    break;

                case 'n':
                    token = treatNull(value);
                    break;

                case '"':
                    token = treatString(value);
                    break;

                default:
                    token = treatNumber(value);
                    break;
            }

            list.add(token);

            if (table.length() > value.length()) {
                table = table.substring(valueLength + 1);
            } else {
                table = "";
            }
        }

        return new JSONToken(list);
    }

    public static JSONToken treatString(String string) {
        return new JSONToken(string);
    }

    public static JSONToken treatNull(String nullString) {
        return new JSONToken();
    }

    public static JSONToken treatBoolean(String bool) throws JSONFormatException {
        switch (bool) {
            case "true":
                return new JSONToken(true);

            case "false":
                return new JSONToken(false);

            default:
                throw new JSONFormatException("Invalid boolean string : " + bool);
        }
    }

    public static JSONToken treatNumber(String number) throws JSONFormatException {
        String newNombre= "";
        boolean exponentielle = false;
        boolean nombre = false;
        boolean presenceoperateur = false;
        boolean fraction = false;

        for(char character : number.toCharArray()){
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
        return new JSONToken(0);
    }
}
