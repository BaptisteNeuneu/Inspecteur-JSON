package projet.api;

import java.awt.Color;

public class ColoredString {
    private String string;
    private Color color;

    public ColoredString(String string, Color color) {
        this.string = string;
        this.color = color;
    }

    public String getString() {
        return string;
    }
    
    public Color getColor() {
        return color;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
