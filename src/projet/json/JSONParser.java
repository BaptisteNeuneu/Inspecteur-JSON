package projet.json;

import java.util.ArrayDeque;
import java.util.Objects;

import projet.api.Token;
import projet.api.Tree;

/**
 * La classe <code>JSONParser</code> transforme un fichier json en arbre syntaxique
 * 
 * @version 1.1
 */
public class JSONParser {

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
    

    /***
     * Convertit un fichier JSON sous forme de texte en arborescence.
     * @param json le fichier JSON à convertir
     * @return un Tree contenant toute les données
     * @throws JSONFormatException le fichier JSON est malformé
     */
    public static Tree<Token> deserialize(String json) throws JSONFormatException {

        // Vérifications
        Objects.requireNonNull(json, "Cannot accept null JSON string");
        if (json == "") throw new JSONFormatException("Cannot accept empty JSON string");
        if (!checkBlocks(json)) throw new JSONFormatException("Invalid block formatting");

        // On crée l'arbre et on commence à traiter les données
        Tree<Token> tree = new JSONTree();
        
        JSONToken root;

        json = json.trim();

        switch (json.charAt(0)) {
            case '[':
                root = JSONValueParser.treatTable(json);
                break;

            case '{':
                root = JSONValueParser.treatObject(json);
                break;

            case 't':
                root = JSONValueParser.treatBoolean(json);
                break;

            case 'f':
                root = JSONValueParser.treatBoolean(json);
                break;

            case 'n':
                root = JSONValueParser.treatNull(json);
                break;

            case '"':
                root = JSONValueParser.treatString(json);
                break;

            default:
                root = JSONValueParser.treatNumber(json);
                break;
        }

        tree.add(root, "");

        return tree;
    }
}
