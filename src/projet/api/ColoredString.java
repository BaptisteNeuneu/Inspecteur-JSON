package projet.api;

import java.awt.Color;

/**
 * La classe <code>ColoredString</code> représente un couple String / Color pour l'affichage Syntaxique
 *  
 * @version 1.1
 */
public class ColoredString {
    private String string;
    private Color color;

    /**
   * Constructeur initialisant les attibuts string et color
   *
   * @param string la chaine de caractères à mémoriser
   * @param color la couleur de cette chaine de caractères
   */
    public ColoredString(String string, Color color) {
        this.string = string;
        this.color = color;
    }

    /**
   * Getter du string
   *
   * @return le string correspondant
   */
    public String getString() {
        return string;
    }

    /**
   * Getter de la couleur
   *
   * @return la couleur du string
   */
    public Color getColor() {
        return color;
    }

    /**
   * Setter du string
   *
   * @param string le string à mémoriser
   */
    public void setString(String string) {
        this.string = string;
    }

    /**
   * Setter da la couleur
   *
   * @param color la couleur du string
   */
    public void setColor(Color color) {
        this.color = color;
    }
}
