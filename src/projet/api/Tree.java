package projet.api;

/***
 * La classe <code>Tree\<T\></code> représente un arbre stockant des données simples.
 * Les données sont stockées sous un chemin d'accès unique.
 */
public interface Tree<T> {

    /***
     * Ajoute un objet dans l'arbre au chemin d'accès donné.
     * Retourne l'objet précédemment stocké à cet endroit ou null.
     * @param object l'objet à stocker
     * @param path le chemin d'accès à l'objet
     * @return l'objet précédemment stocké à cet endroit ou null
     * @throws NullPointerException si object ou path est null
     * @throws IllegalStateException si le parent de l'objet au chemin donné est du mauvais type ou inexistant
     * @throws IllegalArgumentException si le chemin donné passe par des token non array / dict
    */  
    public T add(T object, String path);

    /***
     * Supprime l'objet de l'arbre au chemin d'accès donné et le retourne.
     * @param path le chemin d'accès à l'objet
     * @return l'object précédemment stocké à cet endroit ou null
     */
    public T remove(String path);

    /***
     * Retourne l'objet contenu au chemin d'accès donné ou null.
     * @param path le chemin d'accès à l'objet
     * @return l'objet contenu au chemin d'accès donné ou null
     */
    public T get(String path);


    /***
     * Retourne true ssi un objet existe au chemin d'accès donné.
     * @param path le chemin d'accès à vérifier
     * @return true ssi un objet existe au chemin d'accès donné
     */
    public boolean pathExists(String path);
    

    /***
     * Retourne true ssi l'arbre contient l'objet demandé.
     * @param value l'objet à vérifier
     * @return true ssi l'arbre contient l'objet demandé
     */
    public boolean contains(T value);

    /***
     * Retourne la racine de l'arbre
     * @return la racine de l'arbre
     */
    public T getRoot();
    
    /***
     * Définit la racine de l'arbre.
     * @param root la nouvelle racine
     */
    public void setRoot(T root);
}