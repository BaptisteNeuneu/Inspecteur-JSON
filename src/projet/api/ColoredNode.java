package projet.api;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class ColoredNode {
    
    private boolean folded = true;
    private ColoredString value;
    private List<ColoredNode> fils = new LinkedList<ColoredNode>();

    public ColoredNode(String string, Color color) {
        this.value = new ColoredString(string, color);
    }

    public List<ColoredNode> getFils(){
        return this.fils;
    }

    public void addFils(ColoredNode newFils){
        this.fils.add(newFils);
    }

    public void removeFils(ColoredNode filsToRemove){
        this.fils.remove(filsToRemove);
    }

    public ColoredString getValue() {
        return value;
    }

    public void setValue(ColoredString value) {
        this.value = value;
    }

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    public ColoredString toColoredString() {
        if (fils.size() > 0) {
            if (isFolded()) {
                return new ColoredString("[...] (" + fils.size() + " élément" + (fils.size() > 1 ? "s" : "") + ")", Color.BLACK);
            } else {
                return value;
            }
        } else {
            return value;
        }
    }
}
