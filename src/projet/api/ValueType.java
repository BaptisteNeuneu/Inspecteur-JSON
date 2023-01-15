package projet.api;

/**
 * La classe énumérative <code>ValueType</code> représente les différents types que peuvent 
 * prendre les valeurs.
 *  
 * @version 1.1
 */
public enum ValueType {
    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean"),
    NULL("null"),
    ARRAY("array"),
    DICT("dict");

    private String name;

    
    private ValueType(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}
