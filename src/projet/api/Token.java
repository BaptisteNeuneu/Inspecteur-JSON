package projet.api;

import java.util.List;
import java.util.Map;

/***
 * La classe <code>Token</code> représente un token dans une arborescence de données d'un Tree.
 * Un token peut être un dictionnaire, un tableau ou une valeur au choix de ValueType
 */
public interface Token {
    /***
     * Retourne la valeur stockée ssi le token n'est ni un tableau ni un objet.
     * @throws IllegalStateException si le token est un tableau ou un objet
     * @return la valeur stockée
     */
    public Object getValue() throws IllegalStateException;

    /***
     * Retourne le type de valeur stockée ou null si le token est un tableau ou un dictionnaire.
     * @return le type de valeur stockée
     */
    public ValueType getValueType();

    /***
     * Stocke la valeur ssi le token n'est ni un tableau ni un objet.
     * Retourne la valeur précédemment stockée ou null
     * @param value la valeur à stocker
     * @return la valeur précédemment stockée
     * @throws NullPointerException si value est null
     * @throws IllegalStateException si le token est un tableau ou un objet
     */
    public Object setValue(Object value) throws IllegalStateException;

    /***
     * Retourne les éléments du token ssi le token est un tableau.
     * @return les éléments du token
     * @throws IllegalStateException si le token n'est pas un tablau
     */
    public List<Token> getValues() throws IllegalStateException;

    /***
     * Retourne les membres du token ssi le token est un objet. 
     * @return les membres du token
     * @throws IllegalStateException si le token n'est pas un objet
     */
    public Map<String, Token> getMembers() throws IllegalStateException;
}
