package projet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projet.api.ColoredNode;
import projet.view.AffichageJSON;

/**
 * La classe <code>UnfoldListener</code> déplie les tableaux et dictionnaires. Implémente ActionListener
 * 
 * @version 1.1
 */
final class UnfoldListener implements ActionListener {
    private final AffichageJSON affichage;

    /**
    * Constructeur qui indique sur quel affichage déplier les éléments
    * 
    * @param affichage l'objet qui gère l'affichage
    */
    UnfoldListener(AffichageJSON affichage) {
        this.affichage = affichage;
    }

     /**
    * méthode récursive qui déplie tous les noeuds
    * 
    * @param node à déplier
    */
    private void deployNode(ColoredNode node) {
        node.setFolded(false);
        for (ColoredNode fils : node.getFils()) {
            deployNode(fils);
        }
    }

    @Override
    /**
    * implémentation de ActionListener
    * 
    * @param e
    */
    public void actionPerformed(ActionEvent e) {
        deployNode(affichage.getHighlightTreeRoot());
        affichage.prettyColoredPrint();
        JButton unflodButton = (JButton)e.getSource();
        unflodButton.setEnabled(false);
    }
}