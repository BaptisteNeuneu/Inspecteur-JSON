package projet.json;
import java.util.Map.Entry;
import java.util.List;
import java.util.Map;
import java.util.Set;

import projet.api.Token;
import projet.api.ValueType;

public class JSONToken implements Token {

    private Object value;
    private ValueType valueType;

    private Map<String, Token> dict;
    private List<Token> list;

    @Override
    public Object getValue() throws IllegalStateException {
        if (valueType == ValueType.DICT) throw new IllegalStateException("Impossible d'accéder à la valeur d'un token dict.");
        else if (valueType == ValueType.ARRAY) throw new IllegalStateException("Impossible d'accéder à la valeur d'un token array.");
        return value;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public Object setValue(Object value) throws IllegalStateException {
        if (valueType == ValueType.DICT) throw new IllegalStateException("Impossible d'accéder à la valeur d'un token dict.");
        else if (valueType == ValueType.ARRAY) throw new IllegalStateException("Impossible d'accéder à la valeur d'un token array.");

        Object oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public List<Token> getValues() throws IllegalStateException {
        if (valueType != ValueType.ARRAY) throw new IllegalStateException("Impossible d'accéder à la valeur d'un token non array.");
        return list;
    }

    @Override
    public Set<Entry<String, Token>> getMembers() throws IllegalStateException {
        if (valueType != ValueType.DICT) throw new IllegalStateException("Impossible d'accéder à la valeur d'un token non dict.");
        return dict.entrySet();
    }

    @Override
    public void setKey(String key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
