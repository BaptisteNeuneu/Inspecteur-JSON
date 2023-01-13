package projet.controller;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class ColoredNode extends ColoredString{
    private ColoredNode pere;
    private List<ColoredNode> fils = new LinkedList<ColoredNode>();

    public ColoredNode(String string, Color color, ColoredNode pere) {
        super(string, color);
        this.pere = pere;
    }

    public ColoredNode getPere(){
        return this.pere;
    }

    public List<ColoredNode> getFils(){
        return this.fils;
    }

    public void setPere(ColoredNode pere){
        this.pere = pere;
    }

    public void addFils(ColoredNode newFils){
        this.fils.add(newFils);
    }

    public void removeFils(ColoredNode filsToRemove){
        this.fils.remove(filsToRemove);
    }
}
