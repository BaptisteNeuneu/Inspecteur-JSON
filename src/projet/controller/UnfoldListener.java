package projet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import projet.api.ColoredNode;
import projet.view.AffichageJSON;

final class UnfoldListener implements ActionListener {
    private final AffichageJSON affichage;

    UnfoldListener(AffichageJSON affichage) {
        this.affichage = affichage;
    }

    private void deployNode(ColoredNode node) {
        node.setFolded(false);
        for (ColoredNode fils : node.getFils()) {
            deployNode(fils);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deployNode(affichage.getHighlightTreeRoot());
        affichage.prettyColoredPrint();
    }
}