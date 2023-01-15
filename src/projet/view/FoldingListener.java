package projet.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import projet.api.ColoredNode;

public class FoldingListener implements MouseListener {
    private final AffichageJSON affichageJSON;
    private final ColoredNode node;

    FoldingListener(AffichageJSON affichageJSON, ColoredNode node) {
        this.affichageJSON = affichageJSON;
        this.node = node;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            node.setFolded(!node.isFolded());
            this.affichageJSON.prettyColoredPrint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}