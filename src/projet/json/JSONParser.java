package projet.json;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projet.api.Token;
import projet.api.Tree;

public class JSONParser {

    private enum BlockType {
        SQUARE,
        CURLY
    }


    /***
     * Vérifie si les blocs JSON sont correctement faits.
     * Retourne true ssi les blocs sont corrects.
     * @param json le fichier JSON à vérifier
     * @return true ssi les blocs sont corrects
     */
    private static boolean checkBlocks(String json) {
        ArrayDeque<BlockType> stack = new ArrayDeque<>();
        
        boolean inString = false;
        boolean escaping = false;
        for (char character : json.toCharArray()) {

            boolean justEscaped = false;
            switch (character) {
                case '{':
                    if (inString) break;
                    stack.addFirst(BlockType.CURLY);
                    break;

                case '}':
                    if (inString) break;
                    if (stack.size() == 0) return false;
                    BlockType c = stack.removeFirst();
                    if (c != BlockType.CURLY) return false;
                    break;

                case '[':
                    if (inString) break;
                    stack.addFirst(BlockType.SQUARE);
                    break;

                case ']':
                    if (inString) break;
                    if (stack.size() == 0) return false;
                    BlockType s = stack.removeFirst();
                    if (s != BlockType.SQUARE) return false;
                    break;

                case '"':
                    if (escaping) break;
                    inString = !inString;
                    break;

                case '\\':
                justEscaped = true;
                escaping = true;
                break;
            }

            if (escaping && !justEscaped) escaping = false;
        }

        if (stack.size() > 0 || inString) return false;

        return true;
    }

    private static void treatObject(Tree<Token> tree, String currentPath, String object) {
        
    }

    private static void treatTable(Tree<Token> tree, String currentPath, String table) {
        System.out.println("table");
    }

    private static void treatBoolean(Tree<Token> tree, String currentPath, String bool) {
        switch (bool) {
            case "true":
                tree.add(new JSONToken(true), currentPath);
                break;
            case "false":
                tree.add(new JSONToken(false), currentPath);
                break;
        }

        System.out.println("Boolean : " + bool);
    }

    private static void treatNull(Tree<Token> tree, String currentPath) {
        tree.add(new JSONToken(), currentPath);
        System.out.println("Null");
    }

    private static void treatString(Tree<Token> tree, String currentPath, String string) {
        tree.add(new JSONToken(string), currentPath);
        System.out.println("String : " + string);
    }

    private static void treatNumber(Tree<Token> tree, String currentPath, String number) {
        
        System.out.println("Number : " + number);
    }

    /**
     * Retourne la première valeur à traiter et son type dans un tableau
     * @param json le morceau de donnée JSON à traiter
     * @return un tableau de String contenant le type et la première valeur
     * @throws JSONFormatException le morceau de donnée JSON est invalide
     */
    private static String[] getFirstValueAndType(String json) throws JSONFormatException {
        
        // 10/10 expression régulière
        Pattern p = Pattern.compile("^\\{(.*)\\}|^\\[(.*)\\]|^(true|false)|^(null)|^\\\"(.*)\\\"|^([0-9\\.\\-\\+eE]*)");
        Matcher m = p.matcher(json);

        //Cherche la première valeur JSON à traiter, en détermine le type et renvoie l'ensemble.
        if (m.find()) {
            /* 1: Objet
               2: Tableau
               3: Booléen
               4: Null
               5: String
               6: Nombre
            */

            int found = 0;
            for (int i = 1; i < 7; i++) {
                if (m.group(i) != null) {
                    found = i;
                    break;
                }
            }

            switch (found) {
                case 1:
                    return new String[] {"OBJECT", m.group(found)};
                case 2:
                    return new String[] {"TABLE", m.group(found)};
                case 3:
                    return new String[] {"BOOLEAN", m.group(found)};
                case 4:
                    return new String[] {"NULL"};
                case 5:
                    return new String[] {"STRING", m.group(found)};
                case 6:
                    return new String[] {"NUMBER", m.group(found)};
                default:
                    throw new JSONFormatException("Invalid value formatting");
            }
        } else {
            throw new JSONFormatException("Value type not recognised");
        }
    }

    /***
     * Convertit un fichier JSON sous forme de texte en arborescence.
     * @param json le fichier JSON à convertir
     * @return un Tree contenant toute les données
     * @throws JSONFormatException le fichier JSON est malformé
     */
    public static Tree<Token> deserialize(String json) throws JSONFormatException {
        Objects.requireNonNull(json, "Cannot accept null JSON string");
        if (json == "") throw new JSONFormatException("Cannot accept empty JSON string");
        if (!checkBlocks(json)) throw new JSONFormatException("Invalid block formatting");

        Tree<Token> tree = new JSONTree();

        String[] valueAndType = getFirstValueAndType(json);

        switch (valueAndType[0]) {
            case "OBJECT":
                treatObject(tree, ".", valueAndType[1]);
                break;

            case "TABLE":
                treatTable(tree, ".", valueAndType[1]);
                break;

            case "BOOLEAN":
                treatBoolean(tree, ".", valueAndType[1]);
                break;

            case "NULL":
                treatNull(tree, ".");
                break;

            case "String":
                treatString(tree, ".", valueAndType[1]);
                break;

            case "NUMBER":
                treatNumber(tree, ".", valueAndType[1]);
                break;
        }
        
        return tree;
    }
}
