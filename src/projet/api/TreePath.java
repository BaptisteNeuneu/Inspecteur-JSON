package projet.api;

/***
 * Représente un chemin d'accès pour un arbre
 */
public interface TreePath {
    /***
     * Retourne true ssi le chemin est valide.
     * @return true ssi le chemin est valide
     */
    public boolean isValid();

    /***
     * Retourne le chemin d'accès sous forme de String.
     * @return le chemin d'accès sous forme de String
     */
    public String toString();
}
