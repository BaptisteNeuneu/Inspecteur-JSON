package projet.api;

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
