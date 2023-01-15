package projet.api;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * La classe <code>ColoredNode</code> Hérite de la classe <code>ColoredString</code>.
 * Elle permet de gérer un arbre de coloredString, comme elle connait ses successeurs.
 *  
 * @version 1.1
 */
public class ColoredNode {
    
    private boolean folded = true;
    private ColoredString value;
    private List<ColoredNode> fils = new LinkedList<ColoredNode>();

    /**
   * Constructeur générant un coloredString représentant la valeur de l'objet créé
   *
   * @param string la chaine de caractères à mémoriser
   * @param color la couleur de cette chaine de caractères
   */
    public ColoredNode(String string, Color color) {
        this.value = new ColoredString(string, color);
    }
    /**
   * getter de la liste des fils
   *
   * @return la liste des fils du noeud
    */
    public List<ColoredNode> getFils(){
        return this.fils;
    }

    /**
   * Ajoute un fils
   *
   * @param newFils le nouveau fils à ajouter à la liste des successeurs
    */
    public void addFils(ColoredNode newFils){
        this.fils.add(newFils);
    }

    /**
   * Supprime un fils
   *
   * @param filsToRemove le fils à retirer de la liste des successeurs
    */
    public void removeFils(ColoredNode filsToRemove){
        this.fils.remove(filsToRemove);
    }

    /**
   * Getter du coloredString associé, donc du couple chaine de caractères / couleur
   *
   * @return Le coloredString associé
    */
    public ColoredString getValue() {
        return value;
    }

    /**
   * Setter du coloredString associé
   *
   * @param value le coloredString à représenter
    */
    public void setValue(ColoredString value) {
        this.value = value;
    }

    /**
   * Indique si le noeud affiche son contenu ou non
   *
   * @return si le noeud est déplié ou non 
    */
    public boolean isFolded() {
        return folded;
    }

    /**
   * Setter de l'attribut folded, à savoir si le noeud montre ses fils ou non
   * 
   * @param folded le bouleen qui indique si le noeud montrer ses fils ou non
    */
    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    /**
   * Recupère le coloredString en fonction de si le noeud montre ses fils ou non
   * 
   * @return le coloredString à représenter
    */
    public ColoredString toColoredString() {
        if (fils.size() > 0) {
            if (isFolded()) {
                return new ColoredString("[...] (" + fils.size() + " élément" + (fils.size() > 1 ? "s" : "") + ")", Color.WHITE);
            } else {
                return value;
            }
        } else {
            return value;
        }
    }
}
