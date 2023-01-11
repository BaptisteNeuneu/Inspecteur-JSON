package projet.view;

import java.awt.*;
import javax.swing.*;

public class Accueil extends JFrame{

    public Accueil(){
        /*
         * Parametres fenetre
         */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension (screenSize.width *2/3,screenSize.height * 6/7);
	    this.setSize(frameSize);
        this.setTitle("Interprétateur JSON");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        /*
         * Remplissage avec des elements fixes
         */
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel texteFichier = new JLabel("URL du fichier JSON :");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        this.add(texteFichier, constraints);

        JTextField zoneURL = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        this.add(zoneURL, constraints);

        JButton refresh = new JButton("Lancer");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        this.add(refresh, constraints);

        JTextArea result = new JTextArea();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 5.0f;
        constraints.weighty = 5.0f;
        this.add(result, constraints);

        JButton details = new JButton("Detailler");
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        this.add(details, constraints);




        setVisible(true);
    }
    
}
