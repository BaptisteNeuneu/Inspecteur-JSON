package projet.Vue;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window {
    JFrame window = new JFrame();
    JButton refresh = new JButton("rafraichir");
    JButton php = new JButton("affichage en php");
    FlowLayout gestionpage = new FlowLayout();

    public void createwindow(){
        window.setLayout(gestionpage);
        window.setBackground(Color.BLACK);
        window.setLocation(500,500);
        window.setSize(500,500);
        window.add(refresh);
        window.add(php);
        window.setVisible(true);
    }

    public JFrame getWindow(){
        return window;
    }

    public void setWindow(JFrame window){
        this.window = window;
    }
}
